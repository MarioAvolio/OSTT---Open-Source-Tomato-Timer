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
import com.application.care.model.WorkTime;
import com.application.care.util.UtilDB;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HandlerDB extends SQLiteOpenHelper {

    private static final String TAG = "HandlerDB";
    private static final int UPDATE_WORK_TIME = 1;
    private static HandlerDB instance;
    private static Context context;

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
    //Add WorkTime
    public void addWorkTime(@NotNull WorkTime workTime) {

//        if there is just a date, it will update this worktime
        if (thereIsADate(workTime.getDate())) {
            Log.d(TAG, workTime.toString() + " go to increase!");
            increaseWorkTime(workTime);
            return;
        }

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UtilDB.KEY_DATE, workTime.getDate());
        values.put(UtilDB.KEY_TIME, workTime.getTime());

        Log.d(TAG, workTime.toString());

        //Insert to row
        db.insert(UtilDB.TABLE_DATA_TIME, null, values); // TODO

        Log.d(TAG, "addWorkTime: --> " + workTime + " item added");
        db.close(); //closing db connection!

    }

    //Get a workTime
    public WorkTime getWorkTime(String date) throws SQLException {
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


        WorkTime workTime = new WorkTime(cursor.getString(0), cursor.getFloat(1));

        Log.d(TAG, "getWorkTime item get -> " + workTime.toString());
        return workTime;
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

    //Get all WorkTimes
    public List<WorkTime> getAllWorkTimes() {
        List<WorkTime> workTimeList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        //Select all workTimes
        String selectAll = "SELECT * FROM " + UtilDB.TABLE_DATA_TIME;
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(selectAll, null);

        //Loop through our data
        if (cursor.moveToFirst()) {
            do {
                WorkTime workTime = new WorkTime(cursor.getString(0), Float.parseFloat(cursor.getString(1)));

                //add workTime objects to our list
                workTimeList.add(workTime);
            } while (cursor.moveToNext());
        }

        Log.d(TAG, "getAllWorkTimes items get -> " + workTimeList);
        return workTimeList;
    }

    //Update workTime
    public int updateWorkTime(@NotNull WorkTime workTime) {

//        if there isn't a worktime with a custom date, it will add this.
        if (!thereIsADate(workTime.getDate())) {
            addWorkTime(workTime);
            return UPDATE_WORK_TIME;
        }

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UtilDB.KEY_TIME, workTime.getTime());

        //update the row

        Log.d(TAG, "updateWorkTime update ok");

        return db.update(UtilDB.TABLE_DATA_TIME, values, UtilDB.KEY_DATE + "=?",
                new String[]{String.valueOf(workTime.getDate())});
    }


    //increase workTime
    public int increaseWorkTime(@NotNull WorkTime workTime) {

//        if there isn't a worktime with a custom date, it will add this.
        if (!thereIsADate(workTime.getDate())) {
            addWorkTime(workTime);
            Log.d(TAG, "increaseWorkTime " + workTime.toString() + "pass to addWorkTime");

            return UPDATE_WORK_TIME;
        }

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        try {
            WorkTime oldWorkTime = getWorkTime(workTime.getDate());
            Log.d(TAG, "increaseWorkTime  OLD -> " + oldWorkTime.toString());

            values.put(UtilDB.KEY_TIME, workTime.getTime() + oldWorkTime.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //update the row

        Log.d(TAG, "increaseWorkTime increase ok");

        return db.update(UtilDB.TABLE_DATA_TIME, values, UtilDB.KEY_DATE + "=?",
                new String[]{String.valueOf(workTime.getDate())});
    }

    //Delete single workTime
    public void deleteWorkTime(@NotNull WorkTime workTime) {

        if (!thereIsADate(workTime.getDate()))
            return;

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(UtilDB.TABLE_DATA_TIME, UtilDB.KEY_DATE + "=?",
                new String[]{String.valueOf(workTime.getDate())});

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
