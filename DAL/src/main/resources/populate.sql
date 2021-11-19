insert into roles(title)
values
('Scrum Master'),
('Product Owner'),
('Team Member');

insert into users(last_name, first_name, email, password, role_id)
values
('Bolonyi', 'Andreea', 'andreea@yahoo.com', 'andreea', 3),
('Indries', 'George', 'george@yahoo.com', 'george', 3),
('Balogh', 'Luca', 'luca@yahoo.com', 'luca', 3),
('Bodea', 'Catalin', 'catalin@yahoo.com', 'catalin', 3),
('Lazar', 'Ana', 'ana@yahoo.com', 'ana', 3),
('Hendre', 'Cristina', 'cristina@yahoo.com', 'cristina', 3),
('Frunzescu', 'Vlad', 'vlad@yahoo.com', 'vlad', 3),
('Ghiorghe', 'Bianca', 'bianca@yahoo.com', 'bianca', 3),
('Dochitoiu', 'Eduardo', 'eduardo@yahoo.com', 'eduardo', 3),
('Campean', 'Catalina', 'catalina@yahoo.com', 'catalina', 3);

insert into projects(title)
values
('Proiect Colectiv');

insert into epics(title, created, project_id)
values
('Demo 1', '2021-11-05', 1),
('Demo 2', '2021-11-19', 1);

insert into sprints(title, start_date, end_date)
values
('IE Sprint 1', '2021-11-05', '2021-11-19');

insert into user_stories(title, description, status, created, assigned_to_id, created_by_id, epic_id, sprint_id)
values
('US - Login', 'As an user, I want to be able to access my account, so that I can view my backlog and progress.', 'IN_PROGRESS', '2021-11-05', 4, 1, 1, 1),
('US - Architecture', 'Layered Architecture', 'DONE', '2021-11-05', 1, 2, 1, 1);

insert into tasks(title, description, created, assigned_to_id, created_by_id, user_story_id)
values
('Create and populate database', 'create database in pgadmin with name ProjectManagement; run only commands from pm.sql and then populate database with your own data',
 '2021-11-10', 2, 1, 2),
 ('Model', 'domain classes - User, Task, Role, RememberMeToken',
 '2021-11-10', 1, 2, 2);



