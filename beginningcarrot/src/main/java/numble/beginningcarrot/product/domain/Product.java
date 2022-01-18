package numble.beginningcarrot.product.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

	private Product(String title, String content, Integer price) {
		this.title = title;
		this.content = content;
		this.price = price;
		this.status = SellStatus.SELLING;
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
