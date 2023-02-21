CREATE TABLE IF NOT EXISTS `manager`
(
    `id`         int         NOT NULL AUTO_INCREMENT,
    `first_name` varchar(50) NOT NULL,
    `last_name`  varchar(50) NOT NULL,
    `status`     int         NOT NULL,
    `created_at` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;