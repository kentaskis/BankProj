INSERT INTO `user` (`id`, `login`, `password`, `role`, `client_id`, `manager_id`)
VALUES (unhex(replace('80cf3044-b1cd-11ed-8545-08979887bb19', '-', '')), 'admin', 'admin', '["ADMIN"]',null,null),
       (unhex(replace('80cf3044-b1cd-11ed-8545-08979887bb20', '-', '')), 'manager','manager', '["MANAGER"]',null, 1),
       (unhex(replace('80cf3044-b1cd-11ed-8545-08979887bb21', '-', '')), 'client', 'client', '["CLIENT"]',unhex(replace('80cf3044-b1cd-11ed-8545-08979887bb18', '-', '')) ,null);