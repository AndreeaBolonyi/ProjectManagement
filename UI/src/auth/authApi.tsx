import axios from "axios";
import { config, getLogger } from "../core";
import { UserProps } from "../model/UserProps";
import { projectBaseUrl } from "../utils/generalConstants";

const log = getLogger("userApi");

const authUrl = `${projectBaseUrl}login`;

export const loginApi: (
  email?: string,
  password?: string
) => Promise<UserProps> = (email, password) => {
  log(`loginApi with ${email} and ${password}`);
  return axios
    .post(authUrl, { email, password }, config)
    .then((res) => {
      log(`login - succeeded`);
      return Promise.resolve(res.data);
    })
    .catch((err) => {
      log(`login - failed`);
      return Promise.reject(err);
    });
};
