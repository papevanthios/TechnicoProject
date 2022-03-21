package gr.codehub.accenture.technicoproject.dto;

import gr.codehub.accenture.technicoproject.enumer.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResultDto<T> {
    private T data;
    private ResponseStatus status;
    private String message;
}
