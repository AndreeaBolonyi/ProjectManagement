import { Epic } from "../model/IEpic";
import { Project } from "../model/IProject";
import { Sprint } from "../model/ISprint";
import { User } from "../model/IUser";
import { UserStory } from "../model/IUserStory";

export const getDummyProject = (): Project => {
    return {
      id: 1,
      title: "Proiect colectiv"
    };
};
  
export const getDummyEpic = (): Epic => {
    return {
      id: 1,
      title: "Demo 1",
      created: new Date("2021-11-20"),
      projectDTO: getDummyProject()
    };
  };

export const getDummySprint = (): Sprint => {
    return {
      id: 1,
      title: "IE Sprint 2",
      startDate: new Date("2021-11-20"),
      endDate: new Date("2021-12-04"),
      epicDTO: getDummyEpic()
    };
  };

export const getDummyUserStory = (): UserStory => {
return {
    id: 1,
    title: "US - Arhitecture",
    description: "layered arhitecture",
    assignedTo: getDummyUser(),
    createdBy: getDummyUser(),
    sprintDTO: getDummySprint(),
    status: "DONE",
    created: new Date("2021-11-20")
    };
};

export const getDummyUser = (): User => {
  return {
    id: 1,
    firstName: "Andreea",
    lastName: "Bolonyi",
    roleId: 3,
    email: "",
    password: ""
  };
};