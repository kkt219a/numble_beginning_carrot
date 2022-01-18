package numble.beginningcarrot.product.domain;

import lombok.Getter;

@Getter
public enum SellStatus {
	SELLING("판매중"), RESERVING("예약중"), SOLD_OUT("판매완료");

	private final String message;

	SellStatus(String message) {
		this.message = message;
	}
}
