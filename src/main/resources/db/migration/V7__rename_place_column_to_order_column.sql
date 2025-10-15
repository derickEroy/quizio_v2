ALTER TABLE `option`
RENAME COLUMN `place` TO `order`;

ALTER TABLE `option`
MODIFY COLUMN `order` INT NULL;

ALTER TABLE `option`
RENAME COLUMN `is_answer` TO `is_correct`;