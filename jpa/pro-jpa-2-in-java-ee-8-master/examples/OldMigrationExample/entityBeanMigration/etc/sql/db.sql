DROP TABLE PROJECT_EMPLOYEE;
DROP TABLE EMPLOYEE;
DROP TABLE PROJECT;
DROP TABLE DEPT;
DROP TABLE ADDRESS;

CREATE TABLE DEPT (ID INTEGER NOT NULL, NAME VARCHAR(255), PRIMARY KEY (ID));
CREATE TABLE PROJECT (ID INTEGER NOT NULL, NAME VARCHAR(255), PRIMARY KEY (ID));
CREATE TABLE EMPLOYEE (ID INTEGER NOT NULL, NAME VARCHAR(255), SALARY BIGINT, 
                       DEPARTMENT_ID INTEGER, MANAGER_ID INTEGER, PRIMARY KEY (ID),
                       CONSTRAINT DEPT_FK FOREIGN KEY (DEPARTMENT_ID) REFERENCES DEPT (ID),
                       CONSTRAINT MGR_FK FOREIGN KEY (MANAGER_ID) REFERENCES EMPLOYEE (ID));
CREATE TABLE PROJECT_EMPLOYEE (EMPLOYEES_ID INTEGER, PROJECTS_ID INTEGER,
                               CONSTRAINT EMPLOYEES_FK FOREIGN KEY (EMPLOYEES_ID) REFERENCES EMPLOYEE(ID),
                               CONSTRAINT PROJECTS_FK FOREIGN KEY (PROJECTS_ID) REFERENCES PROJECT(ID));
CREATE TABLE ADDRESS (ID INTEGER NOT NULL, CITY VARCHAR(255), STATE VARCHAR(255), STREET VARCHAR(255), ZIP_CODE VARCHAR(255), PRIMARY KEY (ID));


INSERT INTO ADDRESS (ID, CITY, STATE, STREET, ZIP_CODE) VALUES (1, 'New York', 'NY', '123 Apple Tree Cr.', '10001');
INSERT INTO ADDRESS (ID, CITY, STATE, STREET, ZIP_CODE) VALUES (2, 'Manhattan', 'NY', '654 Stanton Way.', '10003');
INSERT INTO ADDRESS (ID, CITY, STATE, STREET, ZIP_CODE) VALUES (3, 'New York', 'NY', '99 Queen St.', '10001');
INSERT INTO ADDRESS (ID, CITY, STATE, STREET, ZIP_CODE) VALUES (4, 'Redwood Shores', 'CA', '445 McDonell Cr.', '90123');
INSERT INTO ADDRESS (ID, CITY, STATE, STREET, ZIP_CODE) VALUES (5, 'San Jose', 'CA', '624 Hamilton Dr.', '90123');


INSERT INTO DEPT (ID, NAME) VALUES (1, 'Engineering');
INSERT INTO DEPT (ID, NAME) VALUES (2, 'QA');
INSERT INTO DEPT (ID, NAME) VALUES (3, 'Accounting');

INSERT INTO PROJECT (ID, NAME) VALUES (1, 'Design Release2');
INSERT INTO PROJECT (ID, NAME) VALUES (2, 'Release1');
INSERT INTO PROJECT (ID, NAME) VALUES (3, 'Test Release2');

