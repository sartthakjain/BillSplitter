package com.example.sarthakjain.billsplitter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.sql.SQLException;

/**
 * Created by sarthak jain on 30-07-2015.
 */
public class Display extends Activity
{
    TextView et11,et1;
    int i=0;
    String data[]=new String[6];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.displaytab);

        et1=(TextView)findViewById(R.id.et1);
        et11=(TextView)findViewById(R.id.et11);

        Database info=new Database(this);
        try {
            Intent ourintent=getIntent();
             i=ourintent.getIntExtra("i",0);
            data=ourintent.getStringArrayExtra("data");

            info.writeOnIt();
            String detaildata= info.getDetailData(i);
            info.close();
            et1.setText(detaildata);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(View v)
    {
        Database infoo=new Database(this);
        try {
            infoo.writeOnIt();
            infoo.deleteEntry(data[i]);
            infoo.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Intent ourintent=new Intent("android.intent.action.SAVEDDATA");
        startActivity(ourintent);
    }



}
