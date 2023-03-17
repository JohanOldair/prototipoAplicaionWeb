package com.practica.anime.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practica.anime.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
