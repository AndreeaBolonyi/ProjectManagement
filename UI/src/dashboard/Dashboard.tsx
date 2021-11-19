import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../auth/AuthProvider";
import { getLogger } from "../core";

const log = getLogger("Dashboard");

const Dashboard = () => {
  const { isAuthenticated } = useAuth();
  const navigate = useNavigate();

  useEffect(() => {
    if (!isAuthenticated) {
      log("isAuthenticated false");
      navigate("/login");
    }
  }, [isAuthenticated]);

  log("render");
  return <div>Hello world</div>;
};

export default Dashboard;
