import { DetailsList, IColumn, Stack, StackItem } from "@fluentui/react";
import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate} from "react-router-dom";
import { useAuth } from "../auth/AuthProvider";
import { config, getLogger } from "../core";
import LoginFoot from "../images/foot.svg";
import { IDashboardProps } from "../model/IDashboardProps";
import { Sprint } from "../model/Sprint";
import { SprintsService } from "../utils/service";
import { getDefaultSprint, getStringFromDate, getViewportAsPixels } from "../utils/utilsMethods";
import { setGapBetweenHeaders } from "./Dashboard.styles";

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
  return name === title ? description : name === assignedTo ? createdBy : name;
};

const getColumn = (pageWidth: number, name: string): IColumn => {
  return {
      key: name,
      name: getColumnName(TITLE_COLUMN, DESCRIPTION_COLUMN, ASSIGNED_TO_COLUMN, CREATED_BY_COLUMN, name),
      fieldName: name,
      minWidth: getViewportAsPixels(pageWidth, 7),
      maxWidth: getViewportAsPixels(pageWidth, 7),
      isResizable: true
  };
};

const getColumns = (pageWidth: number, names: string[]): IColumn[] => {
  return names.map((name: string) => getColumn(pageWidth, name));
};

const Dashboard = (props: IDashboardProps): JSX.Element => {
  const { isAuthenticated } = useAuth();
  const navigate = useNavigate();
  const [currentSprint, setCurrentSprint] = useState<Sprint>(getDefaultSprint());
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
    setCurrentSprint(sprint);
  };

  const getTitle = (): string => {
    return `${"Projects"}${" / "}${currentSprint.epic.project.title}`;
  };

  const getSubtitle = (): string => {
    return `${currentSprint.title}${" / "}${currentSprint.startDate}${" -> "}${currentSprint.endDate}`;
  };

  return (
      <Stack className="hero is-fullheight has-background-dark">
          <Stack tokens={setGapBetweenHeaders}>
            <p className="title has-text-white is-size-5 has-text-left marginFH1"> {getTitle()} </p>
            <p className="title has-text-white is-size-5 has-text-left marginFH1"> {getSubtitle()} </p>
            <p className="subtitle has-text-white is-size-3 marginFH2"> {BACKLOG_TITLE} </p>
          </Stack>
        <StackItem className="hero-foot">
          <figure className="image is-fullwidth">
              <img src={LoginFoot} alt="Homepage" className="" />
          </figure>
        </StackItem>
        <StackItem>
          <DetailsList className="hero is-fullheight has-background-dark" 
                      items={currentSprint.userStories} 
                      columns={columns}>
          </DetailsList>
        </StackItem>
      </Stack>
  );
};

export default Dashboard;
