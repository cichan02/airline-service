--liquibase formatted sql

--changeset cichan:create-events-table
create table if not exists events(
    id bigserial primary key,
    aggregate_id uuid,
    type varchar(30) not null,
    created timestamp not null,
    payload jsonb
);
--rollback drop table events

--changeset cichan:create-airlines-table
create table if not exists airline_aggregates(
    id uuid primary key,
    updated timestamp not null,
    "name" varchar(50) not null unique,
    iata varchar check (length(iata) = 2) unique,
    icao varchar check (length(icao) = 3) unique,
    callsign varchar(30) not null unique
);
--rollback drop table airlines;
