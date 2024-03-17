package vn.iback.studentmanager.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/showLoginPage")
    public String showLoginpage(){
        return "login";
    }
    @GetMapping("/showPage403")
    public String showPage403(){
        return "403";
    }
}
