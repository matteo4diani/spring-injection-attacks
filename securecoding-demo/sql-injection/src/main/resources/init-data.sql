INSERT INTO injection.users(id, username, password, name, surname)
    VALUES (1, 'admin1', 'admin1!', 'Admin', 'User');
INSERT INTO injection.users(id, username, password, name, surname)
    VALUES (2, 'user1', 'user1!', 'Test', 'User');
INSERT INTO injection.users(id, username, password, name, surname)
    VALUES (3, 'user2', 'user2!', 'Test2', 'User');


INSERT INTO injection.user_roles(id, user_id, role)
    VALUES (1, 1, 'ADMIN');
INSERT INTO injection.user_roles(id, user_id, role)
    VALUES (2, 2, 'USER');
INSERT INTO injection.user_roles(id, user_id, role)
    VALUES (3, 3, 'USER');