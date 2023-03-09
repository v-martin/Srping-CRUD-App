package lab4.lab4.exceptions;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends HttpException {

    public ForbiddenException(String message){
        super(message);
        setStatusCode(HttpStatus.FORBIDDEN);
    }

    public ForbiddenException(){
        super("Forbidden resource");
        setStatusCode(HttpStatus.FORBIDDEN);
    }
}