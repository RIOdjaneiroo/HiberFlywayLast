package org.example.dao;
import org.example.entity.Ticket;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.example.hibernate.HibernateUtils;
import org.example.entity.Client;

import java.util.List;

public class ClientDao {
    private SessionFactory sessionFactory = HibernateUtils.getInstance().getSessionFactory();

    // Create
    public void createClient(Client client) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.persist(client);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            throw new RuntimeException("Помилка при збереженні об'єкта Client", e);
        }
    }

    // Read
    public Client findById (Long clientId){
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()){
            Client client = session.get(Client.class, clientId);
            return client;
        }
    }
    // Read all clients
    public List<Client> getAllClients() {
        try (Session session = sessionFactory.openSession()) {
            Query<Client> query = session.createQuery("FROM Client", Client.class);
            return query.list();
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Помилка при отриманні списку клієнтів", e);
        }
    }

    // Update
    public void update(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("Помилка: об'єкт Client для оновлення є null");
        }
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.merge(client);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            throw new RuntimeException("Помилка при оновленні об'єкта Client", e);
        }
    }

    // Delete
    public void delete(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("Помилка: об'єкт Client для видалення є null");
        }
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.remove(client);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            throw new RuntimeException("Помилка при видаленні об'єкта Client", e);
        }
    }
    private Long getClientIdByName(Session session, String clientName) {
        try {
            Query<Long> query = session.createQuery("SELECT c.id FROM Client c WHERE c.name = :name", Long.class);
            query.setParameter("name", clientName);
            List<Long> results = query.list();

            if (!results.isEmpty()) {
                return results.get(0); // повертаємо перший знайдений ідентифікатор
            } else {
                return null; // якщо нічого не знайдено
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Помилка при отриманні ідентифікатора клієнта за іменем", e);
        }
    }
    public List<Ticket> getTickets(Long clientId) {
        try (Session session = sessionFactory.openSession()) {
            Query<Ticket> query = session.createQuery("FROM Ticket WHERE client.id = :clientId", Ticket.class);
            query.setParameter("clientId", clientId);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Помилка при отриманні списку квитків для клієнта", e);
        }
    }
    public Client assertClient(Client client) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();

                // Check if the client exists
                Client persistedClient = getClientByName(session, client.getName());

                if (persistedClient != null) {
                    // If the client exists, update its properties
                    persistedClient.setName(client.getName());
                    // оновлюйте інші властивості за необхідності

                    session.merge(persistedClient);
                } else {
                    // If the client does not exist, create it
                    session.persist(client);
                    persistedClient = client; // оновіть або встановіть інші властивості за необхідності
                }

                tx.commit();
                return persistedClient;
            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                }
                e.printStackTrace();
                throw new RuntimeException("Error asserting and saving/updating Client", e);
            }
        }
    }
    private Client getClientByName(Session session, String clientName) {
        try {
            Query<Client> query = session.createQuery("FROM Client c WHERE c.name = :name", Client.class);
            query.setParameter("name", clientName);
            //client.getProjects().forEach(Project::getId); // to do for lazy loading Initiated get projects
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error getting Client by name", e);
        }
    }
    public Client findByName(String name) {
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            Query<Client> query = session.createQuery("FROM Client c WHERE c.name = :name", Client.class);
            query.setParameter("name", name);
            List<Client> results = query.list();

            if (!results.isEmpty()) {
                return results.get(0); // Повертаємо перший знайдений клієнт
            } else {
                return null; // Якщо нічого не знайдено
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Помилка при отриманні клієнта за іменем", e);
        }
    }
//    public Client assert1Client(Client client) {
//        try (Session session = sessionFactory.openSession()) {
//            Transaction tx = null;
//            try {
//                tx = session.beginTransaction();
//
//                // Check if the client exists
//                Client persistedClient = getClientByName(session, client.getName());
//
//                if (persistedClient != null) {
//                    // If the client exists, update it
//                    persistedClient.setSomeProperty(client.getSomeProperty());  // Оновіть залежно від ваших потреб
//                } else {
//                    // If the client does not exist, create it
//                    session.persist(client);
//                    persistedClient = client;
//                }
//
//                tx.commit();
//                return persistedClient;
//            } catch (Exception e) {
//                if (tx != null) {
//                    tx.rollback();
//                }
//                e.printStackTrace();
//                throw new RuntimeException("Error asserting Client", e);
//            }
//        }
//    }
}


