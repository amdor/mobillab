package herokuapp.autocomparator.zsolt.skyscraper.interactor;

import dagger.Module;
import dagger.Provides;

@Module
public class InteractorModule {
    @Provides
    CarListInteractor provideCarListInteractor(){
        return new CarListInteractor();
    }

}
