PRAGMA foreign_keys = ON;

-- Add users
INSERT INTO User (username, password) VALUES ('user1', 'pass1');
INSERT INTO User (username, password) VALUES ('user2', 'pass2');

-- Add students
INSERT INTO Student (first_name, last_name, GPA, units_completed, date_of_birth, enrollment_date, enrollment_status)
VALUES ('Alice', 'Smith', 3.2, 60, '2001-03-15', '2024-01-10', 'active'); -- valid
INSERT INTO Student (first_name, last_name, GPA, units_completed, date_of_birth, enrollment_date, enrollment_status)
VALUES ('Bob', 'Jones', 2.8, 30, '2002-06-20', '2023-09-01', 'graduated'); -- valid

-- Add courses
INSERT INTO Course (course_name, course_description, units, capacity)
VALUES ('Intro to Databases', 'Covers SQL and relational design.', 3, 35); -- valid
INSERT INTO Course (course_name, course_description, units, capacity)
VALUES ('Software Testing', 'Covers testing theory and automation.', 4, 30); -- valid

-- Add enrollments
INSERT INTO Enrollment (student_id, course_id, course_status, grade)
VALUES (15, 15, 'in_progress', 'B'); -- valid
INSERT INTO Enrollment (student_id, course_id, course_status, grade)
VALUES (2, 2, 'completed', 'A'); -- valid

-- Invalid inserts
INSERT INTO Student (first_name, last_name, GPA, units_completed, date_of_birth, enrollment_date, enrollment_status)
VALUES ('Invalid', 'GPA', 4.5, 20, '2001-01-01', '2023-09-01', 'active'); -- GPA too high
INSERT INTO Student (first_name, last_name, GPA, units_completed, date_of_birth, enrollment_date, enrollment_status)
VALUES ('Invalid', 'Status', 3.0, 40, '2000-01-01', '2023-09-01', 'paused'); -- bad status
INSERT INTO Course (course_name, course_description, units, capacity)
VALUES ('Intro to Databases', 'Duplicate name test.', 3, 30); -- duplicate name
INSERT INTO Enrollment (student_id, course_id, course_status, grade)
VALUES (1, 2, 'completed', 'AB'); -- grade too long
INSERT INTO Enrollment (student_id, course_id, course_status, grade)
VALUES (999, 1, 'in_progress', 'C'); -- non-existent student

-- Updates
UPDATE Student SET GPA = 3.5 WHERE student_id = 1; -- valid update
UPDATE Enrollment SET grade = 'A' WHERE student_id = 1 AND course_id = 1; -- valid update
UPDATE Enrollment SET course_status = 'withdrawn' WHERE student_id = 2 AND course_id = 2; -- invalid status

-- Deletes (test cascade)
DELETE FROM Course WHERE course_id = 2; -- should delete enrollment too
DELETE FROM Student WHERE student_id = 1; -- should delete enrollment too

-- Final checks
SELECT * FROM Student;
SELECT * FROM Course;
SELECT * FROM Enrollment;
