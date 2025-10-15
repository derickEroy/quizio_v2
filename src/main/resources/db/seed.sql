SET @id_category_angular    := UUID();
SET @id_category_cpp        := UUID();
SET @id_category_csharp     := UUID();
SET @id_category_css        := UUID();
SET @id_category_docker     := UUID();
SET @id_category_git        := UUID();
SET @id_category_github     := UUID();
SET @id_category_golang     := UUID();
SET @id_category_html       := UUID();
SET @id_category_java       := UUID();
SET @id_category_js         := UUID();
SET @id_category_nodejs     := UUID();
SET @id_category_php        := UUID();
SET @id_category_python     := UUID();
SET @id_category_react      := UUID();
SET @id_category_ruby       := UUID();
SET @id_category_vue        := UUID();

SET @id_quiz1               := UUID();
SET @id_quiz2               := UUID();
SET @id_quiz3               := UUID();
SET @id_quiz4               := UUID();
SET @id_quiz5               := UUID();
SET @id_quiz6               := UUID();
SET @id_quiz7               := UUID();
SET @id_quiz8               := UUID();
SET @id_quiz9               := UUID();
SET @id_quiz10              := UUID();

INSERT INTO `category`(`id`, `name`, `image`) VALUES
    (@id_category_angular,  'Angular',      'angular'),
    (@id_category_cpp,      'C++',          'cpp'),
    (@id_category_csharp,   'C#',           'csharp'),
    (@id_category_css,      'CSS',          'css'),
    (@id_category_docker,   'Docker',       'docker'),
    (@id_category_git,      'Git',          'git'),
    (@id_category_github,   'GitHub',       'github'),
    (@id_category_golang,   'Go',           'golang'),
    (@id_category_html,     'HTML',         'html'),
    (@id_category_java,     'Java',         'java'),
    (@id_category_js,       'JavaScript',   'js'),
    (@id_category_nodejs,   'Node.js',      'nodejs'),
    (@id_category_php,      'PHP',          'php'),
    (@id_category_python,   'Python',       'python'),
    (@id_category_react,    'React',        'react'),
    (@id_category_ruby,     'Ruby',         'ruby'),
    (@id_category_vue,      'Vue',          'vue');

INSERT INTO `quiz`(`id`, `title`, `creator`, `created_at`, `updated_at`) VALUES
    (@id_quiz1, 'Java Inheritance and Polymorphism',        'quizio', NOW(), NOW()),
    (@id_quiz2, 'Common Design Patterns in OOP',            'quizio', NOW(), NOW()),
    (@id_quiz3, 'Basic DOM Manipulation with JavaScript',   'quizio', NOW(), NOW()),
    (@id_quiz4, 'Version Control with Git & GitHub',        'quizio', NOW(), NOW()),
    (@id_quiz5, 'Basic CSS Styling',                        'quizio', NOW(), NOW()),
    (@id_quiz6, 'State & Lifecycle: React Fundamentals',    'quizio', NOW(), NOW()),
    (@id_quiz7, 'Vue Composition API',                      'quizio', NOW(), NOW()),
    (@id_quiz8, 'Python Lists, Tuples, and Dictionaries',   'quizio', NOW(), NOW()),
    (@id_quiz9, 'C++ Standard Template Library',            'quizio', NOW(), NOW()),
    (@id_quiz10, 'Ruby Object Oriented Programming',        'quizio', NOW(), NOW());

INSERT INTO `quiz_category`(`id`, `quiz_id`, `category_id`) VALUES
    (UUID(), @id_quiz1, @id_category_java),
    (UUID(), @id_quiz2, @id_category_java), (UUID(), @id_quiz2, @id_category_csharp),
    (UUID(), @id_quiz3, @id_category_js), (UUID(), @id_quiz3, @id_category_html), (UUID(), @id_quiz3, @id_category_css),
    (UUID(), @id_quiz4, @id_category_git), (UUID(), @id_quiz4, @id_category_docker),
    (UUID(), @id_quiz5, @id_category_css),
    (UUID(), @id_quiz6, @id_category_react), (UUID(), @id_quiz6, @id_category_js),
    (UUID(), @id_quiz7, @id_category_vue), (UUID(), @id_quiz7, @id_category_js),
    (UUID(), @id_quiz8, @id_category_python),
    (UUID(), @id_quiz9, @id_category_cpp),
    (UUID(), @id_quiz10, @id_category_ruby)



--
-- Quiz 1 Sample Data
--

