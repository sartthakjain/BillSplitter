package com.example.sarthakjain.billsplitter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioButton;
import android.widget.TextView;


public class ChooseWhoPaid extends Activity
{
    RadioButton cb[]=new RadioButton[11];

    CheckBox mu[]=new CheckBox[11];
    EditText et[]=new EditText[11];
    Button btdone;
    double sum;
    int no,i;
    TextView tvrrr,tvrr2;
    String splitselect,purpose;
    Bundle b = new Bundle();
    String x[]=new String[11],paid;
    double payer[],pp[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choosewhopaid);

      initialize();
        btdone=(Button)findViewById(R.id.btdone);

        Intent intent=getIntent();
        x =intent.getStringArrayExtra("names");
        //payer=intent.getDoubleArrayExtra("payers");

        paid=intent.getStringExtra("whopaid");
        no=intent.getIntExtra("friends",2);
        payer=intent.getDoubleArrayExtra("payers");
        purpose=intent.getStringExtra("purpose");
        sum=intent.getDoubleExtra("sum",0);
        pp=intent.getDoubleArrayExtra("perpersons");
            splitselect=intent.getStringExtra("splitselect");

        for(int i=1;i<11;i++) {
            cb[i].setVisibility(View.INVISIBLE);
            et[i].setVisibility(View.INVISIBLE);
            mu[i].setVisibility(View.INVISIBLE);
               }
        tvrr2.setVisibility(View.INVISIBLE);
        tvrrr.setVisibility(View.INVISIBLE);

        btdone.setVisibility(View.INVISIBLE);

        for( i=1;i<=no;i++) {
          cb[i].setVisibility(View.VISIBLE);
            cb[i].setText(x[i]);
        }
        cb[i].setVisibility(View.VISIBLE);
        cb[i].setText("MULTIPLE PERSONS");













        }




    public void sendMessage (View v){

        for(i=1;i<=no+1;i++) {

            if (cb[i].isChecked()) {

               if (!cb[i].getText().toString().equals("MULTIPLE PERSONS")) {
                 Intent intents = new Intent("android.intent.action.BILLMENU");
                //   Intent tosplit=new Intent("android.intent.action.SPLIT");
                    //Bundle b = new Bundle();
                    b.putString("whopaid",x[i]);
                b.putStringArray("names",x);
                b.putInt("friends",no);
                b.putDouble("sum",sum);
                 b.putString("splitselect",splitselect);
                b.putString("purpose",purpose);
                   b.putDoubleArray("payers",payer);
                   b.putDoubleArray("perpersons",pp);
                    intents.putExtras(b);
                   this.finish();
                    startActivity(intents);
                }
                else {


                    tvrr2.setVisibility(View.VISIBLE);
                   tvrrr.setVisibility(View.VISIBLE);
                   for (int j=1;j<=no;j++)
                   {
                       et[j].setVisibility(View.VISIBLE);
                       mu[j].setVisibility(View.VISIBLE);
                       mu[j].setText(x[j]);

                   }
                    btdone.setVisibility(View.VISIBLE);

               }

            }
        }
    }





public void sendMessage2(View v)
{
         Intent intents = new Intent("android.intent.action.BILLMENU");
    sum=0;
int flag=0;
    for(i=1;i<=no;i++) {

        if (mu[i].isChecked()){
            if(et[i].getText().toString().equals("")) {
            flag=1;
            }
        }
    }




            if(flag==1){

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setCancelable(true);
                builder.setMessage("EMPTY FIELD");
                AlertDialog alert = builder.create();
                alert.show();


            }else {

                for(i=1;i<=no;i++) {
                    payer[i]=0;
                    if (mu[i].isChecked()) {
                        payer[i] = payer[i] + Double.parseDouble(et[i].getText().toString());
                        sum = sum + payer[i];
                    }
                }
                    b.putString("whopaid", "MULTIPLE USERS");
                    b.putStringArray("names", x);
                    b.putInt("friends", no);
                    b.putString("purpose", purpose);
                    b.putDouble("sum", sum);
                    b.putDoubleArray("payers", payer);
                    b.putString("splitselect", splitselect);
                    b.putDoubleArray("perpersons", pp);

//b.putString("whopaid",paid);


                    intents.putExtras(b);

                    startActivity(intents);





    }





}


public void initialize(){

    tvrr2=(TextView)findViewById(R.id.tvrr2);
    tvrrr=(TextView)findViewById(R.id.tvrrr);
    cb[1]=(RadioButton)findViewById(R.id.cb1);
    cb[2]=(RadioButton)findViewById(R.id.cb2);
    cb[3]=(RadioButton)findViewById(R.id.cb3);
    cb[4]=(RadioButton)findViewById(R.id.cb4);
    cb[5]=(RadioButton)findViewById(R.id.cb5);
    cb[6]=(RadioButton)findViewById(R.id.cb6);
    cb[7]=(RadioButton)findViewById(R.id.cb7);
    cb[8]=(RadioButton)findViewById(R.id.cb8);
    cb[9]=(RadioButton)findViewById(R.id.cb9);
    cb[10]=(RadioButton)findViewById(R.id.cb10);

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
}
