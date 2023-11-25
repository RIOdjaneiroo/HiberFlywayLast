package org.example.dao;
import org.example.entity.Client;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.example.hibernate.HibernateUtils;
import org.example.entity.Planet;

import java.util.List;

public class PlanetDao {
    private SessionFactory sessionFactory = HibernateUtils.getInstance().getSessionFactory();

    // Create
    public void createPlanet(Planet planet) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.persist(planet);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            throw new RuntimeException("Помилка при збереженні об'єкта Planet", e);
        }
    }

    // Read
    public Planet findById(String id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Planet.class, id);
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Помилка при отриманні об'єкта Planet за ідентифікатором", e);
        }
    }

    // Read all planets
    public List<Planet> getAllPlanets() {
        try (Session session = sessionFactory.openSession()) {
            Query<Planet> query = session.createQuery("FROM Planet", Planet.class);
            return query.list();
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Помилка при отриманні списку планет", e);
        }
    }

    // Update
    public void update(Planet planet) {
        if (planet == null) {
            throw new IllegalArgumentException("Помилка: об'єкт Planet для оновлення є null");
        }
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.merge(planet);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            throw new RuntimeException("Помилка при оновленні об'єкта Planet", e);
        }
    }

    // Delete
    public void delete(Planet planet) {
        if (planet == null) {
            throw new IllegalArgumentException("Помилка: об'єкт Planet для видалення є null");
        }
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.remove(planet);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            throw new RuntimeException("Помилка при видаленні об'єкта Planet", e);
        }
    }
    public boolean isPlanetExists(String planetId) {
        try (Session session = sessionFactory.openSession()) {
            Query<Boolean> query = session.createQuery("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Planet p WHERE p.id = :id", Boolean.class);
            query.setParameter("id", planetId);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Помилка при перевірці наявності планети за ідентифікатором", e);
        }
    }

    public Planet findPlanetById (String planetId){
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()){
            Planet planet = session.get(Planet.class, planetId);
            return planet;
        }
    }

}

