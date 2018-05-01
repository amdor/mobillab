package herokuapp.autocomparator.zsolt.skyscraper.ui.carlist;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import herokuapp.autocomparator.zsolt.skyscraper.R;
import herokuapp.autocomparator.zsolt.skyscraper.data.AppDatabase;
import herokuapp.autocomparator.zsolt.skyscraper.interactor.CarListInteractor;
import herokuapp.autocomparator.zsolt.skyscraper.model.CarDetail;
import herokuapp.autocomparator.zsolt.skyscraper.model.CarDetails;
import herokuapp.autocomparator.zsolt.skyscraper.model.CarDetailsEntity;
import herokuapp.autocomparator.zsolt.skyscraper.model.CarQueryObject;
import herokuapp.autocomparator.zsolt.skyscraper.ui.carlist.dummy.DummyContent;

/**
 * An activity representing a list of Cars. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link CarDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class CarListActivity extends AppCompatActivity {

    CarListInteractor carListInteractor;

    // TODO: connect with UI, replace dummy
    private static AppDatabase db;

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    private EditText newCarUrl;
    private Button addUrl;
    private View recyclerView;

    private final Gson gson = new Gson();
    public static String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-car-data").allowMainThreadQueries().build();
        this.carListInteractor = new CarListInteractor();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        if (findViewById(R.id.car_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        recyclerView = findViewById(R.id.car_list);
        assert recyclerView != null;

        newCarUrl = (EditText) findViewById(R.id.carUrl);
        //TODO: remove
        newCarUrl.setText("https://www.hasznaltauto.hu/szemelyauto/audi/a7/audi_a7_3_0_v6_tdi_dpf_quattro_tiptronic_ic_full-12835346");
        addUrl = (Button) findViewById(R.id.addButton);
        addUrl.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        CarQueryObject queryObject = new CarQueryObject();
                        List<String> urls = new ArrayList<String>();
                        urls.add(newCarUrl.getText().toString());
                        queryObject.setCarUrls(urls);
                        CarDetails carDetails = carListInteractor.postCars(queryObject);

                        //the must be only one since we sent one link
                        DummyContent.addItem(carDetails.get(0));

                        Gson gson = new Gson();
                        String carDetailsJson = gson.toJson(DummyContent.ITEMS, CarDetails.class);
                        CarDetailsEntity entity = new CarDetailsEntity();
                        entity.uname = userName;
                        entity.carDetails = carDetailsJson;
                        db.carDataDao().insertAll(entity);

                        View recyclerView = findViewById(R.id.car_list);
                        assert recyclerView != null;
                        setupRecyclerView((RecyclerView) recyclerView);
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            userName = bundle.getString("userName");
        }

        CarDetailsEntity carDetailsEntity = db.carDataDao().getCarDataByUser(userName);
        if (carDetailsEntity != null) {
            Log.i("CarListActivity", "Car details is not null");
            DummyContent.ITEMS = gson.fromJson(carDetailsEntity.carDetails, CarDetails.class);
        } else {
            DummyContent.ITEMS = new CarDetails();
        }
        setupRecyclerView((RecyclerView) recyclerView);
    }

    private void setupRecyclerView(@NonNull final RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, DummyContent.ITEMS, mTwoPane));
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                SimpleItemRecyclerViewAdapter adapter = (SimpleItemRecyclerViewAdapter) recyclerView.getAdapter();
                adapter.removeAt(viewHolder.getAdapterPosition());
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView,
                                    RecyclerView.ViewHolder viewHolder, float dX, float dY,
                                    int actionState, boolean isCurrentlyActive) {
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    // Get RecyclerView item from the ViewHolder
                    View itemView = viewHolder.itemView;

                    Paint p = new Paint();
                    if (dX > 0) {
                        /* Set your color for positive displacement */

                        // Draw Rect with varying right side, equal to displacement dX
                        c.drawRect((float) itemView.getLeft(), (float) itemView.getTop(), dX,
                                (float) itemView.getBottom(), p);
                    } else {
                        /* Set your color for negative displacement */

                        // Draw Rect with varying left side, equal to the item's right side plus negative displacement dX
                        c.drawRect((float) itemView.getRight() + dX, (float) itemView.getTop(),
                                (float) itemView.getRight(), (float) itemView.getBottom(), p);
                    }

                    super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                }
            }
        };

// attaching the touch helper to recycler view
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
    }

    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final CarListActivity mParentActivity;
        private final CarDetails mValues;
        private final boolean mTwoPane;

        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer itemIndex = (Integer) view.getTag();
                CarDetail item = DummyContent.ITEMS.get(itemIndex);
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putInt(CarDetailFragment.ARG_ITEM_INDEX, itemIndex);
                    CarDetailFragment fragment = new CarDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.car_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, CarDetailActivity.class);
                    intent.putExtra(CarDetailFragment.ARG_ITEM_INDEX, itemIndex);

                    context.startActivity(intent);
                }
            }
        };

        SimpleItemRecyclerViewAdapter(CarListActivity parent,
                                      CarDetails items,
                                      boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.car_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            String[] carName = mValues.get(position).getCarUri().split("/");
            holder.wothField.setText("Rating:" + mValues.get(position).getWorth().toString());
            holder.carName.setText(carName[4] + " " + carName[5]);
            holder.dateAdded.setText(mValues.get(position).getProdDate());

            holder.itemView.setTag(position);
            holder.itemView.setOnClickListener(mOnClickListener);
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }


        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView wothField;
            final TextView dateAdded;
            final TextView carName;

            ViewHolder(View view) {
                super(view);
                wothField = (TextView) view.findViewById(R.id.wothField);
                dateAdded = (TextView) view.findViewById(R.id.dateAdded);
                carName = (TextView) view.findViewById(R.id.carName);
            }
        }

        public void removeAt(int position) {
            DummyContent.ITEMS.remove(position);
            Gson gson = new Gson();
            String carDetailsJson = gson.toJson(DummyContent.ITEMS, CarDetails.class);
            CarDetailsEntity entity = new CarDetailsEntity();
            entity.uname = userName;
            entity.carDetails = carDetailsJson;
            db.carDataDao().insertAll(entity);
            notifyItemRemoved(position);
        }
    }

    // TODO: use on UI
    public CarDetails queryCarData(String url) {
        CarQueryObject carQueryObject = new CarQueryObject();
        carQueryObject.setCarUrls(Arrays.asList(url));
        return carListInteractor.postCars(carQueryObject);
    }
}
