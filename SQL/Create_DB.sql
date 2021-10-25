Create database webshop;
use webshop;

CREATE TABLE role(
                     rolecode VARCHAR(150) NOT NULL PRIMARY KEY,
                     rolename NVARCHAR(250) NOT NULL
);

CREATE TABLE user(
                     username VARCHAR(150) NOT NULL PRIMARY KEY,
                     password VARCHAR(250) NOT NULL,
                     fullname NVARCHAR(250) NOT NULL,
                     email VARCHAR(250),
                     phone VARCHAR(10),
                     createddate DATETIME,
                     createdby VARCHAR(150),
                     modifieddate DATETIME,
                     modifiedby VARCHAR(150),
                     status INT NOT NULL,
                     rolecode VARCHAR(150),
                     CONSTRAINT fk_user_role FOREIGN KEY (rolecode) REFERENCES role(rolecode)
);

INSERT INTO role VALUES('USER', 'Người dùng');
INSERT INTO role VALUES('ADMIN', 'Quản lý');

INSERT INTO user VALUES('trieulc', '$2a$10$w5/0AKoVodPvmGnqWfTMFeHaZQhEYNDXuiifmY58v6O43jhdF7a6i', 'le cao trieu', NULL, NULL, NULL, NULL, NULL, NULL,'1','USER');
INSERT INTO user VALUES('admin', '$2a$10$w5/0AKoVodPvmGnqWfTMFeHaZQhEYNDXuiifmY58v6O43jhdF7a6i', 'admin', NULL,NULL , NULL, NULL, NULL, NULL,'1','ADMIN');

CREATE TABLE product(
                        productId BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                        productcode VARCHAR(255) NOT NULL,
                        productname NVARCHAR(255) NOT NULL,
                        createddate DATETIME,
                        createdby VARCHAR(150),
                        modifieddate DATETIME,
                        modifiedby VARCHAR(150),
                        price BIGINT,
                        sale INT,
                        image varchar(255),
                        description TEXT,
                        store NVARCHAR(255)
);

CREATE TABLE cart(
                     cartid BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                     username VARCHAR(150),
                     createddate DATETIME,
                     createdby VARCHAR(150),
                     modifieddate DATETIME,
                     modifiedby VARCHAR(150),
                     status INT NOT NULL,

                     CONSTRAINT fk_cart_user FOREIGN KEY (username) REFERENCES user(username)
);

CREATE TABLE cartproduct(
                            id BIGINT NOT NULL PRIMARY KEY AUTO_iNCREMENT,
                            productId BIGINT,
                            cartid BIGINT,
                            quantity int,
                            totalmoney BIGINT,
                            CONSTRAINT fk_cartproduct_product FOREIGN KEY (productId) REFERENCES product(productId),
                            CONSTRAINT fk_cartproduct_cart FOREIGN KEY (cartid) REFERENCES cart(cartid)
);

CREATE TABLE checkout(
                         id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
                         createddate DATETIME,
                         address NVARCHAR(255),
                         phone VARCHAR(11),
                         money BIGINT NOT NULL ,
                         cartid BIGINT NOT NULL UNIQUE,
                         username VARCHAR(150),
                         CONSTRAINT fk_checkout_cart FOREIGN KEY (cartid) REFERENCES cart(id),
                         CONSTRAINT fk_checkout_user FOREIGN KEY (username) references user(username)
)