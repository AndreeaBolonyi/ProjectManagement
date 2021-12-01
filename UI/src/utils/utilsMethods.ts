import { Epic } from "../model/IEpic";
import { Project } from "../model/IProject";
import { Sprint } from "../model/ISprint";
import { User } from "../model/IUser";
import { UserStory } from "../model/IUserStory";

export const getViewportAsPixels = (pageSizePx: number, viewportSize: number): number => {
    return (viewportSize * pageSizePx) / 100;
};

export const getMonthFromString = (month: string): string => {
  return (new Date(Date.parse(month +" 1, 2021")).getMonth() + 1).toString();
}
export const formatDate = (date: Date): string => {
  const dateAsString: string = date.toString();
  let day: string = "";
  let month: string = "";
  let year: string = "";

  if (dateAsString.includes("(Eastern European Standard Time)")) {
    const newFormat: string = dateAsString.split("(")[0];
    const splitedDate: string [] = newFormat.split(" ");
    day = splitedDate[2];
    month = getMonthFromString(splitedDate[1]);
    year = splitedDate[3];
  }
  else {
    const splitedDate: string[] = dateAsString.split("-");
    day = splitedDate[2];
    month = splitedDate[1];
    year = splitedDate[0];
  }

  return `${day}${"."}${month}${"."}${year}`;
};

export const getDefaultProject = (): Project => {
  return {
    id: 0,
    title: ""
  };
};
  
export const getDefaultEpic = (): Epic => {
  return {
    id: 0,
    title: "",
    created: new Date(),
    projectDTO: getDefaultProject()
  };
};

export const getDefaultSprint = (): Sprint => {
  return {
    id: 0,
    title: "",
    startDate: new Date(),
    endDate: new Date(),
    epicDTO: getDefaultEpic()
  };
};

export const getDefaultUserStory = (): UserStory => {
  return {
    id: 0,
    title: "",
    description: "",
    assignedTo: getDefaultUser(),
    createdBy: getDefaultUser(),
    sprintDTO: getDefaultSprint(),
    status: "",
    created: new Date()
  };
};

export const getDefaultUser = (): User => {
  return {
    id: 0,
    firstName: "",
    lastName: "",
    roleId: 0,
    email: "",
    password: ""
  };
};