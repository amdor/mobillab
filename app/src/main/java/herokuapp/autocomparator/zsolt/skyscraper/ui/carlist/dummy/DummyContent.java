package herokuapp.autocomparator.zsolt.skyscraper.ui.carlist.dummy;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import herokuapp.autocomparator.zsolt.skyscraper.model.CarDetail;
import herokuapp.autocomparator.zsolt.skyscraper.model.CarDetails;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final CarDetails ITEMS = new CarDetails();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, CarDetail> ITEM_MAP = new HashMap<String, CarDetail>();

    private static final int COUNT = 5;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    public static void addItem(CarDetail item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getCarUri(), item);
    }

    private static CarDetail createDummyItem(int position) {
        String positionString = String.valueOf(position);
        CarDetail carDetail = new CarDetail();
        carDetail.setCarUri("https://www.hasznaltauto.hu/szemelyauto/bmw/x6/bmw_x6_3_0_d_automata_5_szemelyes_m-sport_edition_pdc_hifi-12780966");
        carDetail.setPower(positionString + "kw");
        carDetail.setPrice(positionString + "000Ft");
        carDetail.setProdDate(new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Calendar.getInstance().getTime()));
        carDetail.setSpeedometer(positionString + "000km");
        carDetail.setWorth(position);
        return carDetail;
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }
}
