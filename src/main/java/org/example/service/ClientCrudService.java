package org.example.service;
import org.example.dao.ClientDao;
import org.example.entity.Client;

import java.util.List;

public class ClientCrudService {
    private ClientDao clientDao = new ClientDao();

    public void saveClient(Client client) {
        clientDao.createClient(client);
    }

    public Client findClientById(Long id) {
        return clientDao.findById(id);
    }

    public List<Client> getAllClients() {
        return clientDao.getAllClients();
    }

    public void updateClient(Client client) {
        clientDao.update(client);
    }

    public void deleteClient(Client client) {
        clientDao.delete(client);
    }
}

