package ru.job4j.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.model.Item;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateUtil implements Store {

    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();

    private SessionFactory sf;


    private HibernateUtil() {
        sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    private static final class Lazy {
        private static final Store INST = new HibernateUtil();
    }

    public static Store instOf() {
        return Lazy.INST;
    }

    @Override
    public List<Item> findAllItems() {
        Session session = sf.openSession();
        session.beginTransaction();
        List<Item> result = session.createQuery("from ru.job4j.model.Item").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public Item save(Item item) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        session.close();
        return item;
    }

    @Override
    public Item findById(Integer id) {
        Session session = sf.openSession();
        session.beginTransaction();
        Item result = session.get(Item.class, id);
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public void update(int id) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.createQuery("UPDATE ru.job4j.model.Item set done = :done where id = :id")
                .setParameter("done", true)
                .setParameter("id", id)
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Item findByDescription(String description) {
        Session session = sf.openSession();
        Item result = session.get(Item.class, description);
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public List<Item> findByDone() {
        Session session = sf.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from ru.job4j.model.Item where done = :done");
        List<Item> result = query.setParameter("done", false).list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

}
