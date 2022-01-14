import axios from "axios";
import { config } from "../core";
import { User } from "../model/IUser";
import { projectBaseUrl } from "../utils/generalConstants";
import { currentUser, setCurrentUser } from "../utils/utilsMethods";

import jwt from 'jwt-decode'

const authUrl = `${projectBaseUrl}login`;

export const loginApi: (
    email?: string,
    password?: string
) => Promise<User> = (email, password) => {
    return axios
        .post(authUrl, { email, password }, config)
        .then((res) => {
            const token=res.data;
            const user=jwt(token);
            console.log(user);
            setCurrentUser(res.data);
      return Promise.resolve(res.data);
    })
    .catch((err) => {
      return Promise.reject(err);
    });
};
