-- 4) Add Course
INSERT INTO Course (course_name, course_description, units, capacity)
VALUES ('Quantum Computing', 'Intro to quantum circuits', 4, 30);

-- 5) Remove Course
DELETE FROM Course WHERE course_name = 'Quantum Computing';

-- 6) Register Student
INSERT INTO Student (first_name, last_name, GPA, units_completed, date_of_birth, enrollment_date, enrollment_status)
VALUES ('Bob', 'Builder', 3.5, 0, '2004-01-01', CURRENT_DATE, 'active');

-- 7) Dismiss Student
DELETE FROM Student
WHERE first_name = 'Bob' AND last_name = 'Builder';

-- 8) Add Course to Student
INSERT INTO Enrollment (student_id, course_id, course_status, grade)
VALUES (1, 5, 'in_progress', 'F');

-- 9) Drop Course from Student
DELETE FROM Enrollment WHERE student_id = 1 AND course_id = 5;

-- 10) Complete Course for Student
UPDATE Enrollment
SET course_status = 'completed', grade = 'A'
WHERE student_id = 1 AND course_id = 2;

-- 11) Login
SELECT * FROM User WHERE username = 'houstongregory' AND password = 'password1';

-- 12) Sign Up
INSERT INTO User (username, password) VALUES ('newuser', 'securepass');

--List Students
SELECT * FROM Student;
SELECT first_name, last_name, units_completed, enrollment_status FROM Student;

--List Courses
SELECT * FROM Course;
SELECT course_name, course_description, units, capacity FROM Course;

--List Users
SELECT * FROM User;

--View Student Info
SELECT * FROM Student WHERE student_id = 1;
SELECT course_name, units, course_status, grade
FROM Course, Enrollment
WHERE Course.course_id = Enrollment.course_id AND Enrollment.student_id = 1;