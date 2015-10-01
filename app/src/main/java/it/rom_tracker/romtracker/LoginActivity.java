package it.rom_tracker.romtracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends Activity {
    private Button btnLogin;
    private TextView txtUsername;
    private TextView txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ///////////////////////////////////////////////////////
        btnLogin = (Button) findViewById(R.id.btnLogin);
        txtPassword = (TextView) findViewById(R.id.txtPassword);
        txtUsername = (TextView) findViewById(R.id.txtUsername);
    }

    @Override
    protected void onStart() {
        super.onStart();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // take username and password from textview
                final String username = txtUsername.getText().toString();
                final String password = txtPassword.getText().toString();
                // show error if the textview are blank
                if(username.equals("") || password.equals("")){
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.error_no_data_login), Toast.LENGTH_LONG).show();
                }else {
                    // try to login...
                    ParseUser.logInInBackground(username, password, new LogInCallback() {
                        @Override
                        public void done(ParseUser parseUser, ParseException e) {
                            // correct login
                            if(parseUser != null){
                                Toast.makeText(getApplicationContext(), getResources().getString(R.string.welcome_login) + "," + username + "!", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(), DeveloperActivity.class));
                            // incorrect login
                            }else{
                                Toast.makeText(getApplicationContext(), getResources().getString(R.string.error_wrong_data_login), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
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
