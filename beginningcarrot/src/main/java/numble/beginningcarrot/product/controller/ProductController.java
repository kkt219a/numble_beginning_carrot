package numble.beginningcarrot.product.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

	/**
	 * Product를 페이지 별로 DTO로 반환
	 */
	@GetMapping
	public ResponseEntity<Void> findAllProduct() {
		return ResponseEntity.ok().build();
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
	@PostMapping
	public ResponseEntity<Void> createProduct() {
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
