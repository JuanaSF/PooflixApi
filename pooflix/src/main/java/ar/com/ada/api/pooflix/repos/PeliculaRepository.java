package ar.com.ada.api.pooflix.repos;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ar.com.ada.api.pooflix.entities.Pelicula;

@Repository
public interface PeliculaRepository extends MongoRepository<Pelicula,ObjectId>{

    Pelicula findBy_id(ObjectId id);

    Pelicula findByNombre(String nombre);

    List<Pelicula> findAllByNombre(String nombre);
}