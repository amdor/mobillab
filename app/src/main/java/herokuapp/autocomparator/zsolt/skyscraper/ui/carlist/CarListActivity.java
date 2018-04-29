package herokuapp.autocomparator.zsolt.skyscraper.ui.carlist;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import herokuapp.autocomparator.zsolt.skyscraper.R;

import herokuapp.autocomparator.zsolt.skyscraper.data.AppDatabase;
import herokuapp.autocomparator.zsolt.skyscraper.interactor.CarListInteractor;
import herokuapp.autocomparator.zsolt.skyscraper.model.CarDetail;
import herokuapp.autocomparator.zsolt.skyscraper.model.CarDetails;
import herokuapp.autocomparator.zsolt.skyscraper.model.CarQueryObject;
import herokuapp.autocomparator.zsolt.skyscraper.ui.carlist.dummy.DummyContent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

/**
 * An activity representing a list of Cars. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link CarDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class CarListActivity extends AppCompatActivity {


    @Inject
    CarListInteractor carListInteractor;

    // TODO: connect with UI, replace dummy
    private AppDatabase db;

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    private EditText newCarUrl;
    private Button addUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list);

        this.db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-car-data").build();
        this.carListInteractor = new CarListInteractor();

        Bundle bundle = getIntent().getExtras();
        String userName = bundle.getString("userName");
        //TODO: load data from db

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

        View recyclerView = findViewById(R.id.car_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView, null);

        newCarUrl = (EditText) findViewById(R.id.carUrl);
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
                        View recyclerView = findViewById(R.id.car_list);
                        assert recyclerView != null;
                        setupRecyclerView((RecyclerView) recyclerView, carDetails);
                    }
                });
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView, CarDetails carDetails) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, DummyContent.ITEMS, mTwoPane));
    }

    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final CarListActivity mParentActivity;
        private final CarDetails mValues;
        private final boolean mTwoPane;

        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CarDetail item = (CarDetail) view.getTag();
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString(CarDetailFragment.ARG_ITEM_ID, item.getCarUri());
                    CarDetailFragment fragment = new CarDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.car_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, CarDetailActivity.class);
                    intent.putExtra(CarDetailFragment.ARG_ITEM_ID, item.getCarUri());

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
            holder.dateAdded.setText(new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Calendar.getInstance().getTime()));

            holder.itemView.setTag(mValues.get(position));
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
    }

    // TODO: use on UI
    public CarDetails queryCarData(String url) {
        CarQueryObject carQueryObject = new CarQueryObject();
        carQueryObject.setCarUrls(Arrays.asList(url));
        return carListInteractor.postCars(carQueryObject);
    }
}
