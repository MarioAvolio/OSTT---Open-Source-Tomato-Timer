package com.application.care.data;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.application.care.R;
import com.application.care.model.TimeDate;
import com.application.care.util.UtilDB;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HandlerDB extends SQLiteOpenHelper {

    private static final String TAG = "HandlerDB";
    private static final int UPDATE_WORK_TIME = 1;
    private static HandlerDB instance;
    private static Context context;
    private Map<Integer, Float> timeMonthMap;

    public HandlerDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static HandlerDB getInstance(Context context) {

        if (instance == null)
            instance = new HandlerDB(context, UtilDB.DATABASE_NAME, null, UtilDB.DATABASE_VERSION);
        return instance;
    }

    public static HandlerDB getInstance() throws Exception {

        if (context == null)
            throw new Exception("Context is null.");

        if (instance == null)
            instance = new HandlerDB(context, UtilDB.DATABASE_NAME, null, UtilDB.DATABASE_VERSION);
        return instance;
    }

    public static void setContext(Context context) {
        HandlerDB.context = context;
    }

    @Override
    public void onCreate(@NotNull SQLiteDatabase db) {
        //SQL - Structured Query Language
        String CREATE_DATA_TIME_TABLE = "CREATE TABLE " + UtilDB.TABLE_DATA_TIME + "("
                + UtilDB.KEY_DATE + " TEXT PRIMARY KEY,"
                + UtilDB.KEY_TIME + " REAL" + ")";
        db.execSQL(CREATE_DATA_TIME_TABLE); //creating our table

    }

    @Override
    public void onUpgrade(@NotNull SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = String.valueOf(R.string.drop);
        db.execSQL(DROP_TABLE, new String[]{UtilDB.DATABASE_NAME});

        //Create a table again
        onCreate(db);
    }

    /*
       CRUD = Create, Read, Update, Delete

     */
    //Add TimeDate
    public void addTimeDate(@NotNull TimeDate timeDate) {

        /*
         *   TIME MONTH MAP SERVES FOR CHART STATISTICS.
         *   IT WILL INSERT THE "TIMEDATE" VALUE IN THIS MAP ONLY IF IT IS JUST USED.
         *   IF THE MAP WILL BE INCREASED EVERY TIME A TIME DATE IS ADDED, IT CAN BE INCREASE RAM LEVEL
         *
         * */
        if (timeMonthMap != null) {
            insertInMap(timeDate);
        }

//        if there is just a date, it will update this worktime
        if (thereIsADate(timeDate.getDate())) {
            Log.d(TAG, timeDate.toString() + " go to increase!");
            increaseTimeDate(timeDate);
            return;
        }


        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UtilDB.KEY_DATE, timeDate.getDate());
        values.put(UtilDB.KEY_TIME, timeDate.getTime());

        Log.d(TAG, timeDate.toString());


        //Insert to row
        db.insert(UtilDB.TABLE_DATA_TIME, null, values); // TODO

        Log.d(TAG, "addWorkTime: --> " + timeDate + " item added");
        db.close(); //closing db connection!

    }

    //Get a workTime
    public TimeDate getTimeDate(String date) throws SQLException {
        SQLiteDatabase db = this.getReadableDatabase();

        @SuppressLint("Recycle") Cursor cursor = db.query(UtilDB.TABLE_DATA_TIME,
                new String[]{UtilDB.KEY_DATE, UtilDB.KEY_TIME},
                UtilDB.KEY_DATE + "=?", new String[]{date},
                null, null, null);


        if (cursor == null)
            throw new SQLException("cursor == null");
        else if (!cursor.moveToFirst())
            throw new SQLException("!cursor.moveToFirst( )");


        Log.d(TAG, "getWorkTime item get -> " + cursor.getString(0));
        Log.d(TAG, "getWorkTime item get -> " + cursor.getFloat(1));


        TimeDate timeDate = new TimeDate(cursor.getString(0), cursor.getFloat(1));

        Log.d(TAG, "getWorkTime item get -> " + timeDate.toString());
        return timeDate;
    }


    //    check if there is a worktime with a custom date
    public boolean thereIsADate(String date) {
        SQLiteDatabase db = this.getReadableDatabase();

        @SuppressLint("Recycle") Cursor cursor = db.query(UtilDB.TABLE_DATA_TIME,
                new String[]{UtilDB.KEY_DATE, UtilDB.KEY_TIME},
                UtilDB.KEY_DATE + "=?", new String[]{date},
                null, null, null);


        Log.d(TAG, "thereIsADate " + date + " --> " + (cursor != null));
        return cursor != null && cursor.moveToNext();
    }

    //Get works of any date
    public List<TimeDate> getAllTimeDate() {
        List<TimeDate> timeDateList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        //Select all workTimes
        String selectAll = "SELECT * FROM " + UtilDB.TABLE_DATA_TIME;
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(selectAll, null);

        //Loop through our data
        if (cursor.moveToFirst()) {
            do {
                TimeDate timeDate = new TimeDate(cursor.getString(0), Float.parseFloat(cursor.getString(1)));

                //add timeDate objects to our list
                timeDateList.add(timeDate);
            } while (cursor.moveToNext());
        }

        Log.d(TAG, "getAllWorkTimes items get -> " + timeDateList);
        return timeDateList;
    }


    //Get works of any month
    public Map<Integer, Float> getAllTimeMonth() {

        if (timeMonthMap == null) {
            timeMonthMap = new HashMap<>();
            for (TimeDate timeDate : getAllTimeDate()) {
                insertInMap(timeDate);
            }
        }

        return timeMonthMap;
    }

    private void insertInMap(@NotNull TimeDate timeDate) {
        if (!timeMonthMap.containsKey(timeDate.getMonth())) {
            timeMonthMap.put(timeDate.getMonth(), timeDate.getTime());
        } else {
            float monthTime = timeMonthMap.get(timeDate.getMonth());
            timeMonthMap.put(timeDate.getMonth(), monthTime + timeDate.getTime());
        }
    }


    //Update timeDate
    public int updateTimeDate(@NotNull TimeDate timeDate) {

//        if there isn't a worktime with a custom date, it will add this.
        if (!thereIsADate(timeDate.getDate())) {
            addTimeDate(timeDate);
            return UPDATE_WORK_TIME;
        }

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UtilDB.KEY_TIME, timeDate.getTime());

        //update the row

        Log.d(TAG, "updateWorkTime update ok");

        return db.update(UtilDB.TABLE_DATA_TIME, values, UtilDB.KEY_DATE + "=?",
                new String[]{String.valueOf(timeDate.getDate())});
    }


    //increase timeDate
    public int increaseTimeDate(@NotNull TimeDate timeDate) {

//        if there isn't a worktime with a custom date, it will add this.
        if (!thereIsADate(timeDate.getDate())) {
            addTimeDate(timeDate);
            Log.d(TAG, "increaseWorkTime " + timeDate.toString() + "pass to addWorkTime");

            return UPDATE_WORK_TIME;
        }

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        try {
            TimeDate oldTimeDate = getTimeDate(timeDate.getDate());
            Log.d(TAG, "increaseWorkTime  OLD -> " + oldTimeDate.toString());

            values.put(UtilDB.KEY_TIME, timeDate.getTime() + oldTimeDate.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //update the row

        Log.d(TAG, "increaseWorkTime increase ok");

        return db.update(UtilDB.TABLE_DATA_TIME, values, UtilDB.KEY_DATE + "=?",
                new String[]{String.valueOf(timeDate.getDate())});
    }

    //Delete single timeDate
    public void deleteTimeDate(@NotNull TimeDate timeDate) {

        if (!thereIsADate(timeDate.getDate()))
            return;

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(UtilDB.TABLE_DATA_TIME, UtilDB.KEY_DATE + "=?",
                new String[]{String.valueOf(timeDate.getDate())});

        Log.d(TAG, "deleteWorkTime delete ok ");

        db.close();
    }

    //Get workTimes count
    public int getCount() {
        String countQuery = "SELECT * FROM " + UtilDB.TABLE_DATA_TIME;
        SQLiteDatabase db = this.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(countQuery, null);


        Log.d(TAG, "getCount get count ok! ");
        return cursor.getCount();

    }
}
