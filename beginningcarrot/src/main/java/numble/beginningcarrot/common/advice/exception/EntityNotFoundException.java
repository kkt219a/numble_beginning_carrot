package numble.beginningcarrot.common.advice.exception;

public class EntityNotFoundException extends BusinessException{
    public EntityNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
