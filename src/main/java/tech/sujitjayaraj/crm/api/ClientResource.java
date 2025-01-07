package tech.sujitjayaraj.crm.api;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import tech.sujitjayaraj.crm.entity.Client;
import tech.sujitjayaraj.crm.service.ClientService;
import tech.sujitjayaraj.crm.service.UserService;

@RestController
@RequestMapping("/api/clients")
public class ClientResource {

    private final ClientService clientService;

    private final UserService userService;

    @Autowired
    public ClientResource(ClientService clientService, UserService userService) {
        this.clientService = clientService;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    ResponseEntity<Client> getClient(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.findById(id));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{email}")
    ResponseEntity<Void> createClient(@PathVariable String email, @RequestBody @Valid Client client, UriComponentsBuilder uriComponentsBuilder) {
        client.setUser(userService.loadUserByUsername(email));
        Client savedClient = clientService.saveClient(client);

        return ResponseEntity.created(uriComponentsBuilder.path("/api/clients/{id}").buildAndExpand(savedClient.getId()).toUri()).build();
    }

    @PutMapping("/{email}")
    ResponseEntity<Void> updateUser(@PathVariable String email, @RequestBody @Valid Client client) {
        client.setUser(userService.loadUserByUsername(email));
        clientService.saveClient(client);

        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    ResponseEntity<String> handleUsernameNotFoundException(UsernameNotFoundException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
