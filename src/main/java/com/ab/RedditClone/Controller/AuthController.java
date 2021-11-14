package com.ab.RedditClone.Controller;

import com.ab.RedditClone.dto.AuthenticationResponse;
import com.ab.RedditClone.dto.LoginRequest;
import com.ab.RedditClone.dto.RefreshTokenRequest;
import com.ab.RedditClone.dto.RegisterRequest;
import com.ab.RedditClone.service.AuthService;
import com.ab.RedditClone.service.RefreshTokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static javax.security.auth.callback.ConfirmationCallback.OK;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor

public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;


    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
        authService.signup(registerRequest);
        return new ResponseEntity<String>("User Registration Successful",
                HttpStatus.OK);
    }

    @GetMapping("accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token) {
        authService.verifyAccount(token);
        return new ResponseEntity<String>("Account Activated Successfully",
                HttpStatus.OK);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/refresh/token")
    public AuthenticationResponse refreshTokens(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return authService.refreshToken(refreshTokenRequest);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return ResponseEntity.status(OK).body("Refresh Token Deleted Successfully!!");
    }
}
