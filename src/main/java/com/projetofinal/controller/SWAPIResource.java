package com.projetofinal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projetofinal.dao.FilmeRepository;
import com.projetofinal.model.Filme;

@RestController
public class SWAPIResource {

	@Autowired
	private FilmeRepository filmeRepository;
	
	@RequestMapping(value = "/film", method = RequestMethod.GET)
	public ResponseEntity<List<Filme>> listar() {
		
		List<Filme> filmes = filmeRepository.findAll();
	    
		return new ResponseEntity<List<Filme>>( filmes, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/film/{episodio}", method = RequestMethod.GET)
	public ResponseEntity<Filme> listarPorSalario(@PathVariable("episodio") String episodio) {
		
		Filme filme = filmeRepository.findFilmeByEpisode_id(episodio);
		
	    return new ResponseEntity<Filme>( filme, HttpStatus.OK);
	}
}