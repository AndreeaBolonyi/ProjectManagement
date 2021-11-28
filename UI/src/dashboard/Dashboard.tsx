import { DetailsList, IColumn,Stack, StackItem, ThemeProvider } from "@fluentui/react";
import { DetailsListLayoutMode, IObjectWithKey, Selection, SelectionMode } from '@fluentui/react/lib/DetailsList';
import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate} from "react-router-dom";
import { useAuth } from "../auth/AuthProvider";
import { config, getLogger } from "../core";
import { IDashboardProps } from "../model/IDashboardProps";
import { Sprint } from "../model/Sprint";
import { UserStory } from "../model/UserStory";
import { UserStoryDetailsListItem } from "../model/UserStoryDetailsListItem";
import { getDummySprint, getDummyUserStory } from "../utils/dummyData";
import { SprintsService } from "../utils/service";
import { getDefaultSprint, formatDate, getViewportAsPixels } from "../utils/utilsMethods";
import { detailsListColumnStyle, setGapBetweenHeaders, setGapBetweenHeadersAndDetailsList, transparentTheme } from "./Dashboard.styles";

const log = getLogger("Dashboard");
const TITLE_COLUMN: string = "Title";
const DESCRIPTION_COLUMN: string = "Description";
const ASSIGNED_TO_COLUMN: string = "Assigned to";
const CREATED_BY_COLUMN: string = "Created by";
const BACKLOG_TITLE: string = "Backlog";

const getByRequestUrl = (requestUrl: string) => {
  return axios
  .get(requestUrl, config)
  .then((res) => {
    return Promise.resolve(res.data);
  })
  .catch((err) => {
    log(err);
    return Promise.reject(err);
  });
};

const getColumnName = (title: string, description: string, assignedTo: string, createdBy: string, name: string): string => {
  return name === title ? title : name === description ? description : name === assignedTo ? assignedTo : name === createdBy ? createdBy : name;
};

const getColumn = (pageWidth: number, name: string): IColumn => {
  return {
      key: name,
      name: getColumnName(TITLE_COLUMN, DESCRIPTION_COLUMN, ASSIGNED_TO_COLUMN, CREATED_BY_COLUMN, name),
      fieldName: name,
      minWidth: getViewportAsPixels(pageWidth, 25),
      maxWidth: getViewportAsPixels(pageWidth, 30),
      isResizable: true,
      styles: detailsListColumnStyle
  };
};

const getColumns = (pageWidth: number, names: string[]): IColumn[] => {
  return names.map((name: string) => getColumn(pageWidth, name));
};

const getListItemFromUserStory = (userStory: UserStory): UserStoryDetailsListItem => {
  return {
    title: userStory.title,
    description: userStory.description,
    assignedTo: `${userStory.assignedTo.firstName}${" "}${userStory.assignedTo.lastName}`,
    createdBy: `${userStory.createdBy.firstName}${" "}${userStory.createdBy.lastName}`
  };
}

const Dashboard = (props: IDashboardProps): JSX.Element => {
  const { isAuthenticated } = useAuth();
  const navigate = useNavigate();
  const [currentSprint, setCurrentSprint] = useState<Sprint>(getDefaultSprint());
  const [selectedItems, setSelectedItems] = useState<Object[] | undefined>(undefined);
  const [selection] = useState<Selection>(() => new Selection({
    onSelectionChanged: () => {
        setSelectedItems(selection.getSelection());
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
    getCurrentSprint();
  }, []);

  const getCurrentSprint = async () => {
    const sprint: Sprint = await getByRequestUrl(SprintsService.GET_CURRENT_SPRINT);
    const dummySprint: Sprint = getDummySprint();
    setCurrentSprint(dummySprint);
  };

  const getUserStoriesForCurrentSprint = (): UserStoryDetailsListItem[] => {
    const dummyUserStories: UserStory[] = [ getDummyUserStory() ];
    return dummyUserStories.map((item) => getListItemFromUserStory(item) );
  };

  const getTitle = (): string => {
    return `${"Projects"}${" / "}${currentSprint.epic.project.title}`;
  };

  const getSubtitle = (): string => {
    return `${currentSprint.title}${" / "}${formatDate(currentSprint.startDate)}${" - "}${formatDate(currentSprint.endDate)}`;
  };

  //a se folosi atunci cand se apasa pe un user story si vrem sa afisam tasks ce tin de el
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
                        items={getUserStoriesForCurrentSprint()} 
                        columns={columns}
                        selectionMode={SelectionMode.single}
                        layoutMode={DetailsListLayoutMode.justified}
                        selection={selection}
                        selectionPreservedOnEmptyClick={true}>
            </DetailsList>
          </ThemeProvider>
        </StackItem>
      </Stack>
  );
};

export default Dashboard;
