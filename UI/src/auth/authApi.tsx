import axios from "axios";
import { baseUrl, config, getLogger } from "../core";
import { UserProps } from "./AuthProvider";

const log = getLogger("userApi");

export interface AuthProps {
  token: string;
}

export const loginApi: (user?: UserProps) => Promise<AuthProps> = (user) => {
  return axios
    .post(baseUrl, { user }, config)
    .then((res) => {
      log(`login - succeeded`);
      return Promise.resolve(res.data);
    })
    .catch((err) => {
      log(`login - failed`);
      return Promise.reject(err);
    });
};
