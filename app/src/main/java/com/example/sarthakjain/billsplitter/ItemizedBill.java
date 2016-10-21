package com.example.sarthakjain.billsplitter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * Created by sarthak jain on 05-08-2015.
 */
public class ItemizedBill extends Activity implements TextWatcher {
    CheckBox mu[]=new CheckBox[11];
    TextView et[]=new TextView[11];
    EditText etamt,etiname;
    String iname[]=new String[7];
    double sum,amount[]={0,0,0,0,0,0,0};
    double peramt;
    int no,i,j=1;
    String splitselect,purpose;
    Bundle b = new Bundle();
    String x[]=new String[11],paid;
    double payer[],pp[],ppitem[][]={{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0}};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itemizedbill);
        initialize();
        Intent ourintent=getIntent();
        receiveintent(ourintent);

        for(i=1;i<=10;i++)
        {
            mu[i].setVisibility(View.INVISIBLE);
            et[i].setVisibility(View.INVISIBLE);
        }


        for(i=1;i<=no;i++)
        {
            mu[i].setVisibility(View.VISIBLE);
            et[i].setVisibility(View.VISIBLE);
            mu[i].setText(x[i]);

        }




        etamt.addTextChangedListener(this);

    }






    public  void sendMessageAdd(View v)
    {

        iname[j]=etiname.getText().toString();
        if(iname[j].equals("")|etamt.getText().toString().equals("")){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setMessage("FIELDS CAN NOT BE LEFT EMPTY");
            AlertDialog alert = builder.create();
            alert.show();
        }else {
            for (int i = 1; i <= no; i++) {
                if (mu[i].isChecked()) {
                    ppitem[j][i] = ppitem[j][i] + Double.parseDouble(et[i].getText().toString());
                } else
                    ppitem[j][i] = ppitem[j][i] + 0;
            }
            amount[j] = Double.parseDouble(etamt.getText().toString());
            Intent ourintent = new Intent("android.intent.action.ITEMADDED");
            transferdata(ourintent);
        }
    }


    public void initialize(){


        etamt=(EditText)findViewById(R.id.etamt);
        etiname=(EditText)findViewById(R.id.etiname);


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

        et[1]=(TextView)findViewById(R.id.et1);
        et[2]=(TextView)findViewById(R.id.et2);
        et[3]=(TextView)findViewById(R.id.et3);
        et[4]=(TextView)findViewById(R.id.et4);
        et[5]=(TextView)findViewById(R.id.et5);
        et[6]=(TextView)findViewById(R.id.et6);
        et[7]=(TextView)findViewById(R.id.et7);
        et[8]=(TextView)findViewById(R.id.et8);
        et[9]=(TextView)findViewById(R.id.et9);
        et[10]=(TextView)findViewById(R.id.et10);


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
        j=splitintent.getIntExtra("j",1);
        amount=splitintent.getDoubleArrayExtra("amtitem");
        ppitem[1]=splitintent.getDoubleArrayExtra("ppitem1");
        ppitem[2]=splitintent.getDoubleArrayExtra("ppitem2");
        ppitem[3]=splitintent.getDoubleArrayExtra("ppitem3");
        ppitem[4]=splitintent.getDoubleArrayExtra("ppitem4");
        ppitem[5]=splitintent.getDoubleArrayExtra("ppitem5");
        ppitem[6]=splitintent.getDoubleArrayExtra("ppitem6");
        iname=splitintent.getStringArrayExtra("iname");

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
        bucket.putStringArray("iname",iname);
        bucket.putDouble("sum", sum);
        bucket.putInt("j",j);
        bucket.putDoubleArray("amtitem",amount);

        bucket.putDoubleArray("ppitem1",ppitem[1]);
        bucket.putDoubleArray("ppitem2",ppitem[2]);
        bucket.putDoubleArray("ppitem3",ppitem[3]);
        bucket.putDoubleArray("ppitem4",ppitem[4]);
        bucket.putDoubleArray("ppitem5",ppitem[5]);
        bucket.putDoubleArray("ppitem6",ppitem[6]);
        bucket.putString("purpose",purpose);
        intentmethod.putExtras(bucket);
        startActivity(intentmethod);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

         count=0;
        if(!etamt.getText().toString().equals("")) {
            amount[j] = Double.parseDouble(etamt.getText().toString());
            for (int i = 1; i <= no; i++) {
                if (mu[i].isChecked())
                    count++;
            }
            peramt = amount[j] / count;
            for (i = 1; i <= no; i++) {
                if (mu[i].isChecked()) {
                    et[i].setText("" +  peramt);
                } else
                    et[i].setText(""+0);
            }

        }
    }


    @Override
    public void afterTextChanged(Editable s) {

    }



    public void onBackPressed() {
        super.onBackPressed();
        splitselect="SPLIT BY";
        Intent ourintent=new Intent("android.intent.action.SPLIT");
        transferdata(ourintent);
    }

    public void onBackPressed2(View v) {
        onBackPressed();

    }
}
