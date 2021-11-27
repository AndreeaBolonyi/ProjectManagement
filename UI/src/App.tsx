import React from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import { AuthProvider } from "./auth/AuthProvider";
import Login from "./auth/Login";
import Dashboard from "./dashboard/Dashboard";
import { IAppProps } from "./model/IAppProps";
import "./styles/bulma.css";
import "./styles/custom.css";


const App = (props: IAppProps) => {
  return (
    <AuthProvider>
      <BrowserRouter>
        <Routes>
            <Route path="/login" element={<Login />} />
            <Route path="/dashboard" element={<Dashboard pageHeight={props.pageHeight} pageWidth={props.pageWidth} />} />
            <Route path="/" element={<Login/>}></Route>
        </Routes>
      </BrowserRouter>
    </AuthProvider>
  );
};

export default App;
