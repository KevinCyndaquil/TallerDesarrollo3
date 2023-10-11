package com.tallerdesarollo.Api.services;

import com.tallerdesarollo.Api.entities.Categoria;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ICategoria {
    List<Categoria> consultarTodasCategorias();
    ResponseEntity<Categoria> consultarUno(int idCat);
    ResponseEntity<Map<String, String>> actualizarCategoria(Categoria obj, int idCat);
    ResponseEntity<Map<String, String>> insertarCategoria(Categoria obj);
    ResponseEntity<?> eliminarCategoria(int idCat);
    ResponseEntity<Categoria> consultarbyNombre(String nombre);
    List<Categoria> consultarPorNivel(int niv);
}
