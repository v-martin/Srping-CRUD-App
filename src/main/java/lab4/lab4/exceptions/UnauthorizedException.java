package lab4.lab4.exceptions;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends HttpException {
    public UnauthorizedException() {
        super("Unauthorized");
        this.statusCode = HttpStatus.UNAUTHORIZED;
    }

    public UnauthorizedException(String message) {
        super(message);
        this.statusCode = HttpStatus.UNAUTHORIZED;
    }
}
