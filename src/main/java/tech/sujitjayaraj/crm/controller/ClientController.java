package tech.sujitjayaraj.crm.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import tech.sujitjayaraj.crm.entity.Client;
import tech.sujitjayaraj.crm.entity.User;
import tech.sujitjayaraj.crm.repository.ClientRepository;

import java.security.InvalidParameterException;
import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientController {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @ModelAttribute("client")
    public Client client() {
        return new Client();
    }

    @ModelAttribute("statuses")
    public List<String> statuses() {
        return List.of("Active", "Inactive", "Prospect");
    }

    @GetMapping("/add")
    public String add() {
        return "client/add";
    }

    @PostMapping("/add")
    public String register(@ModelAttribute @Valid Client client, @AuthenticationPrincipal User user, Errors errors) {
        if (errors.hasErrors()) {
            return "client/add";
        }

        client.setUser(user);
        clientRepository.save(client);

        return "client/success";
    }

    @GetMapping("/edit/{id}")
    public String editClient(@PathVariable Long id, Model model) {
        model.addAttribute("client", clientRepository.findById(id).orElseThrow(() -> new InvalidParameterException("Invalid Client ID " + id)));

        return "client/edit";
    }

    @PostMapping("/edit/{id}")
    public String saveClient(@ModelAttribute @Valid Client client, @AuthenticationPrincipal User user, @PathVariable Long id, Errors errors) {
        if (errors.hasErrors()) {
            return "client/edit";
        }

        client.setUser(user);

        return "client/success";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("client", clientRepository.findById(id).orElseThrow(() -> new InvalidParameterException("Invalid Client ID " + id)));

        return "client/details";
    }

    @ExceptionHandler(InvalidParameterException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String invalidParameterException(Exception e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());

        return "client/edit";
    }
}
