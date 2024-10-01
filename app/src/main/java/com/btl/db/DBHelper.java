package com.btl.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.btl.model.Account;
import com.btl.model.ChamCong;
import com.btl.model.Employee;
import com.btl.model.Luong;
import com.btl.model.PhongBan;

import java.util.ArrayList;
import java.util.Date;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION =33;
    private static final String DATABASE_NAME = "EmployeeManager";
    private static final String TABLE_Employee = "Employee";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_ADDRES = "address";
    private static final String KEY_GT = "gioitinh";
    private static final String KEY_PHONE = "phone_number";
    private static final String KEY_CHUCVU = "chucvu";
    private static final String KEY_PHONGBAN = "id_pb";
    private static final String KEY_BACLUONG = "id_bl";

    private static final String TABLE_BACLUONG = "BacLuong";
    private static final String KEY_ID_BACLUONG="idbacluong";
    private static final String KEY_LCB_BACLUONG="LCB";
    private static final String KEY_HSL_BACLUONG="HSL";
    private static final String KEY_PHUCAP="PC";

    private static final String TABLE_PHONGBAN = "PhongBan";
    private static final String KEY_ID_PHONGBAN="idphongban";
    private static final String KEY_TEN_PHONGBAN="tenpb";
    private static final String KEY_DIADIEM_PHONGBAN="diadiem";

    private static final String TABLE_ACCOUNT = "ACCOUNT";
    private static final String KEY_ID_ACCOUNT="idnv";
    private static final String KEY_TEN_ACCOUNT="user";
    private static final String KEY_PASS="password";

    private static final String TABLE_CHAMCONG = "chamcong";
    private static final String KEY_ID_CHAMCONG="id_cc";
    private static final String KEY_NHANVIEN="idnv";
    private static final String KEY_THOIGIAN="thoigian";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_Employee_TABLE = "CREATE TABLE " + TABLE_Employee +"(" + KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME +" TEXT ," + KEY_GT + " TEXT," + KEY_ADDRES+ " TEXT," + KEY_PHONE+ " TEXT, " + KEY_CHUCVU + " TEXT ,"
                + KEY_PHONGBAN +" INTEGER, " + KEY_BACLUONG + " INTEGER," +
                " FOREIGN KEY( " + KEY_PHONGBAN + " ) REFERENCES " + TABLE_PHONGBAN + " ( " + KEY_ID_PHONGBAN + "), " +
                " FOREIGN KEY( " + KEY_BACLUONG + " ) REFERENCES " + TABLE_BACLUONG + " ( " + KEY_ID_BACLUONG + "))";

        String create_accCount="CREATE TABLE " + TABLE_ACCOUNT + "(" + KEY_ID_ACCOUNT + " INTEGER PRIMARY KEY  AUTOINCREMENT," + KEY_TEN_ACCOUNT+ " TEXT ," + KEY_PASS+ " TEXT, FOREIGN KEY(" + KEY_ID_ACCOUNT+ ") REFERENCES "+ TABLE_Employee + "(" + KEY_ID + ") )";

        String CREATE_BACLUONG_TABLE ="CREATE TABLE " + TABLE_BACLUONG + "(" +KEY_ID_BACLUONG +" INTEGER PRIMARY KEY  AUTOINCREMENT, "+KEY_LCB_BACLUONG +" FLOAT, "
                +KEY_HSL_BACLUONG +" FLOAT, "+KEY_PHUCAP+" FLOAT)";

        String CREATE_PHONGBAN_TABLE="CREATE TABLE "+ TABLE_PHONGBAN + "(" +KEY_ID_PHONGBAN +" INTEGER PRIMARY KEY  AUTOINCREMENT, "+KEY_TEN_PHONGBAN +" TEXT, "
                +KEY_DIADIEM_PHONGBAN +" TEXT)";

        String CREATE_CHAMCONG_TABLE = "CREATE TABLE " + TABLE_CHAMCONG + "( "+ KEY_ID_CHAMCONG + " INTEGER PRIMARY KEY  AUTOINCREMENT," + KEY_NHANVIEN + " INTEGER," + KEY_THOIGIAN + " TEXT ,FOREIGN KEY(" + KEY_NHANVIEN+ ") REFERENCES "+ TABLE_Employee + "(" + KEY_ID + "))";

        db.execSQL(create_accCount);
        db.execSQL(CREATE_PHONGBAN_TABLE);
        db.execSQL(CREATE_BACLUONG_TABLE);
        db.execSQL(CREATE_Employee_TABLE);
        db.execSQL(CREATE_CHAMCONG_TABLE);

    }
    @Override
    public void onConfigure(SQLiteDatabase db){
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_ACCOUNT);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_CHAMCONG);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_Employee);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_BACLUONG);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_PHONGBAN);

        onCreate(db);
    }
}
