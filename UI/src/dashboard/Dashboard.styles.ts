import { createTheme, IDetailsColumnStyles, IStackTokens, Theme } from "@fluentui/react";

export const setGapBetweenHeaders: IStackTokens = {
    childrenGap: "2vw"
};

export const setGapBetweenHeadersAndDetailsList: IStackTokens = {
    childrenGap: "5vh"
};

export const detailsListColumnStyle: Partial<IDetailsColumnStyles> = {
    root: {
        color: "white",
        "&:hover": {
            color: "green"
        },
        borderTop: "0.2vh groove",
        borderBottom: "0.2vh groove",
        borderRight: "0.2vh groove",
        borderImage: "linear-gradient( to right, gray, transparent ) 1"
    }
};

export const transparentTheme: Theme = createTheme({
    palette: {
      white: '#0',                 
      neutralLighter: '#0',        
      neutralLight: '#0',         
      neutralQuaternaryAlt: '#0'   
    }
  });