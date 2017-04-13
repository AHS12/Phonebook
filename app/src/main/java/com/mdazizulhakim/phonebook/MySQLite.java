package com.mdazizulhakim.phonebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Shovon on 4/6/2017.
 */

public class MySQLite extends SQLiteOpenHelper {
    public MySQLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contactDB.db";
    private static final String TABLE_NAME = "contact";

    private static final String COLUMN1 = "ID";
    private static final String COLUMN2 = "NAME";
    private static final String COLUMN3 = "NUMBER";
    private static final String COLUMN4 = "EMAIL";


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN1 + " INTEGER PRIMARY KEY," + COLUMN2 + " TEXT UNIQUE," + COLUMN3 + " TEXT," +
                COLUMN4 + " TEXT)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        String query = "DROP IF EXISTS " + TABLE_NAME;
        sqLiteDatabase.execSQL(query);
        onCreate(sqLiteDatabase);
    }

    public boolean addToTable(String ID, String NAME, String NUMBER, String EMAIL) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN1, ID);
        contentValues.put(COLUMN2, NAME);
        contentValues.put(COLUMN3, NUMBER);
        contentValues.put(COLUMN4, EMAIL);

        long chk = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        /*String query = "insert into " + TABLE_NAME + "(" + COLUMN1 + "," + COLUMN2 + "," + COLUMN3 + "," + COLUMN4 +")" + " values(" + ID + "," + NAME + "," + NUMBER +
             "," + EMAIL + ")"; */

        if (chk == -1) return false;
        else return true;
    }

    public Cursor display() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME+" ORDER BY NAME ASC", null); //Ascending Order
        return result;
    }

    public boolean updateData(String ID, String NAME, String NUMBER, String EMAIL) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN2, NAME);
        contentValues.put(COLUMN3, NUMBER);
        contentValues.put(COLUMN4, EMAIL);
        int chk = sqLiteDatabase.update(TABLE_NAME, contentValues, "ID = ?", new String[]{(ID)});

        if (chk > 0) return true;
        else return false;
    }

    public boolean deleteData(String ID) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        int chk = sqLiteDatabase.delete(TABLE_NAME, "ID = ?", new String[]{(ID)});

        if (chk > 0) return true;
        else return false;

    }
}
