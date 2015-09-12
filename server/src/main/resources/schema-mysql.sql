CREATE DATABASE IF NOT EXISTS spitter;
USE spitter;

DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS threads;

CREATE TABLE `comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) DEFAULT NULL,
  `thread_id` int(11) NOT NULL,
  `created` datetime NOT NULL,
  `text` varchar(1024) NOT NULL,
  `author` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `likes` int(11) NOT NULL DEFAULT '0',
  `ip_address` varbinary(16) NOT NULL,
  `voters` blob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `threads` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
