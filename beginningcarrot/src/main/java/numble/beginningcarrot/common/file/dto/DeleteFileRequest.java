package numble.beginningcarrot.common.file.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import numble.beginningcarrot.common.file.domain.File;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class DeleteFileRequest {

	private String fileName;
	private String folderName;

	public static DeleteFileRequest of(File file) {
		return new DeleteFileRequest(
			file.getFileName(),
			file.getFolderName()
		);
	}

	public File toFile() {
		return new File(fileName, folderName, null);
	}

}
