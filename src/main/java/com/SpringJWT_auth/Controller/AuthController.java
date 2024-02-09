package com.SpringJWT_auth.Controller;

import com.SpringJWT_auth.Config.JWT.JwtHelper;
import com.SpringJWT_auth.Entities.JwtRequest;
import com.SpringJWT_auth.Entities.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired // used for fetching user's information
    private UserDetailsService userDetailsService;

    @Autowired // we use this manager for authenticate the things.
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtHelper jwtHelper;


    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody JwtRequest req){

        UserDetails detail = userDetailsService.loadUserByUsername(req.getEmail());
        String token = this.jwtHelper.generateToken(detail);

        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .userName(detail.getUsername()).build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    private void doAuthenticate(String email, String pass){
        // we had done this same thing in filter
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(email, pass);

        try{
            authenticationManager.authenticate(auth);
        }
        catch (BadCredentialsException e){
            throw new RuntimeException("Invalid Username or password");
        }
    }

}
