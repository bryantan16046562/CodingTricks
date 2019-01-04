package bryantan.codingtricks.web;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import bryantan.codingtricks.R;

public class WebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        WebFragment fragmentweb = new WebFragment();
        FragmentTransaction fragmentTransactionWeb = getSupportFragmentManager().beginTransaction();
        fragmentTransactionWeb.replace(R.id.webcontent, fragmentweb,"FragmentName");
        fragmentTransactionWeb.commit();
    }

}