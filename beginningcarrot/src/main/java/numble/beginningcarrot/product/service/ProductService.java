package numble.beginningcarrot.product.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import numble.beginningcarrot.product.domain.Product;
import numble.beginningcarrot.product.domain.ProductRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {
	private final ProductRepository productRepository;

	public List<Product> findAll() {
		return productRepository.findAll();
	}
}
