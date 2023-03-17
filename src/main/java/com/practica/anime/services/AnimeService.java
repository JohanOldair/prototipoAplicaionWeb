package com.practica.anime.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.practica.anime.entity.Anime;
import com.practica.anime.respository.AnimeRepositoryDao;

@Service
public class AnimeService {

	@Autowired
	private AnimeRepositoryDao animeRepositoryDao;
	
	public List<Anime> listAll(String PalabraClave) {
		if(PalabraClave != null) {
			return animeRepositoryDao.findAll(PalabraClave);
		}
		return animeRepositoryDao.findAll();
	}
}
