//package br.com.campanhas.app.service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import br.com.campanhas.app.model.Campanhas;
//import br.com.campanhas.app.model.Clube;
//import br.com.campanhas.app.repository.ClubeRepository;
//import br.com.campanhas.app.request.CampanhasRequest;
//import br.com.campanhas.app.request.ClubeRequest;
//import br.com.campanhas.app.response.BaseResponse;
//
//@Service
//public class ClubeService {
//	@Autowired
//	ClubeRepository repository;
//	
//	public BaseResponse inserir(ClubeRequest request) {
//		BaseResponse baseResponse = new BaseResponse();
//		Clube clube = new Clube();
//		baseResponse.statusCode = 400;
//	
//	boolean validaId = verificaId(request);
//	if (validaId) {
//		adicionaId();
//		baseResponse.message = "Por favor, digite um ID de Clube cadastrado!";
//		return baseResponse;
//	}
//	
//	
//	public boolean verificaId(ClubeRequest Clube) {
//		List<Clube> geral = repository.findAll();
//		for (Clube seleciona : geral) {
//			if (seleciona.getClube().equals(clube.getClube())) {
//				return true;
//			}
//
//		}
//
//		return false;
//	}
//	
//	public List<Clube> adicionaId() {
//		List<Clube> nova = new ArrayList<>();
//		List<Clube> geral = repository.findAll();
//		for (Clube seleciona : geral) {
//			seleciona.setClube(seleciona.getClube());
//			Clube save = repository.save(seleciona);
//		}
//
//		return nova;
//	}
//
//}
