package com.rishat.loginsql;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "register.db";
    public static final String TABLE_NAME = "register";
    public static final String TABLE_NAMEv2 = "userTime";

    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE register (ID INTEGER PRIMARY KEY AUTOINCREMENT, FullName TEXT, Email TEXT, Password TEXT, Phone TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE userTime (ID INTEGER PRIMARY KEY AUTOINCREMENT, UserName TEXT, Time TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " +TABLE_NAME);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " +TABLE_NAMEv2);
        onCreate(sqLiteDatabase);


    }

    public long addTime(String uName, String lTime){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("UserName",uName);
        contentValues.put("LoginTime",lTime);
        long res = db.insert("userTime", null,contentValues);
        db.close();
        return  res;
    }


    public long addUser(String fullname, String email, String password, String phone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("FullName", fullname);
        contentValues.put("Email", email);
        contentValues.put("Password", password);
        contentValues.put("Phone", phone);
        long res = db.insert("register", null, contentValues);
        db.close();
        return res;
    }
    public boolean checkUser(String email, String password){

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = { "Email" , "Password"};
        String selection = "Email =? and Password=?";
        String[] selectionArgs = {email, password};
        Cursor cursor = db.query("register", columns, selection, selectionArgs, null, null, null);
        int count= cursor.getCount();
        cursor.close();
        db.close();

        if(count>0)
            return true;
        else
            return false;

    }

}
