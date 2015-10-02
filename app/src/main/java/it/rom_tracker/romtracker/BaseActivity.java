package it.rom_tracker.romtracker;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by Matteo on 01/10/2015.
 */
public class BaseActivity extends ActionBarActivity{

    public Toolbar toolbar;
    private DrawerLayout drawerLayout;

    protected void onCreateDrawer(){

        toolbar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.nav_drawer);
        drawerFragment.setUp((DrawerLayout)findViewById(R.id.drawer_layout), toolbar);

    }

    /**
     * Apre la MainActivity;
     * Ritarda di 250 millisecondi per sincornizzarsi col Navigation Drawer.
     */
    public void main(View v) {

        android.os.Handler mHandler = new android.os.Handler();

        mHandler.removeCallbacksAndMessages(null);

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(BaseActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        }, 250);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawers();

    }

    /**
     * Apre la SettingsActivity;
     * Ritarda di 250 millisecondi per sincornizzarsi col Navigation Drawer.
     */
    public void settings(View v) {

        android.os.Handler mHandler = new android.os.Handler();

        mHandler.removeCallbacksAndMessages(null);

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(BaseActivity.this, SettingsActivity.class);
                startActivity(intent);
                overridePendingTransition(0,0);
                finish();
            }
        }, 250);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawers();

    }

}