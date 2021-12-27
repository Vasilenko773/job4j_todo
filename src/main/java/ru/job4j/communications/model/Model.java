package ru.job4j.communications.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "auto_model")
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "mark_id")
    private Mark markCar;

    public Model() {
    }

    public static Model of(String name, Mark markCar) {
        Model model = new Model();
        model.name = name;
        model.markCar = markCar;
        return model;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Mark getMarkCar() {
        return markCar;
    }

    public void setMarkCar(Mark markCar) {
        this.markCar = markCar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Model modelCar = (Model) o;
        return id == modelCar.id && Objects.equals(name, modelCar.name) && Objects.equals(markCar, modelCar.markCar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, markCar);
    }

    @Override
    public String toString() {
        return "Model [id=" + id + ", name=" + name + "]";
    }
}
