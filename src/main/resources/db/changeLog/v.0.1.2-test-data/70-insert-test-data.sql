INSERT INTO `manager` (`id`, `first_name`, `last_name`, `status`)
VALUES (1, 'Aleksey', 'Pobedonosec', 1),
       (2, 'Viktoria', 'Secret', 1),
       (3, 'Michael', 'Jackson', 1),
       (4, 'Arnold', 'Schwarzenegger', 1),
       (5, 'Silvester', 'Stallone', 3),
       (6, 'Adriano', 'Chilintano', 2);


INSERT INTO `client` (`id`, `manager_id`, `status`, `tax_code`, `first_name`, `last_name`, `email`, `address`, `phone`)
VALUES ('80cf3044-b1cd-11ed-8545-08979887bb18', 1, 1, '8254547854875', 'Aleksey', 'Lavrov', 'kentaskis.l@gmail.com',
        'Kamen, Einsteinstrasse 3', '380668882744'),
       ('4c15d4b3-b1d3-11ed-8545-08979887bb18', 2, 1, '8254798798798', 'Aleksey', 'Babaykin', 'a.babaykin@gmail.com',
        'Berlin, lessingsstrasse 5', '380658565555'),
       ('4c15d4f1-b1d3-11ed-8545-08979887bb18', 3, 1, '8259878743213', 'Aleksey', 'Hudoshin', 'hudoshin7605@gmail.com',
        'Keln, Hohler Weg 3', '380678789794'),
       ('5cd16c92-b1d3-11ed-8545-08979887bb18', 1, 1, '8259876543643', 'Michail', 'Egorov', 'michail.e@gmail.com',
        'Barselona, Barselonsstrasse 13', '6806689882744'),
       ('a04b2b18-b1d3-11ed-8545-08979887bb18', 4, 1, '8284365165465', 'Nataliya', 'Horb', 'nightangnata@gmail.com',
        'Munster, Einigestrasse 15', '380996654521'),
       ('ae6c8264-b1d4-11ed-8545-08979887bb18', 4, 1, '8283268546545', 'Irina', 'Naumenko', 'irina.n@gmail.com',
        'Dusseldorf, Einigestrasse 25', '09989554521');


INSERT INTO `account` (`id`, `client_id`, `name`, `type`, `status`, `balance`, `currency_code`)
VALUES ('b8ed7c33-b1cd-11ed-8545-08979887bb18', '80cf3044-b1cd-11ed-8545-08979887bb18', 'First', 1, 1, 100.00, 1),
       ('e5655ad4-b1cd-11ed-8545-08979887bb18', '80cf3044-b1cd-11ed-8545-08979887bb18', 'Second', 2, 1, 2000.00, 2),
       ('4ce519a0-b1d5-11ed-8545-08979887bb18', '4c15d4b3-b1d3-11ed-8545-08979887bb18', 'Credit', 2, 1, 300.00, 3),
       ('4ce51a24-b1d5-11ed-8545-08979887bb18', '4c15d4f1-b1d3-11ed-8545-08979887bb18', 'Some name', 2, 1, 400.00, 1),
       ('5e938b24-b1d5-11ed-8545-08979887bb18', '5cd16c92-b1d3-11ed-8545-08979887bb18', 'Third', 2, 1, 500.00, 3),
       ('72c98cb4-b1d5-11ed-8545-08979887bb18', 'a04b2b18-b1d3-11ed-8545-08979887bb18', 'Erst', 2, 1, 600.00, 1),
       ('72c98cd3-b1d5-11ed-8545-08979887bb18', 'a04b2b18-b1d3-11ed-8545-08979887bb18', 'Second card', 2, 1, 700.00, 2),
       ('72c98cd7-b1d5-11ed-8545-08979887bb18', 'ae6c8264-b1d4-11ed-8545-08979887bb18', 'My first', 2, 1, 800.00, 4),
       ('72c98cdb-b1d5-11ed-8545-08979887bb18', 'ae6c8264-b1d4-11ed-8545-08979887bb18', 'New', 2, 1, 900.00, 3);


INSERT INTO `transaction` (`id`, `debit_account_id`, `credit_account_id`, `type`, `amount`, `description`)
VALUES ('38820e01-b1ce-11ed-8545-08979887bb18', 'e5655ad4-b1cd-11ed-8545-08979887bb18',
        'b8ed7c33-b1cd-11ed-8545-08979887bb18', 1, 100, 'first transaction'),
       ('f42cf7fa-b1d9-11ed-8545-08979887bb18', 'e5655ad4-b1cd-11ed-8545-08979887bb18',
        '5e938b24-b1d5-11ed-8545-08979887bb18', 1, 1000, 'transaction 2'),
       ('f42cf819-b1d9-11ed-8545-08979887bb18', 'b8ed7c33-b1cd-11ed-8545-08979887bb18',
        '72c98cdb-b1d5-11ed-8545-08979887bb18', 1, 150, 'transaction 3'),
       ('f42cf81d-b1d9-11ed-8545-08979887bb18', 'b8ed7c33-b1cd-11ed-8545-08979887bb18',
        '4ce51a24-b1d5-11ed-8545-08979887bb18', 1, 200, 'transaction 4'),
       ('f42cf821-b1d9-11ed-8545-08979887bb18', '72c98cb4-b1d5-11ed-8545-08979887bb18',
        '72c98cdb-b1d5-11ed-8545-08979887bb18', 1, 350, 'transaction 5'),
       ('f42cf825-b1d9-11ed-8545-08979887bb18', '72c98cd7-b1d5-11ed-8545-08979887bb18',
        '4ce519a0-b1d5-11ed-8545-08979887bb18', 1, 180, 'transaction 6'),
       ('f42cf828-b1d9-11ed-8545-08979887bb18', '72c98cdb-b1d5-11ed-8545-08979887bb18',
        '4ce51a24-b1d5-11ed-8545-08979887bb18', 1, 120, 'transaction 7'),
       ('0a17b3e8-b1da-11ed-8545-08979887bb18', '5e938b24-b1d5-11ed-8545-08979887bb18',
        '4ce519a0-b1d5-11ed-8545-08979887bb18', 1, 350, 'transaction 8');


INSERT INTO `product` (`manager_id`, `name`, `status`, `currency_code`, `interest_rate`, `limit`)
VALUES (1, 'Deposit', 1, 1, 15, 1000000),
       (2, 'Credit', 1, 1, 5, 10000),
       (2, 'Credit', 1, 2, 4, 10000),
       (3, 'Ipoteka', 1, 1, 2, 1000000),
       (3, 'Ipoteka', 1, 2, 3, 1000000),
       (1, 'Deposit', 1, 2, 12, 10000);

INSERT INTO agreement (account_id, product_id, interest_rate, status, sum)
VALUES ('b8ed7c33-b1cd-11ed-8545-08979887bb18', 1, 15, 1, 10000),
       ('4ce519a0-b1d5-11ed-8545-08979887bb18', 2, 5, 1, 10000),
       ('5e938b24-b1d5-11ed-8545-08979887bb18', 3, 4, 1, 10000),
       ('72c98cb4-b1d5-11ed-8545-08979887bb18', 4, 2, 1, 1000000),
       ('72c98cd3-b1d5-11ed-8545-08979887bb18', 5, 3, 1, 1000000),
       ('72c98cd7-b1d5-11ed-8545-08979887bb18', 6, 12, 1, 10000);



