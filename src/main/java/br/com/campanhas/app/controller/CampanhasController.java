package br.com.campanhas.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.campanhas.app.request.CampanhasRequest;
import br.com.campanhas.app.response.BaseResponse;
import br.com.campanhas.app.response.CampanhasResponse;
import br.com.campanhas.app.service.CampanhasService;

@RestController
@RequestMapping("/campanha")
public class CampanhasController {

	@Autowired
	private CampanhasService service;

	@PostMapping
	public ResponseEntity inserir(@RequestBody CampanhasRequest request) {
		try {
			BaseResponse response = service.inserir(request);
			return ResponseEntity.status(response.statusCode).body(response);
		
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: Campanha não encontrada!");
		}
	}
		
		@GetMapping(path ="/{id}")
		
		public ResponseEntity obter(@PathVariable Long id) {
			try {
				CampanhasResponse response = service.obter(id);
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}catch (Exception e) {
				return null;
			}
		}
		
//		@GetMapping
//		public ResponseEntity listar() {
//			try {
//				Iterable<Campanhas> campanha = service.listar();
//				return ResponseEntity.status(HttpStatus.OK).body(campanha);
//			} catch (Exception e) {
//				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro na aplicação!");
//			}
	
}
