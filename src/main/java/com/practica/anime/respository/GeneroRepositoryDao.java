package com.practica.anime.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practica.anime.entity.Genero;

public interface GeneroRepositoryDao extends JpaRepository<Genero, Integer> {

}
