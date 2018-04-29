package herokuapp.autocomparator.zsolt.skyscraper;

import android.app.Application;
import android.os.StrictMode;

import herokuapp.autocomparator.zsolt.skyscraper.ui.UIModule;

public class SkyscraperApplication extends Application {

    public static SkyscraperApplicationComponent injector;

    @Override
    public void onCreate() {
        super.onCreate();

        injector =
                DaggerSkyscraperApplicationComponent.builder().
                        uIModule(
                                new UIModule(this)
                        ).build();
    }
}
