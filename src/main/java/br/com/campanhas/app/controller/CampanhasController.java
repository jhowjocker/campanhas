package br.com.campanhas.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.campanhas.app.model.Campanhas;
import br.com.campanhas.app.repository.CampanhasRepository;
import br.com.campanhas.app.request.CampanhasRequest;
import br.com.campanhas.app.response.BaseResponse;
import br.com.campanhas.app.service.CampanhasList;
import br.com.campanhas.app.service.CampanhasService;

@RestController
@RequestMapping("/campanha")
public class CampanhasController {

	@Autowired
	private CampanhasService service;

	@Autowired
	private CampanhasRepository repository;

	@PostMapping
	public ResponseEntity inserir(@RequestBody CampanhasRequest request) {
		try {
			BaseResponse response = service.inserir(request);
			return ResponseEntity.status(response.statusCode).body(response);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: Campanha não cadastrada!");
		}
	}

	@GetMapping
	public ResponseEntity listar() {
		try {
			List<Campanhas> campanhas = service.listar();
			return ResponseEntity.status(HttpStatus.OK).body(campanhas);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: Campanha não encontrada!");
		}

	}

	@PutMapping(path = "/{id}")
	public ResponseEntity atualizar(@RequestBody Campanhas campanhaSpec, @PathVariable Long id) {

		try {
			BaseResponse response = service.atualizar(id, campanhaSpec);
			return ResponseEntity.status(response.statusCode).body(response);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: Campanha não atualizada!");
		}
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity deletar(@PathVariable Long id) {
		try {

			BaseResponse response = service.deletar(id);
			return ResponseEntity.status(response.statusCode).build();

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: Campanha não deletada!");
		}
	}

}
