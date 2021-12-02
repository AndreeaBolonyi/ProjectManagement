import { projectBaseUrl } from "./generalConstants";

export const sprintsBaseUrl: string = `${projectBaseUrl}${"sprints/"}`;
export const userStoriesBaseUrl: string = `${projectBaseUrl}${"user-stories/"}`;

export namespace SprintsService {
    export const GET_CURRENT_SPRINT: string = `${sprintsBaseUrl}${"get-current-sprint"}`;
}

export namespace UserStoriesService {
    export const GET_ALL_BY_SPRINT_ID: string = `${userStoriesBaseUrl}${"get-all-by-sprint/"}`;
}