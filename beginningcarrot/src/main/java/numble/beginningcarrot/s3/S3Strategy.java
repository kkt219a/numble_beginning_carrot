package numble.beginningcarrot.s3;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import numble.beginningcarrot.common.advice.exception.BusinessException;
import numble.beginningcarrot.common.advice.exception.ErrorCode;
import numble.beginningcarrot.common.file.domain.File;

@Service
@RequiredArgsConstructor
@Slf4j
public class S3Strategy {

	private final AmazonS3 amazonS3;
	private final S3Config s3Config;

	public S3FileDto uploadFile(MultipartFile file, String folder) throws IOException {
		checkFileValid(file);
		String fileName = file.getOriginalFilename();
		String folderName = getFolderName(folder);
		Long fileSize = file.getSize();
		amazonS3.putObject(new PutObjectRequest(s3Config.getBucket() + folderName, fileName, file.getInputStream(), null));
		log.info("AWS S3에 파일 저장 완료: "+folderName+fileName);
		return new S3FileDto(fileName,folderName+"/",fileSize);
	}

	public void deleteFile(S3FileDto file){
		String folderName = file.getFolderName();
		folderName = folderName.substring(0,folderName.length()-1);
		amazonS3.deleteObject(new DeleteObjectRequest(s3Config.getBucket()+folderName,file.getFileName()));
		log.info("AWS S3에서 파일 삭제 완료: "+file.getFolderName()+file.getFileName());
	}


	public S3FileDto updateFile(S3FileDto originFile, MultipartFile file, String folder) throws IOException {
		deleteFile(originFile);
		S3FileDto uploadFile = uploadFile(file, folder);
		log.info("AWS S3에 파일 업데이트 완료: "+originFile.getFolderName()+originFile.getFileName()
			+" 에서 "+uploadFile.getFolderName()+uploadFile.getFileName());
		return uploadFile;
	}

	public String getFileUrl(File file){
		return s3Config.getDomain() + file.getFolderName() + file.getFileName();
	}

	private void checkFileValid(MultipartFile file){
		if(file.isEmpty())
			throw new BusinessException(ErrorCode.FILE_NOT_FOUND);
	}

	/** /저장폴더/현재시간키값 **/
	private String getFolderName(String folder){
		return "/" + folder
			+"/" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
	}
}
