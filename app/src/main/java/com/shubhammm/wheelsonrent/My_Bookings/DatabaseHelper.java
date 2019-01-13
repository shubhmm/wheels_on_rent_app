package com.shubhammm.wheelsonrent.My_Bookings;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Shubhamm Arora on 27-06-2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static  final String TAG = "DatabaseHelper";
    private static  final String Table_Name = "My_Booking";
    private static  final String COl1 = "ID";
    private static  final String COl2 = "Date";
    private static  final String COl3 = "Time";
    private static  final String COl4 = "Hours";

    public DatabaseHelper(Context context) {

        super(context, Table_Name,null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

            String createTable = "CREATE TABLE" + Table_Name + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COl2+ "TEXT)";
             db.execSQL(createTable);
    }
/*
    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable = "CREATE TABLE" + Table_Name + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COl1+ " , "+COl3+" , "+COl4+" TEXT)";
        db.execSQL(createTable);
    }
 */

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP IF TABLE EXISTS" + Table_Name);
        onCreate(db);
    }

    public  boolean addData(String a ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COl2,a);
       // contentValues.put(COl3,b);
       // contentValues.put(COl4,c);
        Log.d(TAG," addData : Adding " + a + "to" + Table_Name);
long result = db.insert(Table_Name,null,contentValues );

        if(result==-1)
            return false;
        else
            return true;
    }


/*
    public  boolean addData(String a ,String b,String c){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COl2,a);
        contentValues.put(COl3,b);
        contentValues.put(COl4,c);
        long result = db.insert(Table_Name,null,contentValues );

        if(result==-1)
            return false;
        else
            return true;
    }
*/

    public Cursor getData(){
        SQLiteDatabase db = (this).getWritableDatabase();
        String query = "SELECT * FROM"+ Table_Name;
        Cursor data = db.rawQuery(query,null);
        return  data;
    }

}
