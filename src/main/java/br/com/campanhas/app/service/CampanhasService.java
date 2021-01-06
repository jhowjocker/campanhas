package br.com.campanhas.app.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.campanhas.app.model.Campanhas;
import br.com.campanhas.app.repository.CampanhasRepository;
import br.com.campanhas.app.request.CampanhasRequest;
import br.com.campanhas.app.response.BaseResponse;
import br.com.campanhas.app.response.CampanhasResponse;

@Service
public class CampanhasService {

	@Autowired
	CampanhasRepository repository;

	public BaseResponse inserir(CampanhasRequest request) {
			
			Campanhas campanha = new Campanhas();
//			SimpleDateFormat inicio = new SimpleDateFormat();
//			SimpleDateFormat fim = new SimpleDateFormat();
			BaseResponse base = new BaseResponse();
			
			
			if (request.getNome() == "" || request.getNome() == null)
				return new BaseResponse(400, "Nome da campanha não informado!");
										
			if(request.getDataInicio() == "" ||request.getDataInicio() == null)
				return new BaseResponse(400, "Data inicio da campanha não informado!");
						
			if(request.getDataFim() == "" || request.getDataFim() == null)
				return new BaseResponse(400, "Data fim da campanha não informado!");
						
			campanha.setNome(request.getNome());
			campanha.setDataInicio(request.getDataInicio());
			campanha.setDataFim(request.getDataFim());
			
			repository.save(campanha);
			return new BaseResponse(201, "Campanha inserida com sucesso!");
			
		}

		

	public CampanhasResponse obter(long id) {

		Optional<Campanhas> campanha = repository.findById(id);

		CampanhasResponse response = new CampanhasResponse();

		if (campanha.get().getId() == 0) {
			response.statusCode = 400;
			response.message = "Id não encontrado";
			return response;
		}

		response.setId(campanha.get().getId());
		response.setNome(campanha.get().getNome());
		response.message = "Campanha obtido com sucesso";
		return response;

	}

}
