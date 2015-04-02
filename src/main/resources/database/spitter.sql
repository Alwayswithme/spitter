# CREATE DATABASE spitter;
#
# CREATE TABLE IF NOT EXISTS Person (
#   `id` INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
#   `name` VARCHAR(50) NOT NULL,
#   `age` TINYINT NOT NULL DEFAULT 1
# )

# INSERT INTO spitter.Person (name, age) VALUES ('root', 23);
# INSERT INTO spitter.Person (name, age) VALUES ('finch', 40);
# INSERT INTO spitter.Person (name, age) VALUES ('john', 38);

# CREATE TABLE IF NOT EXISTS House (
#   `id` INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
#   `owner_id` INT(11),
#   `size` VARCHAR(10) NOT NULL,
#   `location` VARCHAR(50) NOT NULL,
#   FOREIGN KEY (owner_id) REFERENCES Person(id)
# )

# INSERT INTO spitter.House (owner_id, size, location) VALUES (1, 'LARGE', 'downtown');
# INSERT INTO spitter.House (owner_id, size, location) VALUES (2, 'SMALL', 'village');
# INSERT INTO spitter.House (owner_id, size, location) VALUES (3, 'LARGE', 'beach');
# INSERT INTO spitter.House (owner_id, size, location) VALUES (2, 'MEDIUM', 'beach');