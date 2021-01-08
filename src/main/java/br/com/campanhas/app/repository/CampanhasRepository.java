package br.com.campanhas.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.campanhas.app.model.Campanhas;
import br.com.campanhas.app.request.CampanhasRequest;

@Repository
public interface CampanhasRepository extends  JpaRepository<Campanhas,Long> {

	Campanhas save(CampanhasRequest campanhasRequest);

	

	}