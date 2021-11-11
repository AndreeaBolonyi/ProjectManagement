CREATE TABLE roles(
	id serial,
	title varchar(255),
	CONSTRAINT pk_roles PRIMARY KEY(id)
);


CREATE TABLE remember_me_tokens(
	id serial,
	email varchar(255),
	password varchar(255),
	constraint remember_me_tokens_email_un unique (email),
	CONSTRAINT pk_remember_me_tokens PRIMARY KEY(id)
);


CREATE TABLE users(
	id serial,
	last_name varchar(255),
	first_name varchar(255),
	email varchar(255),
	constraint users_email_un unique (email),
	password varchar(255),
	role_id int,
	CONSTRAINT fk_users_roles FOREIGN KEY(role_id) REFERENCES roles(id),
	CONSTRAINT pk_users PRIMARY KEY(id)
);


CREATE TABLE projects(
	id serial,
	title varchar(255),
	CONSTRAINT pk_projects PRIMARY KEY(id)
);


CREATE TABLE epics(
	id serial,
	title varchar(255),
	created date,
	project_id int,
	CONSTRAINT fk_epics_projects FOREIGN KEY(project_id) REFERENCES projects(id),
	CONSTRAINT pk_epics PRIMARY KEY(id)
);


CREATE TABLE sprints(
	id serial,
	title varchar(255),
	start_date date,
	end_date date,
	CONSTRAINT pk_sprints PRIMARY KEY(id)
);


CREATE TABLE user_stories(
	id serial,
	title varchar(255),
	description varchar(255),
	status varchar(255),
	created date,
	assigned_to_id int,
	created_by_id int,
	epic_id int,
	sprint_id int,
	CONSTRAINT fk_user_stories_users_assigned FOREIGN KEY(assigned_to_id) REFERENCES users(id),
	CONSTRAINT fk_user_stories_users_created FOREIGN KEY(created_by_id) REFERENCES users(id),
	CONSTRAINT fk_user_stories_epics FOREIGN KEY(epic_id) REFERENCES epics(id),
	CONSTRAINT fk_user_stories_sprints FOREIGN KEY(sprint_id) REFERENCES sprints(id),
	CONSTRAINT pk_user_stories PRIMARY KEY(id)
);


CREATE TABLE tasks(
	id serial,
	title varchar(255),
	description varchar(255),
	created DATE,
	assigned_to_id int,
	created_by_id int,
	user_story_id int,
	CONSTRAINT fk_tasks_users_assigned FOREIGN KEY(assigned_to_id) REFERENCES users(id),
	CONSTRAINT fk_tasks_users_created FOREIGN KEY(created_by_id) REFERENCES users(id),
	CONSTRAINT pk_tasks PRIMARY KEY(id)
);

/*
drop table remember_me_tokens;
drop table tasks;
drop table user_stories;
drop table sprints;
drop table epics;
drop table projects;
drop table users;
drop table roles;
*/



