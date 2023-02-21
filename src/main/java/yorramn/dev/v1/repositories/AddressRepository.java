package yorramn.dev.v1.repositories;

import org.springframework.http.ResponseEntity;

public interface AddressRepository {
	public ResponseEntity<?> findAddress(String cep);
}
