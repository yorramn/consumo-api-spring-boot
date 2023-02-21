package yorramn.dev;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import yorramn.dev.v1.services.AddressService;

@SpringBootTest
public class FindAddressByCep {
	@Autowired
	private AddressService addressService;

	@Test
	void tryGetLogradouroAddressByCep() {
		Assertions.assertEquals("Avenida Ibirapuera", 
				this.addressService.findAddressByCepUsingExternalApi("04028003").getBody().getLogradouro());
	}
}
