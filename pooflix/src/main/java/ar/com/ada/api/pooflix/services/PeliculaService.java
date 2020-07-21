package ar.com.ada.api.pooflix.services;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.pooflix.entities.Pelicula;
import ar.com.ada.api.pooflix.repos.PeliculaRepository;

@Service
public class PeliculaService {

    @Autowired
    PeliculaRepository peliculaRepo;

    public void grabar(Pelicula pelicula) {
        peliculaRepo.save(pelicula);
    }    
    public List<Pelicula> listarPeliculas() {
        return peliculaRepo.findAll();
    }

    public Pelicula buscarPorId(ObjectId id) {
        return peliculaRepo.findBy_id(id);
    }

    public Pelicula buscarPorNombre(String nombre) {
        return peliculaRepo.findByNombre(nombre);
    }
}