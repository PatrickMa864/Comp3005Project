CREATE TABLE  AUTHOR(
	first_name	VARCHAR(50)	NOT NULL,
	last_name	VARCHAR(50) 	NOT NULL,
              PRIMARY KEY (first_name, last_name)	
);

CREATE TABLE  ADDRESS (
	address_id	SERIAL      PRIMARY KEY,
	apartment_num    INT,
	street_name   VARCHAR(50),
	street_num	    INT,
	city	           VARCHAR(50)	NOT NULL,
	province          VARCHAR(30)	NOT NULL,
	country	            VARCHAR(50)	NOT NULL,
	postal_code    VARCHAR(6)	NOT NULL
);

CREATE TABLE  PUBLISHER (
	Publisher_name    VARCHAR(50)	    PRIMARY KEY,
	email	VARCHAR(100)    UNIQUE,
	phone_number    NUMERIC(10, 0)    NOT NULL,
	banking_account    NUMERIC(15, 0)    UNIQUE    NOT NULL,
	address_id	SERIAL ,
               FOREIGN KEY (address_id) REFERENCES ADDRESS(address_id)
);

CREATE TABLE USERS (
	user_name    	VARCHAR(50)	PRIMARY KEY,
	password       	VARCHAR(50)        NOT NULL,
	first_name	VARCHAR(50),
	last_name          VARCHAR(50),
	email	              VARCHAR(100)    UNIQUE,
	address_id	SERIAL,
               FOREIGN KEY (address_id) REFERENCES ADDRESS(address_id)
);

CREATE TABLE  BOOK (
	ISBN	NUMERIC(13, 0)    PRIMARY KEY,
	name	VARCHAR(250)       NOT NULL,
	genre	VARCHAR(150)       NOT NULL,
	no_of_copies	NUMERIC(10,0)	   NOT NULL,
	price		NUMERIC(6, 2)     NOT NULL ,
	no_of_pages	NUMERIC(5,0),
	version		NUMERIC(3,0)      NOT NULL,
	royalty		NUMERIC(4,2)      NOT NULL,
	published_year	 DATE,
	publisher_name     VARCHAR(50),
FOREIGN KEY (publisher_name) REFERENCES PUBLISHER (Publisher_name)
);

CREATE TABLE ADDTOBASKET(
	user_name    	VARCHAR(50)       NOT NULL,
	ISBN	NUMERIC(13, 0)    NOT NULL,
	quantity INT,
	FOREIGN KEY(user_name) REFERENCES USERS(user_name),
	FOREIGN KEY (ISBN) REFERENCES BOOK (ISBN),
	PRIMARY KEY (user_name, ISBN)
);

CREATE TABLE WRITES(
first_name	VARCHAR(50)	NOT NULL,
	last_name	VARCHAR(50) 	NOT NULL,
	ISBN	NUMERIC(13, 0),
FOREIGN KEY (ISBN) REFERENCES BOOK (ISBN),
FOREIGN KEY (first_name, last_name) REFERENCES AUTHOR,
	PRIMARY KEY (first_name, last_name, ISBN)
);

CREATE TABLE ORDERS(
	order_no	SERIAL 		PRIMARY KEY,
	initial_date	DATE	NOT NULL,
	price		NUMERIC(9,2)	NOT NULL,
	estimate_delivery	DATE,
	status 	VARCHAR(15),
	shipping_address_id	SERIAL      NOT NULL,
	billing_address_id	SERIAL,
	user_name	VARCHAR(50)       NOT NULL,
	FOREIGN KEY (shipping_address_id) REFERENCES ADDRESS(address_id),
	FOREIGN KEY (billing_address_id) REFERENCES ADDRESS(address_id),
	FOREIGN KEY(user_name) REFERENCES USERS(user_name)
);

CREATE TABLE ADDTOORDER(
	ISBN	NUMERIC(13, 0) 	REFERENCES BOOK(ISBN),
	order_no	SERIAL		REFERENCES ORDERS(order_no)	
);
