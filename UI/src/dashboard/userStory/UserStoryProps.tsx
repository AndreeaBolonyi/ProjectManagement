import { Sprint } from "../../model/ISprint";
import { User } from "../../model/IUser";

export interface UserStoryProps {
  id?: number;
  title?: string;
  description?: string;
  assignedTo?: User;
  createdBy?: User;
  sprintDTO?: Sprint;
  status?: string;
  created?: Date;
}
