import { DetailsList, IColumn, Stack, StackItem, ThemeProvider } from "@fluentui/react";
import { DetailsListLayoutMode, IObjectWithKey, Selection, SelectionMode } from '@fluentui/react/lib/DetailsList';
import React, { useEffect, useState} from "react";
import { useNavigate} from "react-router-dom";
import { useAuth } from "../auth/AuthProvider";
import { getLogger } from "../core";
import { IDashboardProps} from "../model/IDashboardProps";
import { Sprint } from "../model/ISprint";
import { UserStory } from "../model/IUserStory";
import { UserStoryDetailsListItem } from "../model/IUserStoryDetailsListItem";
import { SprintsService, UserStoriesService } from "../utils/service";
import { getDefaultSprint, formatDate, getViewportAsPixels, getByRequestUrl, setSelectedUserStory } from "../utils/utilsMethods";
import { detailsListColumnStyle, itemStyle, setGapBetweenHeaders, setGapBetweenHeadersAndDetailsList, transparentTheme } from "./Dashboard.styles";

const log = getLogger("Dashboard");
const TITLE_COLUMN: string = "Title";
const DESCRIPTION_COLUMN: string = "Description";
const ASSIGNED_TO_COLUMN: string = "Assigned to";
const CREATED_BY_COLUMN: string = "Created by";
const BACKLOG_TITLE: string = "Backlog";

const getColumnName = (title: string, description: string, assignedTo: string, createdBy: string, name: string): string => {
  return name === title ? title : name === description ? description : name === assignedTo ? assignedTo : name === createdBy ? createdBy : name;
};

const getFieldName = (columnName: string): string => {
  return columnName === TITLE_COLUMN ? "title" :  columnName === DESCRIPTION_COLUMN ? "description" : columnName === ASSIGNED_TO_COLUMN ? "assignedTo" : columnName === CREATED_BY_COLUMN ? "createdBy" : "";
};

const getColumn = (pageWidth: number, name: string): IColumn => {
  return {
      key: name,
      name: getColumnName(TITLE_COLUMN, DESCRIPTION_COLUMN, ASSIGNED_TO_COLUMN, CREATED_BY_COLUMN, name),
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

const getListItemFromUserStory = (userStory: UserStory): UserStoryDetailsListItem => {
  return {
    id: userStory.id,
    title: userStory.title,
    description: userStory.description,
    assignedTo: `${userStory.assignedTo.firstName}${" "}${userStory.assignedTo.lastName}`,
    createdBy: `${userStory.createdBy.firstName}${" "}${userStory.createdBy.lastName}`
  };
};

const renderItemColumn = (item: any, index?: number, column?: IColumn): React.ReactFragment => {
  const fieldContent = item[column!.fieldName as keyof UserStoryDetailsListItem] as string;

  return (
    <React.Fragment>
      <span className={itemStyle}>{fieldContent}</span>
    </React.Fragment>
  );
};

const Dashboard = (props: IDashboardProps): JSX.Element => {
  const { isAuthenticated } = useAuth();
  const navigate = useNavigate();
  const [currentSprint, setCurrentSprint] = useState<Sprint>(getDefaultSprint());
  const [items, setItems] = useState<UserStoryDetailsListItem[]>([]);
  const [selectedItems, setSelectedItems] = useState<IObjectWithKey[] | undefined>(undefined);
  const [selection] = useState<Selection>(() => new Selection({
    onSelectionChanged: () => {
        const selectedItems: IObjectWithKey[] = selection.getSelection();
        const selected: UserStoryDetailsListItem = selectedItems[0] as UserStoryDetailsListItem;
        setSelectedUserStory(selected);
        setSelectedItems(selectedItems);
    }
  }));
  const columns: IColumn[] = getColumns(props.pageWidth, [TITLE_COLUMN, DESCRIPTION_COLUMN, ASSIGNED_TO_COLUMN, CREATED_BY_COLUMN]);

  useEffect(() => {
    if (!isAuthenticated) {
      log("isAuthenticated false");
      navigate("/login");
    }
  }, [isAuthenticated]);

    useEffect(() => {
        if(selectedItems !== undefined) {
            navigate("/tasks");
        }
    }, [selectedItems]);

  useEffect(() => {
    getCurrentSprint();
  }, []);

  const getCurrentSprint = async () => {
    const sprint: Sprint = await getByRequestUrl(SprintsService.GET_CURRENT_SPRINT);
    const allUserStories = await getByRequestUrl(`${UserStoriesService.GET_ALL_BY_SPRINT_ID}${sprint.id}`);

    setCurrentSprint(sprint);
    setItems(getUserStoriesForCurrentSprint(allUserStories));
  };

  const getUserStoriesForCurrentSprint = (allUserStories: UserStory[]): UserStoryDetailsListItem[] => {
    return allUserStories.map((item) => getListItemFromUserStory(item) );
  };

  const getTitle = (): string => {
    return `${"Projects"}${" / "}${currentSprint.epicDTO.projectDTO.title}`;
  };

  const getSubtitle = (): string => {
    return `${currentSprint.title}${" / "}${formatDate(currentSprint.startDate)}${" - "}${formatDate(currentSprint.endDate)}`;
  };

  const getSelectedItem = (): IObjectWithKey => {
    return selectedItems![0];
  };

    return (
      <Stack className="hero is-fullheight has-background-dark" tokens={setGapBetweenHeadersAndDetailsList}>
          <Stack tokens={setGapBetweenHeaders}>
            <p className="title has-text-white is-size-5 has-text-left marginFH1"> {getTitle()} </p>
            <p className="title has-text-white is-size-5 has-text-left marginFH1"> {getSubtitle()} </p>
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

export default Dashboard;
