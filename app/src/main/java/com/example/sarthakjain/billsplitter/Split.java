package com.example.sarthakjain.billsplitter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.AbstractCollection;

/**
 * Created by sarthak jain on 16-07-2015.
 */
public class Split extends Activity implements TextWatcher {

    EditText etammount;
    TextView tvshow;
    String iname[]={"","","","","","",""};
    Button btdone,btitemized;
    RadioButton cb[]=new RadioButton[11];
    double pp[]={0,0,0,0,0,0,0,0,0,0,0},sum=0,amount[]={0,0,0,0,0,0,0};
    double payer[]={0,0,0,0,0,0,0,0,0,0,0};
   double ppitem[][]={{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0}};

    int no;
    String splitselect,purpose;
    CheckBox mu[]=new CheckBox[11];
    EditText et[]=new EditText[11];
    double  ammount;
    TextView oo,ooo;
    String x[]=new String[11];
    String paid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.split);

        Intent intents=getIntent();
        receiveintent(intents);
        initialization();
        oo.setVisibility(View.INVISIBLE);
        ooo.setVisibility(View.INVISIBLE);
        ammount=sum;
        tvshow.setText(""+ammount);
        for(int i=1;i<=10;i++)
        {
            mu[i].setVisibility(View.INVISIBLE);
            et[i].setVisibility(View.INVISIBLE);
            btdone.setVisibility(View.INVISIBLE);
         //   btitemized.setVisibility(View.INVISIBLE);
        }

        for(int i=1;i<=no;i++)
        et[i].addTextChangedListener(this);

    }




public void sendMessage(View v)
{
    for(int i=1;i<=3;i++)
    {
        if(cb[i].isChecked())
        {
            if(cb[i].getText().toString().equals("SPLIT EQUALLY"))
            {

                splitselect = cb[i].getText().toString();
                Intent ourintent=new Intent("android.intent.action.BILLMENU");
                sendintent(ourintent);
            }else
            if(cb[i].getText().toString().equals("SPLIT BY AMOUNT"))
            {
                oo.setVisibility(View.VISIBLE);
                ooo.setVisibility(View.VISIBLE);
                for(int j=1;j<=no;j++)
                {
                    mu[j].setVisibility(View.VISIBLE);
                    et[j].setVisibility(View.VISIBLE);
                    btdone.setVisibility(View.VISIBLE);
                    mu[j].setText(x[j]);
                }


            }
            else
                if(cb[i].getText().toString().equals("ITEMIZED BILL"))
                {
                   // btitemized.setVisibility(View.VISIBLE);
                    Intent ourintent=new Intent("android.intent.action.ITEMIZEDBILL");
                    sendintent(ourintent);

                }


        }
    }

}



    public void sendMessage2(View v)
    {int flag=0;

        for(int i=1;i<=no;i++) {
            if (mu[i].isChecked()) {
                if(et[i].getText().toString().equals(""))
                flag=1;
            }
        }
if(flag==1)
{
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setCancelable(true);
    builder.setMessage("EMPTY FIELDS");
    AlertDialog alert = builder.create();
    alert.show();
    flag=0;
}else {
    for (int i = 1; i <= no; i++) {
        if (mu[i].isChecked()) {
            pp[i] = Integer.parseInt(et[i].getText().toString());
            ammount = ammount - pp[i];
        }

    }
    splitselect = "SPLIT BY AMOUNT";

    if (tvshow.getText().toString().equals("0.0")) {
        Intent intent = new Intent("android.intent.action.BILLMENU");
        sendintent(intent);
    } else {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setMessage("YOUR EXPENSE SUM IS NOT EQUAL TO PAID AMMOUNT");
        AlertDialog alert = builder.create();
        alert.show();
        ammount = sum;
    }

}
    }


    public void sendintent(Intent intent)
{
    Bundle bucket=new Bundle();
    bucket.putString("splitselect",splitselect);
    bucket.putString("whopaid",paid);
    bucket.putString("purpose",purpose);
    bucket.putStringArray("names", x);
    bucket.putInt("friends", no);
    bucket.putDoubleArray("payers", payer);
    bucket.putDouble("sum", sum);
    bucket.putDoubleArray("amtitem",amount);
    bucket.putDoubleArray("ppitem1",ppitem[1]);
    bucket.putDoubleArray("ppitem2",ppitem[2]);
    bucket.putDoubleArray("ppitem3",ppitem[3]);
    bucket.putDoubleArray("ppitem4",ppitem[4]);
    bucket.putDoubleArray("ppitem5",ppitem[5]);
    bucket.putDoubleArray("ppitem6",ppitem[6]);
    bucket.putStringArray("iname",iname);
    bucket.putDoubleArray("perpersons",pp);
    intent.putExtras(bucket);
    startActivity(intent);
}

