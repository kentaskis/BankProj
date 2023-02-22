CREATE TABLE IF NOT EXISTS `transaction`
(
    `id`                binary(16)    NOT NULL,
    `debit_account_id`  binary(16)    NOT NULL,
    `credit_account_id` binary(16)    NOT NULL,
    `type`              int            NOT NULL,
    `amount`            decimal(12, 0) NOT NULL,
    `description`       varchar(255)   NOT NULL,
    `created_at`        timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


ALTER TABLE `transaction`
    ADD CONSTRAINT `transaction_fk0` FOREIGN KEY (`debit_account_id`) REFERENCES `account` (`id`);