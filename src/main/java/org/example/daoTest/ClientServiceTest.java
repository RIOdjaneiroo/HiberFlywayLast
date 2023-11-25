package org.example.daoTest;
import org.example.service.ClientCrudService;
import org.example.entity.Client;

import java.util.List;

public class ClientServiceTest {
    public static void main(String[] args) {
        // cтворюємо обєкт клієнта
        Client testClient = new Client();
        testClient.setName("Karapuz");
        // cтворюємо сервіс та зберігаємо клієнта в БД
        ClientCrudService clientCrudService = new ClientCrudService();
        clientCrudService.saveClient(testClient);
        System.out.println("Saved client with ID: " + testClient.getId());
        // читаємо клієнта за ID
        Long clientId = testClient.getId();
        Client getDbClient = clientCrudService.findClientById(clientId);
        System.out.println("Retrieved client: " + getDbClient);
        // оновлюємо клієнта
        getDbClient.setName("KarapuzUpdated");
        clientCrudService.updateClient(getDbClient);
        System.out.println("Updated client with ID: " + getDbClient.getId());
        // виводимо всіх клієнтів
        List<Client> allClients = clientCrudService.getAllClients();
        System.out.println("All clients:");
        for (Client client : allClients) {
            System.out.println(client);
        }
        // видаляємо клієнта
//        clientCrudService.deleteClient(getDbClient);
//        System.out.println("Deleted client with ID: " + getDbClient.getId());
//        // Перевірка, чи об'єкт більше не існує в базі даних
//        Client deletedClient = clientCrudService.findClientById(clientId);
//        if (deletedClient == null) {
//            System.out.println("Client with ID " + clientId + " is not found (as expected).");
//        } else {
//            System.out.println("Client with ID " + clientId + " still exists (unexpected).");
//        }
    }
}
