package com.practica.anime.respository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.practica.anime.entity.Anime;

public interface AnimeRepositoryDao extends JpaRepository<Anime, Integer> {

	@Query("SELECT a FROM Anime a WHERE a.titulo LIKE %?1%")
	public List<Anime> findAll(String PalabraClave);
	
}
