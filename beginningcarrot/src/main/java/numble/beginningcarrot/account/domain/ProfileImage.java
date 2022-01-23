package numble.beginningcarrot.account.domain;

import java.util.Objects;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import numble.beginningcarrot.common.file.domain.File;

@Entity
@DiscriminatorValue(value = "Profile")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProfileImage extends File {
	/** Only Relation Mapping N:1 **/
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACCOUNT_ID")
	private Account account;

	public ProfileImage(String fileName, String folderName, Long fileSize) {
		super(fileName, folderName, fileSize);
	}

	public void addAccount(Account account) {
		Objects.requireNonNull(account);
		this.account = account;
	}
}
