package es.pue.android.adc_movie_db.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import es.pue.android.adc_movie_db.models.Movie;

public class MovieDBManager {

    private static final String TAB_MOVIES = "movies";

    private static final String COL_ID = "_id";
    private static final String COL_TITLE = "title";
    private static final String COL_GENRE = "genre";
    private static final String COL_DIRECTOR = "director";
    private static final String COL_DURATION = "duration";
    private static final String COL_URL_POSTER = "poster_url";

    private static final String[] TAB_COL_MOVIES = { COL_TITLE, COL_GENRE,
            COL_DIRECTOR, COL_DURATION, COL_URL_POSTER };

    private Context context;
    private MovieDB movieDB;

    public MovieDBManager(Context context) {
        movieDB = new MovieDB(context);
    }

    public long addMovie(Movie movie) {
        SQLiteDatabase db = movieDB.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COL_TITLE, movie.getTitle());
        cv.put(COL_GENRE, movie.getGenre());
        cv.put(COL_DIRECTOR, movie.getDirector());
        cv.put(COL_DURATION, movie.getDuration());
        cv.put(COL_URL_POSTER, movie.getPoster_url());

        long id = db.insertOrThrow(TAB_MOVIES, null, cv);

        db.close();

        return id;
    }

    public Movie getMovie(long id) {
        SQLiteDatabase db = movieDB.getReadableDatabase();

        Cursor cursor = db.query(TAB_MOVIES, null, COL_ID + " = ?", new String[]{String.valueOf(id)}, null, null, null);

        if(cursor.moveToFirst()) {
            Movie movie = new Movie();
            return setMovie(cursor);
        }

        return null;
    }

    public List<Movie> getAllMovies() {
        List<Movie> movieList = new ArrayList<>();
        String query = "select * from " + TAB_MOVIES;
        SQLiteDatabase db = movieDB.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do {
                Movie m = setMovie(cursor);
                movieList.add(m);
            } while(cursor.moveToNext());
        }

        return movieList;
    }

    private Movie setMovie(Cursor cursor) {
        Movie movie = new Movie();
        movie.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COL_ID))));
        movie.setTitle(cursor.getString(cursor.getColumnIndex(COL_TITLE)));
        movie.setGenre(cursor.getString(cursor.getColumnIndex(COL_GENRE)));
        movie.setDirector(cursor.getString(cursor.getColumnIndex(COL_DIRECTOR)));
        movie.setPoster_url(cursor.getString(cursor.getColumnIndex(COL_URL_POSTER)));
        return movie;
    }
}
