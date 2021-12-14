package ru.job4j.store;

import ru.job4j.model.Item;

import java.util.List;

public interface Store {

    List<Item> findAllItems();

    Item save(Item item);

    Item findById(Integer id);

    void update(int id);

    Item findByDescription(String description);

    List<Item> findByDone();
}

