import { User } from "./IUser";
import { UserStory } from "./IUserStory";

export interface Task {
    id: number;
    title: string;
    description: string;
    assignedTo: User;
    createdBy: User;
    created: Date;
    userStory: UserStory;
}