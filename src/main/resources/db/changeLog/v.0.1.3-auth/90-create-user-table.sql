CREATE TABLE IF NOT EXISTS `user`
(
    `id`         binary(16)  NOT NULL,
    `login`      varchar(50) NOT NULL,
    `password`   varchar(50) NOT NULL,
    `role`       json  NOT NULL,
    `client_id`  binary(16)  NULL,
    `manager_id` int         null,
    `created_at` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

ALTER TABLE `user`
    ADD CONSTRAINT `user_manager_fk0` FOREIGN KEY (`manager_id`) REFERENCES `manager` (`id`);

ALTER TABLE `user`
    ADD CONSTRAINT `user_client_fk0` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`);