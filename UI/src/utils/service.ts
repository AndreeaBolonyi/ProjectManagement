import { projectBaseUrl } from "./generalConstants";

export const sprintsBaseUrl = `${projectBaseUrl}${"sprints/"}`;

export namespace SprintsService {
    export const GET_CURRENT_SPRINT: string = `${sprintsBaseUrl}${"get-current-sprint"}`;
}