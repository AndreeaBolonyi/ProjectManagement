import { Epic } from "./Epic";

export interface Sprint {
    id: number;
    title: string;
    startDate: Date;
    endDate: Date;
    epic: Epic;
}