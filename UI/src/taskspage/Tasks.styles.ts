import { createTheme, ICommandBarStyles, IDetailsColumnStyles, IStackTokens, mergeStyles, Theme } from "@fluentui/react";

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
        borderLeft: "0.2vh groove"
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

export const itemStyle = mergeStyles({
    color: "white",
    fontSize: "16px",
    height: '100%',
    display: 'block',
    borderRight: "0.2vh groove"
});

export const commandBarStyles: ICommandBarStyles = {
    root: { 
        paddingLeft: '0px'
    }
};

export const defaultMenuItemStyle : React.CSSProperties = {
   backgroundColor: 'transparent',
   color: 'grey'
};

export const enabledMenuItemStyle : React.CSSProperties = {
    backgroundColor: 'transparent',
   color: 'white'
};