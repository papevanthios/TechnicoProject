package gr.codehub.accenture.technicoproject.dto;

import gr.codehub.accenture.technicoproject.enumer.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Response result is a format to display to the user the data, the responseStatus and the message.
 * @param <T> an object of T
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResultDto<T> {
    private T data;
    private ResponseStatus status;
    private String message;
}
