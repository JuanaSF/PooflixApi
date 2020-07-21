package ar.com.ada.api.pooflix.services;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.pooflix.entities.Serie;
import ar.com.ada.api.pooflix.repos.SerieRepository;

@Service
public class SerieService {

    @Autowired
    SerieRepository serieRepo;

    public void grabar(Serie serie) {
        serieRepo.save(serie);
    }
        
    public List<Serie> listarSeries() {
        return serieRepo.findAll();
    }

    public Serie buscarPorId(ObjectId id) {
        return serieRepo.findBy_id(id);
    }

    public Serie buscarPorNombre(String nombre) {
        return serieRepo.findByNombre(nombre);
    }
}