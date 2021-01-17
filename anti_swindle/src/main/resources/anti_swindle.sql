CREATE DATABASE IF NOT EXISTS `antiswindle` DEFAULT CHARACTER SET utf8;

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


DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `anti_swindle`;
CREATE TABLE IF NOT EXISTS `user`
(
    `id`         int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `name`       varchar(255)        NOT NULL,
    `avatar_url` varchar(255)        NOT NULL DEFAULT '',
    `phone`      varchar(255) UNIQUE NOT NULL DEFAULT '',
    `password`   varchar(255)        NOT NULL DEFAULT '',
    `created_at` TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP           NOT NULL DEFAULT '1970-01-01 08:00:02'
) engine = InnoDB
  charset = utf8;

create table if not exists `anti_swindle`
(
    `id`             int(11) primary key not null auto_increment,
    `source_account` varchar(255),
    `timestamp`      TIMESTAMP,
    `source`         varchar(255),
    `title`          varchar(255),
    `detail_content` varchar(255),
    `comment`        varchar(255),
    `video`          varchar(255),
    `image` varchar(255)
) engine = InnoDB
  charset = utf8;

# ALTER TABLE `anti_swindle`
# ADD COLUMN `image` varchar(255);



