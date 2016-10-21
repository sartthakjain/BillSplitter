package com.example.sarthakjain.billsplitter;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

import java.sql.SQLException;
import java.util.ArrayList;


public class Names extends Activity implements  OnItemClickListener, OnItemSelectedListener
{
 int no,flag=0,flag2=0;
    TextView tv1;
    String name[]=new String[11],purpose="";
    String paid="WHO PAID",splitselect="HOW TO SPLIT";
    double pp[]={0,0,0,0,0,0,0,0,0,0,0};
    double payer[]={0,0,0,0,0,0,0,0,0,0,0};
    AutoCompleteTextView []et=new AutoCompleteTextView[11];
    String contactName = null;
    EditText toNumber=null;
    String toNumberValue="";
    String nameofselected="";
    String number[]=new String[11];
    private ArrayAdapter<String> adapter;
    public static ArrayList<String> phoneValueArr = new ArrayList<String>();
    public static ArrayList<String> nameValueArr = new ArrayList<String>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.names);

      Intent ourinten=getIntent();
       Bundle b=ourinten.getExtras();
       no=Integer.parseInt(String.valueOf(b.get("friends")));


        et[1]=(AutoCompleteTextView)findViewById(R.id.et1);

        et[2]=(AutoCompleteTextView)findViewById(R.id.et2);
        et[3]=(AutoCompleteTextView)findViewById(R.id.et3);
        et[4]=(AutoCompleteTextView)findViewById(R.id.et4);
        et[5]=(AutoCompleteTextView)findViewById(R.id.et5);
        et[6]=(AutoCompleteTextView)findViewById(R.id.et6);
        et[7]=(AutoCompleteTextView)findViewById(R.id.et7);
        et[8]=(AutoCompleteTextView)findViewById(R.id.et8);
        et[9]=(AutoCompleteTextView)findViewById(R.id.et9);
        et[10]=(AutoCompleteTextView)findViewById(R.id.et10);
        for(int i=1;i<=10;i++)
        {

            et[i].setVisibility(View.INVISIBLE);

        }

        adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_dropdown_item_1line, new ArrayList<String>());


        for(int i=1;i<=no;i++)
        {




            et[i].setVisibility(View.VISIBLE);
            et[i].setThreshold(1);
            et[i].setAdapter(adapter);
            et[i].setOnItemSelectedListener(this);
            et[i].setOnItemClickListener(this);


        }






        readContactData();


    }


public void sendMessage(View v)
{
for(int i=1;i<=no;i++)
    name[i] = et[i].getText().toString();

    for(int i=1;i<=no;i++) {
        for(int j=i+1;j<=no;j++) {

            if (name[i].equals(""))
               flag = 1;
            if(name[i].equals(name[j]))
                flag2=1;
        }
    }

        if(flag==0&&flag2==0) {
            // Intent ourintent=new Intent("android.intent.action.CHOOSEWHOPAID");
            Intent billintent = new Intent("android.intent.action.BILLMENU");
            Bundle b = new Bundle();
            // for(int i=1;i<=no;i++)
            b.putInt("friends", no);
            b.putStringArray("names", name);
            b.putDoubleArray("perpersons", pp);
            b.putString("purpose", purpose);
            b.putString("whopaid",paid);
            b.putString("splitselect",splitselect);
            b.putDoubleArray("payers", payer);
            billintent.putExtras(b);
            startActivity(billintent);

        }else
        if(flag==1){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setMessage("EMPTY NAMES");
            AlertDialog alert = builder.create();
            alert.show();
            flag=0;
        }
    else if(flag2==1){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setMessage("DUPLICATE NAMES");
            AlertDialog alert = builder.create();
            alert.show();
            flag2=0;
        }
}


public void addContact(View v)
{

    String NameSel = "";
    NameSel = toNumber.getText().toString();


    final String ToNumber = toNumberValue;


    if (ToNumber.length() == 0 ) {
        Toast.makeText(getBaseContext(), "Please fill phone number",
                Toast.LENGTH_SHORT).show();
    }
    else
    {
        Toast.makeText(getBaseContext(), NameSel+" : "+toNumberValue,
                Toast.LENGTH_LONG).show();
    }

}



    private void readContactData() {

    try {

    /*********** Reading Contacts Name And Number **********/

    String phoneNumber = "";
    ContentResolver cr = getBaseContext()
            .getContentResolver();

    //Query to get contact name

    Cursor cur = cr
            .query(ContactsContract.Contacts.CONTENT_URI,
                    null,
                    null,
                    null,
                    null);

    // If data data found in contacts
    if (cur.getCount() > 0) {

        Log.i("AutocompleteContacts", "Reading   contacts........");

        int k=0;
        String name = "";

        while (cur.moveToNext())
        {

            String id = cur
                    .getString(cur
                            .getColumnIndex(ContactsContract.Contacts._ID));
            name = cur
                    .getString(cur
                            .getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

            //Check contact have phone number
            if (Integer
                    .parseInt(cur
                            .getString(cur
                                    .getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0)
            {

                //Create query to get phone number by contact id
                Cursor pCur = cr
                        .query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                                        + " = ?",
                                new String[] { id },
                                null);
                int j=0;

                while (pCur
                        .moveToNext())
                {
                    // Sometimes get multiple data
                    if(j==0)
                    {
                        // Get Phone number
                        phoneNumber =""+pCur.getString(pCur
                                .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                        // Add contacts names to adapter
                        adapter.add(name);

                        // Add ArrayList names to adapter
                        phoneValueArr.add(phoneNumber.toString());
                        nameValueArr.add(name.toString());

                        j++;
                        k++;
                    }
                }  // End while loop
                pCur.close();
            } // End if

        }  // End while loop

    } // End Cursor value check
    cur.close();


} catch (Exception e) {
    Log.i("AutocompleteContacts","Exception : "+ e);
}


}


    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position,
                               long arg3) {
        // TODO Auto-generated method stub
        //Log.d("AutocompleteContacts", "onItemSelected() position " + position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

        InputMethodManager imm = (InputMethodManager) getSystemService(
                INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        // TODO Auto-generated method stub

        // Get Array index value for selected name
        int i = nameValueArr.indexOf(""+arg0.getItemAtPosition(arg2));

        // If name exist in name ArrayList
        if (i >= 0) {

            // Get Phone Number
            toNumberValue = phoneValueArr.get(i);

            InputMethodManager imm = (InputMethodManager) getSystemService(
                    INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

            // Show Alert
           /* Toast.makeText(getBaseContext(),
                    "Position:"+arg2+" Name:"+arg0.getItemAtPosition(arg2)+" Number:"+toNumberValue,
                    Toast.LENGTH_LONG).show();*/

            Log.d("AutocompleteContacts",
                    "Position:"+arg2+" Name:"+arg0.getItemAtPosition(arg2)+" Number:"+toNumberValue);
nameofselected=arg0.getItemAtPosition(arg2).toString();
        }
        boolean diditwork=true;
    Database info=new Database(this);
        try {
            info.writeOnIt();
            info.storeNames(nameofselected,toNumberValue);
            info.close();
        } catch (SQLException e) {
          diditwork=false;
            e.printStackTrace();
        }
        finally {
            if(diditwork)

                Toast.makeText(getApplicationContext(), "SUCCESSFULLY ENTERED", Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
        Intent ourintent=new Intent("android.intent.action.START");
        startActivity(ourintent);

    }

    public void sendMessageback(View v)
    {
        onBackPressed();
    }
}
