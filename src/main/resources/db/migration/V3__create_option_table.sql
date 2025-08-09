CREATE TABLE `option` (
  `id` CHAR(36) PRIMARY KEY,
  `question_id` CHAR(36) NOT NULL,
  `is_answer` BOOLEAN DEFAULT FALSE,
  `place` CHAR(1) NOT NULL,
  `text` VARCHAR(255) NOT NULL,

  FOREIGN KEY (`question_id`) REFERENCES `question`(`id`) ON DELETE CASCADE
);
