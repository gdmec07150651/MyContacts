package com.s07150651.mycontacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by LH on 2016/10/24.
 */
public class MyDB extends SQLiteOpenHelper {
    private static String DB_NAME = "My_DB.db";
    private static int DB_VERSION = 2;
    private SQLiteDatabase db;
    public MyDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        db=getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public SQLiteDatabase openConnecting(){
        if (!db.isOpen()){
            db = getWritableDatabase();
        }return db;
    }

    public void closeConnection(){
        try {
            if (db!=null&&db.isOpen()){
                db.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean createTable(String createTableSql){
        try {
            openConnecting();
            db.execSQL(createTableSql);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean save(String tableName, ContentValues values){
        try {
            openConnecting();
            db.insert(tableName,null,values);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            closeConnection();
        }
        return true;
    }

    public boolean update(String table, ContentValues values,String whereClsuse,String[] whereArgs){
        try {
            openConnecting();
            db.update(table,values,whereClsuse,whereArgs);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            closeConnection();
        }
        return true;
    }

    public boolean dalete(String table,String daleteSql,String obj[]){
        try {
            openConnecting();
            db.delete(table,daleteSql,obj);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            closeConnection();
        }
        return true;
    }


    public Cursor find(String findSql,String obj[]){
        try {
           openConnecting();
            Cursor cursor = db.rawQuery(findSql,obj);
            return cursor;
            }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean isTableExits(String tableName){
        try {
            openConnecting();
            String str = "select count(*)Xcount from"+tableName;
           db.rawQuery(str,null).close();;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            closeConnection();
        }
        return true;
    }








}
