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

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import edu.rutgers.rumad.rumadworkshopthree.R;

public class SecondActivity extends ActionBarActivity {

    Button post, seeList;
    EditText todofield;

    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ctx = this;

        //initialize buttons
        post = (Button)findViewById(R.id.postBtn);
        seeList = (Button)findViewById(R.id.seeListBtn);

        //initialize editext
        todofield = (EditText)findViewById(R.id.todoText);

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //this is the object we are going to save
                ParseObject todo = new ParseObject("Todo");

                //get the text of the todo item from user
                String todotext = todofield.getText().toString();

                //check if todotext is null
                if(todotext == null){
                    Toast.makeText(ctx,"You must enter text for your todo",Toast.LENGTH_SHORT).show();
                }
                else {
                    //assign text to the todo object
                    todo.put("text", todotext);

                    //Parse automatically caches the current user when they are logged in so this is how you get an instance
                    //of the current user
                    ParseUser currentUser = ParseUser.getCurrentUser();

                    //a ParseUser is just a special subclass of ParseObject and each ParseObject has a unique objectId
                    //thus this acts as an identifier too
                    String userId = currentUser.getObjectId();

                    //link the object to a user
                    //we use objectId because a username might change in the future or a name might be repeated but
                    //objectId stays the same
                    todo.put("userId", userId);

                    //save the todo object!
                    //we can also use todo.saveEventually() which saves it whenever user has online access
                    //instead of right away so it doesn't fail if user is not connected to internet
                    todo.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            //check if error is null
                            if (e == null) {
                                //success! notify user their item was saved
                                Toast.makeText(ctx, "Your todo was saved!", Toast.LENGTH_SHORT).show();
                            }
                            //oops there was an error
                            else {
                                Log.e("error saving todo", e.getMessage());

                            }
                        }
                    });
                }
            }
        });

        seeList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //go to list activity
                Intent intent = new Intent(ctx,SeeListActivity.class);

                startActivity(intent);
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second, menu);
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
