package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/add_movie")
    public ResponseEntity addMovie(@RequestBody Movie movie){
        String response = movieService.addMovie(movie);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/add_director")
    public ResponseEntity addDirector(@RequestBody Director director){
        String response = movieService.addDirector(director);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @PostMapping("/add-movie-director-pair")
    public ResponseEntity  addMovieDirectorPair(@RequestParam("movie") String movie, @RequestParam("director") String director){
        movieService.addMovieDirectorPair(movie,director);
        return new ResponseEntity<>("new movie director pair added",HttpStatus.CREATED);
    }

    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity getMovieByName(@PathVariable("name") String name){
        Movie movie = movieService.getMovieByName(name);
        return new ResponseEntity<>(movie,HttpStatus.FOUND);
    }

    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity getDirectorByName(@PathVariable("name") String name){
        Director director = movieService.getDirectorByName(name);
        return new ResponseEntity<>(director,HttpStatus.FOUND);
    }


    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable String director){
        List<String> movies = movieService.getMoviesByDirectorName(director);
        return new ResponseEntity<>(movies, HttpStatus.CREATED);
    }



    @GetMapping("/findAllMovies")
    public ResponseEntity findAllMovies(){
        List<String> movies = movieService.findAllMovies();
        return new ResponseEntity<>(movies,HttpStatus.FOUND);
    }

    @DeleteMapping("/delete_director")
    public ResponseEntity deleteDirectorByName(@RequestParam("director") String director){
      movieService.deleteDirectorByName(director);
     return new ResponseEntity<>(director + "removed successfully",HttpStatus.FOUND);
    }

    @DeleteMapping("/delete_allDirectors")
    public ResponseEntity deleteAllDirectors(){
         movieService.deleteAllDirectors();
        return new ResponseEntity<>("Deleted Successfully" , HttpStatus.FOUND);
    }
}
