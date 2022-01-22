package numble.beginningcarrot.product.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import numble.beginningcarrot.common.valid.IsValidListSize;
import numble.beginningcarrot.product.domain.Product;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateRequest {
	@NotBlank(message = "제목을 입력해주세요.")
	@Size(min=1,max=255,message = "제목을 확인해주세요.")
	private String title;

	@NotBlank(message = "카테고리를 입력해주세요.")
	@Size(min=1,max=255,message = "카테고리를 확인해주세요.")
	private String category;

	@NotNull(message = "가격을 입력해주세요.")
	@Range(min=0,max=Integer.MAX_VALUE, message = "가격을 올바르게 입력해주세요.")
	private Integer price;

	@NotBlank(message = "내용 미입력")
	@Size(min=1,max=65535, message = "내용을 확인해주세요.")
	private String content;

	@IsValidListSize(min=1, max=10,message = "첨부파일은 최소 1개, 최대 10개까지 첨부가능합니다.")
	private List<MultipartFile> images = new ArrayList<>();

	public Product toProduct() {
		return Product.create(title, content, price);
	}
}
