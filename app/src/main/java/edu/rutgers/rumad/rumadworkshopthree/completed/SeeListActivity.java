package edu.rutgers.rumad.rumadworkshopthree.completed;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;

import edu.rutgers.rumad.rumadworkshopthree.R;

public class SeeListActivity extends ActionBarActivity {

    ListView todoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_list);

        //this adapter queries all Todo items
        ParseQueryAdapter<ParseObject> adapter = new ParseQueryAdapter<ParseObject>(this,"Todo");
        //we want the text of the object so this is what it displays
        adapter.setTextKey("text");

        //load objects
        adapter.loadObjects();

        //instantiate the listview
        todoList = (ListView)findViewById(R.id.todolist);

        //set the adapter to load listview
        todoList.setAdapter(adapter);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_see_list, menu);
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
