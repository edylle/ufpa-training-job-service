CREATE TABLE categories (
    category_id CHAR(36) NOT NULL,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255) NULL,
    PRIMARY KEY(category_id),
    UNIQUE KEY(name)
);