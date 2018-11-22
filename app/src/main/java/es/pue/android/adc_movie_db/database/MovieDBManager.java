package es.pue.android.adc_movie_db.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import es.pue.android.adc_movie_db.models.Movie;

public class MovieDBManager {

    private static final String TAB_MOVIES = "movies";
    private static final String COL_TITLE = "title";
    private static final String COL_GENRE = "genre";
    private static final String COL_DIRECTOR = "director";
    private static final String COL_DURATION = "duration";
    private static final String COL_URL_POSTER = "poster_url";

    private Context context;
    private MovieDB movieDB;

    public MovieDBManager(Context context) {
        movieDB = new MovieDB(context);
    }

    public void addMovie(Movie movie) {
        SQLiteDatabase db = movieDB.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COL_TITLE, movie.getTitle());
        cv.put(COL_GENRE, movie.getGenre());
        cv.put(COL_DIRECTOR, movie.getDirector());
        cv.put(COL_DURATION, movie.getDuration());
        cv.put(COL_URL_POSTER, movie.getPoster_url());

        db.insertOrThrow(TAB_MOVIES, null, cv);
    }
}
