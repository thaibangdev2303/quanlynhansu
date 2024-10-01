package com.btl.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.btl.model.ChamCong;

import java.util.ArrayList;
import java.util.Date;

public class ChamCongDao {
    private static final String TABLE_CHAMCONG = "chamcong";
    private static final String KEY_ID_CHAMCONG="id_cc";
    private static final String KEY_NHANVIEN="idnv";
    private static final String KEY_THOIGIAN="thoigian";
    DBHelper dbHelper;
    public ChamCongDao(Context context){
        dbHelper=new DBHelper(context);
    }
    public void addChamcong(ChamCong chamcong) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NHANVIEN, chamcong.getIDNV());
        values.put(KEY_THOIGIAN, chamcong.getDate());
        db.insert(TABLE_CHAMCONG, null, values);
        db.close();
    }
    public ArrayList<ChamCong> getallchamcongtoid(int id, String month) {
        Date d=new Date();
        int year=d.getYear()+1900;
        ArrayList<ChamCong> chamCongs = new ArrayList<ChamCong>();
        String sql="SELECT * FROM " + TABLE_CHAMCONG+ " WHERE "+ KEY_NHANVIEN +" ='"+id+"'"  +
                "and strftime('%m-%Y','now')='"+month+"-"+year+"' ORDER BY thoigian DESC " ;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                ChamCong chamcong = new ChamCong();
                chamcong.setIDCC(Integer.parseInt(cursor.getString(0)));
                chamcong.setIDNV(Integer.parseInt(cursor.getString(1)));
                chamcong.setDate(cursor.getString(2));
                chamCongs.add(chamcong);
            } while (cursor.moveToNext());
        }

        return chamCongs;
    }
    public boolean isNotDate(String date, int id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql=String.format("SELECT COUNT(*) FROM " + TABLE_CHAMCONG+ " WHERE "+ KEY_THOIGIAN +" ='%s' AND " + KEY_NHANVIEN +" = %d",date, id);
        Cursor cursor=db.rawQuery(sql,null);
        int count=0;
        if(cursor.moveToNext()){
            count=cursor.getInt(Integer.parseInt("0"));
        }
        if(count>0){
            return false;
        }
        return true;

    }
    public int getallsongaycong(int id,String month) {
        Date d=new Date();
        int year=d.getYear()+1900;
        String sql="SELECT COUNT(*)  FROM " + TABLE_CHAMCONG+ " WHERE "+ KEY_NHANVIEN +" ="+id+" and strftime('%m-%Y',thoigian)='"+month+"-"+year+"'";
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        int count=0;
        if(cursor.moveToNext()){
            count=cursor.getInt(Integer.parseInt("0"));
        }

        return count;
    }
}
