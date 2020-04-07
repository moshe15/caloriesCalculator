package com.example.CaloriesCalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * The Food Data Base class
 */

public class FoodDB extends SQLiteOpenHelper {

    public FoodDB(Context context) {
        super(context, "Food", null, 1);
    }

    /**
     * This method initialize the food data base
     *
     * @param database - the food data base
     */
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL("create table Food(IdFood text,nameOfFood text, IdCategory text, Calories text, gr text, image text)");
        ContentValues values = new ContentValues();

        values.put("IdFood", "123451");
        values.put("nameOfFood", "tomato");
        values.put("IdCategory", "vegetables");
        values.put("Calories", "78");
        values.put("gr", "100");
        database.insert("Food", null, values);

        values.put("IdFood", "123452");
        values.put("nameOfFood", "cucumber");
        values.put("IdCategory", "vegetables");
        values.put("Calories", "60");
        values.put("gr", "100");
        database.insert("Food", null, values);

        values.put("IdFood", "123453");
        values.put("nameOfFood", "banana");
        values.put("IdCategory", "fruit");
        values.put("Calories", "90");
        values.put("gr", "100");
        database.insert("Food", null, values);

        values.put("IdFood", "123454");
        values.put("nameOfFood", "apple");
        values.put("IdCategory", "fruit");
        values.put("Calories", "80");
        values.put("gr", "100");
        database.insert("Food", null, values);

        values.put("IdFood", "123455");
        values.put("nameOfFood", "Chicken");
        values.put("IdCategory", "meat");
        values.put("Calories", "250");
        values.put("gr", "100");
        database.insert("Food", null, values);

        values.put("IdFood", "123456");
        values.put("nameOfFood", "meat ball");
        values.put("IdCategory", "meat");
        values.put("Calories", "350");
        values.put("gr", "100");
        database.insert("Food", null, values);

        values.put("IdFood", "123457");
        values.put("nameOfFood", "cheese");
        values.put("IdCategory", "milk");
        values.put("Calories", "600");
        values.put("gr", "100");
        database.insert("Food", null, values);

        values.put("IdFood", "123458");
        values.put("nameOfFood", "cream cheese");
        values.put("IdCategory", "milk");
        values.put("Calories", "200");
        values.put("gr", "100");
        database.insert("Food", null, values);

        values.put("IdFood", "123459");
        values.put("nameOfFood", "yogurt");
        values.put("IdCategory", "milk");
        values.put("Calories", "133");
        values.put("gr", "100");
        database.insert("Food", null, values);

        values.put("IdFood", "123460");
        values.put("nameOfFood", "Yogurt pro");
        values.put("IdCategory", "milk");
        values.put("Calories", "170");
        values.put("gr", "100");
        database.insert("Food", null, values);

        values.put("IdFood", "123461");
        values.put("nameOfFood", "White cheese");
        values.put("IdCategory", "milk");
        values.put("Calories", "210");
        values.put("gr", "100");
        database.insert("Food", null, values);

        values.put("IdFood", "123462");
        values.put("nameOfFood", "Mozzarella");
        values.put("IdCategory", "milk");
        values.put("Calories", "250");
        values.put("gr", "100");
        database.insert("Food", null, values);

        values.put("IdFood", "123463");
        values.put("nameOfFood", "Mascarpone");
        values.put("IdCategory", "milk");
        values.put("Calories", "400");
        values.put("gr", "100");
        database.insert("Food", null, values);

        values.put("IdFood", "123464");
        values.put("nameOfFood", "eggplant");
        values.put("IdCategory", "vegetables");
        values.put("Calories", "70");
        values.put("gr", "100");
        database.insert("Food", null, values);

        values.put("IdFood", "123465");
        values.put("nameOfFood", "lettuce");
        values.put("IdCategory", "vegetables");
        values.put("Calories", "25");
        values.put("gr", "100");
        database.insert("Food", null, values);

        values.put("IdFood", "123466");
        values.put("nameOfFood", "pepper");
        values.put("IdCategory", "vegetables");
        values.put("Calories", "66");
        values.put("gr", "100");
        database.insert("Food", null, values);

        values.put("IdFood", "123467");
        values.put("nameOfFood", "cabbage");
        values.put("IdCategory", "vegetables");
        values.put("Calories", "30");
        values.put("gr", "100");
        database.insert("Food", null, values);

        values.put("IdFood", "123468");
        values.put("nameOfFood", "Onion");
        values.put("IdCategory", "vegetables");
        values.put("Calories", "40");
        values.put("gr", "100");
        database.insert("Food", null, values);

        values.put("IdFood", "123469");
        values.put("nameOfFood", "Carrot");
        values.put("IdCategory", "vegetables");
        values.put("Calories", "85");
        values.put("gr", "100");
        database.insert("Food", null, values);

        values.put("IdFood", "123470");
        values.put("nameOfFood", "Grapes");
        values.put("IdCategory", "fruit");
        values.put("Calories", "85");
        values.put("gr", "100");
        database.insert("Food", null, values);

        values.put("IdFood", "123471");
        values.put("nameOfFood", "Strawberries");
        values.put("IdCategory", "fruit");
        values.put("Calories", "120");
        values.put("gr", "100");
        database.insert("Food", null, values);

        values.put("IdFood", "123472");
        values.put("nameOfFood", "watermelon");
        values.put("IdCategory", "fruit");
        values.put("Calories", "180");
        values.put("gr", "100");
        database.insert("Food", null, values);

