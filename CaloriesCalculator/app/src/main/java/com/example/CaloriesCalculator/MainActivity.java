package com.example.CaloriesCalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The Main Activity class
 *
 * @author Chen ravia - 314884412
 * @author Moshe Davila - 308459841
 * @author Mor Yemin - 316046093
 */
public class MainActivity extends AppCompatActivity {

    UsersDB dbUsers;
    FoodDB dbFood;
    MealsDB dbMeals;

    /**
     * The Model class
     */
    class Model {

        /**
         * The method gets a string of food names and calculate their total calories
         *
         * @param FoodName - string of food named that the client marked
         * @return the total calories of the food the client marked
         */

        public String calculateCalArray(String FoodName) {
            return Integer.toString(dbFood.caloriesByFood(FoodName));
        }

        /**
         * The method gets a category and returns a list of food by the category
         *
         * @param Category - category of food
         * @return arrayFood - array of food by the category
         */
        public ArrayList listOfFood(String Category) {
            Cursor cursorFood = dbFood.allFoodBycategort(Category);
            ArrayList<String> arrayFood = new ArrayList<String>();
            while (cursorFood.moveToNext()) {
                Log.i("food ", cursorFood.getString(1));
                arrayFood.add(cursorFood.getString(1));
            }
            return arrayFood;
        }


        /**
         * The method gets a user details and add him to the users data base
         *
         * @param id     - the user's id
         * @param name   - the user's name
         * @param weight - the user's weight
         * @param height - the user's height
         * @param gender - the user's gender
         * @param age    - the user's age
         */
        public void AddUser(final String id, final String name, final String weight, final String height, final String gender, String age) {
            SQLiteDatabase dataBaseUsers = dbUsers.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put("sid", id);
            values.put("sname", name);
            values.put("sweight", weight);
            values.put("sheight", height);
            values.put("sgender", gender);
            values.put("sage", age);
            dataBaseUsers.insert("UsersDB", null, values);

        }

        /**
         * The method gets a meal details and add it to the meals data base
         *
         * @param foodName - the name of the food on the meal
         * @param idUser   - the id of the user that add the meal
         * @param MealTime - the time of the meal
         * @param Calories - the calories of the food on the meal
         */

        public void AddMeal(final String foodName, final String idUser, final String MealTime, final String Calories) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date();

            SQLiteDatabase dataB = dbMeals.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("IdUser", idUser);
            values.put("MealTime", MealTime);
            values.put("nameOfFood", foodName);
            values.put("Date", dateFormat.format(date));
            values.put("Calories", Calories);
            dataB.insert("Meals1", null, values);
            Log.i("values", values.toString());

        }

        /**
         * The method returns all the users on the database
         *
         * @return Cursor of the users
         */
        public String getUsers() {
            Log.i("dbUsers", "IN GET USRERS");
            Cursor cursor = dbUsers.allusers();
            Log.i("dbUsers", cursor.getString(0));
            return cursor.getString(0);
        }

