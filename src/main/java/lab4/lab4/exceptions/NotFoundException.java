package lab4.lab4.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends HttpException {
    public NotFoundException(String message){
        super(message);
        this.statusCode = HttpStatus.NOT_FOUND;
    }

    public NotFoundException(){
        super("Not Found");
        setStatusCode(HttpStatus.NOT_FOUND);
    }
}