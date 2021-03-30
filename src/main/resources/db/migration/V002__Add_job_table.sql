CREATE TABLE jobs (
    job_id CHAR(36) NOT NULL,
    category_id CHAR(36) NOT NULL,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(1000) NOT NULL,
    budget_min INTEGER NULL,
    budget_max INTEGER NULL,
    PRIMARY KEY(job_id, category_id)
);