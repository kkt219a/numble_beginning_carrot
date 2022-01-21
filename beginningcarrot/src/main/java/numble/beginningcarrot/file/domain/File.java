package numble.beginningcarrot.file.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="FTYPE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class File {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(columnDefinition = "TEXT")
	private String fileName;
	private String folderName;
	private Long fileSize;

	public File(String fileName, String folderName, Long fileSize) {
		validateFile(fileName,folderName,fileSize);
		this.fileName = fileName;
		this.folderName = folderName;
		this.fileSize = fileSize;
	}

	private void validateFile(String fileName, String folderName, Long fileSize) {
		Objects.requireNonNull(fileName);
		Objects.requireNonNull(folderName);
		Objects.requireNonNull(fileSize);
	}

}
