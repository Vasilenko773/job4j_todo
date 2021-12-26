package ru.job4j.communications;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.communications.model.Author;
import ru.job4j.communications.model.Book;

public class ManyToMany {

    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Author first = new Author("Лермонтов");
            session.save(first);
            Author second = new Author("Пушкин");
            session.save(second);


            Book book = new Book("Герой нашего времени");
            session.save(book);
            Book book1 = new Book("Совместное произведение");
            session.save(book1);
            Book book2 = new Book("Золотая рыбка");
            session.save(book2);

            first.getBooks().add(book);
            first.getBooks().add(book1);
            session.update(first);
            second.getBooks().add(book2);
            session.update(second);

            session.remove(second);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
