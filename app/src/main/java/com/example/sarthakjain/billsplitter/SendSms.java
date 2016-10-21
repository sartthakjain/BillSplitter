package com.example.sarthakjain.billsplitter;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.telephony.SmsManager;
import android.widget.Toast;

import java.sql.SQLException;

/**
 * Created by sarthak jain on 04-08-2015.
 */
public class SendSms extends Activity {

    double payer[]=new double[11],sum,pp[],balance[],expenditure[]={0,0,0,0,0,0,0,0,0,0,0},amt[][]={{0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0}};
    String x[]=new String[11];
    Button infbt[]=new Button[11];
    String dbDetails="";
    String decide,purpose,sol[][]={{" "," "," "," "," "," "," "," "," "," "," ",},{" "," "," "," "," "," "," "," "," "," "," ",},{" "," "," "," "," "," "," "," "," "," "," ",},{" "," "," "," "," "," "," "," "," "," "," ",},{" "," "," "," "," "," "," "," "," "," "," ",},{" "," "," "," "," "," "," "," "," "," "," ",},{" "," "," "," "," "," "," "," "," "," "," ",},{" "," "," "," "," "," "," "," "," "," "," ",},{" "," "," "," "," "," "," "," "," "," "," ",},{" "," "," "," "," "," "," "," "," "," "," ",},{" "," "," "," "," "," "," "," "," "," "," ",},{" "," "," "," "," "," "," "," "," "," "," ",},{" "," "," "," "," "," "," "," "," "," "," ",}};
    String paid,splitselect;
EditText txtphone,txtMessage,txtname;
    String text,name,number="";
    int no,i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sendsms);
        txtphone=(EditText)findViewById(R.id.etno);
        txtMessage=(EditText)findViewById(R.id.etmsg);
        txtname=(EditText)findViewById(R.id.etname);

        Intent ourintent=getIntent();
        receiveintent(ourintent);


        txtMessage.setText(text);
        txtname.setText(name);


        Database info=new Database(this);
        try {
            info.writeOnIt();
           number= info.getnumber(name);
            info.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(number.equals(""))
            Toast.makeText(this, "ENTER NO. MANUALLY", Toast.LENGTH_SHORT)
                    .show();
        txtphone.setText(number);
    }


    public void sendMessage(View v)
    {
        Log.i("Send SMS", "");
        String phoneNo = txtphone.getText().toString();
        String message = txtMessage.getText().toString();

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, message, null, null);
            Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();
        }

        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "SMS faild, please try again.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    public void sendMessageWhatsapp(View ve)
    {


        PackageManager pm=getPackageManager();
        try {

            text =  txtMessage.getText().toString();
            Intent waIntent = new Intent(Intent.ACTION_SEND);
            waIntent.setType("text/plain");


            PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);

            waIntent.setPackage("com.whatsapp");


            waIntent.putExtra(Intent.EXTRA_TEXT, text);
            startActivity(Intent.createChooser(waIntent, "Share with"));

        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(this, "WhatsApp not Installed", Toast.LENGTH_SHORT)
                    .show();
        }


    }


    public void onBackPressed() {
        super.onBackPressed();
    /*    Intent ourintent=new Intent("android.intent.action.OUTPUT");
       sendintent(ourintent);
*/
    }

    public void sendMessageBack(View v) {
        onBackPressed();

    }


    public void receiveintent(Intent intent)
    {

        x=intent.getStringArrayExtra("names");
        purpose=intent.getStringExtra("purpose");
        no=intent.getIntExtra("friends",2);
        text=intent.getStringExtra("msg");
        name=intent.getStringExtra("name");

        payer=intent.getDoubleArrayExtra("payers");
        pp=intent.getDoubleArrayExtra("perpersons");
        paid=intent.getStringExtra("whopaid");
        splitselect=intent.getStringExtra("splitselect");
        sum=intent.getDoubleExtra("sum",0);
        balance=intent.getDoubleArrayExtra("balance");
    }


    public void  sendintent(Intent intentmethod)
    {

        Bundle bucket=new Bundle();
        bucket.putStringArray("names",x);
        bucket.putString("whopaid",paid);
        bucket.putInt("friends",no);
        bucket.putString("splitselect",splitselect);
        bucket.putDoubleArray("payers",payer);
        bucket.putDoubleArray("perpersons",pp);
        bucket.putDouble("sum",sum);
        bucket.putString("purpose",purpose);
        bucket.putString("msg",text);
        bucket.putString("name",name);
        bucket.putDoubleArray("balance",balance);
        intentmethod.putExtras(bucket);
        startActivity(intentmethod);
    }

}
