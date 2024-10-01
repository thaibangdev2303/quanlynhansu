package com.btl.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.btl.model.Account;

import java.util.ArrayList;

public class AccountDao {
    DBHelper dbHelper;
    private static final String TABLE_ACCOUNT = "ACCOUNT";
    private static final String KEY_ID_ACCOUNT="idnv";
    private static final String KEY_TEN_ACCOUNT="user";
    private static final String KEY_PASS="password";
    public  AccountDao(Context context){
        dbHelper=new DBHelper(context);
    }
    public void addAcCount(Account acCount){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("idnv",acCount.getIdnv());
        contentValues.put("user",acCount.getUser());
        contentValues.put("password",acCount.getPassword());
        db.insert("ACCOUNT",null,contentValues);
        db.close();
    }
    public  void updateAccount(Account pb){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(KEY_TEN_ACCOUNT, pb.getUser());
        contentValues.put(KEY_PASS, pb.getPassword());

        db.update(TABLE_ACCOUNT,contentValues,KEY_ID_ACCOUNT+ " = ?", new String[]{String.valueOf(pb.getIdnv())});

    }
    public ArrayList<Account> getAllAcCount() {

        ArrayList<Account> accounts = new ArrayList<Account>();
        String selectQuery = "SELECT  * FROM ACCOUNT" ;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Account acCount=new Account();
                acCount.setIdnv(Integer.parseInt(cursor.getString(0)));
                acCount.setUser(cursor.getString(1));
                acCount.setPassword(cursor.getString(2));
                accounts.add(acCount);
            } while (cursor.moveToNext());
        }

        return accounts;
    }
    public ArrayList<Account> getAcCounttouser(String username, String password) {

        ArrayList<Account> accounts = new ArrayList<Account>();
        String sql=String.format("SELECT * FROM ACCOUNT WHERE user ='%s' and password ='%s'",username,password);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                Account acCount=new Account();
                acCount.setIdnv(Integer.parseInt(cursor.getString(0)));
                acCount.setUser(cursor.getString(1));
                acCount.setPassword(cursor.getString(2));
                accounts.add(acCount);
            } while (cursor.moveToNext());
        }

        return accounts;
    }

    public ArrayList<Account> getAllAccount(String s) {

        ArrayList<Account> AccountList = new ArrayList<Account>();
        String selectQuery = "SELECT * FROM " +TABLE_ACCOUNT + " WHERE "
                + KEY_TEN_ACCOUNT + " LIKE '%" + s + "%'";
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Account account=new Account();
                account.setIdnv(Integer.parseInt(cursor.getString(0)));
                account.setUser(cursor.getString(1));
                account.setPassword(cursor.getString(2));

                AccountList.add(account);
            } while (cursor.moveToNext());
        }

        return AccountList;
    }
    public void deleteAccount(int ID) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("ACCOUNT",  " idnv = ?",new String[]{String.valueOf(ID)});
        db.close();
    }
    public boolean isExistsAcCount(Account acCount){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql=String.format("SELECT COUNT(*) FROM ACCOUNT WHERE user ='%s' and password ='%s'",acCount.getUser(),acCount.getPassword());
        Cursor cursor=db.rawQuery(sql,null);
        int count=0;
        if(cursor.moveToNext()){
            count=cursor.getInt(0);
        }
        if(count>0){
            return true;
        }
        return false;

    }
    public boolean isExistsAcCount(String user){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql=String.format("SELECT COUNT(*) FROM ACCOUNT WHERE user ='%s' ",user);
        Cursor cursor=db.rawQuery(sql,null);
        int count=0;
        if(cursor.moveToNext()){
            count=cursor.getInt(0);
        }
        if(count>0){
            return true;
        }
        return false;

    }
    public boolean isManager(Account acCount){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql="SELECT chucvu FROM Employee WHERE id =(select idnv FROM ACCOUNT WHERE user = '"+acCount.getUser()+"')";
        Cursor cursor=db.rawQuery(sql,null);
        String chucvu="";
        if(cursor.moveToNext()){
            chucvu=cursor.getString(0);
        }
        if(chucvu.equals("Quản lý")){
            return true;
        }
        return false;

    }
}
