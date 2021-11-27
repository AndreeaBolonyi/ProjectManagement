import { Project } from "./Project";
import { Sprint } from "./Sprint";

export interface Epic {
    id: number;
    title: string;
    created: Date;
    project: Project;
    sprints: Sprint[];
}