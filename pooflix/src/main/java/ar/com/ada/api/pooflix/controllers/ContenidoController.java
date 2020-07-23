package ar.com.ada.api.pooflix.controllers;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.pooflix.entities.*;
import ar.com.ada.api.pooflix.models.response.GenericResponse;
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
    public ResponseEntity<List<Serie>> listarSeries(@RequestParam(value = "nombre", required = false) String nombre){

        List<Serie> ls;

        if(nombre == null){
            ls = serieService.listarSeries();
            return ResponseEntity.ok(ls);
        } else {
            ls = serieService.buscarSeriesPorNombre(nombre);
            return ResponseEntity.ok(ls);
        }
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
    public ResponseEntity<List<Pelicula>> listarPeliculas(@RequestParam(value = "nombre", required = false) String nombre){

        List<Pelicula> lp;

        if(nombre == null){
            lp = peliculaService.listarPeliculas();
            return ResponseEntity.ok(lp);
        } else {
            lp = peliculaService.buscarPelisPorNombre(nombre);
            return ResponseEntity.ok(lp);
        }
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

    @DeleteMapping("/api/series/{id}")
    public ResponseEntity<GenericResponse> eliminarSerie(@PathVariable String id){

        ObjectId obId = new ObjectId(id);
        GenericResponse r = new GenericResponse(); 

        boolean resultado = false;
        resultado = serieService.eliminarSerie(obId);

        if(resultado){

            r.isOK = true;
            r.message = "la serie fue eliminada";
            return ResponseEntity.ok(r);
        }

        r.isOK = false;
        r.message = "algo fallo al eliminar la serie";
        return ResponseEntity.ok(r);
    }

    @DeleteMapping("/api/peliculas/{id}")
    public ResponseEntity<GenericResponse> eliminarPelicula(@PathVariable String id){

        ObjectId obId = new ObjectId(id);
        GenericResponse r = new GenericResponse(); 

        boolean resultado = false;
        resultado = peliculaService.eliminarPelicula(obId);

        if(resultado){

            r.isOK = true;
            r.message = "la peli fue eliminada";
            return ResponseEntity.ok(r);
        }

        r.isOK = false;
        r.message = "algo fallo al eliminar la pelicula";
        return ResponseEntity.ok(r);
    }    
    
}