package com.tricloudcommunications.ce.databasedemo2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try{

            SQLiteDatabase eventsDB = this.openOrCreateDatabase("Events", MODE_PRIVATE, null);
            eventsDB.execSQL("CREATE TABLE IF NOT EXISTS event (eventName VARCHAR, eventYear INT(4))");
            eventsDB.execSQL("INSERT INTO event (eventName, eventYear) VALUES('Stock Market Crash', 1929)");
            eventsDB.execSQL("INSERT INTO event (eventName, eventYear) VALUES('My Birth', 1979)");

            Cursor c = eventsDB.rawQuery("SELECT * FROM event", null);

            int eventNameIndex = c.getColumnIndex("eventName");
            int eventYearIndex = c.getColumnIndex("eventYear");

            c.moveToFirst();

            while (c != null){

                Log.i("Database Result :", "Event Name= " + c.getString(eventNameIndex) + "- - -" + " Event Year= " + c.getString(eventYearIndex));

                c.moveToNext();
            }





        }catch (Exception e){

            e.printStackTrace();
        }


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
