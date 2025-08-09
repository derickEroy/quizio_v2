CREATE TABLE `question` (
  `id` CHAR(36) PRIMARY KEY,
  `quiz_id` CHAR(36) NOT NULL,
  `type` VARCHAR(30) NOT NULL,
  `text` VARCHAR(255) NOT NULL,

  FOREIGN KEY (`quiz_id`) REFERENCES `quiz`(`id`) ON DELETE CASCADE
);
