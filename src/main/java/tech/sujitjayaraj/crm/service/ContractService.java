package tech.sujitjayaraj.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.sujitjayaraj.crm.entity.Contract;
import tech.sujitjayaraj.crm.entity.User;
import tech.sujitjayaraj.crm.repository.ContractRepository;

import java.util.List;

@Service
public class ContractService {

    private final ContractRepository contractRepository;

    @Autowired
    public ContractService(ContractRepository contractRepository){
        this.contractRepository = contractRepository;
    }

    public List<Contract> findByAcceptedBy(User user) {
        return contractRepository.findByAcceptedBy(user);
    }
}
