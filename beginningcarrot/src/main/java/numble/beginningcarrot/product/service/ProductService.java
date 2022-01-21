package numble.beginningcarrot.product.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import numble.beginningcarrot.product.domain.Product;
import numble.beginningcarrot.product.domain.ProductRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {
	private final ProductRepository productRepository;
	private final ProductImageUploadService productImageUploadService;

	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public void addProduct(Product product, List<MultipartFile> images) {
	}
}
