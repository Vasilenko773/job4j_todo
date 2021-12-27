package ru.job4j.communications;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.communications.model.Mark;
import ru.job4j.communications.model.Model;

import java.util.ArrayList;
import java.util.List;

public class LazyInitializationException {

    public static void main(String[] args) {
        List<Mark> markList = new ArrayList<>();
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            markList = session.createQuery("from Mark ").list();
            for (Mark m : markList) {
                for (Model model : m.getModelCars()) {
                    System.out.println(model.toString());
                }
            }

            session.getTransaction().commit();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
