package ru.job4j.model;

import ru.job4j.store.HibernateUtil;

public class MainClass {

    public static void main(String[] args) {

        Item item = HibernateUtil.instOf().findById(1);
        System.out.println(item);
        HibernateUtil.instOf().update(1);
        System.out.println(item);
    }
}
