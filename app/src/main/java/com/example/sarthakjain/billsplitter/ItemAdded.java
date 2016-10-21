package com.example.sarthakjain.billsplitter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.AbstractCollection;

/**
 * Created by sarthak jain on 05-08-2015.
 */
public class ItemAdded extends Activity {

    Button btdel[]=new Button[7],btaddmore,btdone;
    TextView tv[]=new TextView[7];
    TextView amtleft;
    String iname[]=new String[7];
    double sum2=0,sum,amount[];
    double peramt,ppitem[][]={{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0}};
    int no,i,j;
    String splitselect,purpose;
    Bundle b = new Bundle();
    String x[]=new String[11],paid;
    double payer[],pp[],rr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itemadded);
        initialize();
        Intent ourintent=getIntent();
        receiveintent(ourintent);

        for(int i=1;i<=6;i++){
            btdel[i].setVisibility(View.INVISIBLE);
            tv[i].setVisibility(View.INVISIBLE);
        }

        for(int i=1;i<=j;i++)
        {
            btdel[i].setVisibility(View.VISIBLE);
            tv[i].setVisibility(View.VISIBLE);

        }
        for(int k=1;k<=j;k++)
        tv[k].setText(iname[k]+"Rs. "+amount[k]);

        rr=sum;
        for(int i=1;i<=6;i++)
           rr= rr-amount[i];
    amtleft.setText("AMOUNT LEFT RS "+(rr-sum2));

    }



public void initialize(){

    btdel[1]=(Button)findViewById(R.id.btdel1);
    btdel[2]=(Button)findViewById(R.id.btdel2);
    btdel[3]=(Button)findViewById(R.id.btdel3);
    btdel[4]=(Button)findViewById(R.id.btdel4);
    btdel[5]=(Button)findViewById(R.id.btdel5);
    btdel[6]=(Button)findViewById(R.id.btdel6);

    btaddmore=(Button)findViewById(R.id.btaddmore);
    btdone=(Button)findViewById(R.id.btdone);
amtleft=(TextView)findViewById(R.id.amtleft);
    tv[1]=(TextView)findViewById(R.id.tv1);
    tv[2]=(TextView)findViewById(R.id.tv2);
    tv[3]=(TextView)findViewById(R.id.tv3);
    tv[4]=(TextView)findViewById(R.id.tv4);
    tv[5]=(TextView)findViewById(R.id.tv5);
    tv[6]=(TextView)findViewById(R.id.tv6);


}

    public void receiveintent(Intent splitintent)
    {

        x=splitintent.getStringArrayExtra("names");
        no=splitintent.getIntExtra("friends", 2);
        paid=splitintent.getStringExtra("whopaid");
        purpose=splitintent.getStringExtra("purpose");
        amount=splitintent.getDoubleArrayExtra("amtitem");
        payer=splitintent.getDoubleArrayExtra("payers");
        sum=splitintent.getDoubleExtra("sum", 0);
        iname=splitintent.getStringArrayExtra("iname");
        pp=splitintent.getDoubleArrayExtra("perpersons");
        j=splitintent.getIntExtra("j",1);
        ppitem[1]=splitintent.getDoubleArrayExtra("ppitem1");
        ppitem[2]=splitintent.getDoubleArrayExtra("ppitem2");
        ppitem[3]=splitintent.getDoubleArrayExtra("ppitem3");
        ppitem[4]=splitintent.getDoubleArrayExtra("ppitem4");
        ppitem[5]=splitintent.getDoubleArrayExtra("ppitem5");
        ppitem[6]=splitintent.getDoubleArrayExtra("ppitem6");

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
        bucket.putStringArray("iname", iname);
        bucket.putDouble("sum", sum);
        bucket.putInt("j", j);
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


    public void sendMessageAddMore(View v)
    {
        j++;
        Intent ourintent=new Intent("android.intent.action.ITEMIZEDBILL");
        transferdata(ourintent);
    }

    public void  sendMessageDone(View v)
    {
        for(int i=1;i<=6;i++)
        sum2=sum2+amount[i];
        if(sum2==sum) {
            for (int i = 1; i <= no; i++) {
                for (int k = 1; k <= j; k++)
                    pp[i] = pp[i] + ppitem[k][i];
            }
            splitselect = "ITEMIZED BILL";


            Intent ourintent = new Intent("android.intent.action.BILLMENU");
            transferdata(ourintent);
        }else
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setMessage("EXPENDITURE NOT EQUAL TO PAID AMOUNT");
            AlertDialog alert = builder.create();
            alert.show();
        }
        }

public void sendMessageDelete(View v)
{   int i=0;
    switch (v.getId()) {
        case (R.id.btdel1):
            i = 1;
            break;
        case (R.id.btdel2):
            i = 2;
            break;
        case (R.id.btdel3):
            i = 3;
            break;
        case (R.id.btdel4):
            i = 4;
            break;
        case (R.id.btdel5):
            i = 5;
            break;
        case (R.id.btdel6):
            i = 6;
            break;

    }
   int temp=i;
    for(temp=i;temp<j;temp++) {

            for (int k = 1; k <= no; k++) {
                ppitem[temp][k] = ppitem[temp + 1][k];
            }
            amount[temp] = amount[temp + 1];
            iname[temp] = iname[temp + 1];

        }


        amount[j]=0;
        iname[j]="";







        btdel[j].setVisibility(View.INVISIBLE);
        tv[j].setVisibility(View.INVISIBLE);

    j--;

    for(int k=1;k<=j;k++)
        tv[k].setText(iname[k]+"Rs. "+amount[k]);


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
