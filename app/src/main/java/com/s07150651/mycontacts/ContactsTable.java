package com.s07150651.mycontacts;

import android.content.ContentValues;
import android.content.Context;

/**
 * Created by LH on 2016/10/24.
 */
public class ContactsTable {
    private final static String TABLENAME = "contactsTable";
   private MyDB db;

    public ContactsTable(Context context) {
        db = new MyDB(context);
        if (!db.isTableExits(TABLENAME)){
            String createTableSql = "CREATE TABLE IF NOT EXISTS" +TABLENAME+"(id_DB integer"+
             "primary key AUTOINCREMENT,"+
              User.NAME+"VARCHAR,"+
              User.MOBLIE+"VARCHAR,"+
              User.QQ+"VARCHAR,"+
              User.ADDRESS+"VARCHAR)";
               db.createTable(createTableSql);
        }

    }
    public boolean addData(User user){
        ContentValues values = new ContentValues();
        values.put(User.NAME,user.getName());
        values.put(User.MOBLIE,user.getMoblie());
        values.put(User.DANWEI,user.getDanwei());
        values.put(User.QQ,user.getQq());
        values.put(User.ADDRESS,user.getAddress());
        return db.save(TABLENAME,values);
    }
}
