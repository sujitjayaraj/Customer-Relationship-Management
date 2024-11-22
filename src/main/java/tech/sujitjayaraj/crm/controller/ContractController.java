package tech.sujitjayaraj.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import tech.sujitjayaraj.crm.entity.Contract;

@Controller
@RequestMapping("/contract")
public class ContractController {

    @ModelAttribute("contract")
    public Contract contract() {
        return new Contract();
    }

    @GetMapping("/new")
    public String newContract() {
        return "contract/new";
    }
}
