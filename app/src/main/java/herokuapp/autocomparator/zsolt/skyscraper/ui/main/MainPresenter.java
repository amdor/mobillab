package herokuapp.autocomparator.zsolt.skyscraper.ui.main;

import herokuapp.autocomparator.zsolt.skyscraper.ui.Presenter;

public class MainPresenter extends Presenter<MainScreen> {

    @Override
    public void attachScreen(MainScreen screen) {
        super.attachScreen(screen);
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
    }

    public void loginWithUserName(String userName) {

        screen.login(userName);
    }
}
