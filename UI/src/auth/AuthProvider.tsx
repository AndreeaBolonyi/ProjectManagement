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

const log = getLogger("AuthContext");

type LoginFn = (user: UserProps) => void;

const initialState: AuthState = {
  isAuthenticated: false,
  isAuthenticating: false,
  authenticationError: null,
  pendingAuthentication: false,
  user: undefined,
  token: "",
};

export interface AuthState {
  authenticationError: Error | null;
  isAuthenticated: boolean;
  isAuthenticating: boolean;
  pendingAuthentication?: boolean;
  user?: UserProps;
  token: string;
  login?: LoginFn;
}

export interface UserProps {
  username?: string;
  password?: string;
}

const AuthContext = createContext(initialState);

interface AuthProviderProps {
  children: PropTypes.ReactNodeLike;
}

export const useAuth = () => {
  return useContext(AuthContext);
};

export const AuthProvider: React.FC<AuthProviderProps> = ({ children }) => {
  const [state, setState] = useState<AuthState>(initialState);
  const {
    isAuthenticated,
    isAuthenticating,
    authenticationError,
    pendingAuthentication,
    token,
    user,
  } = state;
  const login = useCallback<LoginFn>(loginCallback, [state]);
  useEffect(authenticationEffect, [pendingAuthentication, state]);
  const value = {
    isAuthenticated,
    isAuthenticating,
    authenticationError,
    token,
    user,
    login,
  };

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;

  function loginCallback(user?: UserProps): void {
    log("login");
    setState({
      ...state,
      pendingAuthentication: true,
      user,
    });
  }

  function authenticationEffect() {
    let canceled = false;
    authenticate();
    return () => {
      canceled = true;
    };

    async function authenticate() {
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
        const { user } = state;
        const { token } = await loginApi(user);
        if (canceled) {
          return;
        }
        log("authenticate succeeded");
        setState({
          ...state,
          token,
          pendingAuthentication: false,
          isAuthenticated: true,
          isAuthenticating: false,
        });
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
