/* Created by IntelliJ IDEA.
 User: Mirzaahmatov Aziz
Date: 3/25/2021
Time: 8:16 PM
 To change this template use File | Settings | File Templates.
*/
package uz.pdp.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.demo.entity.User;
import uz.pdp.demo.security.CurrentUser;

@RestController
@RequestMapping("api/test")
public class TestController {

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("admin")
    public String getTest() {
        return "welcome admin ";
    }

    @GetMapping("user")
    public String getTestSecuredForUser(@CurrentUser User user) {
        return "welcome user " +user.getUsername();
    }

    @GetMapping("public")
    public String getTestSecuredForPublic() {
        return "welcome user all";
    }
}
