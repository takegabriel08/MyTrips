package ro.scoalainformala.hello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ro.scoalainformala.hello.service.UserDetailsServiceImpl;
import ro.scoalainformala.hello.service.dto.UserDTO;

import java.security.Principal;


@Controller
public class LoginController {
    @Autowired
    UserDetailsServiceImpl userService;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @GetMapping(path = "/login-custom")
    public String login() {
        return "login";
    }

    @GetMapping(path = "/register")
    public String register(Model model) {
        model.addAttribute("user", new UserDTO());
        return "register-form";
    }

    @PostMapping(path = "/register")
    public String registerUser(@ModelAttribute UserDTO userDTO) {
        userDetailsService.addUserToDatabase(userDTO);
        return "redirect:/login-custom";
    }

    @PostMapping("/userDetails")
    public String updateUserDetailsPage(@ModelAttribute UserDTO userDTO, Principal principal ) {
        userDetailsService.editUserInfo(userDTO, principal.getName());
        return "redirect:/";
    }

    @GetMapping("/userDetails")
    public String getUserDetails(Model model, Principal principal) {
        //principal.getName() returneaza username pentru userul logat ca si la functia addUser
        UserDTO userDTO = userService.getUser(principal.getName());
        model.addAttribute("userDTO", userDTO);
        return "userDetails";
    }
}

