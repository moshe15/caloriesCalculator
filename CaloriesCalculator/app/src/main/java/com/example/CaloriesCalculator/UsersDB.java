package com.example.CaloriesCalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * The User Data Base class
 */
public class UsersDB extends SQLiteOpenHelper {

    public UsersDB(Context context) {
        super(context, "UsersDB", null, 1);
    }

    /**
     * This method initialize the users data base
     *
     * @param db - the user data base
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table UsersDB (sid text,sname text,sweight text,sheight text,sgender text,sage text)");
        ContentValues values = new ContentValues();
        values.put("sid", "123456");
        values.put("sname", "mor yemin");
        values.put("sweight", "60");
        values.put("sheight", "170");
        values.put("sgender", "female");
        values.put("sage", "20");
        db.insert("UsersDB", null, values);

        values.put("sid", "112233");
        values.put("sname", "mor mor");
        values.put("sweight", "60");
        values.put("sheight", "170");
        values.put("sgender", "female");
        values.put("sage", "20");
        db.insert("UsersDB", null, values);

    }

    /**
     * That method is called when version of DB changed
     *
     * @param db         - the user data base
     * @param oldVersion - the old version of the data base
     * @param newVersion - the new version of the data base
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists UsersDB");
        onCreate(db);

    }

    /**
     * The method returns all the users from the data base
     *
     * @return Cursor of all the users in the DB
     */
    public Cursor allusers() {
        SQLiteDatabase db = this.getReadableDatabase();
        String pro[] = {"sid", "sname", "sweight", "sheight", "sgender", "sage"};
        Cursor c = db.query("UsersDB", pro, null, null, null, null, null);
        c.moveToPosition(0);
        Log.i("value of the id in db1", c.getString(0));
        return c;
    }

}