INSERT INTO EMPLOYEE (ID, NAME, SALARY, DEPARTMENT_ID, MANAGER_ID) VALUES (10, 'Joan', 59000, 1, NULL);
INSERT INTO EMPLOYEE (ID, NAME, SALARY, DEPARTMENT_ID, MANAGER_ID) VALUES (9, 'Sarah', 52000, 2, 10);
INSERT INTO EMPLOYEE (ID, NAME, SALARY, DEPARTMENT_ID, MANAGER_ID) VALUES (1, 'John', 55000, 2, 9);
INSERT INTO EMPLOYEE (ID, NAME, SALARY, DEPARTMENT_ID, MANAGER_ID) VALUES (2, 'Rob', 53000, 2, 9);
INSERT INTO EMPLOYEE (ID, NAME, SALARY, DEPARTMENT_ID, MANAGER_ID) VALUES (3, 'Peter', 40000, 2, 9);
INSERT INTO EMPLOYEE (ID, NAME, SALARY, DEPARTMENT_ID, MANAGER_ID) VALUES (4, 'Frank', 41000, 1, 10);
INSERT INTO EMPLOYEE (ID, NAME, SALARY, DEPARTMENT_ID, MANAGER_ID) VALUES (5, 'Scott', 60000, 1, 10);
INSERT INTO EMPLOYEE (ID, NAME, SALARY, DEPARTMENT_ID, MANAGER_ID) VALUES (6, 'Sue', 62000, 1, 10);
INSERT INTO EMPLOYEE (ID, NAME, SALARY, DEPARTMENT_ID, MANAGER_ID) VALUES (7, 'Stephanie', 54000, 1, 10);
INSERT INTO EMPLOYEE (ID, NAME, SALARY, DEPARTMENT_ID, MANAGER_ID) VALUES (8, 'Jennifer', 45000, 1, NULL);
INSERT INTO EMPLOYEE (ID, NAME, SALARY, DEPARTMENT_ID, MANAGER_ID) VALUES (11, 'Marcus', 35000, NULL, NULL);
INSERT INTO EMPLOYEE (ID, NAME, SALARY, DEPARTMENT_ID, MANAGER_ID) VALUES (12, 'Joe', 36000, 3, 11);
INSERT INTO EMPLOYEE (ID, NAME, SALARY, DEPARTMENT_ID, MANAGER_ID) VALUES (13, 'Jack', 43000, 3, NULL);

INSERT INTO PROJECT_EMPLOYEE (EMPLOYEES_ID, PROJECTS_ID) VALUES (1, 1);
INSERT INTO PROJECT_EMPLOYEE (EMPLOYEES_ID, PROJECTS_ID) VALUES (2, 2);
INSERT INTO PROJECT_EMPLOYEE (EMPLOYEES_ID, PROJECTS_ID) VALUES (2, 3);
INSERT INTO PROJECT_EMPLOYEE (EMPLOYEES_ID, PROJECTS_ID) VALUES (3, 1);
INSERT INTO PROJECT_EMPLOYEE (EMPLOYEES_ID, PROJECTS_ID) VALUES (3, 2);
INSERT INTO PROJECT_EMPLOYEE (EMPLOYEES_ID, PROJECTS_ID) VALUES (3, 3);
INSERT INTO PROJECT_EMPLOYEE (EMPLOYEES_ID, PROJECTS_ID) VALUES (4, 1);
INSERT INTO PROJECT_EMPLOYEE (EMPLOYEES_ID, PROJECTS_ID) VALUES (5, 2);
INSERT INTO PROJECT_EMPLOYEE (EMPLOYEES_ID, PROJECTS_ID) VALUES (5, 3);
INSERT INTO PROJECT_EMPLOYEE (EMPLOYEES_ID, PROJECTS_ID) VALUES (6, 1);
INSERT INTO PROJECT_EMPLOYEE (EMPLOYEES_ID, PROJECTS_ID) VALUES (6, 2);
INSERT INTO PROJECT_EMPLOYEE (EMPLOYEES_ID, PROJECTS_ID) VALUES (7, 3);
INSERT INTO PROJECT_EMPLOYEE (EMPLOYEES_ID, PROJECTS_ID) VALUES (8, 1);
INSERT INTO PROJECT_EMPLOYEE (EMPLOYEES_ID, PROJECTS_ID) VALUES (8, 2);
INSERT INTO PROJECT_EMPLOYEE (EMPLOYEES_ID, PROJECTS_ID) VALUES (9, 3);
INSERT INTO PROJECT_EMPLOYEE (EMPLOYEES_ID, PROJECTS_ID) VALUES (9, 1);
INSERT INTO PROJECT_EMPLOYEE (EMPLOYEES_ID, PROJECTS_ID) VALUES (10, 1);
INSERT INTO PROJECT_EMPLOYEE (EMPLOYEES_ID, PROJECTS_ID) VALUES (10, 2);
INSERT INTO PROJECT_EMPLOYEE (EMPLOYEES_ID, PROJECTS_ID) VALUES (10, 3);