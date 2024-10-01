package com.btl.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.btl.model.PhongBan;

import java.util.ArrayList;

public class PhongBanDao {
    DBHelper dbHelper;
    private static final String TABLE_PHONGBAN = "PhongBan";
    private static final String KEY_ID_PHONGBAN="idphongban";
    private static final String KEY_TEN_PHONGBAN="tenpb";
    private static final String KEY_DIADIEM_PHONGBAN="diadiem";
    public PhongBanDao(Context context){
        dbHelper=new DBHelper(context);
    }
    public ArrayList<PhongBan> getAllPhongBan(){
        ArrayList<PhongBan> pb = new ArrayList<>();
        //cau lenh query
        String selectQuery = "SELECT  * FROM "+TABLE_PHONGBAN;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                PhongBan phongBan =new PhongBan();
                phongBan.setIdPB(Integer.parseInt(cursor.getString(0)));
                phongBan.setTenPB(cursor.getString(1));
                phongBan.setDiaDiem(cursor.getString(2));
                pb.add(phongBan);
            } while (cursor.moveToNext());
        }
        return pb;
    }
    public ArrayList<PhongBan>getPhongBanName(int $id){
        ArrayList<PhongBan> pb = new ArrayList<>();
        //cau lenh query
        String selectQuery = "SELECT  * FROM "+TABLE_PHONGBAN +" WHERE " + KEY_ID_PHONGBAN+ " = " + $id;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                PhongBan phongBan =new PhongBan();
                phongBan.setIdPB(Integer.parseInt(cursor.getString(0)));
                phongBan.setTenPB(cursor.getString(1));
                phongBan.setDiaDiem(cursor.getString(2));
                pb.add(phongBan);
            } while (cursor.moveToNext());
        }
        return pb;
    }

    public void addPhongBan(PhongBan phongBan){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(KEY_TEN_PHONGBAN,phongBan.getTenPB());
        contentValues.put(KEY_DIADIEM_PHONGBAN,phongBan.getDiaDiem());
        db.insert(TABLE_PHONGBAN,null,contentValues);
        db.close();
    }

    public PhongBan getPhongBan(int id_pb){
        String selectQuery = "SELECT * FROM PhongBan where idphongban ="+id_pb ;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        PhongBan phongBan=new PhongBan();

        if (cursor.moveToFirst()) {
            do {
                phongBan.setIdPB(Integer.parseInt(cursor.getString(0)));
                phongBan.setTenPB(cursor.getString(1));
                phongBan.setDiaDiem(cursor.getString(2));
            } while (cursor.moveToNext());
        }

        return phongBan;
    }

    public int deletePhongBan(int idPB) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            db.delete("PhongBan",  " idphongban = ?",new String[]{String.valueOf(idPB)});
        }
        catch (Exception e){
            return 0;
        }
        db.close();
        return 1;

    }
    public  void updatePhongBan(PhongBan pb){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(KEY_TEN_PHONGBAN,pb.getTenPB());
        contentValues.put(KEY_DIADIEM_PHONGBAN,pb.getDiaDiem());

        db.update(TABLE_PHONGBAN,contentValues,"idphongban = ?", new String[]{String.valueOf(pb.getIdPB())});

    }
}
