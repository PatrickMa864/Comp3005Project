SELECT * FROM users -- Select all users currently registers on the system

SELECT * FROM address WHERE address_id='" + address_id + "' -- Take address_id as a parameter and select all the corresponding address' from the database

SELECT * FROM BOOK -- Select all the available books in the database

SELECT first_name, last_name from writes WHERE ISBN = '" + ISBN + "' -- Select an author for a corresponding book through passing the ISBN of that book

SELECT * from publisher WHERE  publisher_name = '" + publisher_name +"' -- Select a publisher with the passed name

SELECT * FROM addtobasket -- Select all from addtobasket in the database

UPDATE addtobasket SET quantity='" + quantity + "' WHERE user_name='" + user_name + "' and isbn='" + ISBN + "'  -- Update current user addstobasket in the database

INSERT into addtobasket values ('" + user_name + "','" + ISBN + "','" + quantity + "') -- Insert to current user addstobasket in the database

SELECT * FROM addtobasket -- Select all from addtobasket

UPDATE addtobasket SET quantity='" + quantity + "' WHERE user_name='" + user_name + "' and isbn='" + ISBN + "' -- Update current user addstobasket in the database

DELETE FROM addtobasket WHERE user_name = '" + user_name + "' AND ISBN = '" + ISBN + "' -- Delete from current user addstobasket in the database

SELECT count(address_id) From (SELECT * from address)counts; -- Select the count of address_id in the current number of addresses in the database

SELECT count(order_no) From (SELECT * from orders)counts; -- Select the count of order_no in the current number of orders in the database

SELECT count(user_name) From (SELECT * from users)counts; -- Select the number of user_names in the current number of users in the database

INSERT into orders values ('" + order_no + "','" + initial_date + "','" + price + "', '" + estimate_delivery + "', '" + status + "', '" + shipping_address_id + "', '" + billing_address_id + "', '" + user_name + "') -- Insert orders with corresponding values and given address value into the database

INSERT into address values ('" + address_id + "','" + apartment_num + "','" + street_name + "', '" + street_num + "', '" + city + "', '" + province + "', '" + country + "', '" + postal_code + "') -- Insert the address with corresponding values to the list of addresses in the DataBase

INSERT into users values ('" + user_name + "','" + password + "','" + first_name + "','" + last_name + "','" + email + "', '" + address_id + "') -- Insert the given user with corresponding values to the list of users in the dataBase

INSERT into book values ('" + ISBN + "','" + name + "','" + genre + "', '" + no_of_copies + "', '" + price + "', '" + no_of_pages + "', '" + version + "', '" + royalty + "', '" + publisher_year + "', '" + publisher_name + "') -- Inserts a new book with corresponding values to the list of books in the database

SELECT * FROM book -- Select all from books in the database

UPDATE book SET no_of_copies='" + quantity + "' WHERE isbn='" + ISBN + "' -- Updates the quantity of a corresponding book in the store

INSERT into  publisher ('" + publisher_name + "','" + email + "','" + phone_number + "', '" + banking_account + "', '" + address_id + "') -- Insert the given publisher to list of publishers in the database

INSERT into  AUTHOR ('" + first_name + "','" + last_name + "') -- Insert the given author to list of authors in the database

INSERT into WRITES ('" + ISBN + "','" + first_name + "','" + last_name + "') -- Insert into writes the given ISBN, author's first and last name into the database

SELECT * FROM AUTHOR -- Select all from authors in the database

SELECT * FROM PUBLISHER -- Select all from publishers in the database


