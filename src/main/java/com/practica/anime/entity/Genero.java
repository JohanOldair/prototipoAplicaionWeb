package com.practica.anime.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Genero {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_genero")
	private int id;
	
	private String titulo;

	public Genero(int id, String titulo) {
		super();
		this.id = id;
		this.titulo = titulo;
	}

	public Genero() {
		super();
	}

	public Genero(String titulo) {
		super();
		this.titulo = titulo;
	}

	public Genero(int id) {
		super();
		this.id = id;
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

	
	
}
