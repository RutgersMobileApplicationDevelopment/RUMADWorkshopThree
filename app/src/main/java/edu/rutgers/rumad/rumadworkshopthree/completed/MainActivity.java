package edu.rutgers.rumad.rumadworkshopthree.completed;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import edu.rutgers.rumad.rumadworkshopthree.R;


public class MainActivity extends ActionBarActivity {

    Button login, signin, cancel;
    EditText usernameField, passwordField, nameField;
    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //store context in variable so we can access in inner methods
        ctx = this;

        //initialize buttons
        login = (Button)findViewById(R.id.loginBtn);
        signin = (Button)findViewById(R.id.signinBtn);
        cancel = (Button)findViewById(R.id.cancelBtn);


        //initialize text fields
        usernameField = (EditText)findViewById(R.id.username);
        passwordField = (EditText)findViewById(R.id.password);
        nameField = (EditText)findViewById(R.id.name);


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (signin.getText().toString().equalsIgnoreCase("Sign Up")) {

                    login.setVisibility(View.INVISIBLE);
                    cancel.setVisibility(View.VISIBLE);
                    signin.setText("Done");
                    nameField.setVisibility(View.VISIBLE);

                } else {



                    //initialize a new user
                    ParseUser user = new ParseUser();



                    String username = usernameField.getText().toString();
                    String password = passwordField.getText().toString();
                    String name = nameField.getText().toString();

                    //built in method that assigns username to user
                    user.setUsername(username);

                    //built in method that encrypts and assigns password to user
                    user.setPassword(password);

                    //or add in your own data like:
                    user.put("name", name);
                    //where the first parameter is the key and the second parameter is the value

                /*
                You can do other stuff too like:

                //another built in method
                user.setEmail(email);

                //whatever you want
                user.put("age", 18);

                for now we will just do username and password
                 */

                    //check if username or password is not there
                    if (username == null || password == null) {
                        Toast.makeText(ctx, "Oops, you must enter a username AND password to sign up", Toast.LENGTH_SHORT).show();
                    } else {
                        //parse automatically does it in the background thread so no need for AsyncTasks!!!
                        //it also checks automatically if the username was already used
                        user.signUpInBackground(new SignUpCallback() {
                            @Override
                            public void done(ParseException e) {

                                //check if there is no error
                                if (e == null) {
                                    //if there isn't we're successful! Go to the next screen and resume initial state of screen!
                                    signin.setText("Sign Up");
                                    cancel.setVisibility(View.INVISIBLE);
                                    nameField.setVisibility(View.INVISIBLE);
                                    login.setVisibility(View.INVISIBLE);


                                    Intent intent = new Intent(ctx, SecondActivity.class);

                                    startActivity(intent);
                                }
                                //otherwise, let's see what happened
                                else {
                                    //go to LogCat and look for error tag if what you expected didn't happen
                                    Log.e("error", "user sign up error: " + e.getMessage());
                                }
                            }
                        });

                    }
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    //get inputs
                    String username = usernameField.getText().toString();
                    String password = passwordField.getText().toString();

                    ParseUser.logInInBackground(username, password, new LogInCallback() {
                        @Override
                        public void done(ParseUser parseUser, ParseException e) {

                            //make sure there is no error
                            if(e == null){
                                //go to next activity
                                Intent intent = new Intent(ctx, SecondActivity.class);

                                startActivity(intent);
                            }
                            //error, what's wrong?
                            else{

                                Log.e("error logging in",e.getMessage());

                            }
                        }
                    });
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
