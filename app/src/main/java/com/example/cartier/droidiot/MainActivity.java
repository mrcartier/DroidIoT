package com.example.cartier.droidiot;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextClock;

public class MainActivity extends AppCompatActivity {

    private TextClock tvClock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvClock = (TextClock)findViewById(R.id.clock);
        getTimeDate();
    }


    public void getTimeDate() {

        tvClock.setFormat12Hour(null);
        tvClock.setFormat24Hour("MM/dd/yyyy hh:mm:ss a");
        //tvClock.setFormat24Hour("hh:mm:ss a  EEE MMM d");
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
