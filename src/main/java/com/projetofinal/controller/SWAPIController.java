package com.projetofinal.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.projetofinal.dao.FilmeRepository;
import com.projetofinal.model.Filme;
import com.projetofinal.model.Personagem;
import com.projetofinal.model.Planeta;

@Controller
public class SWAPIController {
	
	@Autowired
	private FilmeRepository filmeRepository;
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping("/filme/{url}")
	public String salvaFilme(@PathVariable("url") String url) {
		Filme filmeTest = filmeRepository.findFilmeByUrl(url);
		
		if(filmeTest!=null)
			return "redirect:/meusfilmes";

		API api = new API();
		
	    GetRequestRepository repository = new GetRequestRepository(api);
		
	    JsonObject film = repository.innerRequest("https://swapi.co/api/films/"+url);
	    
	    Filme filme = new Filme();
	    
	    filme.setTitle( film.get("title").toString() );
        filme.setEpisode_id( film.get("episode_id").toString() );
        filme.setDirector( film.get("director").toString() );
        filme.setProducer( film.get("producer").toString() );
        filme.setRelease_date( film.get("release_date").toString() );
        filme.setUrl( url );
        
        filmeRepository.save(filme);
		
		return "redirect:/meusfilmes";
	}
	
	@GetMapping("/removefilme/{url}")
	public String removeFilme(@PathVariable("url") String url) {
		Filme filme = filmeRepository.findFilmeByUrl(url);
		
		filmeRepository.delete(filme);
		
		return "redirect:/meusfilmes";
	}
	
	@GetMapping("/meusfilmes")
	public String meusfilmes(Model model) {
	    List<Filme> filmes = new ArrayList<>();
	    
	    filmes = filmeRepository.findAll();
	    
        model.addAttribute("filmes", filmes);
        
		return "meusFilmes";
	}
	
	@GetMapping("/filmes")
	public String filmes(Model model) {
		
		API api = new API();
		
	    GetRequestRepository repository = new GetRequestRepository(api);
		
	    JsonObject jsonObject = repository.innerRequest("https://swapi.co/api/films/");
	    
	    JsonArray results = jsonObject.getAsJsonArray("results");
	    
	    List<Filme> filmes = new ArrayList<>();
	    
	    filmes = Printer.printDetailsFilms(results);
	    
        model.addAttribute("filmes", filmes);
        
		return "filmes";
	}
	
	@RequestMapping("/planetas/{page}")
	public String planetas(@PathVariable("page") String page, Model model) {
		
		API api = new API();
		
	    GetRequestRepository repository = new GetRequestRepository(api);
		
	    JsonObject jsonObject = repository.getAll("planets", page);
	    
	    JsonArray results = jsonObject.getAsJsonArray("results");
	    
	    List<Planeta> planetas = new ArrayList<>();
	    
	    planetas = Printer.printDetailsPlanets(results);
	    
	    String next = jsonObject.get("next").toString();
	    String previous = jsonObject.get("previous").toString();
	    
        model.addAttribute("planetas", planetas);
        model.addAttribute("next", next.equals("null") ? "1" : next.charAt(next.length()-2));
        model.addAttribute("previous", previous.equals("null") ? "1" : previous.charAt(previous.length()-2));
		
        return "planetas";
	}
	
	@RequestMapping("/personagens/{page}")
	public String personagens(@PathVariable("page") String page, Model model) {
		
		API api = new API();
		
	    GetRequestRepository repository = new GetRequestRepository(api);
		
	    JsonObject jsonObject = repository.getAll("people", page);
	    
	    JsonArray results = jsonObject.getAsJsonArray("results");
	    
	    List<Personagem> personagens = new ArrayList<>();
	    
	    personagens = Printer.printDetailsPeoples(results);
	    
	    String next = jsonObject.get("next").toString();
	    String previous = jsonObject.get("previous").toString();
	    
        model.addAttribute("personagens", personagens);
        model.addAttribute("next", next.equals("null") ? "1" : next.charAt(next.length()-2));
        model.addAttribute("previous", previous.equals("null") ? "1" : previous.charAt(previous.length()-2));
		
        return "personagens";
	}
	
}