package com.example.sarthakjain.billsplitter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


public class Output extends Activity
{
    String msg,name;
    TextView[]et=new TextView[12];
    double payer[]=new double[11],sum,pp[],balance[],expenditure[]={0,0,0,0,0,0,0,0,0,0,0},amt[][]={{0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0}};
    String x[]=new String[11];
    Button infbt[]=new Button[11];
    String dbDetails="";
    String decide,purpose,sol[][]={{" "," "," "," "," "," "," "," "," "," "," ",},{" "," "," "," "," "," "," "," "," "," "," ",},{" "," "," "," "," "," "," "," "," "," "," ",},{" "," "," "," "," "," "," "," "," "," "," ",},{" "," "," "," "," "," "," "," "," "," "," ",},{" "," "," "," "," "," "," "," "," "," "," ",},{" "," "," "," "," "," "," "," "," "," "," ",},{" "," "," "," "," "," "," "," "," "," "," ",},{" "," "," "," "," "," "," "," "," "," "," ",},{" "," "," "," "," "," "," "," "," "," "," ",},{" "," "," "," "," "," "," "," "," "," "," ",},{" "," "," "," "," "," "," "," "," "," "," ",},{" "," "," "," "," "," "," "," "," "," "," ",},{" "," "," "," "," "," "," "," "," "," "," ",},{" "," "," "," "," "," "," "," "," "," "," ",},{" "," "," "," "," "," "," "," "," "," "," ",},{" "," "," "," "," "," "," "," "," "," "," ",},{" "," "," "," "," "," "," "," "," "," "," ",},{" "," "," "," "," "," "," "," "," "," "," ",},{" "," "," "," "," "," "," "," "," "," "," ",},{" "," "," "," "," "," "," "," "," "," "," ",},{" "," "," "," "," "," "," "," "," "," "," ",},{" "," "," "," "," "," "," "," "," "," "," ",},{" "," "," "," "," "," "," "," "," "," "," ",},{" "," "," "," "," "," "," "," "," "," "," ",},{" "," "," "," "," "," "," "," "," "," "," ",},{" "," "," "," "," "," "," "," "," "," "," ",},{" "," "," "," "," "," "," "," "," "," "," ",}};
String paid,splitselect;
    int no,i,gv[]={0,0,0,0,0,0,0,0,0,0,0},gt[]={1,1,1,1,1,1,1,1,1,1,1};


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.output);


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
        et[11]=(TextView)findViewById(R.id.et11);

        infbt[1]=(Button)findViewById(R.id.infbt1);
        infbt[2]=(Button)findViewById(R.id.infbt2);
        infbt[3]=(Button)findViewById(R.id.infbt3);
        infbt[4]=(Button)findViewById(R.id.infbt4);
        infbt[5]=(Button)findViewById(R.id.infbt5);
        infbt[6]=(Button)findViewById(R.id.infbt6);
        infbt[7]=(Button)findViewById(R.id.infbt7);
        infbt[8]=(Button)findViewById(R.id.infbt8);
        infbt[9]=(Button)findViewById(R.id.infbt9);
        infbt[10]=(Button)findViewById(R.id.infbt10);


