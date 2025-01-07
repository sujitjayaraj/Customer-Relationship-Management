package tech.sujitjayaraj.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tech.sujitjayaraj.crm.entity.Client;
import tech.sujitjayaraj.crm.service.CsvService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/import")
public class ImportController {

    private final CsvService csvService;

    @Autowired
    public ImportController(CsvService csvService) {
        this.csvService = csvService;
    }

    @GetMapping
    public String importView() {
        return "import/import";
    }

    @PostMapping
    public String importCsv(@RequestParam String fileName, Model model) {
        String newFileName = "csv/" + fileName;
        List<Client> clients = new ArrayList<>();

        String result = "File imported successfully";

        try {
            clients = csvService.readCsvWithHeader(newFileName);
        } catch (IOException e) {
            result = "Error importing file";
        }

        model.addAttribute("result", result);
        model.addAttribute("clients", clients);

        return "import/show";
    }
}
