ALTER TABLE `option`
RENAME COLUMN `place` TO `order`;

ALTER TABLE `option`
MODIFY COLUMN `order` INT NULL;