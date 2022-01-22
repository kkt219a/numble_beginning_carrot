package numble.beginningcarrot.product.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import numble.beginningcarrot.product.domain.Product;
import numble.beginningcarrot.product.dto.ProductCreateRequest;
import numble.beginningcarrot.product.service.ProductService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

	private final ProductService productService;
	/**
	 * Product를 페이지 별로 DTO로 반환
	 */
	@GetMapping
	public String findAllProduct(Model model) {
		List<Product> products = productService.findAll();
		model.addAttribute("products", products);
		return "home";
	}

	@GetMapping("/new")
	public String createProductForm(Model model) {
		model.addAttribute("productForm",new ProductCreateRequest());
		return "new_product";
	}

	/**
	 * 특정 Product 상세보기
	 * @param productId product의 Id
	 * @return 특정 Product의 DTO
	 */
	@GetMapping("/{productId}")
	public ResponseEntity<Void> findProduct(@PathVariable Long productId) {
		return ResponseEntity.ok().build();
	}

	/**
	 * 새 Product 생성
	 */
	@PostMapping("/new")
	public ResponseEntity<Void> createProduct(@Valid @ModelAttribute ProductCreateRequest request) {
		productService.addProduct(request.toProduct(), request.getImages());
		return ResponseEntity.ok().build();
	}

	/**
	 * 특정 Product 수정
	 * @param productId product의 Id
	 */
	@PatchMapping("/{productId}")
	public ResponseEntity<Void> updateProduct(@PathVariable Long productId) {
		return ResponseEntity.ok().build();
	}

	/**
	 * 특정 Product 삭제
	 * @param productId product의 Id
	 */
	@DeleteMapping("/{productId}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
		return ResponseEntity.ok().build();
	}
}
