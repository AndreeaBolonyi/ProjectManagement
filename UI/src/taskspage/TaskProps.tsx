import { Sprint } from "../model/ISprint";
import { User } from "../model/IUser"
import { UserStory } from "../model/IUserStory";

export interface TaskProps {
  id?: number;
  title?: string;
  description?: string;
  assignedToDTO?: User;
  createdByDTO?: User;
  created?: Date;
  userStoryDTO?: UserStory;
}