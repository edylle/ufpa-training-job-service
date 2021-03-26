CREATE TABLE category (
    category_id CHAR(36) NOT NULL,
    name VARCHAR(100) NULL,
    description VARCHAR(255) NULL,
    PRIMARY KEY(category_id),
    UNIQUE KEY(name)
);