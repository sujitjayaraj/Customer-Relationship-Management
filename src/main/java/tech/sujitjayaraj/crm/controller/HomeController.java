package tech.sujitjayaraj.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tech.sujitjayaraj.crm.entity.Notification;
import tech.sujitjayaraj.crm.entity.User;
import tech.sujitjayaraj.crm.repository.NotificationRepository;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    private final NotificationRepository notificationRepository;

    @Autowired
    public HomeController(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @GetMapping("/")
    public String home(@AuthenticationPrincipal User user, Model model) {
        List<Notification> notificationList = notificationRepository.findByUser(user);
        model.addAttribute("notifications", notificationList);
        model.addAttribute("user", user);

        return "home";
    }
}
