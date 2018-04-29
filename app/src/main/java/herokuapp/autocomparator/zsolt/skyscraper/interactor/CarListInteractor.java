package herokuapp.autocomparator.zsolt.skyscraper.interactor;

import android.os.StrictMode;

import com.google.gson.Gson;

import java.io.IOException;

import herokuapp.autocomparator.zsolt.skyscraper.model.CarDetails;
import herokuapp.autocomparator.zsolt.skyscraper.model.CarQueryObject;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class CarListInteractor {
    private static final String URL = "https://skyscraper-bes.herokuapp.com";
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

    public CarListInteractor() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public CarDetails postCars(CarQueryObject queryObject){
        try {
            return post(queryObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private CarDetails post(CarQueryObject queryObject) throws IOException {
        Gson gson = new Gson();
        RequestBody body = RequestBody.create(JSON, gson.toJson(queryObject, CarQueryObject.class));
        Request request = new Request.Builder()
                .url(URL)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return gson.fromJson(response.body().string(), CarDetails.class);
    }
}
