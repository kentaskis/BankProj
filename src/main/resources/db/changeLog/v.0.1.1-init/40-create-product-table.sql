CREATE TABLE IF NOT EXISTS `product`
(
    `id`            int           NOT NULL AUTO_INCREMENT,
    `manager_id`    int           NOT NULL,
    `name`          varchar(70)   NOT NULL,
    `status`        int           NOT NULL,
    `currency_code` int           NOT NULL,
    `interest_rate` decimal(6, 4) NOT NULL,
    `limit`         int           NOT NULL,
    `created_at`    timestamp     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`    timestamp     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


ALTER TABLE `product`
    ADD CONSTRAINT `product_fk0` FOREIGN KEY (`manager_id`) REFERENCES `manager` (`id`);