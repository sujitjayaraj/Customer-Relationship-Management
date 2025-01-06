package tech.sujitjayaraj.crm.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tech.sujitjayaraj.crm.entity.Client;
import tech.sujitjayaraj.crm.entity.User;
import tech.sujitjayaraj.crm.repository.ClientRepository;
import tech.sujitjayaraj.crm.repository.UserRepository;
import tech.sujitjayaraj.crm.service.PdfService;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    private final UserRepository userRepository;

    private final ClientRepository clientRepository;
    private final PdfService pdfService;

    @Autowired
    public ManagerController(UserRepository userRepository, ClientRepository clientRepository, PdfService pdfService) {
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
        this.pdfService = pdfService;
    }

    @GetMapping("/search")
    public String search(@AuthenticationPrincipal User user, Model model) {
        List<User> employees = userRepository.findBySupervisor(user);
        List<Client> clients = clientRepository.findByUserIn(employees);
        model.addAttribute("clients", clients);

        return "manager/search";
    }

    @PostMapping("/search/status")
    public String statusSearch(@RequestParam String status, Model model) {
        List<Client> clients = clientRepository.findByStatusOrderByNameAsc(Client.Status.valueOf(status));
        model.addAttribute("clients", clients);

        return "manager/search";
    }

    @PostMapping("/search/name")
    public String nameSearch(@RequestParam String name, Model model) {
        List<Client> clients = clientRepository.findByNameContainingIgnoreCaseOrderByName(name);
        model.addAttribute("clients", clients);

        return "manager/search";
    }

    @GetMapping("/print")
    public void print(HttpServletResponse response, @AuthenticationPrincipal User user, @ModelAttribute("clients") List<Client> clients) throws IOException {
        String filename = user.getLastName() + "_" + Instant.now();
        byte[] result = pdfService.printClientList(filename, clients);
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
        response.getOutputStream().write(result);
        response.getOutputStream().flush();
    }

    @ExceptionHandler(IOException.class)
    public String handleIOException() {
        return "manager/search";
    }
}
