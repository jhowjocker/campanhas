package br.com.campanhas.app.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import br.com.campanhas.app.model.Campanhas;
import br.com.campanhas.app.model.Clube;
import br.com.campanhas.app.repository.CampanhasRepository;
import br.com.campanhas.app.repository.ClubeRepository;
import br.com.campanhas.app.request.CampanhasRequest;
import br.com.campanhas.app.response.BaseResponse;

@Service
public class CampanhasService {

	@Autowired
	private CampanhasRepository campanhaRepository;

	@Autowired
	private ClubeRepository clubeRepository;

	public BaseResponse inserir(CampanhasRequest campanhaRequest) {
		BaseResponse baseResponse = new BaseResponse();
		Campanhas camp = new Campanhas();
		baseResponse.statusCode = 400;

		boolean validaNome = verificaNome(campanhaRequest);
		if (validaNome) {
			baseResponse.message = "Campanha existente. Tente outro nome!";
			return baseResponse;

		}

		boolean valida = verifica(campanhaRequest);
		if (valida) {
			adicionaDia();
		}

		Clube clube = clubeRepository.findByNome(campanhaRequest.getTime());

		if (clube == null) {
			baseResponse.message = "Nome do time não existe!";
			return baseResponse;
		}

		camp.setNome(campanhaRequest.getNome());
		camp.setClube(clube);
		camp.setDataInicio(campanhaRequest.getDataInicio());
		camp.setDataFim(campanhaRequest.getDataFim());

		campanhaRepository.save(camp);
		return new BaseResponse(201, "Campanha inserida com sucesso!");

	}

	public Boolean verificaNome(CampanhasRequest campanhas) {
		List<Campanhas> nomeOk = campanhaRepository.findAll();
		for (Campanhas validaNome : nomeOk) {
			if (validaNome.getNome().equals(campanhas.getNome())) {
				return true;

			}
		}
		return false;
	}

	public boolean verifica(CampanhasRequest campanhas) {
		List<Campanhas> geral = campanhaRepository.findAll();
		for (Campanhas seleciona : geral) {
			if (seleciona.getDataFim().equals(campanhas.getDataFim())) {
				return true;
			}
		}

		return false;
	}

	public List<Campanhas> adicionaDia() {
		List<Campanhas> nova = new ArrayList<>();
		List<Campanhas> geral = campanhaRepository.findAll();
		for (Campanhas seleciona : geral) {
			seleciona.setDataFim(seleciona.getDataFim().plusDays(1));
			Campanhas save = campanhaRepository.save(seleciona);
		}

		return nova;
	}

	public List<Campanhas> listar() {
		BaseResponse response = new BaseResponse();

		List<Campanhas> atual = new ArrayList<>();
		List<Campanhas> lista = campanhaRepository.findAll();
		for (Campanhas campanhas : lista) {
			if (campanhas.getDataFim().isAfter(LocalDate.now())) {
				Campanhas save = campanhaRepository.save(campanhas);
				atual.add(save);

			}

		}

		response.statusCode = 200;
		response.message = "Lista obtida com sucesso!";

		return atual;
	}

	public BaseResponse atualizar(Long id, CampanhasRequest campanhasRequest) {
		Campanhas campanhas = new Campanhas();
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.statusCode = 400;
		
		Clube clube = clubeRepository.findByNome(campanhasRequest.getTime());

		if (clube == null) {
			baseResponse.message = "Nome do time não existe!";
			return baseResponse;
		}
		
		campanhas.setId(id);
		campanhas.setNome(campanhasRequest.getNome());
		campanhas.setClube(clube);
		campanhas.setDataInicio(campanhasRequest.getDataInicio());
		campanhas.setDataFim(campanhasRequest.getDataFim());

		campanhaRepository.save(campanhas);
		baseResponse.statusCode = 200;
		baseResponse.message = "Dados alterados com sucesso!";
		return baseResponse;

	}

	public BaseResponse deletar(Long id) {
		BaseResponse response = new BaseResponse();

		campanhaRepository.deleteById(id);
		response.statusCode = 200;
		response.message = "Dados excluidos com sucesso!";
		return response;
	}
}