SET @quiz_id_1 := UUID();
SET @quiz_id_2 := UUID();
SET @quiz_id_3 := UUID();
SET @quiz_id_4 := UUID();
SET @quiz_id_5 := UUID();
SET @quiz_id_6 := UUID();
SET @quiz_id_7 := UUID();
SET @quiz_id_8 := UUID();
SET @quiz_id_9 := UUID();
SET @quiz_id_10 := UUID();

SET @category_id_1 := UUID();
SET @category_id_2 := UUID();
SET @category_id_3 := UUID();
SET @category_id_4 := UUID();
SET @category_id_5 := UUID();
SET @category_id_6 := UUID();
SET @category_id_7 := UUID();
SET @category_id_8 := UUID();
SET @category_id_9 := UUID();
SET @category_id_10 := UUID();



INSERT INTO `quiz`(`id`, `title`, `creator`, `created_at`, `updated_at`) VALUES
(@quiz_id_1, 'Inheritance and Polymorphism', 'Quizio', NOW(), NOW()),
(@quiz_id_2, 'Linear Data Structures', 'Quizio', NOW(), NOW()),
(@quiz_id_3, 'Sorting Algorithms', 'Quizio', NOW(), NOW()),
(@quiz_id_4, 'Essential Linux Commands', 'Quizio', NOW(), NOW()),
(@quiz_id_5, 'Regular Expressions', 'Quizio', NOW(), NOW()),
(@quiz_id_6, 'Encapsulation and Abstraction', 'Quizio', NOW(), NOW()),
(@quiz_id_7, 'Responsive Web Design', 'Quizio', NOW(), NOW()),
(@quiz_id_8, 'DOM Manipulation with JavaScript', 'Quizio', NOW(), NOW()),
(@quiz_id_9, 'Advanced SQL Queries', 'Quizio', NOW(), NOW()),
(@quiz_id_10, 'Version Control with Git', 'Quizio', NOW(), NOW());



INSERT INTO `category`(`id`, `name`, `image`) VALUES
(@category_id_1, 'HTML', 'html'),
(@category_id_2, 'CSS', 'css'),
(@category_id_3, 'JavaScript', 'javascript'),
(@category_id_4, 'Java', 'java'),
(@category_id_5, 'Python', 'python'),
(@category_id_6, 'PHP', 'php'),
(@category_id_7, 'Git', 'git'),
(@category_id_8, 'C++', 'cpp'),
(@category_id_9, 'SQL', 'sql'),
(@category_id_10, 'Linux', 'linux');



INSERT INTO `quiz_category`(`id`, `quiz_id`, `category_id`) VALUES
(UUID(), @quiz_id_1, @category_id_4),
(UUID(), @quiz_id_1, @category_id_8),

(UUID(), @quiz_id_2, @category_id_4),
(UUID(), @quiz_id_2, @category_id_5),
(UUID(), @quiz_id_2, @category_id_8),

(UUID(), @quiz_id_3, @category_id_4),
(UUID(), @quiz_id_3, @category_id_5),
(UUID(), @quiz_id_3, @category_id_8),

(UUID(), @quiz_id_4, @category_id_10),

(UUID(), @quiz_id_5, @category_id_3),
(UUID(), @quiz_id_5, @category_id_5),
(UUID(), @quiz_id_5, @category_id_6),

(UUID(), @quiz_id_6, @category_id_4),
(UUID(), @quiz_id_6, @category_id_8),

(UUID(), @quiz_id_7, @category_id_1),
(UUID(), @quiz_id_7, @category_id_2),

(UUID(), @quiz_id_8, @category_id_1),
(UUID(), @quiz_id_8, @category_id_2),
(UUID(), @quiz_id_8, @category_id_3),

(UUID(), @quiz_id_9, @category_id_9),

(UUID(), @quiz_id_10, @category_id_7);
