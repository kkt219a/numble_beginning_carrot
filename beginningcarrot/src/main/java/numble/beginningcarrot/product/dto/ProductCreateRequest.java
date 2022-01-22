package numble.beginningcarrot.product.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import numble.beginningcarrot.product.domain.Product;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateRequest {
	private String title;
	private String category;
	private Integer price;
	private String content;
	private List<MultipartFile> images;

	public Product toProduct() {
		return Product.create(title, content, price);
	}
}
