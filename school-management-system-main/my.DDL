CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE students (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255),
    password VARCHAR(255),
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    role_id INTEGER,
    role_string VARCHAR(255),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE teachers (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255),
    password VARCHAR(255),
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    role_id INTEGER,
    role_string VARCHAR(255),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE courses (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    description TEXT,
    teacher_id INTEGER,
    teacher_string VARCHAR(255),
    number_of_students INTEGER DEFAULT 0,
    schedule VARCHAR(255),
    time VARCHAR(255),
    FOREIGN KEY (teacher_id) REFERENCES teachers(id)
);

CREATE TABLE student_course (
    student_id INTEGER,
    course_id INTEGER,
    PRIMARY KEY (student_id, course_id),
    FOREIGN KEY (student_id) REFERENCES students(id),
    FOREIGN KEY (course_id) REFERENCES courses(id)
);