package herokuapp.autocomparator.zsolt.skyscraper;

import android.app.Application;
import android.os.StrictMode;

import herokuapp.autocomparator.zsolt.skyscraper.ui.UIModule;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

public class SkyscraperApplication extends Application {

    public static SkyscraperApplicationComponent injector;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());

        injector =
                DaggerSkyscraperApplicationComponent.builder().
                        uIModule(
                                new UIModule(this)
                        ).build();
    }
}
