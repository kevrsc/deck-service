package sample.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//Return a 404 Not Found status code (avoids the 500 Internal Server Error)
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException {

}
