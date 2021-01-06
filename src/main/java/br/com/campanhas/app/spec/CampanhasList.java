package br.com.campanhas.app.spec;

import java.util.List;

import br.com.campanhas.app.model.Campanhas;
import br.com.campanhas.app.response.BaseResponse;

public class CampanhasList extends BaseResponse {
	
	private List<Campanhas> Campanha;
	
	public List<Campanhas> getCampanhas(){
			return Campanha;
			
	}
	
	public void setCampanhas(List<Campanhas> campanhas) {
		campanhas = Campanha;
	}

}
