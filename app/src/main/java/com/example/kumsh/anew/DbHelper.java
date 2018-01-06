package com.example.kumsh.anew;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by kumsh on 25-12-2017.
 */

public class DbHelper extends SQLiteOpenHelper {
    public static final String TAG=DbHelper.class.getSimpleName();
    public static final String DB_NAME="Helping.db";

    public static final String USER_TABLE="Users";
    public static final String Col_id="ID";
    public static final String Col_name="Name";
    public static final String Col_mobile="Mobile";
    public static final String Col_password="Password";
    public static final String CREATE_TABLE_USERS=" CREATE TABLE " + USER_TABLE + "("+ Col_id + " INTEGER PRIMARY KEY AUTOINCREMENT," + Col_name + " TEXT," + Col_mobile + " INTEGER," + Col_password + " Text)";
    public DbHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        onCreate(db);
    }

    public void addUser(String name,String mobile,String password)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Col_name,name);
        values.put(Col_mobile,mobile);
        values.put(Col_password,password);
        long id=db.insert(USER_TABLE,null,values);
        db.close();
        Log.d(TAG, "user inserted"+ id);
    }

    public boolean getUser(String mobile,String password)
    {
        String selectQuery = "SELECT * FROM " + USER_TABLE + " WHERE " + Col_mobile + "=? AND " + Col_password + "=?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,new String[]{mobile,password});
        cursor.moveToFirst();
        if(cursor.getCount()>0)
        {
            return true;
        }
        cursor.close();
        db.close();
        return false;
    }
}
