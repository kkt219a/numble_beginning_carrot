package numble.beginningcarrot.common.advice;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import lombok.extern.slf4j.Slf4j;
import numble.beginningcarrot.common.advice.dto.ErrorResponse;
import numble.beginningcarrot.common.advice.exception.BusinessException;
import numble.beginningcarrot.common.advice.exception.ErrorCode;

/**
 * [Exception Strategy] 아래의 소스에서 참조
 * 출처: https://github.com/cheese10yun/spring-guide
 */

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		final ErrorResponse response = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, e.getBindingResult());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(BindException.class)
	protected ResponseEntity<ErrorResponse> handleBindException(BindException e) {
		final ErrorResponse response = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, e.getBindingResult());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	protected ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(
		MethodArgumentTypeMismatchException e) {
		final ErrorResponse response = ErrorResponse.of(e);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(
		HttpRequestMethodNotSupportedException e) {
		final ErrorResponse response = ErrorResponse.of(ErrorCode.METHOD_NOT_ALLOWED);
		return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	protected ResponseEntity<ErrorResponse> handleHttpMediaTypeNotSupportedException(
		HttpMediaTypeNotSupportedException e) {
		final ErrorResponse response = ErrorResponse.of(ErrorCode.MEDIA_TYPE_NOT_SUPPORTED);
		return new ResponseEntity<>(response, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	protected ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
		final ErrorResponse response = ErrorResponse.of(ErrorCode.NOT_READABLE_MESSAGE);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	protected ResponseEntity<ErrorResponse> handleMissingServletRequestParameterException() {
		final ErrorResponse response = ErrorResponse.of(ErrorCode.MISSING_REQUEST_PARAMETER);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MissingServletRequestPartException.class)
	protected ResponseEntity<ErrorResponse> handleMissingServletRequestPartException() {
		final ErrorResponse response = ErrorResponse.of(ErrorCode.MISSING_REQUEST_PART);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	/**
	 * BusinessException과 이를 상속받은 모든 Exception 처리
	 */
	@ExceptionHandler(BusinessException.class)
	protected ResponseEntity<ErrorResponse> handleBusinessException(final BusinessException e) {
		final ErrorCode errorCode = e.getErrorCode();
		final ErrorResponse response = ErrorResponse.of(errorCode);
		return new ResponseEntity<>(response, HttpStatus.valueOf(errorCode.getStatus()));
	}

	/** 파일 처리 과정 Exception **/
	@ExceptionHandler(IOException.class)
	protected ResponseEntity<ErrorResponse> handleIOException(IOException e) {
		log.error("handleIOException", e);
		final ErrorResponse response = ErrorResponse.of(ErrorCode.FILE_PROCESS_ERROR);
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * 위에 언급한 Exception을 제외한 별도 처리가 필요한 모든 Exception은 제보를 통해 추가
	 */
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<ErrorResponse> handleException(Exception e) {
		log.error("handleException", e);
		final ErrorResponse response = ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * 파일 크기 초과
	 */
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	protected ResponseEntity<ErrorResponse> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
		final ErrorResponse response = ErrorResponse.of(ErrorCode.FILE_MAX_SIZE_EXCEEDED);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

}
