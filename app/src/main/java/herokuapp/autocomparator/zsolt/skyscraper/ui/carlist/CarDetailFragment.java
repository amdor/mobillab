package herokuapp.autocomparator.zsolt.skyscraper.ui.carlist;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import javax.inject.Inject;

import herokuapp.autocomparator.zsolt.skyscraper.R;
import herokuapp.autocomparator.zsolt.skyscraper.SkyscraperApplication;
import herokuapp.autocomparator.zsolt.skyscraper.model.CarDetail;
import herokuapp.autocomparator.zsolt.skyscraper.ui.carlist.dummy.DummyContent;

/**
 * A fragment representing a single Car detail screen.
 * This fragment is either contained in a {@link CarListActivity}
 * in two-pane mode (on tablets) or a {@link CarDetailActivity}
 * on handsets.
 */
public class CarDetailFragment extends Fragment implements CarListDetailsScreen {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_INDEX = "item_index";

    /**
     * The dummy content this fragment is presenting.
     */
    private CarDetail mItem;

    @Inject
    CarListDetailPresenter carListDetailPresenter;

    public CarDetailFragment() {
        SkyscraperApplication.injector.inject(this);
    }

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);
        carListDetailPresenter.attachScreen(this);
    }

    @Override
    public void onDetach() {
        carListDetailPresenter.detachScreen();
        super.onDetach();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_INDEX)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = DummyContent.ITEMS.get(getArguments().getInt(ARG_ITEM_INDEX));

//            Activity activity = this.getActivity();
//            Toolbar toolbar = (Toolbar) activity.findViewById(R.id.detail_toolbar);
//            if (toolbar != null) {
//                String[] carName = mItem.getCarUri().split("/");
//                toolbar.setTitle(carName[4] + " " + carName[5]);
//            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.car_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.price)).setText(mItem.getPrice());
            ((TextView) rootView.findViewById(R.id.power)).setText(mItem.getPower());
            ((TextView) rootView.findViewById(R.id.mileage)).setText(mItem.getSpeedometer());
            ((TextView) rootView.findViewById(R.id.prodDate)).setText(mItem.getProdDate());
            ((TextView) rootView.findViewById(R.id.worth)).setText(mItem.getWorth().toString());
        }

        return rootView;
    }
}
