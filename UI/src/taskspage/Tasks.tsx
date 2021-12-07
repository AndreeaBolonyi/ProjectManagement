import { DetailsList, IColumn, Stack, StackItem, ThemeProvider } from "@fluentui/react";
import { DetailsListLayoutMode, IObjectWithKey, Selection, SelectionMode } from '@fluentui/react/lib/DetailsList';
import React, { useEffect, useState} from "react";
import { useNavigate} from "react-router-dom";
import { useAuth } from "../auth/AuthProvider";
import { getLogger } from "../core";
import { Task } from "../model/ITask";
import { ITaskDetailsListItem } from "../model/ITaskDetailsListItem";
import { TasksService } from "../utils/service";
import {formatDate, getByRequestUrl, getViewportAsPixels, selectedUserStory} from "../utils/utilsMethods";
import { detailsListColumnStyle, itemStyle, setGapBetweenHeaders, setGapBetweenHeadersAndDetailsList, transparentTheme } from "./Tasks.styles";
import {ITaskProps} from "../model/ITaskProps";

const log = getLogger("Tasks");
const TITLE_COLUMN: string = "Title";
const DESCRIPTION_COLUMN: string = "Description";
const ASSIGNED_TO_COLUMN: string = "Assigned to";
const CREATED_BY_COLUMN: string = "Created by";
const CREATED_COLUMN: string = "Created at";
const BACKLOG_TITLE: string = "Tasks";

const getColumnName = (title: string, description: string, assignedToDTO: string, createdByDTO: string, created : string, name: string): string => {
    return name === title ? title : name === description ? description : name === assignedToDTO ? assignedToDTO : name === createdByDTO ? createdByDTO : name  === created ? created : name;
};

const getFieldName = (columnName: string): string => {
    return columnName === TITLE_COLUMN ? "title" :  columnName === DESCRIPTION_COLUMN ? "description" : columnName === ASSIGNED_TO_COLUMN ? "assignedTo" : columnName === CREATED_BY_COLUMN ? "createdBy" : columnName === CREATED_COLUMN ? "created" : "";
};

const getColumn = (pageWidth: number, name: string): IColumn => {
    return {
        key: name,
        name: getColumnName(TITLE_COLUMN, DESCRIPTION_COLUMN, ASSIGNED_TO_COLUMN, CREATED_BY_COLUMN, CREATED_COLUMN, name),
        fieldName: getFieldName(name),
        minWidth: getViewportAsPixels(pageWidth, 25),
        maxWidth: getViewportAsPixels(pageWidth, 30),
        isResizable: true,
        isMultiline: true,
        styles: detailsListColumnStyle
    };
};

const getColumns = (pageWidth: number, names: string[]): IColumn[] => {
    return names.map((name: string) => getColumn(pageWidth, name));
};

const getListItemFromTask = (task : Task): ITaskDetailsListItem => {
    return {
        id: task.id,
        title: task.title,
        description: task.description,
        assignedTo: `${task.assignedToDTO.firstName}${" "}${task.assignedToDTO.lastName}`,
        createdBy: `${task.createdByDTO.firstName}${" "}${task.createdByDTO.lastName}`,
        created: formatDate(task.created)
    };
};

const renderItemColumn = (item: any, index?: number, column?: IColumn): React.ReactFragment => {
    const fieldContent = item[column!.fieldName as keyof ITaskDetailsListItem] as string;
    
    return (
        <React.Fragment>
            <span className={itemStyle}>{fieldContent}</span>
        </React.Fragment>
    );
};

const getTaskForCurrentUserStory = (allTasks: Task[]): ITaskDetailsListItem[] => {
    return allTasks.map((item) => getListItemFromTask(item) );
};

const Tasks = (props: ITaskProps): JSX.Element => {
    const { isAuthenticated } = useAuth();
    const navigate = useNavigate();
    const [items, setItems] = useState<ITaskDetailsListItem[]>([]);
    const [selectedItems, setSelectedItems] = useState<IObjectWithKey[] | undefined>(undefined);
    const [selection] = useState<Selection>(() => new Selection({
        onSelectionChanged: () => {
            const selectedItems: IObjectWithKey[] = selection.getSelection();
            setSelectedItems(selectedItems);
        }
    }));
    const columns: IColumn[] = getColumns(props.pageWidth, [TITLE_COLUMN, DESCRIPTION_COLUMN, ASSIGNED_TO_COLUMN, CREATED_BY_COLUMN, CREATED_COLUMN]);

    useEffect(() => {
        if (!isAuthenticated) {
            log("isAuthenticated false");
            navigate("/login");
        }
    }, [isAuthenticated]);

    useEffect(() => {
        getAllTasksForCurrentUserStory();
    }, []);

    const getAllTasksForCurrentUserStory = async () => {
        const allTasks: Task[] = await getByRequestUrl(`${TasksService.GET_ALL_BY_USER_STORY_ID}${selectedUserStory.id}`);
        setItems(getTaskForCurrentUserStory(allTasks));
    };

    const getSelectedItem = (): IObjectWithKey => {
        return selectedItems![0];
    };

    const getTitle = (): string => {
        return `${"Selected user story: "}${selectedUserStory.title}`;
    };

    return (
        <Stack className="hero is-fullheight has-background-dark" tokens={setGapBetweenHeadersAndDetailsList}>
            <Stack tokens={setGapBetweenHeaders}>
                <p className="title has-text-white is-size-5 has-text-left marginFH1"> {getTitle()} </p>
                <p className="subtitle has-text-white is-size-3 marginFH2"> {BACKLOG_TITLE} </p>
            </Stack>
            <StackItem>
                <ThemeProvider theme={transparentTheme}>
                    <DetailsList className="hero is-fullheight has-background-dark"
                        items={items}
                        setKey="set"
                        columns={columns}
                        selectionMode={SelectionMode.single}
                        layoutMode={DetailsListLayoutMode.justified}
                        selection={selection}
                        selectionPreservedOnEmptyClick={true}
                        onRenderItemColumn={renderItemColumn}>
                    </DetailsList>
                </ThemeProvider>
            </StackItem>
        </Stack>
    );
};

export default Tasks;
