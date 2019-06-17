package com.projetofinal.controller;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.projetofinal.model.Filme;
import com.projetofinal.model.Personagem;
import com.projetofinal.model.Planeta;

public class Printer {

    public Printer() { }

    API api = new API();
    GetRequestRepository repository = new GetRequestRepository(api);

    public static List<Filme> printDetailsFilms(JsonArray results) {
    	
    	List<Filme> filmes = new ArrayList<>();
    	
        if (results.size() != 0) {
        	
        	for (int i = 0; i < results.size(); i++) {
        		Filme filme = new Filme();
                JsonObject film = results.get(i).getAsJsonObject();
                filme.setTitle( film.get("title").toString() );
                filme.setEpisode_id( film.get("episode_id").toString() );
                filme.setDirector( film.get("director").toString() );
                filme.setProducer( film.get("producer").toString() );
                filme.setRelease_date( film.get("release_date").toString() );
                String url = film.get("url").toString();
                String idUrl = url.charAt( url.length() - 3) + ""; 
                filme.setUrl( idUrl );
                filmes.add(filme);
        	}
        }
        
        return filmes;
    }
    
    
    public static List<Planeta> printDetailsPlanets(JsonArray planetresults) {

    	List<Planeta> planetas = new ArrayList<>();
    	
        if (planetresults.size() != 0)
            for (int i = 0; i < planetresults.size(); i++) {
            	Planeta planeta = new Planeta();
                JsonObject planet = planetresults.get(i).getAsJsonObject();
                planeta.setName( planet.get("name").toString() );
                planeta.setRotation_period( planet.get("rotation_period").toString() );
                planeta.setOrbital_period( planet.get("orbital_period").toString() );
                planeta.setDiameter( planet.get("diameter").toString() );
                planeta.setClimate( planet.get("climate").toString() );
                planeta.setGravity( planet.get("gravity").toString() );
                planeta.setTerrain( planet.get("terrain").toString() );
                planeta.setSurface_water( planet.get("surface_water").toString() );
                planeta.setPopulation( planet.get("population").toString() );
                planetas.add(planeta);
            }
        
        return planetas;
    }
    
    public static List<Personagem> printDetailsPeoples(JsonArray peopleresults) {

    	List<Personagem> personagens = new ArrayList<>();
    	
        if (peopleresults.size() != 0)
            for (int i = 0; i < peopleresults.size(); i++) {
            	Personagem personagem = new Personagem();
                JsonObject people = peopleresults.get(i).getAsJsonObject();
                personagem.setName( people.get("name").toString() );
                personagem.setHeight( people.get("height").toString() );
                personagem.setMass( people.get("mass").toString() );
                personagem.setHair_color( people.get("hair_color").toString() );
                personagem.setSkin_color( people.get("skin_color").toString() );
                personagem.setEye_color( people.get("eye_color").toString() );
                personagem.setBirth_year( people.get("birth_year").toString() );
                personagem.setGender( people.get("gender").toString() );
                personagens.add(personagem);
            }
        
        return personagens;
    }
}