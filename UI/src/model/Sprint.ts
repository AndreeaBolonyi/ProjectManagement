import { Epic } from "./Epic";
import { UserStory } from "./UserStory";

export interface Sprint {
    id: number;
    title: string;
    startDate: Date;
    endDate: Date;
    epic: Epic;
    userStories: UserStory[];
}