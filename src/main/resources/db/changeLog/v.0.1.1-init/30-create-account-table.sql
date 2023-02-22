CREATE TABLE IF NOT EXISTS `account`
(
    `id`            binary(16)     NOT NULL,
    `client_id`     binary(16)    NOT NULL,
    `name`          varchar(100)   NOT NULL,
    `type`          int            NOT NULL,
    `status`        int            NOT NULL,
    `balance`       decimal(15, 2) NOT NULL,
    `currency_code` int            NOT NULL,
    `created_at`    timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`    timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


ALTER TABLE `account`
    ADD CONSTRAINT `account_fk0` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`);