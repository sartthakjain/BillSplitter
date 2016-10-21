package com.example.sarthakjain.billsplitter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import java.sql.SQLException;


public class SaveData extends Activity
{
    String data[]=new String[7];
    RadioButton tv[]=new RadioButton[7];
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saveddata);
        initialization();

for(int i=1;i<=6;i++)
        tv[i].setVisibility(View.INVISIBLE);
        try {
            for(int i=1;i<=6;i++) {
          //int  i=1;
            Database info=new Database(this);
                info.writeOnIt();
               data[i] = info.getData(i);
                info.close();
                if(!data[i].equals("no data")) {
                 tv[i].setVisibility(View.VISIBLE);
                    tv[i].setText(data[i]);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }




    }

    public void initialization()
    {

        tv[1]=(RadioButton)findViewById(R.id.tv1);
        tv[2]=(RadioButton)findViewById(R.id.tv2);
        tv[3]=(RadioButton)findViewById(R.id.tv3);
        tv[4]=(RadioButton)findViewById(R.id.tv4);
        tv[5]=(RadioButton)findViewById(R.id.tv5);
        tv[6]=(RadioButton)findViewById(R.id.tv6);
    }
public void sendMessageone(View v)
{   int i=0;
    switch (v.getId())
    {
        case R.id.tv1 :   i=1;
            break;

        case R.id.tv2 :   i=2;
            break;

        case R.id.tv3 :   i=3;
            break;

        case R.id.tv4 :   i=4;
            break;

        case R.id.tv5 :   i=5;
            break;

        case R.id.tv6 :   i=6;
            break;

    }

    Intent ourintent=new Intent("android.intent.action.DISPLAYTAB");
    Bundle b=new Bundle();
    b.putInt("i",i);
    b.putStringArray("data", data);
    ourintent.putExtras(b);
    startActivity(ourintent);
}

public void sendMessageTwo(View v)
{


    Intent intent = new Intent(this, MainActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    startActivity(intent);
}

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    public void onBackPressed2(View v)
    {
        this.finish();
        System.exit(0);
    }
}
