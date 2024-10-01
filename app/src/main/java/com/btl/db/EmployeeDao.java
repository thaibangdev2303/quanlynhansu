package com.btl.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.btl.model.Employee;

import java.util.ArrayList;

public class EmployeeDao {
    DBHelper dbHelper;
    private static final String TABLE_Employee="Employee";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_ADDRES = "address";
    private static final String KEY_GT = "gioitinh";
    private static final String KEY_PHONE = "phone_number";
    private static final String KEY_CHUCVU = "chucvu";
    private static final String KEY_PHONGBAN = "id_pb";
    private static final String KEY_BACLUONG = "id_bl";
    public EmployeeDao(Context context){
         dbHelper=new DBHelper(context);
    }
    public ArrayList<Employee> getAllEmployee() {

        ArrayList<Employee> EmployeeList = new ArrayList<Employee>();
        String selectQuery = "SELECT  * FROM "+TABLE_Employee ;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Employee e=new Employee();
                e.setId(Integer.parseInt(cursor.getString(0)));
                e.setName(cursor.getString(1));
                e.setGioiTinh(cursor.getString(2));
                e.setAddr(cursor.getString(3));
                e.setSdt(cursor.getString(4));
                e.setChucVu(cursor.getString(5));
                e.setIDPB(Integer.parseInt(cursor.getString(6)));
                e.setId_bacluong(Integer.parseInt(cursor.getString(7)));

                EmployeeList.add(e);

            } while (cursor.moveToNext());
        }

        return EmployeeList;
    }
    public ArrayList<Employee> getEmployeeNotAC() {

        ArrayList<Employee> EmployeeList = new ArrayList<Employee>();
        String selectQuery = "SELECT  * FROM " + TABLE_Employee + " where id not in(select idnv FROM ACCOUNT)" ;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Employee e=new Employee();
                e.setId(Integer.parseInt(cursor.getString(0)));
                e.setName(cursor.getString(1));
                e.setGioiTinh(cursor.getString(2));
                e.setAddr(cursor.getString(3));
                e.setSdt(cursor.getString(4));
                e.setChucVu(cursor.getString(5));
                e.setIDPB(Integer.parseInt(cursor.getString(6)));

                EmployeeList.add(e);
            } while (cursor.moveToNext());
        }
        return EmployeeList;
    }
    public ArrayList<Employee> getAllEmployee(String s) {

        ArrayList<Employee> EmployeeList = new ArrayList<Employee>();
        String selectQuery = "SELECT * FROM " + TABLE_Employee + " WHERE "
                + KEY_NAME + " LIKE '%" + s + "%' OR "+KEY_ADDRES+" LIKE '%"+s+"%' OR "+KEY_CHUCVU+" LIKE '%"+s+"%' OR "+
                KEY_PHONE +" LIKE '%"+s+"%' OR "+KEY_GT +" LIKE '%"+s+"'" +
                " OR "+KEY_PHONGBAN +" LIKE '%"+s+"' OR "+KEY_BACLUONG +" LIKE '%"+s+"'";
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Employee e=new Employee();
                e.setId(Integer.parseInt(cursor.getString(0)));
                e.setName(cursor.getString(1));
                e.setGioiTinh(cursor.getString(2));
                e.setAddr(cursor.getString(3));
                e.setSdt(cursor.getString(4));
                e.setChucVu(cursor.getString(5));

                EmployeeList.add(e);
            } while (cursor.moveToNext());
        }

        return EmployeeList;
    }
    public void addEmployee(Employee employee) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, employee.getName());
        values.put(KEY_GT, employee.getGioiTinh());
        values.put(KEY_ADDRES, employee.getAddr());
        values.put(KEY_PHONE,employee.getSdt());
        values.put(KEY_CHUCVU,employee.getChucVu());
        values.put(KEY_PHONGBAN, employee.getIDPB());
        values.put(KEY_BACLUONG, employee.getId_bacluong());

        db.insert(TABLE_Employee, null, values);
        db.close();
    }
    public int deleteEmployee(int ID) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            db.delete(TABLE_Employee, KEY_ID + " = ?",new String[]{String.valueOf(ID)});
        }
        catch (Exception e){
            return 0;
        }
        db.close();
        return 1;
    }
    public  void updateEmployee(Employee employee){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(KEY_NAME,employee.getName());
        contentValues.put(KEY_GT,employee.getGioiTinh());
        contentValues.put(KEY_ADDRES,employee.getAddr());
        contentValues.put(KEY_PHONE,employee.getSdt());
        contentValues.put(KEY_CHUCVU,employee.getChucVu());
        contentValues.put(KEY_PHONGBAN, employee.getIDPB());
        contentValues.put(KEY_BACLUONG, employee.getId_bacluong());

        db.update(TABLE_Employee,contentValues,"id = ?", new String[]{String.valueOf(employee.getId())});

    }
    public Employee getEmployee(int idnv) {
        String selectQuery = "SELECT * FROM Employee where id ="+idnv ;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Employee e=new Employee();

        if (cursor.moveToFirst()) {
            do {

                e.setId(Integer.parseInt(cursor.getString(0)));
                e.setName(cursor.getString(1));
                e.setGioiTinh(cursor.getString(2));
                e.setAddr(cursor.getString(3));
                e.setSdt(cursor.getString(4));
                e.setChucVu(cursor.getString(5));

            } while (cursor.moveToNext());
        }

        return e;
    }
}
