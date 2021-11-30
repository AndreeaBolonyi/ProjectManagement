import { Project } from "./Project";

export interface Epic {
    id: number;
    title: string;
    created: Date;
    project: Project;
}