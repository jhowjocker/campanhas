package br.com.campanhas.app.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.campanhas.app.model.Campanhas;
import br.com.campanhas.app.model.Clube;
import br.com.campanhas.app.repository.CampanhasRepository;
import br.com.campanhas.app.repository.ClubeRepository;
import br.com.campanhas.app.request.CampanhasRequest;
import br.com.campanhas.app.request.ClubeRequest;
import br.com.campanhas.app.response.BaseResponse;

@Service
public class CampanhasService {

	@Autowired
	CampanhasRepository repository;

	@Autowired
	ClubeRepository _repository;

	public BaseResponse inserir(CampanhasRequest request) {
		BaseResponse baseResponse = new BaseResponse();
		Campanhas camp = new Campanhas();
		baseResponse.statusCode = 400;

		boolean validaNome = verificaNome(request);
		if (validaNome) {
			alteraNome();
			baseResponse.message = "Campanha existente. Tente outro nome!";
			return baseResponse;

		}
					
		boolean validaId = verificaId(request);
		if (validaId) {
			adicionaId();
			
		}

		boolean valida = verifica(request);
		if (valida) {
			adicionaDia();
		}

		camp.setNome(request.getNome());
		//camp.setClube(request.getIdClube());
		camp.setDataInicio(request.getDataInicio());
		camp.setDataFim(request.getDataFim());
		
		
		repository.save(camp);
		return new BaseResponse(201, "Campanha inserida com sucesso!");

	}

	public Boolean verificaNome(CampanhasRequest campanhas) {
		List<Campanhas> nomeOk = repository.findAll();
		for (Campanhas validaNome : nomeOk) {
			if (validaNome.getNome().equals(campanhas.getNome())) {
				return true;

			}
		}
		return false;
	}

	public Boolean verificaId(CampanhasRequest clube) {
		List<Clube> lista = _repository.findAll();
		for (Clube validaId : lista) {
			if (validaId.getId().equals(clube.getIdClube())) {
				return true;

			}
		}
		return false;
	}

	public boolean verifica(CampanhasRequest campanhas) {
		List<Campanhas> geral = repository.findAll();
		for (Campanhas seleciona : geral) {
			if (seleciona.getDataFim().equals(campanhas.getDataFim())) {
				return true;
			}

		}

		return false;
	}

	public List<Campanhas> alteraNome() {
		List<Campanhas> nova = new ArrayList<>();
		List<Campanhas> geral = repository.findAll();
		for (Campanhas seleciona : geral) {
			seleciona.setNome(seleciona.getNome());
			Campanhas save = repository.save(seleciona);
		}

		return nova;
	}

	
	
	public List<Clube> adicionaId() {

		List<Clube> nova = new ArrayList<>();
		List<Clube> geral = _repository.findAll();
		for (Clube seleciona : geral) {
			seleciona.setId(seleciona.getId());
			Clube save = _repository.save(seleciona);

		}

		return nova;
	}

	public List<Campanhas> adicionaDia() {
		List<Campanhas> nova = new ArrayList<>();
		List<Campanhas> geral = repository.findAll();
		for (Campanhas seleciona : geral) {
			seleciona.setDataFim(seleciona.getDataFim().plusDays(1));
			Campanhas save = repository.save(seleciona);
		}

		return nova;
	}

	public List<Campanhas> listar() {
		BaseResponse response = new BaseResponse();

		List<Campanhas> atual = new ArrayList<>();
		List<Campanhas> lista = repository.findAll();
		for (Campanhas campanhas : lista) {
			if (campanhas.getDataFim().isAfter(LocalDate.now())) {
				Campanhas save = repository.save(campanhas);
				atual.add(save);

			}

		}

		response.statusCode = 200;
		response.message = "Lista obtida com sucesso!";

		return atual;
	}

	public BaseResponse atualizar(Long id, Campanhas campanhaSpec) {
		Campanhas campanhas = new Campanhas();
		BaseResponse base = new BaseResponse();
		base.statusCode = 400;

		campanhas.setId(id);
		campanhas.setNome(campanhaSpec.getNome());
		campanhas.setDataInicio(campanhaSpec.getDataInicio());
		campanhas.setDataFim(campanhaSpec.getDataFim());

		repository.save(campanhas);
		base.statusCode = 200;
		base.message = "Dados alterados com sucesso!";
		return base;
	}

	public BaseResponse deletar(Long id) {
		BaseResponse response = new BaseResponse();

		repository.deleteById(id);
		response.statusCode = 200;
		response.message = "Dados excluidos com sucesso!";
		return response;
	}
}
