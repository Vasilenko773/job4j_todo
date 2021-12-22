package ru.job4j.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.model.Item;

import java.util.List;
import java.util.function.Function;

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
        return this.tx(session -> session.createQuery("from ru.job4j.model.Item").list());
    }

    @Override
    public Item save(Item item) {
        return (Item) this.tx(session -> session.save(item));

    }

    @Override
    public Item findById(Integer id) {
        return this.tx(session -> session.get(Item.class, id));
    }

    @Override
    public void update(int id) {
        this.tx(session -> session.createQuery("UPDATE ru.job4j.model.Item set done = :done where id = :id")
                .setParameter("done", true)
                .setParameter("id", id)
                .executeUpdate());
    }

    @Override
    public Item findByDescription(String description) {
        return this.tx(session -> session.get(Item.class, description));
    }

    @Override
    public List<Item> findByDone() {
        return this.tx(session -> session.createQuery("from ru.job4j.model.Item where done = :done")
                .setParameter("done", false).list());
    }

    private <T> T tx(final Function<Session, T> command) {
        Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}

