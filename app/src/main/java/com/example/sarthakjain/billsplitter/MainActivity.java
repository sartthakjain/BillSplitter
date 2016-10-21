package com.example.sarthakjain.billsplitter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

NumberPicker np;

int x;


    public void sendMessage(View v)
    {
        x=np.getValue();

        Toast.makeText(this,
                "SETTING UP THINGS FOR YOU :)",
                Toast.LENGTH_LONG).show();
        Intent ourintent=new Intent("android.intent.action.NAMES");
        ourintent.putExtra("friends",x);

        startActivity(ourintent);


    }

    public void sendMessage2(View v)
    {


        Intent ourintent=new Intent("android.intent.action.SAVEDDATA");
        ourintent.putExtra("friends",x);

        startActivity(ourintent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    np=(NumberPicker)findViewById(R.id.np);

        np.setMinValue(2);
        np.setMaxValue(9);




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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
        System.exit(0);
    }

    public  void sendMessageback(View v)
    {

        this.finish();
        System.exit(0);
    }
}
