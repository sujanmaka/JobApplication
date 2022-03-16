package edu.miu.cs544.sujan.service.impl;

import edu.miu.cs544.sujan.entity.Client;
import edu.miu.cs544.sujan.exception.DataNotFoundException;
import edu.miu.cs544.sujan.exception.ReferentialIntegrityException;
import edu.miu.cs544.sujan.repository.ClientRepository;
import edu.miu.cs544.sujan.service.ClientService;
import edu.miu.cs544.sujan.util.CustomNullAwareBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    @Autowired
    public void setClientRepository(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client getClientById(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            return client.get();
        } else {
            throw new DataNotFoundException(String.format("Client with id %d not found.", id));
        }
    }

    @Override
    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public String deleteClient(Long id) {
        try {
            clientRepository.delete(getClientById(id));
            return String.format("Client with id %d deleted successfully", id);
        } catch (Exception exception) {
            throw new ReferentialIntegrityException(String.format("Client with id %d is referenced by other entity.", id));
        }
    }

    @Override
    public Client updateClient(Long id, Client client) {
        Client currentClient = getClientById(id);
        CustomNullAwareBeanUtils.myCopyProperties(client, currentClient);
        return clientRepository.save(currentClient);
    }
}
