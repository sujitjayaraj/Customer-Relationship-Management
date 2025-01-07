package tech.sujitjayaraj.crm.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tech.sujitjayaraj.crm.entity.Client;
import tech.sujitjayaraj.crm.entity.User;
import tech.sujitjayaraj.crm.repository.ClientRepository;
import tech.sujitjayaraj.crm.service.PdfService;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private String search;

    private List<Client> clients;

    private final ClientRepository clientRepository;

    private final PdfService pdfService;

    @Autowired
    public EmployeeController(ClientRepository clientRepository, PdfService pdfService) {
        this.clientRepository = clientRepository;
        this.pdfService = pdfService;
    }

    @GetMapping("/search")
    public String search(@AuthenticationPrincipal User user, Model model) {
        clients = clientRepository.findByUser(user);
        model.addAttribute("clients", clients);
        search = "clients_managed_by_" + String.join("_", user.getName().split(" "));

        return "employee/search";
    }

    @GetMapping("/search/city")
    public String citySearch(@AuthenticationPrincipal User user, Model model) {
        List<Client> clients = clientRepository.findByAddressCityOrderByNameAsc(user.getOffice().getAddress().getCity());
        model.addAttribute("clients", clients);
        search = "clients_from_" + user.getOffice().getAddress().getCity();

        return "employee/search";
    }

    @PostMapping("/search/status")
    public String statusSearch(@AuthenticationPrincipal User user, @RequestParam String status, Model model) {
        List<Client> clients = clientRepository.findByStatusAndAddressCityOrderByNameAsc(Client.Status.valueOf(status), user.getOffice().getAddress().getCity());
        model.addAttribute("clients", clients);
        search = "clients_with_status_" + status;

        return "employee/search";
    }

    @PostMapping("/search/name")
    public String nameSearch(@AuthenticationPrincipal User user, @RequestParam String name, Model model) {
        List<Client> clients = clientRepository.findByNameContainingIgnoreCaseAndAddressCityOrderByNameAsc(name, user.getOffice().getAddress().getCity());
        model.addAttribute("clients", clients);
        search = "clients_with_name_" + name;

        return "employee/search";
    }

    @GetMapping("/search/print")
    public void printSearch(@AuthenticationPrincipal User user, HttpServletResponse response) throws IOException {
        String fileName = user.getName() + "_" + search + "_" + System.currentTimeMillis();
        byte[] result = pdfService.printClientList(fileName, clients);
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + ".pdf\"");
        response.getOutputStream().write(result);
        response.getOutputStream().flush();
    }
}
