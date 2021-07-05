package com.example.secondapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class UserBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;//версионность нужна для понимания, обновилась ли таблица
    private static final String DTABASE_NAME = "userBase.db";//обязательно расширение указывать
    public UserBaseHelper(Context context) {
        super(context, DTABASE_NAME, null, VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+USerDbSchema.UserTable.NAME+"("+
                "_id integer primary key autoincrement, "+
                USerDbSchema.Cols.UUID+", "+
                USerDbSchema.Cols.USERNAME+", "+
                USerDbSchema.Cols.USERLASTNAME+", "+
                USerDbSchema.Cols.PHONE+")");
        //всегда нужен id при создании любой таблицы, он всегда гарантирует уникальность на уровне базы данных
        //autoincrement - увеличение id на единицу при создании нового элемента

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
