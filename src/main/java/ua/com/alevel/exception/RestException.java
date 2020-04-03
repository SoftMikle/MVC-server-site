package ua.com.alevel.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class RestException extends RuntimeException {

    private List<FieldErrorModel> fieldErrors;
}
