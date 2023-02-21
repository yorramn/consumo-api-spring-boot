package yorramn.dev.v1.controllers;

import java.util.regex.Pattern;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import yorramn.dev.v1.services.AddressService;

@RestController
@RequestMapping("v1/consulta-endereco")

public class AddressController {
	private AddressService addressService;
	
	public AddressController(AddressService addressService) {
		this.addressService = addressService;
	}

	@GetMapping("/{cep}")
	public ResponseEntity<?> index(@PathVariable(name = "cep") String cep) {
		if(cep.length() != 8 || !Pattern.compile("^\\d+$").matcher(cep).find())
			return ResponseEntity.badRequest().body("CEP inválido!");
		return this.addressService.findAddress(cep);
	}
}
