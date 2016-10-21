package com.example.sarthakjain.billsplitter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Database {

    public static final String KEY_ROWID="_id";
    public static final String KEY_DETAILS="person_detail";
    public static final String KEY_PURPOSE="purpose";
    public static final String KEY_DATE="date";
    public static final String KEY_NAME="Person_name";
    public static final String KEY_NUMBER="person_number";

    private static final String DATABASE_NAME="billsplitter";
    private static final String DATABASE_TABLE="detail_table";
    private static final String CONTACT_TABLE="contact_table";
    private static final int DATABASE_VERSION=2;

    private DbHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;

    public String getData(int i) {
        String colums[]=new String[]{KEY_ROWID,KEY_PURPOSE,KEY_DETAILS,KEY_DATE};
        Cursor c=ourDatabase.query(DATABASE_TABLE,colums,null,null,null,null,null);
int k=1;
        String result="";
        int irow=c.getColumnIndex(KEY_ROWID);
        int idetail=c.getColumnIndex(KEY_DETAILS);
        int idate=c.getColumnIndex(KEY_DATE);
        int ipurpose=c.getColumnIndex(KEY_PURPOSE);
        c.moveToLast();

        for(c.moveToLast();k<=i;k++){
            if(!c.isBeforeFirst())
            result = c.getString(ipurpose);// + "  " + c.getString(idate);
 else
            result="no data";
       //     if(c.getColumnIndex(KEY_ROWID)!=0) {

                c.moveToPrevious();
         //   }

        }

/*
              if(c.getColumnIndex(KEY_ROWID)>=i-1) {
                  c.moveToPosition(c.getColumnIndex(KEY_ROWID) - i+1);
                  result = c.getString(idate) + "  " + c.getString(ipurpose);
              }*/

        return result;
    }


    public String getDetailData(int i) {
        String colums[]=new String[]{KEY_ROWID,KEY_PURPOSE,KEY_DETAILS,KEY_DATE};
        Cursor c=ourDatabase.query(DATABASE_TABLE,colums,null,null,null,null,null);
String result="";
int k=1;
        int irow=c.getColumnIndex(KEY_ROWID);
        int idetail=c.getColumnIndex(KEY_DETAILS);
        int idate=c.getColumnIndex(KEY_DATE);
        int ipurpose=c.getColumnIndex(KEY_PURPOSE);
    c.moveToLast();
        for(c.moveToLast();k<=i;k++){
           // if(c.getColumnIndex(KEY_ROWID)!=0) {
            if(!c.isBeforeFirst())
                result = c.getString(ipurpose) + "\n" + c.getString(idetail);
            else
            result="no data";
                c.moveToPrevious();
        //    }

        }


       /* if(c.getColumnIndex(KEY_ROWID)>=i-1) {
            c.moveToPosition(c.getColumnIndex(KEY_ROWID) - i+1);
             result = c.getString(ipurpose) + "\n" + c.getString(idetail);
        }*/
        return result;
    }

    public void deleteEntry(String data) {
       // String colums[]=new String[]{KEY_ROWID,KEY_PURPOSE,KEY_DETAILS,KEY_DATE};
       // Cursor c=ourDatabase.query(DATABASE_TABLE,colums,null,null,null,null,null);


      //  c.moveToLast();
   //     for(c.moveToLast();k<=i;k++){

     //       c.moveToPrevious();
       // }


        ourDatabase.delete(DATABASE_TABLE , KEY_PURPOSE + " = '"+data+"'", null);
    }

    public String getnumber(String name) {
        String number="";
        String colums[]=new String[]{KEY_ROWID,KEY_NAME,KEY_NUMBER};
        Cursor c=ourDatabase.query(CONTACT_TABLE,colums,null,null,null,null,null);
        int irow=c.getColumnIndex(KEY_ROWID);
        int iname=c.getColumnIndex(KEY_NAME);
        int inumber=c.getColumnIndex(KEY_NUMBER);
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()) {
            if (c.getString(iname).equals(name))
                number=c.getString(inumber);
        }
        return number;
    }


    private static class DbHelper extends SQLiteOpenHelper
    {

        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null , DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {


        db.execSQL(

                "CREATE TABLE "+DATABASE_TABLE+" ( "+
                        KEY_ROWID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        KEY_DETAILS+" TEXT NOT NULL , "+
                        KEY_PURPOSE+" TEXT NOT NULL , "+
                        KEY_DATE+" DATE NOT NULL);"
        );

            db.execSQL(

                    "CREATE TABLE "+CONTACT_TABLE+" ( "+
                            KEY_ROWID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                            KEY_NAME+" TEXT NOT NULL , "+
                            KEY_NUMBER+" TEXT  ); "

            );

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists "+ DATABASE_TABLE);
            db.execSQL("drop table if exists "+ CONTACT_TABLE);
            onCreate(db);
        }
    }


    public Database(Context c)
    {
        ourContext=c;
    }
public Database writeOnIt() throws SQLException
{
    ourHelper=new DbHelper(ourContext);
    ourDatabase=ourHelper.getWritableDatabase();
    return this;

}
    public void close()
    {
        ourHelper.close();
    }

    Calendar c = Calendar.getInstance();
    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
    String formattedDate = df.format(c.getTime());

    public long createEntry(String dbDetail,String purpose) {
        ContentValues cv=new ContentValues();
        cv.put(KEY_DETAILS,dbDetail);
        cv.put(KEY_PURPOSE,purpose);
        cv.put(KEY_DATE,formattedDate);
        return ourDatabase.insert(DATABASE_TABLE,null,cv);
    }

    public long storeNames(String dbname,String dbnumber) {
        ContentValues cv=new ContentValues();
        cv.put(KEY_NAME,dbname);
        cv.put(KEY_NUMBER,dbnumber);

        return ourDatabase.insert(CONTACT_TABLE,null,cv);
    }

}
