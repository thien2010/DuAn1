package com.example.duan1nhom2.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1nhom2.DataBase.DataBase;
import com.example.duan1nhom2.Model.KhoanChi;
import com.example.duan1nhom2.Model.KhoanThu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class KhoanChiDAO {
    DataBase dataBase;

    public static final String TABLE_NAME = "khoanchi";
    public static final String COLUMN_IDCHI= "idchi";
    public static final String COLUMN_NAME = "namechi";
    public static final String COLUMN_SOTIEN = "sotienchi";
    public static final String COLUMN_NGAYCHI = "ngaychi";

    public static final String SQL_KHOANCHI = "CREATE TABLE " + TABLE_NAME + " ( " +
            COLUMN_IDCHI + " text primary key, "+ COLUMN_NAME + " text," + COLUMN_SOTIEN + " float," + COLUMN_NGAYCHI + " date);";
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public KhoanChiDAO(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public long insertKhoanChi(KhoanChi khoanChi){
        SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME,khoanChi.getNamechi());
        contentValues.put(COLUMN_SOTIEN,khoanChi.getSotienchi());
        contentValues.put(COLUMN_NGAYCHI,sdf.format(khoanChi.getNgaychi()));

        return sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
    }

    public long updateKhoanChi(KhoanChi khoanChi){
        SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME,khoanChi.getNamechi());
        contentValues.put(COLUMN_SOTIEN,khoanChi.getSotienchi());
        contentValues.put(COLUMN_NGAYCHI,sdf.format(khoanChi.getNgaychi()));

        return sqLiteDatabase.update(TABLE_NAME,contentValues,COLUMN_IDCHI + "=?", new String[]{String.valueOf(khoanChi.getIdchi())});
    }

    public long deleteKhoanChi(KhoanChi khoanChi){
        SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME,COLUMN_IDCHI + "=?", new String[]{String.valueOf(khoanChi.getIdchi())});
    }

    public List<KhoanChi> getAllKhoanChi(){
        SQLiteDatabase sqLiteDatabase = dataBase.getReadableDatabase();
        List<KhoanChi> chiList = new ArrayList<>();

        String select = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(select,null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            KhoanChi khoanChi = new KhoanChi();
            khoanChi.setIdchi(cursor.getInt(cursor.getColumnIndex(COLUMN_IDCHI)));
            khoanChi.setNamechi(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
            khoanChi.setSotienchi(cursor.getFloat(cursor.getColumnIndex(COLUMN_SOTIEN)));
            try {
                khoanChi.setNgaychi((Date) sdf.parse(cursor.getString(cursor.getColumnIndex(COLUMN_NGAYCHI))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            chiList.add(khoanChi);
            cursor.moveToNext();
        }
        cursor.close();
        return chiList;
    }
}
