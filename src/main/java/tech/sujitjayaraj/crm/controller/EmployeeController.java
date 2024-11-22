package tech.sujitjayaraj.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tech.sujitjayaraj.crm.entity.Client;
import tech.sujitjayaraj.crm.entity.User;
import tech.sujitjayaraj.crm.repository.ClientRepository;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private String search;

    private List<Client> clients;

    private final ClientRepository clientRepository;

    @Autowired
    public EmployeeController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping("/search")
    public String search(@AuthenticationPrincipal User user, Model model) {
        clients = clientRepository.findByUser(user);
        model.addAttribute("clients", clients);
        search = "clients_managed_by_" + String.join("_", user.getName().split(" "));

        return "employee/search";
    }
}
