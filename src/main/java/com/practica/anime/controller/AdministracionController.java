	package com.practica.anime.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.practica.anime.entity.Anime;
import com.practica.anime.entity.Categoria;
import com.practica.anime.entity.Genero;
import com.practica.anime.respository.AnimeRepositoryDao;
import com.practica.anime.respository.CategoriaRepository;
import com.practica.anime.respository.GeneroRepositoryDao;
import com.practica.anime.services.AlmacenServicioImpl;
	
@Controller
@RequestMapping("/administracion")
public class AdministracionController {

	@Autowired
	private AnimeRepositoryDao animeRepositoryDao;

	@Autowired
	private GeneroRepositoryDao generoRepositoryDao;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private AlmacenServicioImpl almacenServicioImpl;
	
	

	@GetMapping("")
	public ModelAndView verPaginaDeInicio(@PageableDefault(sort = "titulo", size = 5) Pageable pageable) {
		Page<Anime> animes = animeRepositoryDao.findAll(pageable);

		return new ModelAndView("admin/index").addObject("animes", animes);
	}

	@GetMapping("/animes/nuevo")
	public ModelAndView mostrarFormularioAnimes() {
		List<Genero> listaGeneros = generoRepositoryDao.findAll(Sort.by("titulo"));
		List<Categoria> listaCategoria = categoriaRepository.findAll(Sort.by("nombreCategoria"));
	
		return new ModelAndView("admin/nuevos-animes")
				.addObject("anime", new Anime())
				.addObject("listaGeneros", listaGeneros)
				.addObject("listaCategoria", listaCategoria);
	}
	
	@PostMapping("guardar")
	public ModelAndView registrarAnime(@Validated Anime anime, BindingResult bindingResult) {
		if(bindingResult.hasErrors() || anime.getPortada().isEmpty()) {
			if(anime.getPortada().isEmpty()) {
				bindingResult.rejectValue("portada","MultipartNotEmpty");
			}
			
			List<Genero> listaGeneros = generoRepositoryDao.findAll(Sort.by("titulo"));
			List<Categoria> listaCategoria = categoriaRepository.findAll(Sort.by("nombreCategoria"));
			
			return new ModelAndView("admin/nuevos-animes")
					.addObject("anime", anime)
					.addObject("listaGeneros", listaGeneros)
					.addObject("listaCategoria", listaCategoria);
		}
		
		String rutaPortada = almacenServicioImpl.almacenarArchivo(anime.getPortada());
		anime.setRutaPortada(rutaPortada);
		
		animeRepositoryDao.save(anime);
		
		return new ModelAndView("redirect:/administracion");
	}
	
	//pendiente mas cosas
	
	
	@GetMapping("/editar")
	public ModelAndView editarAnime(@RequestParam("id") Integer id) {
		
		Anime anime = animeRepositoryDao.findById(id).get();
		List<Genero> listaGeneros = generoRepositoryDao.findAll(Sort.by("titulo"));
		List<Categoria> listaCategoria = categoriaRepository.findAll(Sort.by("nombreCategoria"));
		
		return new ModelAndView("admin/nuevos-animes")
				.addObject("anime", anime)
				.addObject("listaGeneros", listaGeneros)
				.addObject("listaCategoria", listaCategoria);
	}
	
	@PostMapping("/anime/{id}/eliminar")
	public String eliminarAnime(@PathVariable Integer id) {
		Anime anime = animeRepositoryDao.findById(id).get();
		animeRepositoryDao.delete(anime);
		almacenServicioImpl.eliminarArchivo(anime.getRutaPortada());
		
		return "redirect:/administracion";
	}
	

}
