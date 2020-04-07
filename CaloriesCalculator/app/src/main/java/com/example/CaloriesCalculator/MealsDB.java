package com.example.CaloriesCalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The Meals Data Base class
 */
public class MealsDB extends SQLiteOpenHelper {


    public MealsDB(Context context) {
        super(context, "Meals1", null, 1);
    }

    /**
     * This method initialize the meals data base
     *
     * @param database - the meals data base
     */
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL("create table Meals1(IdUser text,MealTime text,nameOfFood text,Date text,Calories text)");
        ContentValues values = new ContentValues();
    }

    /**
     * That method is called when version of DB changed
     *
     * @param db         - the meals data base
     * @param oldVersion - the old version of the data base
     * @param newVersion - the new version of the data base
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Meals1");
        onCreate(db);
    }

    /**
     * The method gets a user id and returns all his meals from today
     *
     * @param idUser - the id of a specific user
     * @return string of ListOfBreakfast, ListOfLunch,  ListOfDinner, ListOfSnacks
     */
    public String allMealsPerUserTime(final String idUser) {
        SQLiteDatabase dbMeals = this.getReadableDatabase();
        String pro[] = {"IdUser", "MealTime", "nameOfFood", "Date"};
        Cursor c = dbMeals.query("Meals1", pro, null, null, null, null, null);
        c.moveToPosition(0);
        String listOfBreakfast = "";
        String listOfLunch = "";
        String listOfDinner = "";
        String listOfSnacks = "";
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();

        try {
            if (c.isNull(0) == false) {
                Log.i("firstmeal1", c.getString(0) + " " + c.getString(1) + " " + c.getString(2) + " " + c.getString(3));
                Log.i("id user", idUser);
                Log.i("time", dateFormat.format(date));
                if (c.getString(0).equals(idUser) && c.getString(3).equals(dateFormat.format(date))) {
                    Log.i("firstmeal2", c.getString(0) + " " + c.getString(1) + " " + c.getString(2) + " " + c.getString(3));
                    switch (c.getString(1)) {
                        case "Breakfast":
                            listOfBreakfast = listOfBreakfast + c.getString(2) + ",";
                            break;
                        case "Lunch":
                            listOfLunch = listOfLunch + c.getString(2) + ",";
                            break;
                        case "Dinner":
                            listOfDinner = listOfDinner + c.getString(2) + ",";
                            break;
                        case "Snacks":
                            listOfSnacks = listOfSnacks + c.getString(2) + ",";
                            break;
                    }
                }
                while (c.moveToNext()) {
                    Log.i("firstmealbeforif", c.getString(0) + " " + c.getString(1) + " " + c.getString(2) + " " + c.getString(3));
                    if (c.getString(0).equals(idUser) && c.getString(3).equals(dateFormat.format(date))) {
                        Log.i("firstmealinwhile", c.getString(0) + " " + c.getString(1) + " " + c.getString(2) + " " + c.getString(3));
                        switch (c.getString(1)) {
                            case "Breakfast":
                                listOfBreakfast = listOfBreakfast + c.getString(2) + ",";
                                break;
                            case "Lunch":
                                listOfLunch = listOfLunch + c.getString(2) + ",";
                                break;
                            case "Dinner":
                                listOfDinner = listOfDinner + c.getString(2) + ",";
                                break;
                            case "Snacks":
                                listOfSnacks = listOfSnacks + c.getString(2) + ",";
                                break;
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
        Log.i("break", listOfBreakfast);
        Log.i("lunch", listOfLunch);
        Log.i("dinner", listOfDinner);
        Log.i("snacks", listOfSnacks);
        return listOfBreakfast + "?" + listOfLunch + "?" + listOfDinner + "?" + listOfSnacks;
    }

    /**
     * The method gets a user id and returns the number of calories he ate today
     *
     * @param idUser - id of a specific user
     * @return Calories - string of the number of calories the user ate until now
     */
    public String caloriesAllMealsPerDay(final String idUser) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();

        SQLiteDatabase dbMeals = this.getReadableDatabase();
        String pro[] = {"IdUser", "Date", "Calories"};
        Cursor c = dbMeals.query("Meals1", pro, null, null, null, null, null);
        c.moveToPosition(0);
        int calories = 0;
        try {
            if (c.isNull(0) == false) {
                if (c.getString(0).equals(idUser) && c.getString(1).equals(dateFormat.format(date))) {
                    calories = calories + Integer.parseInt(c.getString(2));
                }
                while (c.moveToNext()) {
                    if (c.getString(0).equals(idUser) && c.getString(1).equals(dateFormat.format(date))) {
                        calories = calories + Integer.parseInt(c.getString(2));
                    }
                }

            }
        } catch (Exception e) {
        }
        return Integer.toString(calories);
    }

}