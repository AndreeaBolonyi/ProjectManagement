import React, { useEffect } from "react";
import {Link, useNavigate} from "react-router-dom";
import { useAuth } from "../auth/AuthProvider";
import { getLogger } from "../core";
import LoginFoot from "../images/foot.svg";

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
  return (
      <div className="hero is-fullheight has-background-dark">
          <header>
          <h1 className="title has-text-white is-size-5 has-text-left marginFH1">Projects / Intelligent Eagles</h1>
          <h2 className="subtitle has-text-white is-size-3 marginFH2">
              Backlog
          </h2>
    </header>
          <div/>
        <div className="has-text-left">
          <div className="column">
            <div> </div>
          </div>
        </div>
        <div className="hero-foot">
          <figure className="image is-fullwidth">
            <img src={LoginFoot} alt="Homepage" className="" />
          </figure>
        </div>
      </div>
  );
};

export default Dashboard;
