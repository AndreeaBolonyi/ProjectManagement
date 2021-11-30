import { User } from "./User";
import { UserStory } from "./UserStory";

export interface Task {
    id: number;
    title: string;
    description: string;
    assignedTo: User;
    createdBy: User;
    created: Date;
    userStory: UserStory;
}