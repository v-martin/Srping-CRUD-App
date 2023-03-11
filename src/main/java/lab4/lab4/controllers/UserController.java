package lab4.lab4.controllers;

import jakarta.security.auth.message.AuthException;
import jakarta.validation.Valid;
import lab4.lab4.dto.CustomUserDetails;
import lab4.lab4.dto.request.LoginUserDTO;
import lab4.lab4.dto.request.RefreshTokenDTO;
import lab4.lab4.dto.request.SignupUserDTO;
import lab4.lab4.dto.response.MainInfoDTO;
import lab4.lab4.dto.response.TokenDTO;
import lab4.lab4.dto.response.UserInfoDTO;
import lab4.lab4.entity.User;
import lab4.lab4.exceptions.BadRequestException;
import lab4.lab4.exceptions.HttpException;
import lab4.lab4.exceptions.NotFoundException;
import lab4.lab4.services.JWTBase;
import lab4.lab4.services.AuthService;
import lab4.lab4.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("")
public class  UserController {
    private final AuthService authService;
    private final UserService userService;
    private final JWTBase jwtBase;

    @Autowired
    public UserController(AuthService authService,
                          JWTBase jwtBase,
                          UserService userService) {
        this.authService = authService;
        this.jwtBase = jwtBase;
        this.userService = userService;
    }

    @PostMapping("/auth/signup")
    public ResponseEntity<?> registration(@Valid SignupUserDTO registrationDto, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldErrors());
        }
        try {
            User user = this.authService.registration(registrationDto);
            this.userService.AuthenticateUserInContext(user.getEmail());
            String jwtAccessToken = jwtBase.generateJwtAccessToken(user);
            String jwtRefreshToken = jwtBase.generateJwtRefreshToken(user);
            return ResponseEntity.ok(
                    new UserInfoDTO(jwtAccessToken, jwtRefreshToken, user.getId(),
                            user.getEmail(), user.getFirstName(), user.getLastName())
            );
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Map[]{Collections.singletonMap("defaultMessage",
                    e.getMessage())});
        }
    }
    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@Valid LoginUserDTO loginDto, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldErrors());
        }
        try {
            User user = this.authService.login(loginDto);
            this.userService.AuthenticateUserInContext(user.getEmail());
            String jwtAccessToken = jwtBase.generateJwtAccessToken(user);
            String jwtRefreshToken = jwtBase.generateJwtRefreshToken(user);
            return ResponseEntity.ok(
                    new UserInfoDTO(jwtAccessToken, jwtRefreshToken, user.getId(),
                            user.getEmail(), user.getFirstName(), user.getLastName())
            );
        } catch (HttpException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Map[]{Collections.singletonMap("defaultMessage",
                    e.getMessage())});
        }
    }

    @GetMapping("/main")
    public ResponseEntity<?> main() {
        try {
            String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            CustomUserDetails user = (CustomUserDetails) userService.loadUserByUsername(email);
            return ResponseEntity.ok(
                    new MainInfoDTO(user.getEmail(), user.getFirstname(), user.getLastname())
            );
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (ClassCastException | UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping("/auth/refresh")
    public ResponseEntity<TokenDTO> getNewAccessToken(@Valid RefreshTokenDTO request) throws AuthException {
        final TokenDTO token = authService.getAccessToken(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }
}