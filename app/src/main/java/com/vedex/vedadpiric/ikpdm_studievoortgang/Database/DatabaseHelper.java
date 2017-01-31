package com.vedex.vedadpiric.ikpdm_studievoortgang.Database;

/**
 * Created by vedadpiric on 04-01-17.
 */


        import android.database.sqlite.SQLiteOpenHelper;
        import android.content.Context;
        import android.database.sqlite.SQLiteDatabase;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "SchoolVaken";

    // Table columns
    public static final String _ID = "_id";
    public static final String MODULENAAM = "moduleNaam";
    public static final String STUDENT_NUMMER = "student_nummer";
    public static final String ECS ="ecs";
    public static final String CIJFER = "cijfer";

    // Database Information
    static final String DB_NAME = "STUDIEVOORTGANG.DB";

    // database version
    static final int DB_VERSION = 14;

    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY , " + MODULENAAM + " TEXT NOT NULL, "+ STUDENT_NUMMER + " TEXT NOT NULL, " + CIJFER + " decimal(2,1), " + ECS + " INTEGER(2)); ";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

}