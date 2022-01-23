package numble.beginningcarrot.common.file.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import numble.beginningcarrot.common.file.domain.File;
import numble.beginningcarrot.common.file.domain.FileChildType;
import numble.beginningcarrot.common.file.domain.FileConverterFactory;
import numble.beginningcarrot.s3.S3FileDto;
import numble.beginningcarrot.s3.S3Strategy;

@Service
@RequiredArgsConstructor
public class UploadService {

	private final S3Strategy s3Strategy;
	private final FileConverterFactory fileConverterFactory;

	public File addFileToStorage(MultipartFile uploadFile, FileChildType fileChildType) throws IOException {
		S3FileDto s3FileDto = s3Strategy.uploadFile(uploadFile, fileChildType.toString());
		File file = fileConverterFactory.moduleConvertToFile(s3FileDto);
		return fileConverterFactory.toChildFile(file, fileChildType);
	}

	public void deleteFileFromStorage(File originFile) {
		S3FileDto s3FileDto = fileConverterFactory.fileConvertToModule(originFile);
		s3Strategy.deleteFile(s3FileDto);
	}

	public String getFileUrlFromStorage(File file) {
		return s3Strategy.getFileUrl(file);
	}

}
