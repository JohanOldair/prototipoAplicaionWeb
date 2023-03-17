package com.practica.anime.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.practica.anime.entity.Anime;
import com.practica.anime.respository.AnimeRepositoryDao;
import com.practica.anime.services.AnimeService;

@Controller
@RequestMapping("/anime")
public class HomeController {
	
	@Autowired
	private AnimeRepositoryDao animeRepositoryDao;
	@Autowired
	private AnimeService animeService;
	
	@GetMapping("/estrenos")
	public ModelAndView verPaginaDeInicio() {
		List<Anime> ultimosAnimes = animeRepositoryDao.findAll(PageRequest.of(0,4,Sort.by("fechaEstreno").descending())).toList();
		
		return new ModelAndView("anime/estrenos")
				.addObject("ultimosAnimes", ultimosAnimes);
	}
	
	
	@GetMapping("/animes")
	public ModelAndView listarAnimes(@Param("PalabraClave") String PalabraClave, @PageableDefault(sort = "fechaEstreno",direction = Sort.Direction.DESC) Pageable pageable) {
		Page<Anime> animes = animeRepositoryDao.findAll(pageable);
		List<Anime> animess = animeService.listAll(PalabraClave);
		return new ModelAndView("anime/animes")
						.addObject("PalabraClave", PalabraClave)
						.addObject("animess", animess)
				        .addObject("animes",animes);
	}
	
	@GetMapping("/animes/{id}")
	public ModelAndView mostrarDetallesDeAnime(@PathVariable Integer id) {
		Anime anime = animeRepositoryDao.findById(id).get();
		return new ModelAndView("anime/anime")
						.addObject("anime",anime);
	}
}
