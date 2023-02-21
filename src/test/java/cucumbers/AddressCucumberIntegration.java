package cucumbers;

import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.web.client.RestTemplate;

import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import yorramn.dev.v1.dtos.AddressDto;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/cucumbers")
public class AddressCucumberIntegration {
	@When("O client recebe o status da requisição")
	public void theClientCallIssuesGetAddressAndReceiveStatusCode() throws Throwable{
	    RestTemplate restTemplate = new RestTemplate();
	    Assertions.assertEquals(200, restTemplate.getForEntity("http://localhost:8080/v1/consulta-endereco/04028003", AddressDto.class).getStatusCode());
	}
	
	@When("O client recebe os dados do endereço da requisição")
	public void theClientCallIssuesGetAddressAndReceiveUfAddress() throws Throwable{
	    RestTemplate restTemplate = new RestTemplate();
	    Assertions.assertEquals("SP", restTemplate.getForEntity("http://localhost:8080/v1/consulta-endereco/04028003", 
	    		AddressDto.class).getBody().getUf());
	} 
}
