package com.example.duan1nhom2.DAO;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duan1nhom2.DataBase.DataBase;
import com.example.duan1nhom2.Model.NguoiDung;

import java.util.ArrayList;
import java.util.List;

public class NguoiDungDAO {
    DataBase dataBase;
    public static final String TABLE_NAME = "nguoidung";
    public static final String COLUMN_Username = "Username";
    public static final String COLUMN_Password = "Password";
    public static final String COLUMN_Phone = "Phone";
    public static final String COLUMN_Email = "Email";
    public static final String COLUMN_Fullname = "Fullname";
    public static final String SQL_NGUOIDUNG = "CREATE TABLE " + TABLE_NAME + " ( " +
            COLUMN_Username + " TEXT PRIMARY KEY, " + COLUMN_Password + " text, " + COLUMN_Phone + " TEXT, " + COLUMN_Fullname + " TEXT, " + COLUMN_Email + " TEXT)";

    public NguoiDungDAO(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public long insertNguoiDung(NguoiDung nguoiDung) {
        SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_Username, nguoiDung.getUsername());
        contentValues.put(COLUMN_Password, nguoiDung.getPassword());
        contentValues.put(COLUMN_Phone, nguoiDung.getPhone());
        contentValues.put(COLUMN_Fullname, nguoiDung.getFullname());
        contentValues.put(COLUMN_Email, nguoiDung.getEmail());
        return sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    public long updateNguoiDung(NguoiDung nguoiDung) {
        SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_Username, nguoiDung.getUsername());
        contentValues.put(COLUMN_Password, nguoiDung.getPassword());
        contentValues.put(COLUMN_Phone, nguoiDung.getPhone());
        contentValues.put(COLUMN_Email, nguoiDung.getEmail());
        contentValues.put(COLUMN_Fullname, nguoiDung.getFullname());
        return sqLiteDatabase.update(TABLE_NAME, contentValues, COLUMN_Username + "=?", new String[]{nguoiDung.getUsername()});
    }

    public List<NguoiDung> getAllNguoiDung() {
        SQLiteDatabase sqLiteDatabase = dataBase.getReadableDatabase();
        List<NguoiDung> nguoiDungs = new ArrayList<>();
        String select = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(select, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            NguoiDung nguoiDung = new NguoiDung();
            nguoiDung.setUsername(cursor.getString(cursor.getColumnIndex(COLUMN_Username)));
            nguoiDung.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_Password)));
            nguoiDung.setPhone(cursor.getString(cursor.getColumnIndex(COLUMN_Phone)));
            nguoiDung.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_Email)));
            nguoiDung.setFullname(cursor.getString(cursor.getColumnIndex(COLUMN_Fullname)));
            nguoiDungs.add(nguoiDung);
            cursor.moveToNext();
        }
        cursor.close();
        return nguoiDungs;
    }
}

