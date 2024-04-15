package com.userservice.user.services;

import com.userservice.user.controllers.Dto.UserResponseDto;
import com.userservice.user.models.Role;
import com.userservice.user.models.Session;
import com.userservice.user.models.SessionStatus;
import com.userservice.user.models.User;
import com.userservice.user.repositories.RoleRepository;
import com.userservice.user.repositories.SessionRepository;
import com.userservice.user.repositories.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;

import javax.crypto.SecretKey;
import java.time.LocalDate;
import java.util.*;

@Service
public class AuthService implements IAuthService{
    private UserRepository userRepository;
    private SessionRepository sessionRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private SecretKey secretKey;
    private RoleRepository roleRepository;

    public AuthService(UserRepository userRepository,
                       SessionRepository sessionRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder,
                       RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        secretKey = Jwts.SIG.HS256.key().build();
        this.roleRepository = roleRepository;
    }
    // Note: This method should return a custom object containing token, headers, etc
    // For now, to avoid creating an object, directly returning ResponseEntity from here
    @Override
    public ResponseEntity<UserResponseDto> login(String email, String password) {
        Optional<User>userOptional = userRepository.findByEmail(email);
        if(userOptional.isEmpty()){
            return  null;
        }
        User user = userOptional.get();
        if(!bCryptPasswordEncoder.matches(password,user.getPassword())){
            throw  new RuntimeException("Password/username does not match");
        }
//        String token = RandomStringUtils.randomAlphanumeric(30);
        //jwt
        Map<String,Object> jwtData = new HashMap<>();
        jwtData.put("email",email);
//        jwtData.put("roles",user.getRoles());
        jwtData.put("createdAt",new Date());
        jwtData.put("expiryAt",new Date(LocalDate.now().plusDays(3).toEpochDay()));

        String token = Jwts.builder().
                claims(jwtData).
                signWith(secretKey).
                compact();

        Session session = new Session();
        session.setSessionStatus(SessionStatus.ACTIVE);
        session.setToken(token);
        session.setUser(user);
        sessionRepository.save(session);

        UserResponseDto userResponseDto = UserResponseDto.from(user);

        MultiValueMap<String,String> headers = new MultiValueMapAdapter<>(new HashMap<>());
        headers.add(HttpHeaders.SET_COOKIE, "auth-token:"+ token);
        return new ResponseEntity<>(userResponseDto,headers, HttpStatus.OK);
    }

    @Override
    public void logout(String token, Long userId) {
        Optional<Session>sessionOptional = sessionRepository.findByTokenAndUser_Id(token,userId);
        if(sessionOptional.isEmpty()){
            return;
        }
        Session session = sessionOptional.get();
        session.setSessionStatus(SessionStatus.ENDED);
        sessionRepository.save(session);
    }

    @Override
    public UserResponseDto signUp(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
//        for(Role role:roles){
//            roleRepository.save(role);
//        }

//        user.setRoles(new HashSet<>(roles));

        User savedUser = userRepository.save(user);

        return UserResponseDto.from(savedUser);
    }

    @Override
    public SessionStatus validate(Long userId,String token) {
        Optional<Session>sessionOptional = sessionRepository.findByTokenAndUser_Id(token,userId);
        if(sessionOptional.isEmpty()){
            return  null;
        }
        Session session = sessionOptional.get();
        if(!session.getSessionStatus().equals(SessionStatus.ACTIVE)){
            return SessionStatus.ENDED;
        }

        //write logic for verifying jwt token
        Jws <Claims>claimsJws;
        try {
          claimsJws = Jwts.parser().
                    verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token);
        }catch (JwtException e){
            throw new RuntimeException("Token is invalid");
        }
        String email = (String) claimsJws.getPayload().get("email");
        Integer expiryAt = (Integer) claimsJws.getPayload().get("expiryAt");
        Role role = (Role) claimsJws.getPayload().get("roles");
//        System.out.println("Role: "+role);
//        if(expiryAt.before(new Date())){
//            return SessionStatus.ENDED;
//        }
        return SessionStatus.ACTIVE;
    }
}
