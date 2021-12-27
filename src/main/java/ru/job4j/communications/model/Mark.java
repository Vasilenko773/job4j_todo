package ru.job4j.communications.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "auto_mark")
public class Mark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "markCar")
    private List<Model> modelCars = new ArrayList<>();

    public static Mark of(String name) {
        Mark markCar = new Mark();
        markCar.name = name;
        return markCar;
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

    public List<Model> getModelCars() {
        return modelCars;
    }

    public void setModelCars(List<Model> modelCars) {
        this.modelCars = modelCars;
    }

    public void add(Model model) {
        this.getModelCars().add(model);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Mark markCar = (Mark) o;
        return id == markCar.id && Objects.equals(name, markCar.name) && Objects.equals(modelCars, markCar.modelCars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, modelCars);
    }


    @Override
    public String toString() {
        return "Mark [id=" + id + ", name=" + name + "]";
    }
}
