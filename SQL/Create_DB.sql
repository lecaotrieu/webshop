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
                     createddate DATETIME,
                     createby VARCHAR(150),
                     modifieddate DATETIME,
                     modifiedby VARCHAR(150),
                     status INT NOT NULL,
                     rolecode VARCHAR(150),
                     CONSTRAINT fk_user_role FOREIGN KEY (rolecode) REFERENCES role(rolecode)
);

INSERT INTO role VALUES('user', 'Người dùng');
INSERT INTO role VALUES('admin', 'Quản lý');

INSERT INTO user VALUES('trieulc', '123456', 'le cao trieu', NULL, NULL, NULL, NULL,'1','user');
INSERT INTO user VALUES('admin', '123456', 'admin', NULL,NULL , NULL, NULL,'1','admin');

CREATE TABLE product(
                        productId BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                        productcode VARCHAR(255) NOT NULL,
                        productname NVARCHAR(255) NOT NULL,
                        createddate DATETIME,
                        createby VARCHAR(150),
                        modifieddate DATETIME,
                        modifiedby VARCHAR(150),
                        price DECIMAL(15,2),
                        sale INT,
                        image varchar(255),
                        description TEXT,
                        store NVARCHAR(255)
);

CREATE TABLE cart(
                     cartid BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                     username VARCHAR(150) NOT NULL,
                     createddate DATETIME,
                     createby VARCHAR(150),
                     modifieddate DATETIME,
                     modifiedby VARCHAR(150),
                     status INT NOT NULL,

                     CONSTRAINT fk_cart_user FOREIGN KEY (username) REFERENCES user(username)
);

CREATE TABLE cartproduct(
                            id BIGINT NOT NULL PRIMARY KEY AUTO_iNCREMENT,
                            productId BIGINT,
                            cartid bigint,
                            quantity int,
                            totalmoney DECIMAL(15,2),
                            CONSTRAINT fk_cartproduct_product FOREIGN KEY (productId) REFERENCES product(productId),
                            CONSTRAINT fk_cartproduct_cart FOREIGN KEY (cartid) REFERENCES cart(cartid)
);
