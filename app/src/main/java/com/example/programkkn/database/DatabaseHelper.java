package com.example.programkkn.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "db_pemesanan";
    public static final String TABLE_USER = "tb_user";
    public static final String COL_USERNAME = "username";
    public static final String COL_PASSWORD = "password";
    public static final String COL_NAME = "name";
    public static final String TABLE_BOOK = "tb_book";
    public static final String COL_ID_BOOK = "id_book";
    public static final String COL_MENU1 = "menu";
    public static final String COL_MENU2 = "menuu";
    public static final String COL_MENU3 = "menuuu";
    public static final String COL_MENU4 = "menuuuu";
    public static final String COL_MENU5 = "menuuuuu";
    public static final String COL_MENU6 = "menuuuuuu";
    public static final String COL_TANGGAL = "tanggal";
    public static final String COL_JUMLAH1 = "jumlah";
    public static final String COL_JUMLAH2 = "jumlahh";
    public static final String COL_JUMLAH3 = "jumlahhh";
    public static final String COL_JUMLAH4 = "jumlahhhh";
    public static final String COL_JUMLAH5 = "jumlahhhhh";
    public static final String COL_JUMLAH6 = "jumlahhhhhh";
    public static final String TABLE_HARGA = "tb_harga";
    public static final String COL_HARGA_JUMLAH1 = "harga_jumlah";
    public static final String COL_HARGA_JUMLAH2 = "harga_jumlahh";
    public static final String COL_HARGA_JUMLAH3 = "harga_jumlahhh";
    public static final String COL_HARGA_JUMLAH4 = "harga_jumlahhhh";
    public static final String COL_HARGA_JUMLAH5 = "harga_jumlahhhhh";
    public static final String COL_HARGA_JUMLAH6 = "harga_jumlahhhhhh";
    public static final String COL_HARGA_TOTAL = "harga_total";

    private SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("PRAGMA foreign_keys=ON");
        db.execSQL("create table " + TABLE_USER + " (" + COL_USERNAME + " TEXT PRIMARY KEY, " + COL_PASSWORD + " TEXT, " + COL_NAME + " TEXT)");
        db.execSQL("create table " + TABLE_BOOK + " (" + COL_ID_BOOK + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_MENU1 + " TEXT, " + COL_MENU2 + " TEXT," + COL_MENU3 + " TEXT, " + COL_MENU4 + " TEXT, " + COL_MENU5 + " TEXT, " + COL_MENU6 + " TEXT," + COL_TANGGAL + " TEXT," + COL_JUMLAH1 + " TEXT, " + COL_JUMLAH2 + " TEXT," + COL_JUMLAH3 + " TEXT, " + COL_JUMLAH4 + " TEXT, " + COL_JUMLAH5 + " TEXT, " + COL_JUMLAH6 + " TEXT)");
        db.execSQL("create table " + TABLE_HARGA + " (" + COL_USERNAME + " TEXT, " + COL_ID_BOOK + " INTEGER, " + COL_HARGA_JUMLAH1 + " TEXT, " + COL_HARGA_JUMLAH2 + " TEXT, " + COL_HARGA_JUMLAH3 + " TEXT," + COL_HARGA_JUMLAH4 + " TEXT," + COL_HARGA_JUMLAH5 + " TEXT," + COL_HARGA_JUMLAH6 + " TEXT," + COL_HARGA_TOTAL + " TEXT, FOREIGN KEY(" + COL_USERNAME + ") REFERENCES " + TABLE_USER + ", FOREIGN KEY(" + COL_ID_BOOK + ") REFERENCES " + TABLE_BOOK + ")");
        db.execSQL("insert into " + TABLE_USER + " values ('arbysudewa12@gmail.com','arby','Arby Sudewa');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public boolean Register(String username, String password, String name) throws SQLException {

        @SuppressLint("Recycle") Cursor mCursor = db.rawQuery("INSERT INTO " + TABLE_USER + "(" + COL_USERNAME + ", " + COL_PASSWORD + ", " + COL_NAME + ") VALUES (?,?,?)", new String[]{username, password, name});
        if (mCursor != null) {
            return mCursor.getCount() > 0;
        }
        return false;
    }

    public boolean Login(String username, String password) throws SQLException {
        @SuppressLint("Recycle") Cursor mCursor = db.rawQuery("SELECT * FROM " + TABLE_USER + " WHERE " + COL_USERNAME + "=? AND " + COL_PASSWORD + "=?", new String[]{username, password});
        if (mCursor != null) {
            return mCursor.getCount() > 0;
        }
        return false;
    }

}