package herokuapp.autocomparator.zsolt.skyscraper.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import javax.inject.Inject;

import herokuapp.autocomparator.zsolt.skyscraper.R;
import herokuapp.autocomparator.zsolt.skyscraper.SkyscraperApplication;
import herokuapp.autocomparator.zsolt.skyscraper.ui.carlist.CarListActivity;

public class MainActivity extends AppCompatActivity implements MainScreen {

    @Inject
    MainPresenter mainPresenter;

    private EditText userName;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SkyscraperApplication.injector.inject(this);

        userName = (EditText)findViewById(R.id.userNameField);
        login = (Button) findViewById(R.id.loginButton);
        login.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        mainPresenter.loginWithUserName(userName.getText().toString());
                    }
                });
    }


    @Override
    protected void onStart() {
        super.onStart();
        mainPresenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainPresenter.detachScreen();
    }

    @Override
    public void login(String userName) {
        Intent intent = new Intent(this, CarListActivity.class);
        intent.putExtra("userName", userName);
        startActivity(intent);
    }
}
