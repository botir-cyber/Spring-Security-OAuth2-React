/* Created by IntelliJ IDEA.
 User: Mirzaahmatov Aziz
Date: 3/27/2021
Time: 8:54 PM
 To change this template use File | Settings | File Templates.
*/
package uz.pdp.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.demo.payload.ReqSignUp;
import uz.pdp.demo.security.AuthService;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("register")
    public HttpEntity<?> register(@RequestBody ReqSignUp reqSignUp){
        return ResponseEntity.ok(authService.register(reqSignUp));
    }
    @PostMapping("login")
    public HttpEntity<?> login(@RequestBody ReqSignUp reqSignUp){
        return ResponseEntity.ok(authService.login(reqSignUp.getPhoneNumber(),reqSignUp.getPassword()));
    }
}
