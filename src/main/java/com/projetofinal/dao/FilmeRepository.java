package com.projetofinal.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetofinal.model.Filme;

public interface FilmeRepository extends JpaRepository<Filme, Long>{

	public Filme findFilmeByUrl(String url);
}
