import { DetailsList } from "@fluentui/react";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../auth/AuthProvider";
import { getLogger } from "../core";
import { UserStory } from "../model/UserStory";

const log = getLogger("Dashboard");
const TITLE_COLUMN: string = "Title";
const DESCRIPTION_COLUMN: string = "Description";
const ASSIGNED_TO_COLUMN: string = "Assigned to";
const CREATED_BY_COLUMN: string = "Created by";

const Dashboard = (): JSX.Element => {
  const { isAuthenticated } = useAuth();
  const navigate = useNavigate();
  const [items, setItems] = useState<UserStory[]>([]);
  
  const columns: String[] = [TITLE_COLUMN, DESCRIPTION_COLUMN, ASSIGNED_TO_COLUMN, CREATED_BY_COLUMN];

  useEffect(() => {
    if (!isAuthenticated) {
      log("isAuthenticated false");
      navigate("/login");
    }
  }, [isAuthenticated]);

  log("render");
  return (
    <DetailsList items={ [... items, ... columns] } />
  );
};

export default Dashboard;
