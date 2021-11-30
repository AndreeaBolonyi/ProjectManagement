import { Epic } from "../model/Epic";
import { Project } from "../model/Project";
import { Sprint } from "../model/Sprint";
import { User } from "../model/User";
import { UserStory } from "../model/UserStory";

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
      project: getDummyProject()
    };
  };

export const getDummySprint = (): Sprint => {
    return {
      id: 1,
      title: "IE Sprint 2",
      startDate: new Date("2021-11-20"),
      endDate: new Date("2021-12-04"),
      epic: getDummyEpic()
    };
  };

export const getDummyUserStory = (): UserStory => {
return {
    id: 1,
    title: "US - Arhitecture",
    description: "layered arhitecture",
    assignedTo: getDummyUser(),
    createdBy: getDummyUser(),
    sprint: getDummySprint(),
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
    email: "andreea@yahoo.com",
    password: "andreea"
  };
};