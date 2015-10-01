package it.rom_tracker.romtracker;

import com.parse.Parse;
import com.parse.ParseUser;

/**
 * Created by GabrielePC on 01/10/2015.
 */
public class Application extends android.app.Application{

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "D0FEPeFteRyV27UVYJ8SiCxm2eZI511yyCHrlXaN", "kOPq0kALgNp6eDIr36zEg7lkMDGvGmugytGlCuT7");
        ParseUser.enableAutomaticUser();
    }
}
