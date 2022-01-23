package numble.beginningcarrot.common.file.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import numble.beginningcarrot.common.file.domain.File;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReadFileResponse {
	private String fileName;
	private String folderName;
	private String fileUrl;

	public static ReadFileResponse of(File file, String fileUrl) {
		if (file == null) {
			return null;
		}
		return new ReadFileResponse(
			file.getFileName(),
			file.getFolderName(),
			fileUrl
		);
	}

	public static ReadFileResponse of(String fileName, String folderName, String fileUrl) {
		return new ReadFileResponse(fileName, folderName, fileUrl);
	}
}
