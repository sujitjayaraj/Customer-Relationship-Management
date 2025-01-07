package tech.sujitjayaraj.crm.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.sujitjayaraj.crm.dto.ClientDto;
import tech.sujitjayaraj.crm.dto.ClientMapper;
import tech.sujitjayaraj.crm.entity.Client;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvService {

    private final ClientMapper clientMapper;

    private final ClientService clientService;

    @Autowired
    public CsvService(ClientMapper clientMapper, ClientService clientService){
        this.clientMapper = clientMapper;
        this.clientService = clientService;
    }

    public List<Client> readCsvWithHeader(String fileName) throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(fileName + ".csv"));

        CsvToBean<ClientDto> csvToBean = new CsvToBeanBuilder<ClientDto>(reader).withIgnoreLeadingWhiteSpace(true).build();

        List<ClientDto> clientDtoList = csvToBean.parse();
        List<Client> clients = new ArrayList<>();

        for (ClientDto clientDto : clientDtoList) {
            Client client = clientMapper.toEntity(clientDto);
            client = clientService.saveClient(client);
            clients.add(client);
        }

        return clients;
    }
}
