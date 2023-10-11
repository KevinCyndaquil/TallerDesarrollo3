package com.tallerdesarollo.Api.services;

import com.tallerdesarollo.Api.entities.Categoria;
import com.tallerdesarollo.Api.exceptions.ResourceNotFoundException;
import com.tallerdesarollo.Api.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoriaService implements ICategoria {

    protected CategoriaRepository repository;

    @Autowired
    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Categoria> consultarTodasCategorias() {

        List<Categoria> result = repository.findAll();
        if(result.isEmpty())
            throw new ResourceNotFoundException("No existe categorías registradas en la BD");

        return repository.findAll();
    }

    @Override
    public List<Categoria> consultarPorNivel(int niv) { //3

        List<Categoria> result = repository.buscarPorNivel(niv); //3
        if(result.isEmpty())
            throw new ResourceNotFoundException("No existe categorías asociadas a ese Nivel: " + niv);

        return repository.buscarPorNivel(niv);
    }

    @Override
    public ResponseEntity<Categoria> consultarUno(int idCat) {
        Categoria obj = repository.findById(idCat).orElseThrow(() -> new ResourceNotFoundException("No existe categoría con el Id :" + idCat));
        return ResponseEntity.ok(obj);
    }

    @Override
    public ResponseEntity<Categoria> consultarbyNombre(String nombre) {
        Categoria object = repository.buscarPorNombre(nombre);
        if (object==null)
            throw new ResourceNotFoundException("No existe una categoría con el nombre :" + nombre);

        return ResponseEntity.ok(object);
    }

    @Override
    public ResponseEntity<Map<String, String>> insertarCategoria(Categoria obj) {
        Map<String, String> okResponse = new HashMap<>();
        okResponse.put("message", "La Categoría se ha registrado correctamente");
        okResponse.put("status", HttpStatus.CREATED.toString());
        repository.save(obj);
        return new ResponseEntity<>(okResponse,HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Map<String, String>> actualizarCategoria(Categoria obj, int idCat) {
        Map<String, String> okResponse = new HashMap<>();
        okResponse.put("message", "Los datos de la categoría se actualizaron correctamente");
        okResponse.put("status", HttpStatus.OK.toString());

        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", "No existe Categoría con el Id: " + idCat);
        errorResponse.put("status", HttpStatus.NOT_FOUND.toString());

        return repository.findById(idCat).map(p -> {
                    obj.setIdCat(idCat);
                    repository.save(obj);
                    return new ResponseEntity<>(okResponse, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND));

    }

    @Override
    public ResponseEntity<Map<String, String>> eliminarCategoria(int idCat) {

        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", "Ese articulo no fue encontrado");
        errorResponse.put("status", HttpStatus.NOT_FOUND.toString());

        Map<String, String> okResponse = new HashMap<>();
        okResponse.put("message", "El articulo fue eliminado correctamente");
        okResponse.put("status", HttpStatus.OK.toString());

        return repository.findById(idCat).map(p -> {
                    repository.deleteById(idCat);
                    return new ResponseEntity<>(okResponse, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND));

    }
}
