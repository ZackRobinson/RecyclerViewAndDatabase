package com.example.zack.recyclerviewanddatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zack on 9/13/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 6;
    private static final String DATABASE_NAME = "actorDatabase";
    public static final String TABLE_NAME = "Actors";
    public static final String ACTOR_NAME = "name";
    public static final String ACTOR_AGE = "age";
    public static final String ACTOR_GENDER = "gender";
    public static final String ACTOR_HEIGHT = "height";
    public static final String ACTOR_UNIQUE_KEY = "key";
    private static final String TAG = "DatabaseHelperTag";


    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + ACTOR_NAME + " TEXT," + ACTOR_AGE + " INT," + ACTOR_GENDER + " TEXT," + ACTOR_HEIGHT + " FLOAT," + ACTOR_UNIQUE_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT " + ")";
        db.execSQL(CREATE_TABLE);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void createActor(String name, int age, String gender, float height){
        Log.d(TAG, "createActor: ");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ACTOR_NAME, name);
        cv.put(ACTOR_AGE, age);
        cv.put(ACTOR_GENDER, gender);
        cv.put(ACTOR_HEIGHT, height);
        long isSaved = db.insert(TABLE_NAME,null,cv);
    }

    public Actor readActor(int position){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        cursor.move(position);
        Log.d(TAG, "readActor: " + cursor.getString(0) + cursor.getInt(1) + cursor.getString(2) + cursor.getFloat(3));
        Actor a = new Actor(cursor.getString(0), cursor.getInt(1), cursor.getString(2), cursor.getFloat(3));
        a.setId(cursor.getInt(4));
        return a;
    }

    public void updateContact(String newName, String key){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put(ACTOR_NAME, newName);
        String whereClause = ACTOR_UNIQUE_KEY + " LIKE ?";
        String[] whereArgs = {key};
        db.update(TABLE_NAME, v, whereClause, whereArgs);
    }

    public void deleteContact(String key){
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = ACTOR_UNIQUE_KEY + " LIKE ?";
        String[] whereArgs = {key};
        db.delete(TABLE_NAME,whereClause,whereArgs);
    }

    public List<Actor> getAllActors()
    {
        List<Actor> actorsList = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        String query = "Select * From " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst())
        {
            do {
                Actor actor = new Actor(cursor.getString(0), cursor.getInt(1), cursor.getString(2), cursor.getFloat(3));
                actor.setId(cursor.getInt(4));
                actorsList.add(actor);
            }while(cursor.moveToNext());
        }
        return actorsList;
    }
}
