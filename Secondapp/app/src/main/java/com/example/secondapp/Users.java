package com.example.secondapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.secondapp.database.USerDbSchema;
import com.example.secondapp.database.UserBaseHelper;

import java.util.ArrayList;
import java.util.List;

public class Users {
    private ArrayList<User> userList;
    private SQLiteDatabase database;
    private Context context;

    public Users(Context context) {
        this.context = context.getApplicationContext();
        this.database=new UserBaseHelper(context).getWritableDatabase();
    }
    public void addUser(User user) {
        ContentValues values=getContentValues(user);
        database.insert(USerDbSchema.UserTable.NAME, null, values);
    }

    public void removeUser(User user) {
        ContentValues values = getContentValues(user);
        database.delete(USerDbSchema.UserTable.NAME,USerDbSchema.Cols.UUID+"='"+user.getUuid().toString()+"'" ,null);
    }

    private static ContentValues getContentValues(User user) {
        ContentValues values = new ContentValues();
        //Сопоставляем колонки и свойства объекта User
        values.put(USerDbSchema.Cols.UUID, user.getUuid().toString());
        values.put(USerDbSchema.Cols.USERNAME, user.getUserName());
        values.put(USerDbSchema.Cols.USERLASTNAME, user.getUserLastName());
        values.put(USerDbSchema.Cols.PHONE, user.getPhone());

        return values;

    }
    private UserCursorWrapper queryUsers() {
        Cursor cursor = database.query(USerDbSchema.UserTable.NAME, null,null,null,null,null,null);
        return new UserCursorWrapper(cursor);
    }

    public ArrayList<User> getUserList(){
        this.userList = new ArrayList<User>();
        UserCursorWrapper cursorWrapper = queryUsers();
        try {
            cursorWrapper.moveToFirst();
            while (!cursorWrapper.isAfterLast()){
                User user = cursorWrapper.getUser();
                userList.add(user);
                cursorWrapper.moveToNext();
            }
        }finally {
            cursorWrapper.close();
        }
        return userList;

    }
}