public void receiveintent(Intent splitintent)
{

    x=splitintent.getStringArrayExtra("names");
    no=splitintent.getIntExtra("friends",2);
    paid=splitintent.getStringExtra("whopaid");
    purpose=splitintent.getStringExtra("purpose");
    payer=splitintent.getDoubleArrayExtra("payers");
    sum=splitintent.getDoubleExtra("sum",0);
    pp=splitintent.getDoubleArrayExtra("perpersons");


}


public void initialization()
{
    tvshow=(TextView)findViewById(R.id.tvshow);
     btdone=(Button)findViewById(R.id.btdone);
    oo=(TextView)findViewById(R.id.oo);
    ooo=(TextView)findViewById(R.id.ooo);
    cb[1]=(RadioButton)findViewById(R.id.cb1);
    cb[2]=(RadioButton)findViewById(R.id.cb2);
    cb[3]=(RadioButton)findViewById(R.id.cb3);
    cb[4]=(RadioButton)findViewById(R.id.cb4);

    mu[1]=(CheckBox)findViewById(R.id.mu1);
    mu[2]=(CheckBox)findViewById(R.id.mu2);
    mu[3]=(CheckBox)findViewById(R.id.mu3);
    mu[4]=(CheckBox)findViewById(R.id.mu4);
    mu[5]=(CheckBox)findViewById(R.id.mu5);
    mu[6]=(CheckBox)findViewById(R.id.mu6);
    mu[7]=(CheckBox)findViewById(R.id.mu7);
    mu[8]=(CheckBox)findViewById(R.id.mu8);
    mu[9]=(CheckBox)findViewById(R.id.mu9);
    mu[10]=(CheckBox)findViewById(R.id.mu10);

    et[1]=(EditText)findViewById(R.id.et1);
    et[2]=(EditText)findViewById(R.id.et2);
    et[3]=(EditText)findViewById(R.id.et3);
    et[4]=(EditText)findViewById(R.id.et4);
    et[5]=(EditText)findViewById(R.id.et5);
    et[6]=(EditText)findViewById(R.id.et6);
    et[7]=(EditText)findViewById(R.id.et7);
    et[8]=(EditText)findViewById(R.id.et8);
    et[9]=(EditText)findViewById(R.id.et9);
    et[10]=(EditText)findViewById(R.id.et10);


}


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        ammount=sum;
        for(int i=1;i<=no;i++)
        {
            if(mu[i].isChecked())
            {
                if(!et[i].getText().toString().equals("")) {
                    pp[i] = Integer.parseInt(et[i].getText().toString());
                    ammount = ammount - pp[i];
                    tvshow.setText("" + ammount);
                }
                }
        }
        splitselect="SPLIT BY AMMOUNT";




    }

    @Override
    public void afterTextChanged(Editable s) {

    }



    public void onBackPressed() {
        super.onBackPressed();
        splitselect="SPLIT BY";
        this.finish();
        Intent ourintent=new Intent("android.intent.action.BILLMENU");
       sendintent(ourintent);
    }

    public void onBackPressed2(View v) {
        onBackPressed();

    }
}

