import {UserStoryDetailsListItem} from "./IUserStoryDetailsListItem";

export interface ITaskProps {
    pageWidth: number;
    pageHeight: number;
    selectedUserStory?: UserStoryDetailsListItem;
}