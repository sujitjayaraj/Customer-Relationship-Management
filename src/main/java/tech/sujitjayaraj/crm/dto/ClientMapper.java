package tech.sujitjayaraj.crm.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.sujitjayaraj.crm.entity.Address;
import tech.sujitjayaraj.crm.entity.Client;
import tech.sujitjayaraj.crm.service.UserService;

@Component
public class ClientMapper {

    private final UserService userService;

    @Autowired
    public ClientMapper(UserService userService){
        this.userService = userService;
    }

    public Client toEntity(ClientDto clientDto) {
        Client client = new Client();

        client.setName(clientDto.getName());
        client.setStatus(Client.Status.valueOf(clientDto.getStatus()));
        client.setEmail(clientDto.getEmail());
        client.setPhone(clientDto.getPhone());

        Address address = new Address();

        address.setCountry(clientDto.getCountry());
        address.setState(clientDto.getState());
        address.setCity(clientDto.getCity());
        address.setStreet(clientDto.getStreet());
        address.setPincode(clientDto.getPincode());
        client.setAddress(address);
        client.setUser(userService.loadUserByUsername(clientDto.getUserEmail()));

        return client;
    }
}
