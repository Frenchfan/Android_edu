package com.example.secondapp;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.secondapp.database.USerDbSchema;

import java.util.UUID;

public class UserCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public UserCursorWrapper(Cursor cursor) {
        super(cursor);
    }
    public User getUser(){
        String uuidString = getString(getColumnIndex(USerDbSchema.Cols.UUID));
        String userName = getString(getColumnIndex(USerDbSchema.Cols.USERNAME));
        String userLastName = getString(getColumnIndex(USerDbSchema.Cols.USERLASTNAME));
        String phone = getString(getColumnIndex(USerDbSchema.Cols.PHONE));
        User user = new User(UUID.fromString(uuidString));
        user.setUserName(userName);
        user.setUserLastName(userLastName);
        user.setPhone(phone);
        return user;

    }
}
