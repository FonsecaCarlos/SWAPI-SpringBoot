package com.projetofinal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projetofinal.model.Filme;

public interface FilmeRepository extends JpaRepository<Filme, Long>{

	public Filme findFilmeByUrl(String url);
	
	@Query("SELECT f FROM Filme f WHERE f.episode_id = :episode_id")
	public Filme findFilmeByEpisode_id(@Param("episode_id") String episode_id);
}