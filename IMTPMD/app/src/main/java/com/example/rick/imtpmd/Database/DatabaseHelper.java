package com.example.rick.imtpmd.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by juliarijsbergen on 19-06-17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static SQLiteDatabase mySQLDB;
    private static DatabaseHelper mInstance;
    public static final String dbName = "course.db";
    public static final int dbVersion = 1;

    public DatabaseHelper(Context ctx){
        super(ctx, dbName, null, dbVersion);
    }

    public static synchronized DatabaseHelper getHelper (Context ctx) {
        if (mInstance == null){
            mInstance = new DatabaseHelper(ctx);
            mySQLDB = mInstance.getWritableDatabase();
        }
        return mInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DatabaseInfo.CourseTables.user + " (" + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DatabaseInfo.CourseColumn.vak_name + " TEXT," +
                DatabaseInfo.CourseColumn.user_id + " TEXT," +
                DatabaseInfo.CourseColumn.grade + " TEXT);"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ DatabaseInfo.CourseTables.user);
        onCreate(db);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version ){
        super(context,name,factory, version);
    }

    public void insert(String table, String nullColumnHack, ContentValues values){
        mySQLDB.insert(table, nullColumnHack, values);
    }

    public Cursor query(String table, String[] columns, String selection, String[] selectArgs, String groupBy, String having, String orderBy){
        return mySQLDB.query(table, columns, selection, selectArgs, groupBy, having, orderBy);
    }

}