SET @id_question1 := UUID();
SET @id_question2 := UUID();
SET @id_question3 := UUID();
SET @id_question4 := UUID();
SET @id_question5 := UUID();
SET @id_question6 := UUID();
SET @id_question7 := UUID();
SET @id_question8 := UUID();
SET @id_question9 := UUID();
SET @id_question10 := UUID();

INSERT INTO `question`(`id`, `quiz_id`, `type`, `text`) VALUES
    (@id_question1, @id_quiz1, 'FILL_IN_THE_BLANK', 'A method declared with the keyword <blank> in the superclass cannot be overridden by any subclass.'),
    (@id_question2, @id_quiz1, 'FILL_IN_THE_BLANK', 'The annotation <blank> instructs the compiler that you intend to override a method from a superclass.'),
    (@id_question3, @id_quiz1, 'MULTIPLE_CHOICE',   'Which concept allows the same method to behave differently based on the object that calls it?'),
    (@id_question4, @id_quiz1, 'MULTIPLE_CHOICE',   'What keyword is used to inherit a class in Java?'),
    (@id_question5, @id_quiz1, 'MULTIPLE_CHOICE',   'Which of the following statements is true about method resolution at runtime in Java?'),
    (@id_question6, @id_quiz1, 'MULTI_SELECT',      'Which of the following are valid forms of polymorphism in Java?'),
    (@id_question7, @id_quiz1, 'MULTI_SELECT',      'Which of the following statements about method overriding in Java are true?'),
    (@id_question8, @id_quiz1, 'MULTI_SELECT',      'Which of the following can be inherited from a parent Java class?'),
    (@id_question9, @id_quiz1, 'TRUE_OR_FALSE',     'Polymorphism allows you to call the same method on different objects and have each of them respond in their own way.'),
    (@id_question10, @id_quiz1, 'TRUE_OR_FALSE',    'Java supports multiple inheritance of classes directly using the extends keyword.');



INSERT INTO `option`(`id`, `question_id`, `is_correct`, `order`, `text`) VALUES
    (UUID(), @id_question1, TRUE, NULL, 'final'),

    (UUID(), @id_question2, TRUE, NULL, '@Override'),

    (UUID(), @id_question3, FALSE, NULL, 'Inheritance'),
    (UUID(), @id_question3, FALSE, NULL, 'Encapsulation'),
    (UUID(), @id_question3, TRUE, NULL, 'Polymorphism'),
    (UUID(), @id_question3, FALSE, NULL, 'Abstraction'),

    (UUID(), @id_question4, FALSE, NULL, 'imports'),
    (UUID(), @id_question4, TRUE, NULL, 'extends'),
    (UUID(), @id_question4, FALSE, NULL, 'inherits'),
    (UUID(), @id_question4, FALSE, NULL, 'implements'),

    (UUID(), @id_question5, FALSE, NULL, 'Overloaded methods are resolved at runtime.'),
    (UUID(), @id_question5, TRUE, NULL, 'Overridden methods are resolved at runtime.'),
    (UUID(), @id_question5, FALSE, NULL, 'Static methods are resolved at runtime.'),
    (UUID(), @id_question5, FALSE, NULL, 'Constructors are resolved at runtime.'),

    (UUID(), @id_question6, TRUE, NULL, 'Method Overloading'),
    (UUID(), @id_question6, TRUE, NULL, 'Method Overriding'),
    (UUID(), @id_question6, FALSE, NULL, 'Constructor chaining'),
    (UUID(), @id_question6, FALSE, NULL, 'Variable shadowing'),

    (UUID(), @id_question7, TRUE, NULL, 'The return type must be the same or covariant.'),
    (UUID(), @id_question7, FALSE, NULL, 'The access modifier can be more restrictive.'),
    (UUID(), @id_question7, TRUE, NULL, 'The method must have the same name and parameters.'),
    (UUID(), @id_question7, FALSE, NULL, 'Static methods can be overridden.'),
    (UUID(), @id_question7, TRUE, NULL, 'The overriding method can throw narrower checked exceptions.'),

    (UUID(), @id_question8, FALSE, NULL, 'Constructors'),
    (UUID(), @id_question8, TRUE, NULL, 'Public methods'),
    (UUID(), @id_question8, FALSE, NULL, 'Private variables'),
    (UUID(), @id_question8, TRUE, NULL, 'Protected methods'),
    (UUID(), @id_question8, TRUE, NULL, 'Static methods'),

    (UUID(), @id_question9, TRUE, NULL, 'True'),

    (UUID(), @id_question10, FALSE, NULL, 'False');
