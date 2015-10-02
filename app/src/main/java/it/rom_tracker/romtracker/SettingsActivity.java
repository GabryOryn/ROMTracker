package it.rom_tracker.romtracker;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SettingsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        super.onCreateDrawer();
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setStatusBarBackground(R.color.primaryColorDark);
        selectRow();
    }

    /**
     * Scegli i telefoni preferiti.
     */
    public void selectPrefPhones(View v) {



    }

    /**
     * Imposta la riga da colorare nel Nav Drawer.
     */
    public void selectRow() {

        RelativeLayout item_main = (RelativeLayout) findViewById(R.id.nav_drawer_main);
        RelativeLayout item_settings = (RelativeLayout) findViewById(R.id.nav_drawer_settings);
        item_main.setBackground(getResources().getDrawable(R.drawable.touch_bg));
        item_settings.setBackground(getResources().getDrawable(R.drawable.selected_row));

        TextView text_main = (TextView) findViewById(R.id.nav_drawer_main_text);
        TextView text_settings = (TextView) findViewById(R.id.nav_drawer_settings_text);
        text_main.setTextColor(getResources().getColor(R.color.black));
        text_settings.setTextColor(getResources().getColor(R.color.primaryColor));

        ImageView img_main = (ImageView) findViewById(R.id.nav_drawer_main_img);
        ImageView img_settings = (ImageView) findViewById(R.id.nav_drawer_settings_img);
        img_main.setColorFilter(getResources().getColor(R.color.grey_img));
        img_settings.setColorFilter(getResources().getColor(R.color.primaryColor));

    }

}
