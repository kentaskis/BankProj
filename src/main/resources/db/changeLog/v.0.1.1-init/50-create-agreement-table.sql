CREATE TABLE IF NOT EXISTS `agreement`
(
    `id`            int            NOT NULL AUTO_INCREMENT,
    `account_id`    binary(16)    NOT NULL,
    `product_id`    int            NOT NULL,
    `interest_rate` decimal(6, 4)  NOT NULL,
    `status`        int            NOT NULL,
    `sum`           decimal(15, 2) NOT NULL,
    `created_at`    timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`    timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

ALTER TABLE `agreement`
    ADD CONSTRAINT `agreement_fk0` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`);

ALTER TABLE `agreement`
    ADD CONSTRAINT `agreement_fk1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);
