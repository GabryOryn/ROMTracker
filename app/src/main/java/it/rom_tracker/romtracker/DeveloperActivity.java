package it.rom_tracker.romtracker;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class DeveloperActivity extends Activity {

    private Button addRom;
    private TextView txtLinkRom;
    private TextView txtNameRom;
    private TextView txtChangelogRom;
    private TextView txtVersionRom;

    private String nameRom;
    private String linkRom;
    private String versionRom;
    private String changelogRom;

    private AlertDialog addRomDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);

        //////////////////////////////////////////////////////

        //View dialog = getLayoutInflater().inflate(R.layout.dialog_add_rom, null);
        addRom = (Button) findViewById(R.id.btnAddRom);

    }

    @Override
    protected void onStart() {
        super.onStart();

        // add rom button cliecked
        addRom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final LayoutInflater layoutInflater = getLayoutInflater();
                View dialog = layoutInflater.inflate(R.layout.dialog_add_rom, null);
                ////////////////////////////////////////////////////////////////
                txtLinkRom = (TextView) dialog.findViewById(R.id.txtLinkRom);
                txtNameRom = (TextView) dialog.findViewById(R.id.txtNameRom);
                txtChangelogRom = (TextView) dialog.findViewById(R.id.txtChangelogRom);
                txtVersionRom = (TextView) dialog.findViewById(R.id.txtVersionRom);
                ////////////////////////////////////////////////////////////////

                // dialog for add new rom
                addRomDialog = new AlertDialog.Builder(DeveloperActivity.this)
                        .setView(dialog)
                        .setPositiveButton(getString(R.string.add_rom), null)
                        .create();
                addRomDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {
                        Button addRom = addRomDialog.getButton(DialogInterface.BUTTON_POSITIVE);
                        addRom.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // checks for errors
                                String error = checkErrorDialog();
                                // no error on dialog
                                if(!error.equals("")){
                                    Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
                                }else{
                                    // sending the data to parse
                                    ParseObject newRom = new ParseObject("ROM");
                                    newRom.put("name", nameRom);
                                    newRom.put("version", versionRom);
                                    newRom.put("link", linkRom);
                                    if(changelogRom != null) {
                                        newRom.put("changelog", changelogRom);
                                    }
                                    newRom.put("developer", ParseUser.getCurrentUser().getUsername());
                                    newRom.saveInBackground(new SaveCallback() {
                                        @Override
                                        public void done(ParseException e) {
                                            if(e == null){
                                                // sent correctly
                                                Toast.makeText(getApplicationContext(), getString(R.string.submitted_successfully), Toast.LENGTH_LONG).show();
                                            }else{
                                                // sending error
                                                Toast.makeText(getApplicationContext(), getString(R.string.submitted_successfully), Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                                    //
                                    addRomDialog.dismiss();
                                }
                            }
                        });
                    }
                });
                addRomDialog.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_developer, menu);
        return true;
    }

    private String checkErrorDialog(){
        StringBuilder error = new StringBuilder();

        String test = txtNameRom.getText().toString();
        if(txtNameRom.getText().toString().equals("")){
            error.append(getResources().getString(R.string.error_no_name_rom) + "\n");
        }else{
            nameRom = txtNameRom.getText().toString();
        }

        if(txtLinkRom.getText().toString().equals("")){
            error.append(getResources().getString(R.string.error_no_link_rom) + "\n");
        }else{
            linkRom = txtLinkRom.getText().toString();
        }

        if(txtVersionRom.getText().toString().equals("")){
            error.append(getResources().getString(R.string.error_no_version_rom));
        }else{
            versionRom = txtVersionRom.getText().toString();
        }

        // no mandatory
        if(txtChangelogRom.getText().toString().equals("")){
            changelogRom = txtChangelogRom.getText().toString();
        }

        return error.toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
