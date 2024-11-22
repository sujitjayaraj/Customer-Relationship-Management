package tech.sujitjayaraj.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import tech.sujitjayaraj.crm.entity.Event;

@Controller
@RequestMapping("/event")
public class EventController {

    @ModelAttribute("event")
    public Event event() {
        return new Event();
    }

    @GetMapping("/new")
    public String newEvent() {
        return "event/new";
    }
}
