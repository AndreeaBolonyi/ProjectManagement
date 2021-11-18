import React, { useState } from "react";
import { useAuth, UserProps } from "./AuthProvider";
import { getLogger } from "../core";
import { Link } from "react-router-dom";
import LoginFoot from "../images/foot.svg";

const log = getLogger("Login");

const Login: React.FC = () => {
  const {
    isAuthenticated,
    authenticationError,
    login,
  } = useAuth();
  const [user, setUser] = useState<UserProps>();

  const handleSubmit = () => {
    log("handleSubmit");
    const currentUser = user ? { ...user } : {};
    login?.(currentUser);
  };

  if (isAuthenticated) {
    return <Link to="/" />;
  }

  return (
    <div className="hero is-fullheight has-background-dark">
      <div className="columns has-text-centered is-centered mt-6">
        <div className="column is-one-third"></div>
        <div className="column is-one-fifth mt-6">
          <div className="mt-6">
            <h1 className="title has-text-white is-size-1 pb-5">Log in</h1>
            <h2 className="subtitle has-text-white is-size-5">
              Start planning your next big project
            </h2>
            <form onSubmit={handleSubmit} className="form">
              <div className="field py-2">
                <input
                  type="text"
                  placeholder="Username"
                  className="input has-background-info border-info has-text-white"
                  onChange={(e) =>
                    setUser({ ...user, username: e.currentTarget.value || "" })
                  }
                  required
                />
              </div>
              <div className="field py-2">
                <input
                  type="password"
                  placeholder="Password"
                  className="input has-background-info border-info has-text-white"
                  onChange={(e) =>
                    setUser({ ...user, password: e.currentTarget.value || "" })
                  }
                  required
                />
              </div>
              <div className="field py-2">
                <div className="columns">
                  <div className="column">
                    <div className="control">
                      <label className="checkbox has-text-white">
                        <input className="mr-2" type="checkbox" />
                        Remember me
                      </label>
                    </div>
                  </div>
                  <div className="column">
                    <Link to="/forgot" className="has-text-primary">
                      Forgot password?
                    </Link>
                  </div>
                </div>
              </div>
              <div className="py-2">
                {authenticationError && (
                  <div>
                    {authenticationError.message || "Failed to authenticate"}
                  </div>
                )}
              </div>
              <button className="button is-primary is-fullwidth">Log in</button>
            </form>
          </div>
        </div>
        <div className="column is-one-third"></div>
      </div>
      <div className="hero-foot">
        <figure className="image is-fullwidth">
          <img src={LoginFoot} alt="LoginFoot" className="" />
        </figure>
      </div>
    </div>
  );
};

export default Login;
