package ru.job4j.communications;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.communications.model.CarMark;
import ru.job4j.communications.model.CarModel;

public class ToManyDemonstration {

    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            CarModel model1 = new CarModel("First");
            session.save(model1);
            CarModel model2 = new CarModel("Second");
            session.save(model2);
            CarModel model3 = new CarModel("Third");
            session.save(model3);
            CarModel model4 = new CarModel("Four");
            session.save(model4);
            CarModel model5 = new CarModel("Fifth");
            session.save(model5);


            CarMark toyota = new CarMark("Toyota");
            session.save(toyota);

            toyota.addModel(session.load(CarModel.class, 1));
            toyota.addModel(session.load(CarModel.class, 2));
            toyota.addModel(session.load(CarModel.class, 3));
            toyota.addModel(session.load(CarModel.class, 4));
            toyota.addModel(session.load(CarModel.class, 5));

            session.update(toyota);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
