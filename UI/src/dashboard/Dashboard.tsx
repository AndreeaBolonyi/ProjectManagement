import {
  CommandBar,
  DetailsList,
  IColumn,
  IContextualMenuItem,
  Stack,
  StackItem,
  ThemeProvider,
} from "@fluentui/react";
import {
  DetailsListLayoutMode,
  IObjectWithKey,
  Selection,
  SelectionMode,
} from "@fluentui/react/lib/DetailsList";
import React, { useCallback, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../auth/AuthProvider";
import { IDashboardProps } from "../model/IDashboardProps";
import { Sprint } from "../model/ISprint";
import { UserStory } from "../model/IUserStory";
import { UserStoryDetailsListItem } from "../model/IUserStoryDetailsListItem";
import { ADD, DELETE, EDIT, VIEW_TASKS } from "../utils/generalConstants";
import { SprintsService, UserStoriesService } from "../utils/service";
import {
  getDefaultSprint,
  formatDate,
  getViewportAsPixels,
  getByRequestUrl,
  setSelectedUserStory,
  selectedUserStory,
} from "../utils/utilsMethods";
import {
  commandBarStyles,
  defaultMenuItemStyle,
  detailsListColumnStyle,
  itemStyle,
  enabledMenuItemStyle,
  setGapBetweenHeaders,
  setGapBetweenHeadersAndDetailsList,
  transparentTheme,
} from "./Dashboard.styles";
import LoginFoot from "../images/foot.svg";
import EditUserStoryModal from "./userStory/EditUserStoryModal";
import SaveUserStoryModal from "./userStory/SaveUserStoryModal";
import { getLogger } from "../core";

const TITLE_COLUMN: string = "Title";
const DESCRIPTION_COLUMN: string = "Description";
const ASSIGNED_TO_COLUMN: string = "Assigned to";
const CREATED_BY_COLUMN: string = "Created by";
const BACKLOG_TITLE: string = "Backlog";

const log = getLogger("dash");

const getColumnName = (
  title: string,
  description: string,
  assignedTo: string,
  createdBy: string,
  name: string
): string => {
  return name === title
    ? title
    : name === description
    ? description
    : name === assignedTo
    ? assignedTo
    : name === createdBy
    ? createdBy
    : name;
};

const getFieldName = (columnName: string): string => {
  return columnName === TITLE_COLUMN
    ? "title"
    : columnName === DESCRIPTION_COLUMN
    ? "description"
    : columnName === ASSIGNED_TO_COLUMN
    ? "assignedTo"
    : columnName === CREATED_BY_COLUMN
    ? "createdBy"
    : "";
};

const getColumn = (pageWidth: number, name: string): IColumn => {
  return {
    key: name,
    name: getColumnName(
      TITLE_COLUMN,
      DESCRIPTION_COLUMN,
      ASSIGNED_TO_COLUMN,
      CREATED_BY_COLUMN,
      name
    ),
    fieldName: getFieldName(name),
    minWidth: getViewportAsPixels(pageWidth, 25),
    maxWidth: getViewportAsPixels(pageWidth, 30),
    isResizable: true,
    isMultiline: true,
    styles: detailsListColumnStyle,
  };
};

const getColumns = (pageWidth: number, names: string[]): IColumn[] => {
  return names.map((name: string) => getColumn(pageWidth, name));
};

export const getListItemFromUserStory = (
  userStory: UserStory
): UserStoryDetailsListItem => {
  return {
    id: userStory.id,
    title: userStory.title,
    description: userStory.description,
    assignedTo: `${userStory.assignedTo.firstName}${" "}${
      userStory.assignedTo.lastName
    }`,
    createdBy: `${userStory.createdBy.firstName}${" "}${
      userStory.createdBy.lastName
    }`,
  };
};

const renderItemColumn = (
  item: any,
  index?: number,
  column?: IColumn
): React.ReactFragment => {
  const fieldContent = item[
    column!.fieldName as keyof UserStoryDetailsListItem
  ] as string;

  return (
    <React.Fragment>
      <span className={itemStyle}>{fieldContent}</span>
    </React.Fragment>
  );
};

const getMenuItem = (name: string): IContextualMenuItem => {
  return {
    key: name,
    text: name,
    iconProps: { iconName: name },
  };
};

const getMenuItems = (names: string[]): IContextualMenuItem[] => {
  return names.map((name: string) => getMenuItem(name));
};

const Dashboard = (props: IDashboardProps): JSX.Element => {
  const { isAuthenticated } = useAuth();
  const navigate = useNavigate();
  const [currentSprint, setCurrentSprint] = useState<Sprint>(
    getDefaultSprint()
  );
  const [deleteItemId, setDeleteItemId] = useState<number>(0);
  const [items, setItems] = useState<UserStoryDetailsListItem[]>([]);
  const [userStories, setUserStories] = useState<UserStory[]>([]);
  const [selectedItems, setSelectedItems] = useState<
    IObjectWithKey[] | undefined
  >(undefined);
  const [selection] = useState<Selection>(
    () =>
      new Selection({
        onSelectionChanged: () => {
          const selectedItems: IObjectWithKey[] = selection.getSelection();
          const selected: UserStoryDetailsListItem = selectedItems[0] as UserStoryDetailsListItem;
          setSelectedUserStory(selected);
          setSelectedItems(selectedItems);
        },
      })
  );
  const [isSaving, setIsSaving] = useState(false);
  const switchSavingMode = useCallback(
    () => setIsSaving((isSaving) => !isSaving),
    []
  );
  const [isEditing, setIsEditing] = useState(false);
  const switchEditingMode = useCallback(
    () => setIsEditing((isEditing) => !isEditing),
    []
  );
  const columns: IColumn[] = getColumns(props.pageWidth, [
    TITLE_COLUMN,
    DESCRIPTION_COLUMN,
    ASSIGNED_TO_COLUMN,
    CREATED_BY_COLUMN,
  ]);
  const menuItems: IContextualMenuItem[] = getMenuItems([
    VIEW_TASKS,
    ADD,
    EDIT,
    DELETE,
  ]);

  useEffect(() => {
    if (!isAuthenticated) {
      navigate("/login");
    }
  }, [isAuthenticated]);

  useEffect(() => {
    getCurrentSprint();
  }, []);

  useEffect(() => {
    if (deleteItemId === 0) {
      return;
    }

    deleteUserStory();
  }, [deleteItemId]);

  const getCurrentSprint = async () => {
    const sprint: Sprint = await getByRequestUrl(
      SprintsService.GET_CURRENT_SPRINT
    );
    getUserStories(sprint.id);

    setCurrentSprint(sprint);
  };

  const getUserStories = async (sprintId: number) => {
    const allUserStories = await getByRequestUrl(
      `${UserStoriesService.GET_ALL_BY_SPRINT_ID}${sprintId}`
    );
    setItems(getUserStoriesForCurrentSprint(allUserStories));
    setUserStories(allUserStories);
  };

  const deleteUserStory = async () => {
    const userStory: UserStoryDetailsListItem = getSelectedItem() as UserStoryDetailsListItem;
    const requestUrl: string = `${UserStoriesService.DELETE_BY_ID}${userStory.id}`;
    const message: string = await getByRequestUrl(requestUrl);

    if (message === "Success") {
      getUserStories(currentSprint.id);
    } else {
      alert("An error has occurred on delete operation");
    }
  };

  const getUserStoriesForCurrentSprint = (
    allUserStories: UserStory[]
  ): UserStoryDetailsListItem[] => {
    return allUserStories.map((item) => getListItemFromUserStory(item));
  };

  const getTitle = (): string => {
    return `${"Projects"}${" / "}${currentSprint.epicDTO.projectDTO.title}`;
  };

  const getSubtitle = (): string => {
    return `${currentSprint.title}${" / "}${formatDate(
      currentSprint.startDate
    )}${" - "}${formatDate(currentSprint.endDate)}`;
  };

  const getSelectedItem = (): IObjectWithKey => {
    return selectedItems![0];
  };

  const getSelectedUserStory = (): UserStory => {
    const index = userStories.findIndex((it) => it.id === selectedUserStory.id);
    return userStories[index];
  };

  const isEditOrDeleteDisabled = (checkEdit: boolean): boolean => {
    if (!selectedItems) return true;

    if (checkEdit) {
      if (selectedItems.length !== 1) return true;
    } else if (selectedItems.length < 1) return true;
    return false;
  };

  const onEditClicked = (): void => {
    if (userStories.find((us) => us.id === selectedUserStory.id) !== undefined)
      switchEditingMode();
  };

  const onAddClicked = (): void => {
    switchSavingMode();
  };

  const onDeleteClicked = (): void => {
    const deleteUserStory: UserStoryDetailsListItem = getSelectedItem() as UserStoryDetailsListItem;
    setDeleteItemId(deleteUserStory.id);
  };

  const onViewClicked = (): void => {
    if (selectedItems !== undefined) {
      navigate("/tasks");
    }
  };

  const updateMenuItems = (): IContextualMenuItem[] => {
    return menuItems.map((item: IContextualMenuItem) => {
      switch (item.key) {
        case VIEW_TASKS:
          item.disabled = !(selectedItems?.length === 1);
          item.onClick = () => onViewClicked();
          item.style =
            selectedItems?.length === 1
              ? enabledMenuItemStyle
              : defaultMenuItemStyle;
          break;
        case ADD:
          item.onClick = () => onAddClicked();
          item.style = enabledMenuItemStyle;
          break;
        case EDIT:
          item.disabled = isEditOrDeleteDisabled(true);
          item.onClick = () => onEditClicked();
          item.style =
            selectedItems?.length === 1
              ? enabledMenuItemStyle
              : defaultMenuItemStyle;
          break;
        case DELETE:
          item.disabled = isEditOrDeleteDisabled(false);
          item.onClick = () => onDeleteClicked();
          item.style =
            selectedItems?.length === 1
              ? enabledMenuItemStyle
              : defaultMenuItemStyle;
          break;
        default:
          return item;
      }
      return item;
    });
  };

  return (
    <div>
      {isSaving && (
        <SaveUserStoryModal
          switchMode={switchSavingMode}
          sprint={currentSprint}
          items={items}
          setItems={setItems}
        />
      )}
      {isEditing && (
        <EditUserStoryModal
          switchMode={switchEditingMode}
          userStory={getSelectedUserStory()}
          items={items}
          setItems={setItems}
          userStories={userStories}
          setUserStories={setUserStories}
        />
      )}
      <Stack
        className="hero is-fullheight has-background-dark"
        tokens={setGapBetweenHeadersAndDetailsList}
      >
        <Stack tokens={setGapBetweenHeaders}>
          <p className="title has-text-white is-size-5 has-text-left marginFH1">
            {" "}
            {getTitle()}{" "}
          </p>
          <p className="title has-text-white is-size-5 has-text-left marginFH1">
            {" "}
            {getSubtitle()}{" "}
          </p>
          <p className="subtitle has-text-white is-size-3 marginFH2">
            {" "}
            {BACKLOG_TITLE}{" "}
          </p>
        </Stack>
        <StackItem>
          <ThemeProvider theme={transparentTheme}>
            <CommandBar items={updateMenuItems()} styles={commandBarStyles} />
            <DetailsList
              className="hero is-fullheight has-background-dark"
              items={items}
              setKey="set"
              columns={columns}
              selectionMode={SelectionMode.single}
              layoutMode={DetailsListLayoutMode.justified}
              selection={selection}
              selectionPreservedOnEmptyClick={true}
              onRenderItemColumn={renderItemColumn}
            ></DetailsList>
          </ThemeProvider>
        </StackItem>
        <div className="hero-foot">
          <figure className="image is-fullwidth">
            <img src={LoginFoot} alt="LoginFoot" className="" />
          </figure>
        </div>
      </Stack>
    </div>
  );
};

export default Dashboard;
