package com.btl.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.btl.model.Luong;

import java.util.ArrayList;

public class LuongDao {
    private static final String TABLE_BACLUONG = "BacLuong";
    private static final String KEY_ID_BACLUONG="idbacluong";
    private static final String KEY_LCB_BACLUONG="LCB";
    private static final String KEY_HSL_BACLUONG="HSL";
    private static final String KEY_PHUCAP="PC";
    DBHelper dbHelper;
    public LuongDao(Context context){
        dbHelper=new DBHelper(context);
    }
    public ArrayList<Luong> getAllBacLuong(){
        ArrayList<Luong> Luongs = new ArrayList<Luong>();
        String selectQuery = "SELECT  * FROM "+TABLE_BACLUONG ;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Luong luong=new Luong();
                luong.setIdBacLuong(Integer.parseInt(cursor.getString(0)));
                luong.setLuongCoBan(Float.parseFloat(cursor.getString(1)));
                luong.setHeSoLuong(Float.parseFloat(cursor.getString(2)));
                luong.setHeSoPhuCap(Float.parseFloat(cursor.getString(3)));
                Luongs.add(luong);
            } while (cursor.moveToNext());
        }
        return Luongs;
    }
    public void addBacLuong(Luong luong){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_LCB_BACLUONG, luong.getLuongCoBan());
        values.put(KEY_HSL_BACLUONG, luong.getHeSoLuong());
        values.put(KEY_PHUCAP,luong.getHeSoPhuCap());
        db.insert(TABLE_BACLUONG, null, values);
        db.close();
    }
    public void updateBacLuong(Luong luong){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_LCB_BACLUONG, luong.getLuongCoBan());
        values.put(KEY_HSL_BACLUONG, luong.getHeSoLuong());
        values.put(KEY_PHUCAP,luong.getHeSoPhuCap());
        db.update(TABLE_BACLUONG, values, "idbacluong = ?",new String[]{String.valueOf(luong.getIdBacLuong())});
        db.close();
    }
    public int deleteBacLuong(int id){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        try {
            db.delete(TABLE_BACLUONG,  " idbacluong = ?",new String[]{String.valueOf(id)});
            db.close();
        }
        catch (Exception e){
            return 0;
        }
        db.close();
        return 1;

    }
    public Luong getLuong(int id){
        String sql="SELECT * FROM "+TABLE_BACLUONG +" WHERE "+ KEY_ID_BACLUONG +" = "+id ;
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        Cursor cursor=db.rawQuery(sql,null);
        Luong luong=new Luong();
        if(cursor.moveToNext()){
            luong.setIdBacLuong(cursor.getInt(0));
            luong.setLuongCoBan(cursor.getInt(1));
            luong.setHeSoLuong(cursor.getInt(2));
            luong.setHeSoPhuCap(cursor.getInt(3));
        }
        return luong ;
    }
}
