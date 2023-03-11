package lab4.lab4.services;

import io.jsonwebtoken.Claims;
import jakarta.security.auth.message.AuthException;
import lab4.lab4.dto.request.LoginUserDTO;
import lab4.lab4.dto.request.SignupUserDTO;
import lab4.lab4.dto.response.TokenDTO;
import lab4.lab4.entity.User;
import lab4.lab4.exceptions.BadRequestException;
import lab4.lab4.exceptions.NotFoundException;
import lab4.lab4.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    private final JWTBase jwtBase;

    @Autowired
    public AuthService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, JWTBase jwtBase) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtBase = jwtBase;
    }

    public User login(LoginUserDTO authDto) {
        Optional<User> userEntityOptional = userRepository.getUserByEmail(authDto.getEmail());
        if (userEntityOptional.isEmpty()) {
            throw new NotFoundException("User with email " + authDto.getEmail() + " is not registered");
        }
        User user = userEntityOptional.get();
        if (!passwordEncoder.matches(authDto.getPassword(), user.getPassword())) {
            System.out.println(user.getPassword());
            System.out.println(authDto.getPassword());
            throw new BadRequestException("Wrong password or email");
        }
        return user;
    }

    public User registration(SignupUserDTO authDto) {
        Optional<User> userEntityOptional = userRepository.getUserByEmail(authDto.getEmail());
        if (userEntityOptional.isPresent()) {
            throw new BadRequestException("User is already registered");
        }
        String passwordHash = passwordEncoder.encode(authDto.getPassword());
        return userRepository.save(new User(authDto.getEmail(), passwordHash, authDto.getFirstName(), authDto.getLastName()));
    }

    public TokenDTO getAccessToken(@NonNull String refreshToken) throws AuthException {
        if (jwtBase.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtBase.getRefreshClaims(refreshToken);
            final String email = claims.getSubject();
            final User user = userRepository.getUserByEmail(email)
                    .orElseThrow(() -> new AuthException("Пользователь не найден"));
            final String accessToken = jwtBase.generateJwtAccessToken(user);
            return new TokenDTO(accessToken, null);
        }
        return new TokenDTO(null, null);
    }
}
