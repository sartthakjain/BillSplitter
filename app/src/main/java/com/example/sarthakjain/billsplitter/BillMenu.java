package com.example.sarthakjain.billsplitter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class BillMenu extends Activity
{
    EditText etammount,etpurp;
    Button bt,btsplit;
    double pp[],sum=0;
    double payer[],balance[]={0,0,0,0,0,0,0,0,0,0,0};
    int no;
    double  ammount;
    String x[]=new String[11];
    String paid,splitselect,purpose;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.billmenu);
        btsplit=(Button)findViewById(R.id.btsplit);
        bt=(Button)findViewById(R.id.bt1);
        etammount=(EditText)findViewById(R.id.etammount);
        etpurp=(EditText)findViewById(R.id.etpurp);





        Intent newintent=getIntent();
        receiveintent(newintent);

        if(!purpose.equals("")) {
            etpurp.setText("" + purpose);

        }
  /*  if(!paid.equals(""))
        bt.setText(paid);
        if(!splitselect.equals(""))
            btsplit.setText(splitselect);*/
        bt.setText(paid);
        btsplit.setText(splitselect);

        etammount.setText("" + sum);

    }




    public void sendMessage(View v) {
        sum = Double.parseDouble(etammount.getText().toString());
        purpose = etpurp.getText().toString();

            Intent intent = new Intent("android.intent.action.CHOOSEWHOPAID");
            transferdata(intent);

    }

    public void sendMessage3(View v)
    {

       whatselected();
        purpose=etpurp.getText().toString();

        if(purpose.equals(""))
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setMessage("PLEASE ENTER PURPOSE");
            AlertDialog alert = builder.create();
            alert.show();

        }else
        if(btsplit.getText().toString().equals("HOW TO SPLIT"))
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setMessage("PLEASE SELECT HOW TO SPLIT");
            AlertDialog alert = builder.create();
            alert.show();
        }else
        if(bt.getText().toString().equals("WHO PAID"))
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setMessage("PLEASE SELECT WHO PAID");
            AlertDialog alert = builder.create();
            alert.show();
        }else {


           Intent output = new Intent("android.intent.action.OUTPUT");
           this.finish();
            transferdata(output);


        }

    }



    public void sendMessage2(View vie)
     {
         sum=Double.parseDouble(etammount.getText().toString());
         purpose=etpurp.getText().toString();
        Intent splitintent=new Intent("android.intent.action.SPLIT");

        transferdata(splitintent);
     }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent ourintent=new Intent("android.intent.action.NAMES");
        Bundle b=new Bundle();
        b.putInt("friends",no);
        ourintent.putExtras(b);
        startActivity(ourintent);
    }
    public void onBackPressed2(View v) {
        onBackPressed();

    }

    public void  transferdata(Intent intentmethod)
    {

        Bundle bucket=new Bundle();
        bucket.putStringArray("names",x);
        bucket.putString("whopaid", paid);
        bucket.putInt("friends", no);
        bucket.putString("splitselect", splitselect);
        bucket.putDoubleArray("payers", payer);
        bucket.putDoubleArray("perpersons", pp);
        bucket.putDouble("sum", sum);
        bucket.putString("purpose",purpose);
        bucket.putDoubleArray("balance",balance);
        intentmethod.putExtras(bucket);
        startActivity(intentmethod);
    }



    public void receiveintent(Intent intent)
    {

        x=intent.getStringArrayExtra("names");
        no=intent.getIntExtra("friends", 2);
        payer=intent.getDoubleArrayExtra("payers");
        pp=intent.getDoubleArrayExtra("perpersons");
        paid=intent.getStringExtra("whopaid");
        splitselect=intent.getStringExtra("splitselect");
        purpose=intent.getStringExtra("purpose");
        sum=intent.getDoubleExtra("sum",0);
    }


    public void whatselected()
    {

        switch (splitselect)
        {

            case ("SPLIT EQUALLY"):
                ammount = Double.parseDouble(etammount.getText().toString());
                for(int i=1;i<=no;i++)
                {
                    pp[i]=ammount/no;

                }
                splitequallycalculation();
                break;

            case("SPLIT BY AMOUNT"):

            splitequallycalculation();

                break;


            case("ITEMIZED BILL"):
            splitequallycalculation();
    break;


        }

    }




    public void splitequallycalculation()
    {

        ammount = Double.parseDouble(etammount.getText().toString());

        for(int i=1;i<=no;i++)
        {
            if (x[i].equals(paid))
            {
                payer[i] = ammount;
            }
        }

        for(int i=1;i<=no;i++)
        {   balance[i]=payer[i];
            payer[i]=payer[i]-pp[i];
        }

    }



}