        values.put("IdFood", "123473");
        values.put("nameOfFood", "pear");
        values.put("IdCategory", "fruit");
        values.put("Calories", "90");
        values.put("gr", "100");
        database.insert("Food", null, values);

        values.put("IdFood", "123474");
        values.put("nameOfFood", "peach");
        values.put("IdCategory", "fruit");
        values.put("Calories", "100");
        values.put("gr", "100");
        database.insert("Food", null, values);

        values.put("IdFood", "123475");
        values.put("nameOfFood", "coconut");
        values.put("IdCategory", "fruit");
        values.put("Calories", "85");
        values.put("gr", "100");
        database.insert("Food", null, values);

        values.put("IdFood", "123476");
        values.put("nameOfFood", "Kiwi");
        values.put("IdCategory", "fruit");
        values.put("Calories", "90");
        values.put("gr", "100");
        database.insert("Food", null, values);

        values.put("IdFood", "123477");
        values.put("nameOfFood", "persimmon");
        values.put("IdCategory", "fruit");
        values.put("Calories", "110");
        values.put("gr", "100");
        database.insert("Food", null, values);

        values.put("IdFood", "1234578");
        values.put("nameOfFood", "");
        values.put("IdCategory", "meat");
        values.put("Calories", "350");
        values.put("gr", "100");
        database.insert("Food", null, values);

        values.put("IdFood", "1234579");
        values.put("nameOfFood", "hamburger");
        values.put("IdCategory", "meat");
        values.put("Calories", "500");
        values.put("gr", "100");
        database.insert("Food", null, values);

        values.put("IdFood", "1234580");
        values.put("nameOfFood", "Shawarma");
        values.put("IdCategory", "meat");
        values.put("Calories", "600");
        values.put("gr", "100");
        database.insert("Food", null, values);

        values.put("IdFood", "1234581");
        values.put("nameOfFood", "");
        values.put("IdCategory", "meat");
        values.put("Calories", "350");
        values.put("gr", "100");
        database.insert("Food", null, values);

        values.put("IdFood", "1234582");
        values.put("nameOfFood", "kebab");
        values.put("IdCategory", "meat");
        values.put("Calories", "350");
        values.put("gr", "100");
        database.insert("Food", null, values);

        values.put("IdFood", "1234583");
        values.put("nameOfFood", "hot dog");
        values.put("IdCategory", "meat");
        values.put("Calories", "250");
        values.put("gr", "100");
        database.insert("Food", null, values);

        values.put("IdFood", "1234584");
        values.put("nameOfFood", "chicken liver");
        values.put("IdCategory", "meat");
        values.put("Calories", "330");
        values.put("gr", "100");
        database.insert("Food", null, values);

        values.put("IdFood", "1234585");
        values.put("nameOfFood", "chicken breast");
        values.put("IdCategory", "meat");
        values.put("Calories", "280");
        values.put("gr", "100");
        database.insert("Food", null, values);

        values.put("IdFood", "1234586");
        values.put("nameOfFood", "Lamb chops");
        values.put("IdCategory", "meat");
        values.put("Calories", "480");
        values.put("gr", "100");
        database.insert("Food", null, values);

        values.put("IdFood", "1234587");
        values.put("nameOfFood", "Chicken patties");
        values.put("IdCategory", "meat");
        values.put("Calories", "220");
        values.put("gr", "100");
        database.insert("Food", null, values);

        values.put("IdFood", "1234588");
        values.put("nameOfFood", "Meat pastry");
        values.put("IdCategory", "meat");
        values.put("Calories", "330");
        values.put("gr", "100");
        database.insert("Food", null, values);

    }

    /**
     * The method gets a food name and returns the calories of the food
     *
     * @param food - the food name
     * @return the calories of the food on the input
     * if the food does not exist - return 0
     */
    public int caloriesByFood(String food) {

        int count = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        String pro[] = {"nameOfFood", "Calories"};
        Cursor c = db.query("Food", pro, null, null, null, null, null);
        c.moveToPosition(0);
        if (c.getString(0).equals(food.trim())) {
            return Integer.parseInt(c.getString(1));
        } else {
            while (c.moveToNext()) {
                Log.i("what c", c.getString(0));
                if (c.getString(0).equals(food.trim())) {
                    return Integer.parseInt(c.getString(1));
                }
            }
        }
        return 0;
    }


    /**
     * The method gets a name of category and returns all the food from that category
     *
     * @param category - category of food
     * @return cursor of the food from a specific category
     */

    public Cursor allFoodBycategort(String category) {
        SQLiteDatabase db = this.getReadableDatabase();
        Log.i("cat", category);
        String pro[] = {"IdFood", "nameOfFood", "IdCategory", "Calories", "gr"};
        Cursor c;
        if (category.equals("milk")) {
            c = db.rawQuery(
                    "SELECT IdFood, nameOfFood From Food WHERE IdCategory = 'milk'",
                    null);
        } else if (category.equals("meat")) {
            c = db.rawQuery(
                    "SELECT IdFood, nameOfFood From Food WHERE IdCategory = 'meat'",
                    null);
        } else if (category.equals("fruit")) {
            c = db.rawQuery(
                    "SELECT IdFood, nameOfFood From Food WHERE IdCategory = 'fruit'",
                    null);
        } else {
            c = db.rawQuery(
                    "SELECT IdFood, nameOfFood From Food WHERE IdCategory = 'vegetables'",
                    null);
        }

        return c;
    }

    /**
     * That method is called when version of DB changed
     *
     * @param db         - the food data base
     * @param oldVersion - the old version of the data base
     * @param newVersion - the new version of the data base
     */

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Food");
        onCreate(db);
    }

}