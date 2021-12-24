create table items
(
    id          serial primary key,
    description text,
    created     timestamp,
    done        boolean
);

create table users
(
    id          serial primary key,
    name text
);