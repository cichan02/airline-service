--liquibase formatted sql

--changeset cichan:insertion-into-airlines
insert into airline_aggregates(id, updated, name, iata, icao, callsign) values (gen_random_uuid(), current_timestamp, 'Belavia Belarusian Airlines', 'B2', 'BRU', 'BELAVIA');
insert into airline_aggregates(id, updated, name, iata, icao, callsign) values (gen_random_uuid(), current_timestamp, 'PJSC Aeroflot – Russian Airlines', 'SU', 'AFL', 'AEROFLOT');
insert into airline_aggregates(id, updated, name, iata, icao, callsign) values (gen_random_uuid(), current_timestamp, 'Aurora', 'HZ', 'SHU', 'AURORA');
insert into airline_aggregates(id, updated, name, iata, icao, callsign) values (gen_random_uuid(), current_timestamp, 'JSC Siberia Airlines', 'S7', 'SBI', 'SIBERIAN');
insert into airline_aggregates(id, updated, name, iata, icao, callsign) values (gen_random_uuid(), current_timestamp, 'Ukraine International Airlines PJSC', 'PS', 'AUI', 'UKRAINE INTERNATIONAL');
insert into airline_aggregates(id, updated, name, iata, icao, callsign) values (gen_random_uuid(), current_timestamp, 'Polskie Linie Lotnicze LOT S.A.', 'LO', 'LOT', 'LOT');
insert into airline_aggregates(id, updated, name, iata, icao, callsign) values (gen_random_uuid(), current_timestamp, 'Eurowings GmbH', 'EW', 'EWG', 'EUROWINGS');
insert into airline_aggregates(id, updated, name, iata, icao, callsign) values (gen_random_uuid(), current_timestamp, 'Deutsche Lufthansa AG', 'LH', 'DLH', 'LUFTHANSA');
insert into airline_aggregates(id, updated, name, iata, icao, callsign) values (gen_random_uuid(), current_timestamp, 'TUI fly Deutschland', 'X3', 'TUI', 'TUI JET');
insert into airline_aggregates(id, updated, name, iata, icao, callsign) values (gen_random_uuid(), current_timestamp, 'Air Arabia', 'G9', 'ABY', 'ARABIA');
insert into airline_aggregates(id, updated, name, iata, icao, callsign) values (gen_random_uuid(), current_timestamp, 'Air Arabia Abu Dhabi', '3L', 'ADY', 'NAWRAS');
insert into airline_aggregates(id, updated, name, iata, icao, callsign) values (gen_random_uuid(), current_timestamp, 'Emirates', 'EK', 'UAE', 'EMIRATES');
insert into airline_aggregates(id, updated, name, iata, icao, callsign) values (gen_random_uuid(), current_timestamp, 'Etihad Airways', 'EY', 'ETD', 'ETIHAD');
insert into airline_aggregates(id, updated, name, iata, icao, callsign) values (gen_random_uuid(), current_timestamp, 'Flydubai', 'FZ', 'FDB', 'SKYDUBAI');
insert into airline_aggregates(id, updated, name, iata, icao, callsign) values (gen_random_uuid(), current_timestamp, 'Wizz Air Abu Dhabi LLC', '5W', 'WAZ', 'WIZZ SKY');
