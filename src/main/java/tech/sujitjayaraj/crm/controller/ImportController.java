package tech.sujitjayaraj.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/import")
public class ImportController {

    @GetMapping
    public String importView() {
        return "import/import";
    }
}
