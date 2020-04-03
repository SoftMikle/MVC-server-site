package ua.com.alevel.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BadRequestException extends RestException {

    public BadRequestException(List<FieldErrorModel> fieldErrors) {
        super(fieldErrors);
    }
}