        /**
         * The method gets a user id and check if the user exist
         *
         * @param id - the user's id
         * @return true if the user exist on the database , false if it not exist
         */
        public boolean ifUserExist(String id) {
            Log.i("id input ", id);
            Cursor cursor = dbUsers.allusers();
            Log.i("firstposition", cursor.getString(0));
            if (cursor.getString(0).equals(id))
                return true;
            else {
                while (cursor.moveToNext()) {
                    Log.i("firstposition", cursor.getString(0));

                    if (cursor.getString(0).equals(id))
                        return true;
                }
            }
            return false;
        }

    }

    /**
     * The View Model class
     * links between the view and the model
     */

    class ViewModel {

        WebView webView;
        Model model;
        ExecutorService pool;

        public ViewModel(WebView webView, Model model) {
            this.webView = webView;
            this.model = model;
            pool = Executors.newFixedThreadPool(4);
        }

        /**
         * The method links between the view and the model
         * The method gets a category name and sends it to the 'listOfFood' method
         * on the model in another thread
         *
         * @param Category - category of food
         */
        @android.webkit.JavascriptInterface
        public void getFood(final String Category) {
            final ArrayList food = model.listOfFood(Category);
            // calling geyUser on model in another thread
            pool.submit(new Runnable() {
                public void run() {


                    runOnUiThread(new Runnable() {
                        public void run() {
                            webView.evaluateJavascript("printFood('" + food + "')", null);
                        }

                    });

                }

            });
        }

        /**
         * The method links between the view and the model
         * The method gets a user details and sends them to the 'AddUser' method on the model
         * in another thread
         *
         * @param id     - the user's id
         * @param name   - the user's name
         * @param weight - the user's weight
         * @param height - the user's height
         * @param gender - the users gender
         * @param age    - the users age
         */
        @android.webkit.JavascriptInterface
        public void putUsers(final String id, final String name, final String weight, final String height, final String gender, final String age) {
            // calling geyUser on model in another thread
            pool.submit(new Runnable() {
                public void run() {
                    Log.i("mvvm", "view model");

                    runOnUiThread(new Runnable() {
                        public void run() {
                            model.AddUser(id, name, weight, height, gender, age);
                            Toast.makeText(getApplicationContext(), "You have successfully registered", Toast.LENGTH_SHORT).show();
                        }

                    });

                }

            });
        }

        /**
         * The method links between the view and the model
         * The method gets a meal details and sends them to the 'calculateCalArray' method
         * on the model in another thread
         *
         * @param selctedFood - the selected food
         * @param idUser      - user's id
         * @param MealTime    - the meal's time
         */
        @android.webkit.JavascriptInterface
        public void AddMeals(final String[] selctedFood, final String idUser, final String MealTime) {
            for (int i = 0; i < selctedFood.length; i++) {
                final String calc = model.calculateCalArray(selctedFood[i]);
                Log.i("select food ", selctedFood[i]);
                Log.i("id user  ", idUser);
                Log.i("meal time ", MealTime);
                Log.i("calc ", calc);
                model.AddMeal(selctedFood[i], idUser, MealTime, calc);
            }
            pool.submit(new Runnable() {
                final String calories = dbMeals.caloriesAllMealsPerDay(idUser);
                final String AllMeals = dbMeals.allMealsPerUserTime(idUser);

                public void run() {

                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(getApplicationContext(), "The meal successfully added!", Toast.LENGTH_SHORT).show();
                            if (Integer.parseInt(calories) > 1500) {
                                Toast.makeText(getApplicationContext(), "NOTICE! you have passed the recommended calories", Toast.LENGTH_SHORT).show();
                            }
                            webView.evaluateJavascript("MealAdded('" + "Meal Added!" + "+" + calories + "+" + AllMeals + "')", null);
                        }

                    });

                }

            });
        }

        /**
         * The method links between the view and the model
         * The method gets a user id and sends it to the 'allMealsPerUserTime' method
         * on the meals data base in another thread
         *
         * @param idUser - user's id
         */
        @android.webkit.JavascriptInterface
        public void AllMealsPerUser(final String idUser) {
            pool.submit(new Runnable() {
                final String allMeals = dbMeals.allMealsPerUserTime(idUser);

                public void run() {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            webView.evaluateJavascript("GoBack2('" + allMeals + "')", null);
                        }

                    });

                }

            });
        }

        /**
         * The method links between the view and the model
         * The method gets a user id and sends it to the 'caloriesAllMealsPerDay' method
         * on the meals data base in another thread
         *
         * @param idUser - user's id
         */
        @android.webkit.JavascriptInterface
        public void CalcAll(final String idUser) {
            pool.submit(new Runnable() {
                final String calories = dbMeals.caloriesAllMealsPerDay(idUser);

                public void run() {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            webView.evaluateJavascript("getAllCalories('" + calories + "')", null);
                        }

                    });

                }

            });
        }

        /**
         * The method checks if the user exist in the Data Base when the user
         * tries to register
         *
         * @param id - user's id
         */
        @android.webkit.JavascriptInterface
        public void registerUserExist(final String id) {
            final boolean exist = model.ifUserExist(id);
            Log.i("userexist", String.valueOf(exist));
            runOnUiThread(new Runnable() {
                public void run() {
                    if (exist == true) {
                        Toast.makeText(getApplicationContext(), "The ID is already exist, please try again", Toast.LENGTH_SHORT).show();
                        webView.evaluateJavascript("AddUser('" + "true" + "')", null);
                    } else {
                        webView.evaluateJavascript("AddUser('" + "false" + "')", null);
                    }
                }

            });
        }

        /**
         * The method get the user inputs and check validation
         *
         * @param id     - user's ID
         * @param weight - user's weight
         * @param height - user's height
         */
        @android.webkit.JavascriptInterface
        public void validInputs(final String id, final String weight, final String height) {
            boolean isValid = true;
            if (Integer.parseInt(id) > 999999999 || Integer.parseInt(id) < 100000000) {
                isValid = false;
                Log.i("id not valid ", id);
                Toast.makeText(getApplicationContext(), "The ID is not valid , please enter 9 digits", Toast.LENGTH_SHORT).show();
            }
            if (Integer.parseInt(weight) > 250 || Integer.parseInt(weight) < 40) {
                isValid = false;
                Log.i("weight not valid ", weight);
                Toast.makeText(getApplicationContext(), "Your weight is not valid!please enter weight between 40 - 250", Toast.LENGTH_SHORT).show();
            }
            if (Integer.parseInt(height) > 220 || Integer.parseInt(height) < 120) {
                isValid = false;
                Log.i("height is not valid", height);
                Toast.makeText(getApplicationContext(), "Your height is not valid!please enter height between 120-220", Toast.LENGTH_SHORT).show();
            }
            if (isValid == true) {
                Log.i("all valid", id + "," + weight + "," + height);
            }
            final boolean isValid2 = isValid;
            runOnUiThread(new Runnable() {
                public void run() {
                    if (isValid2 == true) {
                        webView.evaluateJavascript("IfvalidUser('" + "true" + "')", null);
                    } else {
                        webView.evaluateJavascript("IfvalidUser('" + "false" + "')", null);
                    }
                }

            });

        }

        /**
         * The method links between the view and the model
         * The method gets a user id and sends it to the 'ifUserExist' method on the model
         * in another thread
         *
         * @param id - user's id
         */

        @android.webkit.JavascriptInterface
        public void fetchUsers(final String id) {
            Log.i("mvvm", "inside the fetchusers");
            // calling geyUser on model in another thread
            pool.submit(new Runnable() {
                final String calories = dbMeals.caloriesAllMealsPerDay(id);
                final String allMeals = dbMeals.allMealsPerUserTime(id);

                public void run() {
                    Log.i("mvvm", "after the function inside the run");

                    final boolean exist = model.ifUserExist(id);
                    runOnUiThread(new Runnable() {
                        public void run() {
                            if (exist == true) {
                                Toast.makeText(getApplicationContext(), "Welcome!", Toast.LENGTH_SHORT).show();
                                if (Integer.parseInt(calories) > 1500) {
                                    Toast.makeText(getApplicationContext(), "NOTICE! you have passed the recommended calories", Toast.LENGTH_SHORT).show();
                                }
                                webView.evaluateJavascript("checkIfUserExist('" + "true" + "+" + calories + "+" + allMeals + "')", null);
                            } else {
                                Toast.makeText(getApplicationContext(), "Please Register!", Toast.LENGTH_SHORT).show();
                                webView.evaluateJavascript("checkIfUserExist('" + "false" + "')", null);
                            }
                        }

                    });

                }

            });
        }
    }

    /**
     * This method initialize the activity
     *
     * @param savedInstanceState
     */

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // creating model
        Model model = new Model();
        dbUsers = new UsersDB(this);
        dbFood = new FoodDB(this);
        dbMeals = new MealsDB(this);
        // creating the webView object
        WebView webView = new WebView(this);
        setContentView(webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl("file:///android_asset/login.html");

        // creating the viewModel
        ViewModel vm = new ViewModel(webView, model);

        // attaching viewModel object to web view
        webView.addJavascriptInterface(vm, "vm");

    }


}