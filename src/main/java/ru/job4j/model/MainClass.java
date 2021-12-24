package ru.job4j.model;

import ru.job4j.store.HibernateUtil;

public class MainClass {

    public static void main(String[] args) {

        System.out.println(HibernateUtil.instOf().findById(1).getUser().getName());

    }
}
