package com.tallerdesarollo.Api.controllers;

import com.tallerdesarollo.Api.entities.Categoria;
import com.tallerdesarollo.Api.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(
        origins = "*",
        methods= {
                RequestMethod.GET,
                RequestMethod.POST,
                RequestMethod.DELETE,
                RequestMethod.PUT})
@RequestMapping("/api")
public class CategoriaController {

    protected CategoriaService service;

    @Autowired
    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @GetMapping("/categorias")
    public ResponseEntity<List<Categoria>> mostrarTodasCategorias(){
        return new ResponseEntity<>(service.consultarTodasCategorias(), HttpStatus.FOUND);
    }

    @GetMapping("/categorias/nivel/{n}")
    public List<Categoria> mostrarPorNivel(@PathVariable("n") int n){
        return service.consultarPorNivel(n);
    }

    @GetMapping("/categorias/nom/{nombre}")
    public ResponseEntity<?> mostrarPorNombre(@PathVariable("nombre") String nom){
        return service.consultarbyNombre(nom);
    }

    @GetMapping("/categorias/{id}")
    public ResponseEntity<Categoria> localizar(@PathVariable("id") int idCat){
        return service.consultarUno(idCat);
    }

    @DeleteMapping("/categorias/{id}")
    public ResponseEntity<?> eliminar(@PathVariable("id") int idCat){
        return service.eliminarCategoria(idCat);

    }

    @PostMapping("/categorias")
    public String insertar(@RequestBody Categoria object){
        service.insertarCategoria(object);
        return "La categoria fue registrada correctamente";
    }

    @PutMapping("/categorias/{id}")
    public String actualizar(@RequestBody Categoria obj, @PathVariable("id") int idCat){
        service.actualizarCategoria(obj, idCat);
        return "Los datos de la categoria fue actualizado correctamente";
    }
}
