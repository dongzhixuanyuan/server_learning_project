CREATE DATABASE `antiswindle` DEFAULT CHARACTER SET utf8;

USE `antiswindle`;

SET FOREIGN_KEY_CHECKS = 0;


/*
class User(
    var id: Int,
    var name: String,
    var avatar_url: String,
    var phone: String,
    var password: String,
    var created_at: Long,
    var updated_at: Long
)*/


# DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user`
(
    `id`         int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `name`       varchar(255)        NOT NULL,
    `avatar_url` varchar(255)        NOT NULL DEFAULT '',
    `phone`      varchar(255) UNIQUE NOT NULL DEFAULT '',
    `password`   varchar(255)        NOT NULL DEFAULT '',
    `created_at` TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP           NOT NULL DEFAULT '1970-01-01 08:00:02'
)