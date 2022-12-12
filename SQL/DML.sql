-- Insert some Author's of Books into the database 
INSERT INTO AUTHOR
VALUES      ('Paulo', 'Coelho'),      -- These values represent the First name and Last name of the Authors.
            ('Lisa', 'Maxwell'),
            ('Scott', 'Reintgen');

-- Insert some Books into the database with their corresponding information such as ISBN, Name, Genre etc
INSERT INTO BOOK
VALUES    (9780062315007, 'The Alchemist', 'FICTION', 10, 25.2, 208, 25, 5, '2014-04-15', NULL),
(9781481432085, 'The Last Magician', 'FANTASY', 15, 20.0, 528, 1, 7, '2018-08-07', NULL),
(9780399556821, 'Nyxia', 'MYSTERY', 20, 15.5, 400, 1, 6, '2018-06-19', NULL);


-- Insert Writes relationships between Authors and Books into the database. The Author with specified first_name and last_name Writes a book with specified ISBN
INSERT INTO WRITES
VALUES		('Paulo', 'Coelho', 9780062315007),
		('Lisa', 'Maxwell', 9781481432085),
		('Scott', 'Reintgen', 9780399556821);


-- Insert Addresses into the database with the corresponding and correct information
INSERT INTO ADDRESS
VALUES     (1, NULL, 'Billing_Bridge', 100, 'Ottawa', 'ON', 'Canada', 'K1TSP3'),
           (2, NULL, 'Ave_South', 25, 'Toronto', 'ON', 'Canada', 'K3F5NZ'),
           (3, NULL, 'Bank St', 73, 'Montreal', 'QC', 'Canada', 'S5P7N1');


-- Insert Publishers into the database with the corresponding and correct information 
INSERT INTO PUBLISHER
VALUES
		('HarperOne', 'HarperOne@gmail.com', 0123456789, 012345678910110, 1),
		('Margaret K. McElderry Books', 'Margaret K. McElderry Books @gmail.com', 9876543210, 011019876543210, 2),
		('Ember', 'Ember@gmail.com', 1357924687, 153860298364174, 3);


-- Queries below to update Books' information in the database such that it's Publisher's name is now adequately inputted, where before it was NULL 
UPDATE BOOK
SET publisher_name = 'HarperOne'
WHERE BOOK.ISBN = 9780062315007;

UPDATE BOOK
SET publisher_name = 'Margaret K. McElderry Books'
WHERE BOOK.ISBN = 9781481432085;

UPDATE BOOK
SET publisher_name = 'Ember'
WHERE BOOK.ISBN = 9780399556821;


-- Inserting an address onto 
INSERT INTO ADDRESS
VALUES     (4, 900, 'Bank st', 70, 'Ottawa', 'ON', 'Canada', 'K1S4SS'),


INSERT INTO USERS
VALUES (‘admin, admin, ‘first’, ‘last’, ‘firstLast@gmail.com’, 4)



INSERT INTO AUTHOR
VALUES      ('Micheal', 'Goodspeed'),
            ('Lisa', 'Maxwell'),
            ('Scott', 'Reintgen');


INSERT INTO BOOK
VALUES    (9781990644313, ' Dead Spy, Cold Grave, 'ADVENTURE', 30, 30.5, 300, 3, 9, '2016-04-20', 'HarperOne'),
(9781481432085, 'The Last Magician', 'FANTASY', 15, 20.0, 528, 1, 7, '2018-08-07', NULL),
(9780399556821, 'Nyxia', 'MYSTERY', 20, 15.5, 400, 1, 6, '2018-06-19', NULL);



INSERT INTO WRITES
VALUES		('Paulo', 'Coelho', 9780062315007),
		('Lisa', 'Maxwell', 9781481432085),
		('Scott', 'Reintgen', 9780399556821);



INSERT INTO BOOK
VALUES    (9780062315012, 'The Treasure', 'FICTION', 90, 50, 200, 20, 9, '2017-08-15', 'HarperOne');

INSERT INTO WRITES
VALUES ('Lisa', 'Maxwell', 9780062315012);

