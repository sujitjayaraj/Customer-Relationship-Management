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
import tech.sujitjayaraj.crm.repository.UserRepository;

import java.util.List;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    private final UserRepository userRepository;

    private final ClientRepository clientRepository;

    @Autowired
    public ManagerController(UserRepository userRepository, ClientRepository clientRepository) {
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
    }

    @GetMapping("/search")
    public String search(@AuthenticationPrincipal User user, Model model) {
        List<User> employees = userRepository.findBySupervisor(user);
        List<Client> clients = clientRepository.findByUserIn(employees);
        model.addAttribute("clients", clients);

        return "manager/search";
    }
}
