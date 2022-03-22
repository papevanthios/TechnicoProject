package gr.codehub.accenture.technicoproject.exception;

import gr.codehub.accenture.technicoproject.dto.ResponseResultDto;
import gr.codehub.accenture.technicoproject.enumer.ResponseStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

/**
 * Global exception handler, extends the response entity exception handler and the class handles the field errors of
 * the project models and returns a response entity of response result dto of object Boolean.
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     * This method delegates to handleExceptionInternal.
     *
     * @param ex      the target exception
     * @param headers the headers to be written to the response
     * @param status  the selected response status
     * @param request the current request
     * @return a ResponseEntity instance
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        return new ResponseEntity<>(new ResponseResultDto<>(false, ResponseStatus.ERROR,
                "Field " + Objects.requireNonNull(ex.getFieldError()).getField() + ": " +
                        ex.getFieldError().getDefaultMessage()), HttpStatus.BAD_REQUEST);
    }
}
