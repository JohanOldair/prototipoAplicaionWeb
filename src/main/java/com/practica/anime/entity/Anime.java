package com.practica.anime.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.multipart.MultipartFile;

@Entity
public class Anime{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_anime")
	private int id;

	@NotBlank
	private String titulo;

	@NotBlank
	private String sinopsis;	

	@NotNull
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate fechaEstreno;

	@NotBlank
	private String urlAnime;
	
	@NotBlank
	private String capitulos;
	
	@NotBlank
	private String youtubeTrailerId;

	private String rutaPortada;

	@NotEmpty(message = "Debe seleccionar al menos un género")
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinTable(name = "genero_anime", joinColumns = @JoinColumn(name = "id_anime"), inverseJoinColumns = @JoinColumn(name = "id_genero"))
	private List<Genero> generos;
	
	
	//relacion conl la tabla caregoria
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_categoria")
	@NotNull
	private Categoria categoria;
	
	//esta anotacion lo que hara es que no se guardara en ll abase de datos solo sera temporal 	 
	@Transient
	private MultipartFile  portada;

	public Anime() {
		super();
	}

	public Anime(int id, @NotBlank String titulo, @NotBlank String sinopsis, @NotNull LocalDate fechaEstreno,
			@NotBlank String urlAnime, @NotBlank String capitulos, @NotBlank String youtubeTrailerId,
			String rutaPortada, @NotEmpty(message = "Debe seleccionar al menos un género") List<Genero> generos,
			@NotNull Categoria categoria, MultipartFile portada) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.sinopsis = sinopsis;
		this.fechaEstreno = fechaEstreno;
		this.urlAnime = urlAnime;
		this.capitulos = capitulos;
		this.youtubeTrailerId = youtubeTrailerId;
		this.rutaPortada = rutaPortada;
		this.generos = generos;
		this.categoria = categoria;
		this.portada = portada;
	}
	public Anime(@NotBlank String titulo, @NotBlank String sinopsis, @NotNull LocalDate fechaEstreno,
			@NotBlank String urlAnime, @NotBlank String capitulos, @NotBlank String youtubeTrailerId,
			String rutaPortada, @NotEmpty(message = "Debe seleccionar al menos un género") List<Genero> generos,
			@NotNull Categoria categoria, MultipartFile portada) {
		super();
		this.titulo = titulo;
		this.sinopsis = sinopsis;
		this.fechaEstreno = fechaEstreno;
		this.urlAnime = urlAnime;
		this.capitulos = capitulos;
		this.youtubeTrailerId = youtubeTrailerId;
		this.rutaPortada = rutaPortada;
		this.generos = generos;
		this.categoria = categoria;
		this.portada = portada;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	public LocalDate getFechaEstreno() {
		return fechaEstreno;
	}

	public void setFechaEstreno(LocalDate fechaEstreno) {
		this.fechaEstreno = fechaEstreno;
	}

	public String getYoutubeTrailerId() {
		return youtubeTrailerId;
	}

	public void setYoutubeTrailerId(String youtubeTrailerId) {
		this.youtubeTrailerId = youtubeTrailerId;
	}

	public String getRutaPortada() {
		return rutaPortada;
	}

	public void setRutaPortada(String rutaPortada) {
		this.rutaPortada = rutaPortada;
	}

	public List<Genero> getGeneros() {
		return generos;
	}

	public void setGeneros(List<Genero> generos) {
		this.generos = generos;
	}

	public MultipartFile getPortada() {
		return portada;
	}

	public void setPortada(MultipartFile portada) {
		this.portada = portada;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getUrlAnime() {
		return urlAnime;
	}

	public void setUrlAnime(String urlAnime) {
		this.urlAnime = urlAnime;
	}

	public String getCapitulos() {
		return capitulos;
	}

	public void setCapitulos(String capitulos) {
		this.capitulos = capitulos;
	}

}
