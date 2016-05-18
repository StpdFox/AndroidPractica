package com.example.surfacepro3.week3.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Surface Pro 3 on 14-5-2016.
 */
public class UserDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "USERINFO";
    private static final int DATABASE_VERSION =1;
    int currentmonth=5;
    private static final String CREATE_QUERY ="CREATE TABLE "+ UserContract.NewUserInfo.TABLE_NAME+"("+ UserContract.NewUserInfo.USER_NAME+" TEXT,"+
            UserContract.NewUserInfo.USER_DAY +" TEXT,"+ UserContract.NewUserInfo.USER_MONTH+" TEXT);";


    public UserDbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        Log.e("Database operations","Database Created....");
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_QUERY);
        Log.e("Database operations", "Table Created....");
    }

    public void addInformations(String name,String day,String dateofbirth, SQLiteDatabase db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserContract.NewUserInfo.USER_NAME,name);
        contentValues.put(UserContract.NewUserInfo.USER_DAY,day);
        contentValues.put(UserContract.NewUserInfo.USER_MONTH,dateofbirth);
        db.insert(UserContract.NewUserInfo.TABLE_NAME,null,contentValues);
        Log.e("Database operations", "One row inserted....");

    }
    public Cursor getInformations(SQLiteDatabase db){
        Cursor cursor;
        String[] projections ={UserContract.NewUserInfo.USER_NAME, UserContract.NewUserInfo.USER_DAY, UserContract.NewUserInfo.USER_MONTH};
        cursor = db.query(UserContract.NewUserInfo.TABLE_NAME,projections, UserContract.NewUserInfo.USER_MONTH+" = "+currentmonth,null,null,null,UserContract.NewUserInfo.USER_DAY+" ASC");
        return cursor;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,int newVersion){

    }
    public Cursor getContact(String user_name, SQLiteDatabase sqLiteDatabase){
        String[] projections = {UserContract.NewUserInfo.USER_DAY, UserContract.NewUserInfo.USER_MONTH};
        String selection = UserContract.NewUserInfo.USER_NAME+" LIKE ?";
        String[] selection_args = {user_name};
        Cursor cursor = sqLiteDatabase.query(UserContract.NewUserInfo.TABLE_NAME,projections,selection,selection_args,null,null,null);
        return cursor;
    }
    public void deleteInformation(String user_name, SQLiteDatabase sqLiteDatabase){
        String selection = UserContract.NewUserInfo.USER_NAME+" LIKE ?";
        String[] selection_args = {user_name};
        sqLiteDatabase.delete(UserContract.NewUserInfo.TABLE_NAME,selection,selection_args);


    }
}
