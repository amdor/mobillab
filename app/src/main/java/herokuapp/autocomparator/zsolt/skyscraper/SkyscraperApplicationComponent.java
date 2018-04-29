package herokuapp.autocomparator.zsolt.skyscraper;

import javax.inject.Singleton;

import dagger.Component;
import herokuapp.autocomparator.zsolt.skyscraper.ui.UIModule;
import herokuapp.autocomparator.zsolt.skyscraper.ui.carlist.CarDetailFragment;
import herokuapp.autocomparator.zsolt.skyscraper.ui.main.MainActivity;

@Singleton
@Component(modules = {UIModule.class})
public interface SkyscraperApplicationComponent {
    void inject(MainActivity mainActivity);
    void inject(CarDetailFragment carDetailFragment);
}
