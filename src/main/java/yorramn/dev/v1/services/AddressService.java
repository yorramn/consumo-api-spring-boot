package yorramn.dev.v1.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import yorramn.dev.v1.dtos.AddressDto;
import yorramn.dev.v1.enums.Regions;
import yorramn.dev.v1.repositories.AddressRepository;

@Service
public class AddressService implements AddressRepository {
	public static final String BASE_URL_VIACEP = "https://viacep.com.br/ws";
	
	@Override
	public ResponseEntity<?> findAddress(String cep) {
		cep = cep.replace(".", "").replace("-", "").replace(" ", "");
		
		try {
			ResponseEntity<AddressDto> responseEntity = this.findAddressByCepUsingExternalApi(cep);
			responseEntity.getBody().setFrete(calculateFreight(verifyRegion(cep)));
			return ResponseEntity.ok(responseEntity.getBody());	
		}catch(HttpClientErrorException httpClientErrorException) {
			return ResponseEntity.badRequest().body(String.format("Erro ao tentar buscar pelo CEP: %s \n Erro: %s", cep, httpClientErrorException.getMessage()));
		}
	}
	
	public ResponseEntity<AddressDto> findAddressByCepUsingExternalApi(String cep) {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate
				.getForEntity(String.format("%s/%s/json", BASE_URL_VIACEP, cep), AddressDto.class);
	}
	
	public String verifyRegion(String cep) {
		Integer validation = Integer.parseInt(cep.substring(0, 5));
		if(validation >= 01000 && validation <= 39999)
			return Regions.SUDESTE.getRegion();
		if(validation >= 40000 && validation <= 65999)
			return Regions.NORDESTE.getRegion();
		if(validation >= 66000 && validation <= 77999)
			return Regions.NORTE.getRegion();
		if(validation >= 80000 && validation <= 99999)
			return Regions.SUL.getRegion();
		return Regions.CENTRO_OESTE.getRegion();
	}

	public double calculateFreight(String region) {
		if(Regions.SUDESTE.getRegion().equals(region))
			return 7.85;
		if(Regions.NORDESTE.getRegion().equals(region))
			return 15.98;
		if(Regions.NORTE.getRegion().equals(region))
			return 20.83;
		if(Regions.SUL.getRegion().equals(region))
			return 17.30;
		return 12.50;
	}
}
