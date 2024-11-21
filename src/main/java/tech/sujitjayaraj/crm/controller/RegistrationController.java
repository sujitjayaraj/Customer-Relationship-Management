package tech.sujitjayaraj.crm.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tech.sujitjayaraj.crm.entity.RegistrationForm;
import tech.sujitjayaraj.crm.entity.Role;
import tech.sujitjayaraj.crm.entity.User;
import tech.sujitjayaraj.crm.repository.UserRepository;

import java.util.HashSet;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @ModelAttribute("registrationForm")
    public RegistrationForm registrationForm() {
        return new RegistrationForm();
    }

    @GetMapping
    public String get() {
        return "register";
    }

    @PostMapping
    public String post(@ModelAttribute @Valid RegistrationForm form, Errors errors) {
        if(errors.hasErrors()) {
            return "register";
        }

        HashSet<Role> roles = new HashSet<>();
        roles.add(Role.OWNER);

        User user = new User();
        user.setFirstName(form.getFirstName());
        user.setLastName(form.getLastName());
        user.setEmail(form.getEmail());
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        user.setPhone(form.getPhone());
        user.setActive(true);
        user.setRoles(roles);
        userRepository.save(user);

        return "redirect:/login";
    }
}
