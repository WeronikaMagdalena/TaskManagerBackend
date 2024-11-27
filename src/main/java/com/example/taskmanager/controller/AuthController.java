package com.example.taskmanager.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Value("${spring.security.oauth2.client.registration.cognito.client-id}")
    private String cognitoClientId;

    @Value("${spring.security.oauth2.client.registration.cognito.token-uri}")
    private String cognitoTokenUri;

//    @PostMapping("/exchange")
//    public ResponseEntity<TokenResponse> exchangeCodeForTokens(@RequestBody CodeRequest codeRequest) {
//        // Exchange code for tokens with Cognito
//        // Use WebClient or RestTemplate to send the request to Cognito's token URI
//
//        // Example:
//         exchangeCodeWithCognito(codeRequest.getCode());
//
//        // Return tokens
//        return ResponseEntity.ok(new TokenResponse("access_token_example"));
//    }

    // Token request body
    public static class CodeRequest {
        private String code;
        public String getCode() { return code; }
        public void setCode(String code) { this.code = code; }
    }

    // Token response body
    public static class TokenResponse {
        private String access_token;
        public TokenResponse(String access_token) { this.access_token = access_token; }
        public String getAccess_token() { return access_token; }
        public void setAccess_token(String access_token) { this.access_token = access_token; }
    }
}
