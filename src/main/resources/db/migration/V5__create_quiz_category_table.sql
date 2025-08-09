CREATE TABLE `quiz_category` (
    `id` CHAR(36) PRIMARY KEY,
    `quiz_id` CHAR(36) NOT NULL,
    `category_id` CHAR(36) NOT NULL,

    FOREIGN KEY (`quiz_id`) REFERENCES `quiz`(`id`) ON DELETE CASCADE,
    FOREIGN KEY (`category_id`) REFERENCES `category`(`id`) ON DELETE CASCADE
);