CREATE TABLE IF NOT EXISTS User (
    username TEXT PRIMARY KEY,
    password TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS Student (
    student_id INTEGER PRIMARY KEY AUTOINCREMENT,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    GPA REAL NOT NULL DEFAULT 0 CHECK(GPA >= 0 AND GPA <= 4),
    units_completed INTEGER NOT NULL DEFAULT 0 CHECK(units_completed >= 0),
    date_of_birth DATE NOT NULL DEFAULT '2000-01-01',
    enrollment_date DATE NOT NULL DEFAULT CURRENT_DATE,
    enrollment_status TEXT NOT NULL DEFAULT 'active' CHECK(enrollment_status IN ('dimissed', 'active', 'graduated'))
);

CREATE TABLE IF NOT EXISTS Course (
    course_id INTEGER PRIMARY KEY AUTOINCREMENT,
    course_name TEXT NOT NULL UNIQUE,
    course_description TEXT NOT NULL DEFAULT 'No description provided',
    units INTEGER NOT NULL CHECK(units > 0) DEFAULT 0,
    capacity INTEGER NOT NULL CHECK(capacity > 0) DEFAULT 0
);

CREATE TABLE IF NOT EXISTS Enrollment (
    student_id INTEGER,
    course_id INTEGER,
    course_status TEXT NOT NULL DEFAULT 'in_progress' CHECK(course_status IN ('in_progress', 'completed')),
    grade TEXT NOT NULL CHECK(length(grade) = 1) DEFAULT 'F',
    PRIMARY KEY (student_id, course_id),
    FOREIGN KEY (student_id) REFERENCES Student(student_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (course_id) REFERENCES Course(course_id) ON UPDATE CASCADE ON DELETE CASCADE
);
