package ar.com.ada.api.pooflix.controllers;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.pooflix.entities.*;
import ar.com.ada.api.pooflix.models.GenericResponse;
import ar.com.ada.api.pooflix.services.*;

@RestController
public class ContenidoController {

    @Autowired
    SerieService serieService;
    @Autowired
    PeliculaService peliculaService;

    @PostMapping("/api/series")
    public ResponseEntity<GenericResponse> crearSerie(@RequestBody Serie serieNueva){
        
        serieService.grabar(serieNueva);

        GenericResponse response = new GenericResponse();
        response.isOK = true;
        response.message =  "Serie creada con exito!";
        response.id= serieNueva.get_id();

        return  ResponseEntity.ok(response);
    }
    
    @GetMapping("/api/series")
    public ResponseEntity<List<Serie>> listarSeries(){

        return ResponseEntity.ok(serieService.listarSeries());
    }

    @PostMapping("/api/peliculas")
    public ResponseEntity<GenericResponse> crearPelicula(@RequestBody Pelicula peliNueva){

        peliculaService.grabar(peliNueva);

        GenericResponse response = new GenericResponse();
        response.isOK = true;
        response.message = "Peli creada con exito!";
        response.id = peliNueva.get_id();

        return ResponseEntity.ok(response);        
    }

    @GetMapping("/api/peliculas")
    public ResponseEntity<List<Pelicula>> listarPeliculas(){

        return ResponseEntity.ok(peliculaService.listarPeliculas());
    }

    @GetMapping("/api/series/{id}")
    public ResponseEntity<Serie> getSeriePor(@PathVariable String id){

        ObjectId obId = new ObjectId(id);

        Serie s = serieService.buscarPorId(obId);
        if (s == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(s);

    }

    @GetMapping("/api/peliculas/{id}")
    public ResponseEntity<Pelicula> getPeliculaPor(@PathVariable String id){

        ObjectId obId = new ObjectId(id);

        Pelicula p = peliculaService.buscarPorId(obId);
        if (p == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(p);

    }
    
}