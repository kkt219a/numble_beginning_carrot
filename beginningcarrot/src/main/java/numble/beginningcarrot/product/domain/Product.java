package numble.beginningcarrot.product.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import numble.beginningcarrot.common.advice.exception.BusinessException;
import numble.beginningcarrot.common.advice.exception.ErrorCode;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	private String content;
	private Integer price;

	@Enumerated(EnumType.STRING)
	private SellStatus status;

	@OneToMany(
		mappedBy = "product",
		cascade = CascadeType.ALL,
		orphanRemoval = true
	)
	private List<ProductImage> images = new ArrayList<>();

	private Product(String title, String content, Integer price, List<ProductImage> images) {
		validateImageSize(images);
		this.title = title;
		this.content = content;
		this.price = price;
		this.status = SellStatus.SELLING;
		addImages(images);
	}

	public Product(String title, String content, Integer price) {
		this.title = title;
		this.content = content;
		this.price = price;
		this.status = SellStatus.SELLING;
	}

	private void addImages(List<ProductImage> images) {
		this.images = new ArrayList<>(images);
		for (ProductImage image : images) {
			image.addProduct(this);
		}
	}

	private void validateImageSize(List<ProductImage> images) {
		Objects.requireNonNull(images);
		if(images.size()<1) {
			throw new BusinessException(ErrorCode.FILE_NUMBER_INSUFFICIENT);
		}
	}

	public static Product create(String title, String content, Integer price, List<ProductImage> images) {
		return new Product(title,content,price, images);
	}

	public static Product create(String title, String content, Integer price) {
		return new Product(title,content,price);
	}

	public void update(String title, String content, Integer price) {
		this.title = title;
		this.content = content;
		this.price = price;
	}

}
