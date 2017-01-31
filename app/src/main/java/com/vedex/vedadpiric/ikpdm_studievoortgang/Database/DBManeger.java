package com.vedex.vedadpiric.ikpdm_studievoortgang.Database;

/**
 * Created by vedadpiric on 04-01-17.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.vedex.vedadpiric.ikpdm_studievoortgang.Model.SchoolVaken;
import com.vedex.vedadpiric.ikpdm_studievoortgang.Model.Student;
import com.vedex.vedadpiric.ikpdm_studievoortgang.SignupActivity;


public class DBManeger {



    private static final String TAG = SignupActivity.class.getSimpleName();
        private DatabaseHelper dbHelper;

        private Context context;

        private SQLiteDatabase database;

        public DBManeger(Context c) {
            context = c;
        }

        public DBManeger open() throws SQLException {
            dbHelper = new DatabaseHelper(context);
            database = dbHelper.getWritableDatabase();
            return this;
        }

        public void close() {
            dbHelper.close();
        }

        public void insert(String st_nummer,String modulenaam, double cijfer, int ecs ) {
            ContentValues contentValue = new ContentValues();
            contentValue.put(DatabaseHelper.MODULENAAM, modulenaam);
            contentValue.put(DatabaseHelper.STUDENT_NUMMER,st_nummer);
            contentValue.put(DatabaseHelper.CIJFER, cijfer);
            contentValue.put(DatabaseHelper.ECS, ecs);
            database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
        }

        public Cursor fetch(String st_nummer) {

            Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, new String[]
                            {DatabaseHelper._ID,DatabaseHelper.MODULENAAM, DatabaseHelper.ECS, DatabaseHelper.CIJFER}, DatabaseHelper.STUDENT_NUMMER + "=?",
                    new String[]{String.valueOf(st_nummer)}, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {


                return cursor;
            }
                return cursor;

        }
        public int update(String st_nummer,long _id, String modulenaam, double cijfer ,int ecs) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(DatabaseHelper.MODULENAAM, modulenaam);
            contentValues.put(DatabaseHelper.CIJFER, cijfer);
            contentValues.put(DatabaseHelper.ECS, ecs);
            int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper.STUDENT_NUMMER + " = " + st_nummer, null);
            return i;
        }

        public void delete(long _id) {
            database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
        }

         public int sumEcs(String st_nummer){
            Cursor cur = database.rawQuery("SELECT SUM(ecs) FROM "+DatabaseHelper.TABLE_NAME+" WHERE student_nummer = ?", new String[] {st_nummer},null);
            if(cur != null && cur.moveToFirst())
            {
            return cur.getInt(0);
            }
             return cur.getInt(0);
        }
}

