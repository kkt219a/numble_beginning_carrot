package numble.beginningcarrot.account.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {
	@Id
	private String id;
	private String password;
	private String name;
	private String phoneNumber;
	private String nickname;
	private boolean emailAuthorization;
	private boolean locationAuthorization;

	@OneToOne(mappedBy = "account",
		cascade = CascadeType.ALL,
		orphanRemoval = true,
		fetch = FetchType.LAZY)
	private ProfileImage profileImage;

	public Account(String id, String password, String name, String phoneNumber, String nickname) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.nickname = nickname;
		this.emailAuthorization = false;
		this.locationAuthorization = false;
	}

	public static Account create(String id, String password, String name, String phoneNumber, String nickname) {
		return new Account(id,password,name,phoneNumber,nickname);
	}
}
