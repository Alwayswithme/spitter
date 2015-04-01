# CREATE DATABASE spitter;
#
# CREATE TABLE IF NOT EXISTS Person (
#   `id` INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
#   `name` VARCHAR(50) NOT NULL,
#   `age` TINYINT NOT NULL DEFAULT 1
# )

INSERT INTO spitter.Person (name, age) VALUES ('root', 23);
INSERT INTO spitter.Person (name, age) VALUES ('finch', 40);
INSERT INTO spitter.Person (name, age) VALUES ('john', 38);