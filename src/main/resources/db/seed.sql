-- Category ID

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

-- Quiz ID

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

-- Question ID

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

SET @id_question11 := UUID();
SET @id_question12 := UUID();
SET @id_question13 := UUID();
SET @id_question14 := UUID();
SET @id_question15 := UUID();
SET @id_question16 := UUID();
SET @id_question17 := UUID();
SET @id_question18 := UUID();
SET @id_question19 := UUID();
SET @id_question20 := UUID();

SET @id_question21 := UUID();
SET @id_question22 := UUID();
SET @id_question23 := UUID();
SET @id_question24 := UUID();
SET @id_question25 := UUID();
SET @id_question26 := UUID();
SET @id_question27 := UUID();
SET @id_question28 := UUID();
SET @id_question29 := UUID();
SET @id_question30 := UUID();

-- Category

INSERT INTO `category`(`id`, `name`, `image`) VALUES
(@id_category_angular,  'Angular',      'angular.png'),
(@id_category_cpp,      'C++',          'cpp.png'),
(@id_category_csharp,   'C#',           'csharp.png'),
(@id_category_css,      'CSS',          'css.png'),
(@id_category_docker,   'Docker',       'docker.png'),
(@id_category_git,      'Git',          'git.png'),
(@id_category_github,   'GitHub',       'github.png'),
(@id_category_golang,   'Go',           'golang.png'),
(@id_category_html,     'HTML',         'html.png'),
(@id_category_java,     'Java',         'java.png'),
(@id_category_js,       'JavaScript',   'js.png'),
(@id_category_nodejs,   'Node.js',      'nodejs.png'),
(@id_category_php,      'PHP',          'php.png'),
(@id_category_python,   'Python',       'python.png'),
(@id_category_react,    'React',        'react.png'),
(@id_category_ruby,     'Ruby',         'ruby.png'),
(@id_category_vue,      'Vue',          'vue.png');

-- Quiz

INSERT INTO `quiz`(`id`, `title`, `creator`, `created_at`, `updated_at`) VALUES
(@id_quiz1, 'Java Inheritance and Polymorphism',        'Quizio', NOW(), NOW()),
(@id_quiz2, 'Common Design Patterns in OOP',            'Quizio', NOW(), NOW()),
(@id_quiz3, 'Basic DOM Manipulation with JavaScript',   'Quizio', NOW(), NOW()),
(@id_quiz4, 'Version Control with Git & GitHub',        'Quizio', NOW(), NOW()),
(@id_quiz5, 'Basic CSS Styling',                        'Quizio', NOW(), NOW()),
(@id_quiz6, 'State & Lifecycle: React Fundamentals',    'Quizio', NOW(), NOW()),
(@id_quiz7, 'Vue Composition API',                      'Quizio', NOW(), NOW()),
(@id_quiz8, 'Python Lists, Tuples, and Dictionaries',   'Quizio', NOW(), NOW()),
(@id_quiz9, 'C++ Standard Template Library',            'Quizio', NOW(), NOW()),
(@id_quiz10, 'Ruby Object Oriented Programming',        'Quizio', NOW(), NOW());

-- Quiz Category

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
(UUID(), @id_quiz10, @id_category_ruby);

-- Question

INSERT INTO `question`(`id`, `quiz_id`, `type`, `text`) VALUES
(@id_question1, @id_quiz1, 'FILL_IN_THE_BLANK', 'A method declared with the keyword {{BLANK}} in the superclass cannot be overridden by any subclass.'),
(@id_question2, @id_quiz1, 'FILL_IN_THE_BLANK', 'The annotation {{BLANK}} instructs the compiler that you intend to override a method from a superclass.'),
(@id_question3, @id_quiz1, 'MULTIPLE_CHOICE',   'Which concept allows the same method to behave differently based on the object that calls it?'),
(@id_question4, @id_quiz1, 'MULTIPLE_CHOICE',   'What keyword is used to inherit a class in Java?'),
(@id_question5, @id_quiz1, 'MULTIPLE_CHOICE',   'Which of the following statements is true about method resolution at runtime in Java?'),
(@id_question6, @id_quiz1, 'MULTI_SELECT',      'Which of the following are valid forms of polymorphism in Java?'),
(@id_question7, @id_quiz1, 'MULTI_SELECT',      'Which of the following statements about method overriding in Java are true?'),
(@id_question8, @id_quiz1, 'MULTI_SELECT',      'Which of the following can be inherited from a parent Java class?'),
(@id_question9, @id_quiz1, 'TRUE_OR_FALSE',     'Polymorphism allows you to call the same method on different objects and have each of them respond in their own way.'),
(@id_question10, @id_quiz1, 'TRUE_OR_FALSE',    'Java supports multiple inheritance of classes directly using the extends keyword.');

