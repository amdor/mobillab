package herokuapp.autocomparator.zsolt.skyscraper.ui.carlist.dummy;

import herokuapp.autocomparator.zsolt.skyscraper.model.CarDetail;
import herokuapp.autocomparator.zsolt.skyscraper.model.CarDetails;


public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static CarDetails ITEMS = new CarDetails();

    public static void addItem(CarDetail item) {
        ITEMS.add(item);
    }

//    private static CarDetail createDummyItem(int position) {
//        String positionString = String.valueOf(position);
//        CarDetail carDetail = new CarDetail();
//        carDetail.setCarUri("https://www.hasznaltauto.hu/szemelyauto/bmw/x6/bmw_x6_3_0_d_automata_5_szemelyes_m-sport_edition_pdc_hifi-12780966");
//        carDetail.setPower(positionString + "kw");
//        carDetail.setPrice(positionString + "000Ft");
//        carDetail.setProdDate(new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Calendar.getInstance().getTime()));
//        carDetail.setSpeedometer(positionString + "000km");
//        carDetail.setWorth(position);
//        return carDetail;
//    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }
}
