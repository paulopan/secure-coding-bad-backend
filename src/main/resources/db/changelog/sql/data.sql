INSERT INTO auth_user(username, password, security_question_one, security_question_two, security_question_three)
VALUES
    ('john.doe', '8a24367a1f46c141048752f2d5bbd14b', 'Cityville', 'High School A', 'Park'),
    ('alice.smith', 'bfb2489633a71c65be85b5025282b361', 'Townsville', 'High School B', 'Beach'),
    ('david.jones', '465c3a138b8dda49dbd3740e3cbcff13', 'Villagetown', 'High School C', 'Library'),
    ('emily.brown', 'b97497cc840d5275f54c38df339c21f0', 'Hamletsville', 'High School D', 'Coffee Shop'),
    ('chris.wilson', '013e3ae1ed6e3546618c14a1ae8d0b92', 'Suburbia', 'High School E', 'Gym');

INSERT INTO user_buyer(id, address)
VALUES
    (1, '123 Main St, Cityville'),
    (2, '456 Oak St, Townsville'),
    (3, '789 Pine St, Villagetown'),
    (4, '101 Elm St, Hamletsville'),
    (5, '202 Maple St, Suburbia');

INSERT INTO auth_user(username, password, security_question_one, security_question_two, security_question_three)
VALUES
    ('sarah.miller', '2c7941e2cc3bd2495a47a50eddacef1d', 'Ruralville', 'High School F', 'Grocery Store'),
    ('jason.white', '9d1ad544331089a4080211a2479bb6b2', 'Downtown', 'High School G', 'Movie Theater'),
    ('lisa.green', '3b21ad7c234eec5fb216077651fe613b', 'Mountainside', 'High School H', 'Park'),
    ('kevin.jenkins', '3c15beb809ef31d87ae875d1c3e437e5', 'Lakeside', 'High School I', 'Beach'),
    ('laura.smith', '6b27e912fb4ec34a7daf28e04bc8eb25', 'Countryside', 'High School J', 'Restaurant');

INSERT INTO user_seller(id, oib)
VALUES
    (6, '67890123456'),
    (7, '78901234567'),
    (8, '89012345678'),
    (9, '90123456789'),
    (10, '12398765432');

INSERT INTO auth_user(username, password, security_question_one, security_question_two, security_question_three)
    VALUES ('admin', 'e3274be5c857fb42ab72d786e281b4b8', 'Osijek', 'Srednja škola', 'Škola');