INSERT INTO `question`(`id`, `quiz_id`, `type`, `text`) VALUES
(@id_question11, @id_quiz2, 'FILL_IN_THE_BLANK', 'The {{BLANK}} pattern provides a simplified interface to a larger body of code, such as a complex subsystem.'),
(@id_question12, @id_quiz2, 'FILL_IN_THE_BLANK', 'In the {{BLANK}} pattern, a class ensures that only one instance exists and provides a global point of access to it.'),
(@id_question13, @id_quiz2, 'MULTIPLE_CHOICE',   'Which design pattern decouples object creation from its usage?'),
(@id_question14, @id_quiz2, 'MULTIPLE_CHOICE',   'Which pattern allows behavior to be added to individual objects dynamically?'),
(@id_question15, @id_quiz2, 'MULTIPLE_CHOICE',   'Which pattern defines a one-to-many dependency between objects so that when one changes, others are notified?'),
(@id_question16, @id_quiz2, 'MULTI_SELECT',      'Which of the following are creational design patterns?'),
(@id_question17, @id_quiz2, 'MULTI_SELECT',      'Which of the following are behavioral design patterns?'),
(@id_question18, @id_quiz2, 'MULTI_SELECT',      'Which statements about the Strategy pattern are true?'),
(@id_question19, @id_quiz2, 'TRUE_OR_FALSE',     'The Singleton pattern can cause issues in multithreaded applications if not implemented carefully.'),
(@id_question20, @id_quiz2, 'TRUE_OR_FALSE',     'The Factory Method pattern is used to restrict subclassing.');

INSERT INTO `question`(`id`, `quiz_id`, `type`, `text`) VALUES
(@id_question21, @id_quiz3, 'FILL_IN_THE_BLANK', 'To select an element by its ID, use document.{{BLANK}}(\'elementId\').'),
(@id_question22, @id_quiz3, 'FILL_IN_THE_BLANK', 'To change the text inside an element, you can modify its {{BLANK}} property.'),
(@id_question23, @id_quiz3, 'MULTIPLE_CHOICE',   'Which method creates a new HTML element in JavaScript?'),
(@id_question24, @id_quiz3, 'MULTIPLE_CHOICE',   'Which property returns all child nodes of an element?'),
(@id_question25, @id_quiz3, 'MULTIPLE_CHOICE',   'Which event is fired when a user clicks an element?'),
(@id_question26, @id_quiz3, 'MULTI_SELECT',      'Which methods can be used to attach event handlers in JavaScript?'),
(@id_question27, @id_quiz3, 'MULTI_SELECT',      'Which are valid ways to insert new elements into the DOM?'),
(@id_question28, @id_quiz3, 'MULTI_SELECT',      'Which statements about querySelector are true?'),
(@id_question29, @id_quiz3, 'TRUE_OR_FALSE',     'The innerHTML property can be used to insert HTML content dynamically.'),
(@id_question30, @id_quiz3, 'TRUE_OR_FALSE',     'The DOM is a static representation of the HTML document that cannot be changed at runtime.');

-- Option

INSERT INTO `option`(`id`, `question_id`, `is_correct`, `text`) VALUES
(UUID(), @id_question1, NULl, 'final'),

(UUID(), @id_question2, NULl, '@Override'),

(UUID(), @id_question3, FALSE, 'Inheritance'),
(UUID(), @id_question3, FALSE, 'Encapsulation'),
(UUID(), @id_question3, TRUE, 'Polymorphism'),
(UUID(), @id_question3, FALSE, 'Abstraction'),

(UUID(), @id_question4, FALSE, 'imports'),
(UUID(), @id_question4, TRUE, 'extends'),
(UUID(), @id_question4, FALSE, 'inherits'),
(UUID(), @id_question4, FALSE, 'implements'),

(UUID(), @id_question5, FALSE, 'Overloaded methods are resolved at runtime.'),
(UUID(), @id_question5, TRUE, 'Overridden methods are resolved at runtime.'),
(UUID(), @id_question5, FALSE, 'Static methods are resolved at runtime.'),
(UUID(), @id_question5, FALSE, 'Constructors are resolved at runtime.'),

(UUID(), @id_question6, TRUE, 'Method Overloading'),
(UUID(), @id_question6, TRUE, 'Method Overriding'),
(UUID(), @id_question6, FALSE, 'Constructor chaining'),
(UUID(), @id_question6, FALSE, 'Variable shadowing'),

