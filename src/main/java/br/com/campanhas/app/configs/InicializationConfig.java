package br.com.campanhas.app.configs;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import br.com.campanhas.app.model.Clube;
import br.com.campanhas.app.repository.ClubeRepository;

@Configuration
public class InicializationConfig {
	
	@Autowired
	private ClubeRepository clubeRepository;
	
	@PostConstruct
	public void inicializacao() {
		
		if (clubeRepository.count() > 0) {
			return;			
		}
		
		Clube clube = new Clube();
		clube.setNome("Corinthians");
		clubeRepository.save(clube);
		
		clube = new Clube();
		clube.setNome("Palmeiras");
		clubeRepository.save(clube);
		
		clube = new Clube();
		clube.setNome("Santos");
		clubeRepository.save(clube);
	}
}
