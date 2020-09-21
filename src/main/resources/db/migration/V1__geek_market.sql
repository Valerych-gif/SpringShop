DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(50) NOT NULL,
  password char(80) NOT NULL,
  first_name varchar(50) NOT NULL,
  last_name varchar(50) NOT NULL,
  email varchar(50) NOT NULL,
  phone varchar(15) NOT NULL,
  enabled boolean,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


INSERT INTO users (username,password,first_name,last_name,email,phone)
VALUES
('alex','$2a$10$Wi2b45tODhn5qOM9Hcpm7e6HVPOmvyp8tuC0QkD4VSUQQRoFrEUxO','Alex','GeekBrains','alex@gb.com', '123');



DROP TABLE IF EXISTS roles;

CREATE TABLE roles (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(50) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


INSERT INTO roles (name)
VALUES
('ROLE_ADMIN'), ('ROLE_USER');


CREATE TABLE users_roles (
  user_id int(11) NOT NULL,
  role_id int(11) NOT NULL,

  PRIMARY KEY (user_id, role_id),

  KEY FK_ROLE_idx (role_id),

  CONSTRAINT FK_USER_05 FOREIGN KEY (user_id)
  REFERENCES users (id)
  ON DELETE NO ACTION ON UPDATE NO ACTION,

  CONSTRAINT FK_ROLE FOREIGN KEY (role_id)
  REFERENCES roles (id)
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO users_roles (user_id, role_id) VALUES (1, 1), (1, 2);

DROP TABLE IF EXISTS categories;

CREATE TABLE categories (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NULL,
  PRIMARY KEY (`id`));

INSERT INTO categories (title) VALUES
("SmartPhone"), ("TV");

DROP TABLE IF EXISTS products;

CREATE TABLE products (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `category_id` INT UNSIGNED NULL,
  `vendor_code` VARCHAR(255) NULL,
  `title` VARCHAR(255) NOT NULL,
  `short_description` VARCHAR(255) NULL,
  `full_description` VARCHAR(255) NULL,
  `price` INT NOT NULL,
  `create_at` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `category_id_fk_idx` (`category_id` ASC) VISIBLE,
  CONSTRAINT `category_id_fk`
    FOREIGN KEY (`category_id`)
    REFERENCES `geek-market`.`categories` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

INSERT INTO products (category_id, vendor_code, title, short_description, full_description, price, create_at)
VALUES
(1, NULL, "IPhone", "IPhone 11 PRO", NULL, 100, NULL),
(1, "Samsung", "Galaxy S20", "Big Phone", NULL, 90, NULL);

DROP TABLE IF EXISTS orders;

CREATE TABLE orders (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `user_id_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `geek-market`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

DROP TABLE IF EXISTS orderitems;

CREATE TABLE orderitems (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `order_id` INT UNSIGNED NOT NULL,
  `quantity` INT NOT NULL,
  `item_price` INT NOT NULL,
  `total_price` INT NOT NULL,
  `product_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `product_id_fk_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `product_id_fk`
    FOREIGN KEY (`product_id`)
    REFERENCES `geek-market`.`products` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `order_id_fk`
    FOREIGN KEY (`order_id`)
    REFERENCES `geek-market`.`orders` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
