import axios from "axios";
import { config } from "../../core";
import { UserStory } from "../../model/IUserStory";
import { UserStoriesService } from "../../utils/service";

export const createUserStory: (userStory: UserStory) => Promise<UserStory> = (
  userStory
) => {
  return axios
    .post(UserStoriesService.CREATE, userStory)
    .then((res) => {
      return Promise.resolve(res.data);
    })
    .catch((err) => {
      return Promise.reject(err);
    });
};

export const updateUserStory: (userStory: UserStory) => Promise<UserStory> = (
  userStory
) => {
  return axios
    .put(`${UserStoriesService.UPDATE}${userStory.id}`, userStory, config)
    .then((res) => {
      return Promise.resolve(res.data);
    })
    .catch((err) => {
      return Promise.reject(err);
    });
};
