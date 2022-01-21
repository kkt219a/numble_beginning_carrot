package numble.beginningcarrot.product.domain;

import java.util.Objects;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import numble.beginningcarrot.file.domain.File;

@Entity
@DiscriminatorValue(value="Product")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductImage extends File {
	/** Only Relation Mapping N:1 **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="PRODUCT_ID")
	private Product product;

	public ProductImage(String fileName, String folderName, Long fileSize) {
		super(fileName, folderName, fileSize);
	}

	public void addProduct(Product product) {
		Objects.requireNonNull(product);
		this.product = product;
	}

}