for(int i=1;i<=10;i++)
    infbt[i].setVisibility(View.INVISIBLE);
        Intent ourintent=getIntent();
     receiveintent(ourintent);
        et[11].setText(purpose);
    for(int i=1;i<=no;i++)
        expenditure[i]=balance[i]-payer[i];

        int k=0;
    massivecalculation(amt,sol,k);


    for( i=1;i<=no;i++) {
        et[i].setText("" + x[i] + "\n paid rs.  : " + balance[i] + "\n expenditure  : " + expenditure[i] + " \n " + giveOrGet(balance[i]-expenditure[i]) + " \n " );
    for(k=0;k<=10;k++) {
        if(amt[k][i]!=0)
        et[i].setText(et[i].getText() + "" + ((int) amt[k][i]) +  toOrFrom(balance[i]-expenditure[i]) +" "+ sol[k][i]+"\n");

    }
        infbt[i].setVisibility(View.VISIBLE);
    }





    }


    public String giveOrGet(double num)
    {
        if(num<=0) {
            decide = " GIVE";
        payer[i]=-payer[i];
        gv[i]=1;
            gt[i]=0;
        }
        else {
            if (num > 0)
                decide = " TAKE";

        }
        return decide;
    }




    public String toOrFrom(double num)
    {
        if(num<=0) {
            decide = " TO";
            payer[i]=-payer[i];
            gv[i]=1;
            gt[i]=0;
        }
        else {
            if (num > 0)
                decide = " FROM";

        }
        return decide;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
          for(int i=0;i<=no;i++)
          {
              payer[i]=0;
              pp[i]=0;
          }
            sum=0;

    Intent ourintent=new Intent("android.intent.action.BILLMENU");
        sendintent(ourintent);

    }



    public void receiveintent(Intent intent)
    {

        x=intent.getStringArrayExtra("names");
        purpose=intent.getStringExtra("purpose");
        no=intent.getIntExtra("friends",2);
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
        bucket.putDoubleArray("balance",balance);
        bucket.putDouble("sum",sum);
        bucket.putString("purpose",purpose);
        bucket.putString("msg",msg);
        bucket.putString("name",name);

        intentmethod.putExtras(bucket);
        startActivity(intentmethod);
    }


    public void massivecalculation(double amt[][],String sol[][],int k)
    {


        for(int i=1;i<=no;i++) {

            if (payer[i] <= 0) {
                payer[i]=-payer[i];
                gv[i] = 1;
                gt[i] = 0;
            }

        }


        for(int i=1;i<=no;i++) {
            for (int j = 1; j <= no; j++) {
                if (gv[i] ==1&& gt[j]==1 && payer[j] <= payer[i]) {
                    sol[k][i] = x[j];
                    sol[k][j]=x[i];

                    amt[k][i] = payer[j];
                    amt[k][j]=payer[j];
                    if(payer[i]==payer[j]) {
                        gv[i] = 0;
                        gt[j] = 0;
                    }else {

                        payer[i]=payer[i]-amt[k][i];
                        gt[j]=0;
                        massivecalculation(amt,sol,k+1);
                    }



                }else
                    if (gv[i] ==1&&gt[j]==1 && payer[j] > payer[i]) {
                    sol[k][i] = x[j];
                    sol[k][j]=x[i];
                    amt[k][i] = payer[i];
                        amt[k][j]=payer[i];
                    payer[j]=payer[j]-amt[k][i];
                    gv[i] = 0;
                    massivecalculation(amt,sol,k+1);
                }
            }
        }


    }

public void sendMessage(View v)
{
for(int i=1;i<=no;i++)
    dbDetails=dbDetails+"\n"+et[i].getText().toString();
boolean diditwork=true;
try {

        Database entry = new Database(Output.this);
        entry.writeOnIt();
        entry.createEntry(dbDetails,purpose);
        entry.close();

}catch (Exception e) {
    diditwork = false;
}finally {
    if(diditwork)
    {
        Toast.makeText(getApplicationContext(), "SUCCESSFULLY UPDATED", Toast.LENGTH_LONG).show();
this.finish();
        Intent ourintent=new Intent("android.intent.action.SAVEDDATA");
        sendintent(ourintent);
    }
}



}

    public void sendMessageInform(View v)
    {
        int i=0;
        switch (v.getId())
        {
            case R.id.infbt1 :   i=1;
                break;

            case R.id.infbt2 :   i=2;
                break;

            case R.id.infbt3 :   i=3;
                break;

            case R.id.infbt4 :   i=4;
                break;

            case R.id.infbt5 :   i=5;
                break;

            case R.id.infbt6 :   i=6;
                break;

            case R.id.infbt7 :   i=7;
                break;

            case R.id.infbt8 :   i=8;
                break;

            case R.id.infbt9 :   i=9;
                break;

            case R.id.infbt10 :   i=10;
                break;


        }
        msg="Dear "+et[i].getText().toString()+"\nsent from bill splitter";
         name=x[i];
Intent ourintent=new Intent("android.intent.action.SENDSMS");
      sendintent(ourintent);
    }


    public void onexit(View v)
    {
        this.finish();
        System.exit(0);
    }


public void sendMessage3(View v)
{
this.finish();
    Intent intent = new Intent(this, MainActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    startActivity(intent);
}
}


