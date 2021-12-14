create table items
(
    id          serial primary key,
    description text,
    created     timestamp,
    done        boolean
);