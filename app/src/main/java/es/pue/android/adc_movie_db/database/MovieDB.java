package es.pue.android.adc_movie_db.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class MovieDB extends SQLiteOpenHelper {

    private static final String DB_NAME = "MovieDB";
    private static final int DB_VERSION = 1;

    public MovieDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public MovieDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table =
                "CREATE TABLE movies (" +
                "   _id         INTEGER PRIMARY KEY AUTOINCREMENT," +
                "   title       TEXT," +
                "   genre       TEXT," +
                "   director    TEXT," +
                "   duration    TEXT," +
                "   poster_url  TEXT" +
                ")";

        db.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
