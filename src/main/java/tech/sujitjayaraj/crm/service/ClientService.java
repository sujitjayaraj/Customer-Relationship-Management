package tech.sujitjayaraj.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tech.sujitjayaraj.crm.entity.Client;
import tech.sujitjayaraj.crm.entity.User;
import tech.sujitjayaraj.crm.repository.ClientRepository;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client findById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Client with id " + id + " not found"));
    }

    public Client findByName(String name) {
        return clientRepository.findByNameIgnoreCase(name).orElseThrow(() -> new UsernameNotFoundException("Client with name " + name + " not found"));
    }

    public Client saveClientWithLoggedUser(Client client) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        client.setUser(user);

        return clientRepository.save(client);
    }

    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    public List<Client> findByUser(User user) {
        return clientRepository.findByUser(user);
    }
}
