package com.tallerdesarollo.Api.repositories;

import com.tallerdesarollo.Api.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    @Query("SELECT c FROM tb_categoria c WHERE c.nombreCat LIKE %?1")
    Categoria buscarPorNombre(String nombre);

    @Query ("SELECT OBJECT(c) FROM tb_categoria c WHERE c.nivel = ?1")
    List<Categoria> buscarPorNivel(int nivel);
}
