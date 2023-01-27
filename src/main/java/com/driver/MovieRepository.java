package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MovieRepository {
    Map<String,Movie> dbOfMovie = new HashMap<>();
    Map<String,Director> dbOfDirector = new HashMap<>();
    Map<String, List<String>> dbOfMovieDirectorPair = new HashMap<>();



    public String addMovie(Movie movie){
        String name = movie.getName();
        dbOfMovie.put(name,movie);
        return "Movie added Successfully";
    }

    public String addDirector(Director director){
        String name = director.getName();
        dbOfDirector.put(name,director);
        return "Director added successfully";
    }

    public String  addMovieDirectorPair(String movie, String director){
        if(dbOfMovie.containsKey(movie) && dbOfDirector.containsKey(director)){
            List<String> listOfMovies = new ArrayList<>();
            if(dbOfMovieDirectorPair.containsKey(director)){
                listOfMovies = dbOfMovieDirectorPair.get(director);
                listOfMovies.add(movie);
                dbOfMovieDirectorPair.put(movie,listOfMovies);
            }
        }
        return "MovieDirector pairs added successfully";
    }

    public Movie getMovieByName(String name){
         return dbOfMovie.get(name);
    }

    public Director getDirectorByName(String name){
        return dbOfDirector.get(name);
    }

    public List<String> getMoviesByDirectorName(String directorName){
        List<String> movies  = new ArrayList<>();
        if(dbOfMovieDirectorPair.containsKey(directorName))
            movies = dbOfMovieDirectorPair.get(directorName);
        return movies;
    }

//    public List<String> findMoviesFromDirector(String director){
//        List<String> moviesList = new ArrayList<String>();
//        if(directorMovieMapping.containsKey(director)) moviesList = directorMovieMapping.get(director);
//        return moviesList;
//    }

    public List<String> findAllMovies(){
        List<String> allMovies = new ArrayList<>();
        for(String movieName : dbOfMovie.keySet()){
            allMovies.add(movieName);
        }
        return allMovies;
    }

    public String deleteDirectorByName(String director){
        List<String> movies = new ArrayList<>();
        if(dbOfMovieDirectorPair.containsKey(director)){
            movies = dbOfMovieDirectorPair.get(director);
            for(String movie : movies) {
                if (dbOfMovie.containsKey(movie)) {
                    dbOfMovie.remove(movie);
                }
            }
        }
        if(dbOfDirector.containsKey(director)){
            dbOfDirector.remove(director);
        }
        return "Deleted the director and his movie";
    }

    public String deleteAllDirectors(){
        List<String> movies = new ArrayList<>();
        for(String name : dbOfMovieDirectorPair.keySet()) {
            movies = dbOfMovieDirectorPair.get(name);

            for (String movieName : movies) {
                if (dbOfMovie.containsKey(name)) {
                    dbOfMovie.remove(name);
                }
            }
        }
        for(String name : dbOfMovieDirectorPair.keySet()){
            if(dbOfDirector.containsKey(name)){
                dbOfDirector.remove(name);
            }
        }
        return "Deleted all the directors and their movies";
    }
}
