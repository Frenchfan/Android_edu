package com.example.secondapp.database;

public class USerDbSchema {
    public static final class UserTable{
        public static final String NAME = "users";
        //название таблицы БД/ final - нельзя переопределить значение, это константа

    }
    public static final class Cols{
        public static final String UUID = "uuid";
        //принято столбцы таблицы, названия таблиц именовать с маленькой буквы,при этом сами константы - с большой буквы!
        //сейчас мы описываем заголовки столбцов таблицы!
        public static final String USERNAME = "user_name";
        public static final String USERLASTNAME = "user_last_name";
        public static final String PHONE = "phone";


    }
}
