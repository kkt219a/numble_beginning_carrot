package numble.beginningcarrot.common.valid;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

@Component
public class ListSizeValidator implements ConstraintValidator<IsValidListSize, List<?>> {

    private int maxSize;
    private int minSize;

    @Override
    public boolean isValid(List<?> itemList, ConstraintValidatorContext cxt) {
        if (itemList.size() <= maxSize && itemList.size() >= minSize) {
            return true;
        } else {
            cxt.disableDefaultConstraintViolation();
            cxt.buildConstraintViolationWithTemplate(
                    cxt.getDefaultConstraintMessageTemplate())
                    .addConstraintViolation();
            return false;
        }
    }

    @Override
    public void initialize(IsValidListSize constraintAnnotation) {
        this.maxSize = constraintAnnotation.max();
        this.minSize = constraintAnnotation.min();
    }
}
