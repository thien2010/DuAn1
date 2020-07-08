package com.example.duan1nhom2.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1nhom2.DataBase.DataBase;
import com.example.duan1nhom2.Model.KhoanThu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class KhoanThuDAO {
    DataBase dataBase;

    public static final String TABLE_NAME = "khoanthu";
    public static final String COLUMN_IDTHU = "idthu";
    public static final String COLUMN_NAME = "namethu";
    public static final String COLUMN_SOTIEN = "sotienthu";
    public static final String COLUMN_NGAYTHU = "ngaythu";

    public static final String SQL_KHOANTHU = "CREATE TABLE " + TABLE_NAME + " ( " +
            COLUMN_IDTHU + " text primary key, " + COLUMN_NAME + " text, " + COLUMN_SOTIEN + " float, " + COLUMN_NGAYTHU + " date)";

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public KhoanThuDAO(DataBase dataBase) {
        this.dataBase = dataBase;
    }


    public long insertKhoanThu(KhoanThu khoanThu) {
        SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, khoanThu.getNamethu());
        contentValues.put(COLUMN_SOTIEN, khoanThu.getSotien());
        contentValues.put(COLUMN_NGAYTHU,sdf.format(khoanThu.getNgaythu()));
        return sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }



    public long updateKhoanThu(KhoanThu khoanThu) {
        SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, khoanThu.getNamethu());
        contentValues.put(COLUMN_SOTIEN, khoanThu.getSotien());
        contentValues.put(COLUMN_NGAYTHU, sdf.format(khoanThu.getNgaythu()));

        return sqLiteDatabase.update(TABLE_NAME, contentValues, COLUMN_IDTHU + "=?", new String[]{String.valueOf(khoanThu.getIdthu())});
    }

    public long deleteKhoanThu(KhoanThu khoanThu) {
        SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME, COLUMN_IDTHU + "=?", new String[]{String.valueOf(khoanThu.getIdthu())});
    }

    public List<KhoanThu> getAllKhoanThu() {
        SQLiteDatabase sqLiteDatabase = dataBase.getReadableDatabase();
        List<KhoanThu> thuList = new ArrayList<>();
        String select = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(select, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            KhoanThu khoanThu = new KhoanThu();
            khoanThu.setIdthu(cursor.getInt(cursor.getColumnIndex(COLUMN_IDTHU)));
            khoanThu.setNamethu(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
            khoanThu.setSotien(cursor.getFloat(cursor.getColumnIndex(COLUMN_SOTIEN)));
            try {
                khoanThu.setNgaythu((Date) sdf.parse(cursor.getString(cursor.getColumnIndex(COLUMN_NGAYTHU))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            thuList.add(khoanThu);
            cursor.moveToNext();
        }
        cursor.close();
        return thuList;
    }
}
