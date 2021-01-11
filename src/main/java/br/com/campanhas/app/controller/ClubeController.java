//package br.com.campanhas.app.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import br.com.campanhas.app.repository.CampanhasRepository;
//import br.com.campanhas.app.request.CampanhasRequest;
//import br.com.campanhas.app.response.BaseResponse;
//import br.com.campanhas.app.service.CampanhasService;
//
//@RestController
//@RequestMapping("/campanha")
//public class ClubeController {
//
//	@Autowired
//	private CampanhasService service;
//
//	@Autowired
//	private CampanhasRepository repository;
//
//	@PostMapping
//	public ResponseEntity inserir(@RequestBody CampanhasRequest request) {
//		try {
//			BaseResponse response = service.inserir(request);
//			return ResponseEntity.status(response.statusCode).body(response);
//
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: Tente novamente!");
//		}
//	}
//}
