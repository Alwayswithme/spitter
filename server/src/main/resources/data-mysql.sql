INSERT INTO spitter.Person (name, age, created_date) VALUES ('root', 23, NOW());
INSERT INTO spitter.Person (name, age, created_date) VALUES ('finch', 40, NOW());
INSERT INTO spitter.Person (name, age, created_date) VALUES ('john', 38, NOW());


INSERT INTO spitter.House (owner_id, size, location, ip_address) VALUES (1, 2, 'downtown', INET6_ATON('fdfe::5a55:caff:fefa:9089'));
INSERT INTO spitter.House (owner_id, size, location, ip_address) VALUES (2, 0, 'village', INET6_ATON('fdfe::5a55:caff:fefa:9089'));
INSERT INTO spitter.House (owner_id, size, location, ip_address) VALUES (3, 2, 'beach', INET6_ATON('10.0.5.9'));
INSERT INTO spitter.House (owner_id, size, location, ip_address) VALUES (2, 1, 'beach', INET6_ATON('192.168.8.1'));

INSERT INTO spitter.Device (owner_id, name, type) VALUES (1, 'iMac 27', 0);
INSERT INTO spitter.Device (owner_id, name, type) VALUES (1, 'Pavilion 15', 1);
INSERT INTO spitter.Device (owner_id, name, type) VALUES (1, 'iPhone 4', 2);
