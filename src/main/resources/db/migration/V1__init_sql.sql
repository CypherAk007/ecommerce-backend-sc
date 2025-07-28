 CREATE TABLE category
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime NULL,
    updated_at datetime NULL,
    deleted    BIT(1) NOT NULL,
    name       VARCHAR(255) NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE category_feature_products
(
    category_id         BIGINT NOT NULL,
    feature_products_id BIGINT NOT NULL
);

CREATE TABLE product
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_at    datetime NULL,
    updated_at    datetime NULL,
    deleted       BIT(1) NOT NULL,
    name          VARCHAR(255) NULL,
    category_id   BIGINT NULL,
    `description` VARCHAR(255) NULL,
    image_url     VARCHAR(255) NULL,
    price DOUBLE NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

CREATE TABLE user
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime NULL,
    updated_at datetime NULL,
    deleted    BIT(1) NOT NULL,
    name       VARCHAR(255) NULL,
    email      VARCHAR(255) NULL,
    password   VARCHAR(255) NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

ALTER TABLE category_feature_products
    ADD CONSTRAINT uc_category_feature_products_featureproducts UNIQUE (feature_products_id);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (id);

ALTER TABLE category_feature_products
    ADD CONSTRAINT fk_catfeapro_on_category FOREIGN KEY (category_id) REFERENCES category (id);

ALTER TABLE category_feature_products
    ADD CONSTRAINT fk_catfeapro_on_product FOREIGN KEY (feature_products_id) REFERENCES product (id);