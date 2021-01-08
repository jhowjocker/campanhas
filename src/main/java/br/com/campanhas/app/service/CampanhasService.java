package br.com.campanhas.app.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.campanhas.app.model.Campanhas;
import br.com.campanhas.app.repository.CampanhasRepository;
import br.com.campanhas.app.request.CampanhasRequest;
import br.com.campanhas.app.response.BaseResponse;

@Service
public class CampanhasService {

	@Autowired
	CampanhasRepository repository;

	public BaseResponse inserir(CampanhasRequest request) {
		Campanhas camp = new Campanhas();

		boolean valida = verifica(request);
		if (valida) {
			acrescentaDia();
		}
		camp.setNome(request.getNome());
		camp.setDataInicio(request.getDataInicio());
		camp.setDataFim(request.getDataFim());

		repository.save(camp);
		return new BaseResponse(201, "Campanha inserida com sucesso!");

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

	public List<Campanhas> acrescentaDia() {
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
