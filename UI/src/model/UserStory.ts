import { Sprint } from "./Sprint";
import { User } from "./User";

export interface UserStory {
    id: number;
    title: string;
    description: string;
    assignedTo: User;
    createdBy: User;
    sprint: Sprint;
    status: string;
    created: Date;
}