-- Database: ProjectManagement

-- DROP DATABASE "ProjectManagement";

CREATE TABLE Projects(
	ID int,
	Title varchar(255),
	CONSTRAINT PK_Projects PRIMARY KEY(ID)
)

CREATE TABLE Epics(
	ID int,
	Title varchar(255),
	Created date,
	ProjectId int,
	CONSTRAINT FK_Epics_Projects FOREIGN KEY(ProjectId) REFERENCES Projects(ID),
	CONSTRAINT PK_Epics PRIMARY KEY(ID)
)

CREATE TABLE Sprints(
	ID int,
	Title varchar(255),
	StartDate date,
	EndDate date,
	CONSTRAINT PK_Sprints PRIMARY KEY(ID)
)

CREATE TABLE UserStories(
	ID int,
	Title varchar(255),
	Description varchar(255),
	Status varchar(255),
	Created date,
	AssignedToId int,
	CreatedById int,
	EpicId int,
	SprintId int,
	CONSTRAINT FK_UserStories_Users_Assigned FOREIGN KEY(AssignedToId) REFERENCES Users(ID),
	CONSTRAINT FK_UserStories_Users_Created FOREIGN KEY(CreatedById) REFERENCES Users(ID),
	CONSTRAINT FK_UserStories_Epics FOREIGN KEY(EpicId) REFERENCES Epics(ID),
	CONSTRAINT FK_UserStories_Sprints FOREIGN KEY(SprintId) REFERENCES Sprints(ID),
	CONSTRAINT PK_UserStories PRIMARY KEY(ID)
)

CREATE TABLE Users(
	ID int,
    LastName varchar(255),
    FirstName varchar(255),
    Email varchar(255),
	constraint Users_Email_UN unique (Email),
    Password varchar(255),
	RoleID int,
	CONSTRAINT FK_Users_Roles FOREIGN KEY(RoleID) REFERENCES Roles(ID),
	CONSTRAINT PK_Users PRIMARY KEY(ID)
)

CREATE TABLE Tasks(
	ID int,
	Title varchar(255),
	Description varchar(255),
	AssignedToId int,
	CreatedById int,
	UserStoryId int,
	CONSTRAINT FK_Tasks_Users_Assigned FOREIGN KEY(AssignedToId) REFERENCES Users(ID),
	CONSTRAINT FK_Tasks_Users_Created FOREIGN KEY(CreatedById) REFERENCES Users(ID),
	CONSTRAINT PK_Tasks PRIMARY KEY(ID)
)

CREATE TABLE Roles(
	ID int,
    Title varchar(255),
	CONSTRAINT PK_Roles PRIMARY KEY(ID)
)

CREATE TABLE RememberMeTokens(
	ID int,
	Email varchar(255),
    Password varchar(255),
	constraint RememberMeTokens_Email_UN unique (Email),
	CONSTRAINT PK_RememberMeTokens PRIMARY KEY(ID)
)