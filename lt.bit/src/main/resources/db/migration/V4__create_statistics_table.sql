CREATE TABLE IF NOT EXISTS test.statistics
(id INT AUTO_INCREMENT PRIMARY KEY,
`number` INT NOT NULL,
`timestamp` TIMESTAMP NOT NULL,
`sum` DECIMAL(13,4) NOT NULL);
