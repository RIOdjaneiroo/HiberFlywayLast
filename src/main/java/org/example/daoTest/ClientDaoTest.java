package org.example.daoTest;
import org.example.dao.ClientDao;
import org.example.entity.Client;

import java.util.List;

public class ClientDaoTest {
    public static void main(String[] args) {

        // cтворюємо DAO
        ClientDao clientDao = new ClientDao();

//        // Створюємо об'єкт клієнта для тестування
//        Client testClient = new Client();
//        testClient.setName("Jonatan Junior");
        Client test1Client = new Client();
        test1Client.setName("Oleksandr Junior");
        Client ansver = clientDao.assertClient(test1Client);
//
//        // cтворюємо запис в БД
//        clientDao.createClient(testClient);
//        System.out.println("Saved client with ID: " + testClient.getId());


//        // читаємо запис за ID
//        Long clientId = testClient.getId();
//        Client retrievedClient = clientDao.findById(clientId);
//        System.out.println("Retrieved client: " + retrievedClient);

        // читаємо запис за IDd
        //Long clientId = test1Client.getId();
        Client retrievedClient = clientDao.findById(ansver.getId());
        System.out.println("Retrieved client: " + retrievedClient);

//        // читаємо запис за іменем
//        String clientname = "Killy";
//        boolean ansver = clientDao.isClientExists(clientname);
//        System.out.println("ansver = " + ansver);

//        // читаємо або створюємо запис за іменем
//        Client test1Client = new Client();
//        test1Client.setName("Shvilly Junior");
//        Long ansver = clientDao.assertClient(test1Client);
//        System.out.println("ansver = " + ansver);


//        // оновлюємо запис
//        retrievedClient.setName("Billy Junior");
//        clientDao.update(retrievedClient);
//        System.out.println("Updated client with ID: " + retrievedClient.getId());

//        // видалення запису
//        clientDao.delete(retrievedClient);
//        System.out.println("Deleted client with ID: " + retrievedClient.getId());

//        // читаємо всі клієнти
//        List<Client> allClients = clientDao.getAllClients();
//        System.out.println("All clients:");
//        for (Client client : allClients) {
//            System.out.println(client);
//        }

//        // перевірка, чи обєкт є в базі даних
//        Client deletedClient = clientDao.findById(clientId);
//        if (deletedClient == null) {
//            System.out.println("Client with ID " + clientId + " is not found (as expected).");
//        } else {
//            System.out.println("Client with ID " + clientId + " still exists (unexpected).");
//        }
    }
}

