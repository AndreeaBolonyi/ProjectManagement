import React, {
  createContext,
  useCallback,
  useContext,
  useEffect,
  useState,
} from "react";
import PropTypes from "prop-types";
import { getLogger } from "../core";
import { loginApi } from "./authApi";
import { User } from "../model/IUser";

const log = getLogger("AuthContext");

type LoginFn = (email?: string, password?: string) => void;

const initialState: AuthState = {
  isAuthenticated: false,
  isAuthenticating: false,
  authenticationError: null,
  pendingAuthentication: false,
};

export interface AuthState {
  authenticationError: Error | null;
  isAuthenticated: boolean;
  isAuthenticating: boolean;
  pendingAuthentication?: boolean;
  email?: string;
  password?: string;
  user?: User;
  login?: LoginFn;
}

export const AuthContext = createContext(initialState);

interface AuthProviderProps {
  children: PropTypes.ReactNodeLike;
}

export const useAuth = () => {
  return useContext(AuthContext);
};

export const AuthProvider: React.FC<AuthProviderProps> = ({ children }) => {
  const [state, setState] = useState<AuthState>(initialState);
  log(state);
  const {
    isAuthenticated,
    isAuthenticating,
    authenticationError,
    pendingAuthentication,
    user,
  } = state;
  const login = useCallback<LoginFn>(loginCallback, [state]);
  useEffect(authenticationEffect, [pendingAuthentication]);
  const value = {
    isAuthenticated,
    isAuthenticating,
    authenticationError,
    user,
    login,
  };

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;

  function loginCallback(email?: string, password?: string): void {
    log("login");
    setState({
      ...state,
      pendingAuthentication: true,
      email,
      password,
    });
  }

  function authenticationEffect() {
    let canceled = false;
    authenticate();
    return () => {
      log("cancelled");
      canceled = true;
    };

    async function authenticate() {
      log("authenticate");
      if (!pendingAuthentication) {
        log("pendingAuthentication is false");
        return;
      }
      try {
        log("authenticating");
        setState({
          ...state,
          isAuthenticating: true,
        });
        const { email, password } = state;
        const user = await loginApi(email, password);
        log(`authenticated with user ${user.id}`);
        if (canceled) {
          return;
        }
        log("authenticate succeeded");
        setState({
          ...state,
          pendingAuthentication: false,
          isAuthenticated: true,
          isAuthenticating: false,
          user,
        });
        log(`set state to user ${state.user?.id}`);
      } catch (error: any) {
        if (canceled) {
          return;
        }
        log("authenticate failed");
        setState({
          ...state,
          authenticationError: error,
          pendingAuthentication: false,
          isAuthenticating: false,
        });
      }
    }
  }
};
