
CREATE TABLE Task (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    details TEXT,
    completed BOOLEAN NOT NULL
);