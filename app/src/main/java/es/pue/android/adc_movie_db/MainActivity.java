package es.pue.android.adc_movie_db;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import es.pue.android.adc_movie_db.database.MovieDBManager;
import es.pue.android.adc_movie_db.models.Movie;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MovieDBManager dbManager = new MovieDBManager(getApplicationContext());

        Movie newMovie = new Movie();
        newMovie.setTitle("Pulp Fiction");
        newMovie.setDirector("Quentin Tarantino");
        newMovie.setDuration("2h 34m");
        newMovie.setGenre("Thriller; Crime");
        newMovie.setPoster_url("https://image.tmdb.org/t/p/original/dM2w364MScsjFf8pfMbaWUcWrR.jpg");

        long newId = dbManager.addMovie(newMovie);
        List<Movie> movieList = dbManager.getAllMovies();
    }
}
