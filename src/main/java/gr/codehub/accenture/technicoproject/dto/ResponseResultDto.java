package gr.codehub.accenture.technicoproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResultDto<T> {
    private T data;
    private ResponseStatus status;
    private String message;
}
