package numble.beginningcarrot.common.file.domain;

import org.springframework.stereotype.Component;

import numble.beginningcarrot.account.domain.ProfileImage;
import numble.beginningcarrot.product.domain.ProductImage;
import numble.beginningcarrot.s3.S3FileDto;

/**
 * 팩토리 패턴
 * 1. File(protected constructor)을 public으로 바꿀수 없기에, 직접 생성할 위험을 제거하기 위함
 * 2. S3에서는 모두 동일한 UploadFile을 반환해줘야하기 때문에, 각 엔티티가 가지는 파일에 맞게 변환해주기 위함
 */
@Component
public class FileConverterFactory {

	public File toChildFile(File file, FileChildType fileChildType) {
		File returnFile = file;
		switch (fileChildType) {
			case product:
				returnFile = toProductImage(file);
				break;
			case profile:
				returnFile = toProfileImage(file);
				break;
		}
		return returnFile;
	}

	public File moduleConvertToFile(S3FileDto file) {
		return new File(file.getFileName(), file.getFolderName(), file.getFileSize());
	}

	public S3FileDto fileConvertToModule(File file) {
		return new S3FileDto(file.getFileName(), file.getFolderName(), file.getFileSize());
	}

	private ProductImage toProductImage(File file) {
		return new ProductImage(file.getFileName(), file.getFolderName(), file.getFileSize());
	}

	private ProfileImage toProfileImage(File file) {
		return new ProfileImage(file.getFileName(), file.getFolderName(), file.getFileSize());
	}
}
