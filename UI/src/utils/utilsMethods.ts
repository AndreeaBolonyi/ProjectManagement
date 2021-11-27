export const getViewportAsPixels = (pageSizePx: number, viewportSize: number): number => {
    return (viewportSize * pageSizePx) / 100;
};

export const getStringFromDate = (date: Date): string => {
  const day: number = date.getDate();
  const month: number = date.getMonth();
  const year: number = date.getFullYear();
  return `${day}${"."}${month}${"."}${year}`;
};

export const getDefaultProject = () => {
    return {
      id: 0,
      title: "",
      epics: []
    }
  };
  
  export const getDefaultEpic = () => {
    return {
      id: 0,
      title: "",
      created: new Date(),
      project: getDefaultProject(),
      sprints: []
    }
  };
  
  export const getDefaultSprint = () => {
    return {
      id: 0,
      title: "",
      startDate: new Date(),
      endDate: new Date(),
      epic: getDefaultEpic(),
      userStories: []
    }
  };