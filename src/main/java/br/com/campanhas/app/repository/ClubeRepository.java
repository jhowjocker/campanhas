package br.com.campanhas.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.campanhas.app.model.Clube;

@Repository
public interface ClubeRepository extends JpaRepository<Clube, Long> {

	public Clube findByNome(String time);
}