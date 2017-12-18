package com.labs.android.lab7.lab7;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "productsDB.db"; // название бд
    private static final int SCHEMA = 1; // версия базы данных
    public static final String TABLE = "products"; // название таблицы в бд

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_UPC = "upc";
    public static final String COLUMN_MANUFACTURER = "manufacturer";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_EXPIRATION = "expiration";
    public static final String COLUMN_AMOUNT = "amount";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS products (" +
                COLUMN_ID + " integer primary key autoincrement, " +
                COLUMN_NAME + " text, " +
                COLUMN_UPC + " integer, " +
                COLUMN_MANUFACTURER + " text, " +
                COLUMN_PRICE + " real, " +
                COLUMN_EXPIRATION + " text, " +
                COLUMN_AMOUNT + " integer)");
        // добавление начальных данных
        db.execSQL("INSERT INTO products VALUES (0, 'first product', 1234, 'Belavia', 25.0, '30.10.2017', 2);");
        db.execSQL("INSERT INTO products VALUES (1, 'second thing', 5462, 'Trello', 10.0, '30.08.2017', 0);");
        db.execSQL("INSERT INTO products VALUES (2, 'one', 5523, 'Aawd', 10.0, '30.09.2017', 1);");
        db.execSQL("INSERT INTO products VALUES (3, 'one', 5522, 'AGAD', 10.0, '30.10.2017', 0);");
        db.execSQL("INSERT INTO products VALUES (4, 'two', 4123, 'aAWD', 10.0, '30.12.2017', 3);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }
}