(UUID(), @id_question7, TRUE, 'The return type must be the same or covariant.'),
(UUID(), @id_question7, FALSE, 'The access modifier can be more restrictive.'),
(UUID(), @id_question7, TRUE, 'The method must have the same name and parameters.'),
(UUID(), @id_question7, FALSE, 'Static methods can be overridden.'),

(UUID(), @id_question8, FALSE, 'Constructors'),
(UUID(), @id_question8, TRUE, 'Public methods'),
(UUID(), @id_question8, FALSE, 'Private variables'),
(UUID(), @id_question8, TRUE, 'Protected methods'),

(UUID(), @id_question9, TRUE, NULL),

(UUID(), @id_question10, FALSE, NULL);

INSERT INTO `option`(`id`, `question_id`, `is_correct`, `text`) VALUES
(UUID(), @id_question11, NULL, 'Facade'),

(UUID(), @id_question12, NULL, 'Singleton'),

(UUID(), @id_question13, TRUE, 'Factory Method'),
(UUID(), @id_question13, FALSE, 'Observer'),
(UUID(), @id_question13, FALSE, 'Decorator'),
(UUID(), @id_question13, FALSE, 'Proxy'),

(UUID(), @id_question14, TRUE, 'Decorator'),
(UUID(), @id_question14, FALSE, 'Adapter'),
(UUID(), @id_question14, FALSE, 'Bridge'),
(UUID(), @id_question14, FALSE, 'Composite'),

(UUID(), @id_question15, TRUE, 'Observer'),
(UUID(), @id_question15, FALSE, 'Command'),
(UUID(), @id_question15, FALSE, 'Prototype'),
(UUID(), @id_question15, FALSE, 'Adapter'),

(UUID(), @id_question16, TRUE, 'Singleton'),
(UUID(), @id_question16, TRUE, 'Abstract Factory'),
(UUID(), @id_question16, TRUE, 'Builder'),
(UUID(), @id_question16, FALSE, 'Iterator'),

(UUID(), @id_question17, TRUE, 'Observer'),
(UUID(), @id_question17, TRUE, 'Strategy'),
(UUID(), @id_question17, TRUE, 'Command'),
(UUID(), @id_question17, FALSE, 'Adapter'),

(UUID(), @id_question18, TRUE, 'It encapsulates interchangeable algorithms.'),
(UUID(), @id_question18, TRUE, 'It uses composition over inheritance.'),
(UUID(), @id_question18, FALSE, 'It limits the number of object instances.'),
(UUID(), @id_question18, FALSE, 'It provides a simplified interface to subsystems.'),

(UUID(), @id_question19, TRUE, NULL),
(UUID(), @id_question20, FALSE, NULL);

INSERT INTO `option`(`id`, `question_id`, `is_correct`, `text`) VALUES
(UUID(), @id_question21, NULL, 'getElementById'),

(UUID(), @id_question22, NULL, 'textContent'),

(UUID(), @id_question23, TRUE, 'document.createElement'),
(UUID(), @id_question23, FALSE, 'document.appendChild'),
(UUID(), @id_question23, FALSE, 'document.getElementById'),
(UUID(), @id_question23, FALSE, 'element.addEventListener'),

(UUID(), @id_question24, TRUE, 'childNodes'),
(UUID(), @id_question24, FALSE, 'child'),
(UUID(), @id_question24, FALSE, 'getChildNodes'),
(UUID(), @id_question24, FALSE, 'getChildren'),

(UUID(), @id_question25, FALSE, 'addEventListener'),
(UUID(), @id_question25, TRUE, 'click'),
(UUID(), @id_question25, FALSE, 'listenEvent'),
(UUID(), @id_question25, FALSE, 'addActionListener'),

(UUID(), @id_question26, TRUE, 'onclick'),
(UUID(), @id_question26, TRUE, 'attachEvent'),
(UUID(), @id_question26, FALSE, 'setTimeout'),
(UUID(), @id_question26, FALSE, 'setInterval'),

(UUID(), @id_question27, TRUE, 'appendChild()'),
(UUID(), @id_question27, TRUE, 'insertBefore()'),
(UUID(), @id_question27, TRUE, 'insertAdjacentHTML()'),
(UUID(), @id_question27, FALSE, 'querySelector()'),

(UUID(), @id_question28, TRUE, 'It returns the first matching element.'),
(UUID(), @id_question28, TRUE, 'It accepts CSS selectors.'),
(UUID(), @id_question28, FALSE, 'It returns all matching elements.'),
(UUID(), @id_question28, FALSE, 'It can insert new elements into the DOM.'),

(UUID(), @id_question29, TRUE, NULL),

(UUID(), @id_question30, FALSE, NULL);
