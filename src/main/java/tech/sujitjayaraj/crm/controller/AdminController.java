package tech.sujitjayaraj.crm.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tech.sujitjayaraj.crm.entity.Role;
import tech.sujitjayaraj.crm.entity.User;
import tech.sujitjayaraj.crm.repository.UserRepository;

import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserRepository userRepository;

    @Autowired
    public AdminController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ModelAttribute("user")
    public User user() {
        return new User();
    }

    @ModelAttribute("roles")
    public Set<Role> roles() {
        return Set.of(Role.values());
    }

    @GetMapping(path = "/add")
    public String add() {
        return "admin/add";
    }

    @PostMapping("/add")
    public String register(@ModelAttribute @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            return "admin/add";
        }

        userRepository.save(user);

        return "admin/success";
    }
}
