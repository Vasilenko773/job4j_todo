create table engines
(
    id serial primary key
);

create table drivers
(
    id serial primary key
);

create table cars
(
    id        serial primary key,
    engine_id int references engines (id)
);

create table history_owner
(
    id        serial primary key,
    driver_id int references drivers (id),
    car_id    int references cars (id)
);