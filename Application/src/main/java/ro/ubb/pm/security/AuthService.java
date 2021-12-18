package ro.ubb.pm.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.ubb.pm.bll.UserBLL;
import ro.ubb.pm.model.dtos.UserDTO;
import ro.ubb.pm.model.User;

@Service
public class AuthService {

    @Autowired
    private transient JwtTokenUtil jwtTokenConfig;

    @Autowired
    private transient UserBLL userService;

    @Autowired
    private transient PasswordEncoder passwordEncoder;

    /**
     * Checks whether there exists a given user in the database.
     *
     * @param user {@link UserDTO user} that stores the input email and password.
     * @return {@link ResponseEntity http response},
     *     UNAUTHORIZED if user was not authenticated, OK otherwise.
     */
    public ResponseEntity<String> authenticate(UserDTO user) {
        User userResult = userService.getUser(user.getEmail());

        if (userResult == null) {
            return new ResponseEntity<String>("Wrong Email", HttpStatus.UNAUTHORIZED);
        }
        if (!passwordEncoder.matches(user.getPassword(), passwordEncoder.encode(userResult.getPassword()))) {
            return new ResponseEntity<String>("Wrong Password", HttpStatus.UNAUTHORIZED);
        }
        userResult.setPassword(passwordEncoder.encode(user.getPassword()));
        return new ResponseEntity<String>(jwtTokenConfig.generateToken( userResult), HttpStatus.OK);
    }
}

