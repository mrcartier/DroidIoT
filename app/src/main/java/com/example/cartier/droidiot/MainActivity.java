package com.example.cartier.droidiot;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.cartier.droidiot.databinding.ActivityMainBinding;
import com.google.android.things.pio.Gpio;
import com.google.android.things.pio.PeripheralManagerService;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;
    private Gpio gpio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        activityMainBinding.offbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gpioLow();
            }
        });

        activityMainBinding.onbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gpioHigh();
            }
        });

        activityMainBinding.shutdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shutDown();
            }
        });

        getTimeDate();

        PeripheralManagerService service = new PeripheralManagerService();

        try {
            gpio = service.openGpio("BCM21");
            gpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
            activityMainBinding.error.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "BCM21 OK", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            activityMainBinding.offbutton.setVisibility(View.GONE);
            activityMainBinding.onbutton.setVisibility(View.GONE);
            e.printStackTrace();
        }
    }

    protected void getTimeDate() {

        activityMainBinding.clock.setFormat12Hour(null);
        activityMainBinding.clock.setFormat24Hour("MM/dd/yyyy hh:mm:ss a");
        //tvClock.setFormat24Hour("hh:mm:ss a  EEE MMM d");
    }

    protected void gpioLow() {
        try {
            gpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void gpioHigh() {
        try {
            gpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_HIGH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void onStop() {
        super.onStop();
        try {
            gpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
            gpio.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void shutDown() {
        try {
            Runtime.getRuntime().exec("reboot -p");
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "UNABLE TO SHUTDOWN", Toast.LENGTH_LONG).show();
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
