import React, { FormEvent, useContext, useState } from "react";
import { Sprint } from "../../model/ISprint";
import { User } from "../../model/IUser";
import { UserStory } from "../../model/IUserStory";
import { UserStoryDetailsListItem } from "../../model/IUserStoryDetailsListItem";
import { Status } from "../../model/Status";
import { getListItemFromUserStory } from "../Dashboard";
import { UserStoryContext } from "./UserStoryProvider";

export interface EditUserStoryModalProps {
  switchMode: () => void;
  userStory: UserStory;
  items: UserStoryDetailsListItem[];
  setItems: (items: UserStoryDetailsListItem[]) => void;
  userStories: UserStory[];
  setUserStories: (items: UserStory[]) => void;
}

interface EditUserStoryModalState {
  id: number;
  title: string;
  description: string;
  assignedTo: User;
  createdBy: User;
  sprintDTO: Sprint;
  status: string;
  created: Date;
}

const CreateUserStoryModal: React.FC<EditUserStoryModalProps> = ({
  switchMode,
  userStory,
  items,
  setItems,
  userStories,
  setUserStories,
}) => {
  const { saving, savingError, saveUserStory } = useContext(UserStoryContext);
  const initialState = userStory;
  const [state, setState] = useState<EditUserStoryModalState>(initialState);

  const handleSubmit = (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    state.created = new Date();
    saveUserStory?.(state).then((userStory: UserStory) => {
      if (userStory) {
        const index = items.findIndex((it) => it.id === userStory.id);
        if (index !== -1) {
          items[index] = getListItemFromUserStory(userStory);
          userStories[index] = userStory;
        }
        setItems(items);
        setUserStories(userStories);
        switchMode();
      }
    });
  };

  return (
    <div className="modal is-active">
      <div
        className="modal-background"
        onClick={() => {
          switchMode();
        }}
      />
      <div className="modal-card">
        <section className="modal-card-body has-background-light p-6">
          <div className="content">
            <p className="modal-card-title">Edit User Story</p>
            <form onSubmit={handleSubmit} className="form">
              <div className="field mt-4">
                <div className="label has-text-weight-light">Title</div>
                <input
                  value={state.title}
                  type="text"
                  className="input"
                  onChange={(e) =>
                    setState({ ...state, title: e.currentTarget.value || "" })
                  }
                  required
                />
              </div>
              <div className="field mt-4">
                <div className="label has-text-weight-light">Description</div>
                <textarea
                  value={state.description}
                  className="textarea"
                  onChange={(e) =>
                    setState({
                      ...state,
                      description: e.currentTarget.value || "",
                    })
                  }
                  required
                />
              </div>
              <div className="field mt-4">
                <div className="label has-text-weight-light">Created by</div>
                <input
                  value={`${state.createdBy.firstName} ${state.createdBy.lastName}`}
                  type="text"
                  className="input"
                  readOnly
                />
              </div>
              <div className="field is-expanded is-fullwidth mt-4">
                <label className="label has-text-weight-light">
                  Assigned to
                </label>
                <div className="control is-expanded is-fullwidth">
                  <span className="select is-fullwidth">
                    <select className="has-text-weight-normal">
                      <option>{`${state.createdBy.firstName} ${state.createdBy.lastName}`}</option>
                    </select>
                  </span>
                </div>
              </div>
              <div className="field is-expanded is-fullwidth mt-4">
                <label className="label has-text-weight-light">Status</label>
                <div className="control is-expanded is-fullwidth">
                  <span className="select is-fullwidth">
                    <select
                      className="has-text-weight-normal"
                      onChange={(e) => {
                        setState({
                          ...state,
                          status: e.currentTarget.value,
                        });
                      }}
                    >
                      {Object.values(Status)
                        .filter((item) => {
                          return isNaN(Number(item));
                        })
                        .map((item) =>
                          item === state.status ? (
                            <option key={item} selected>
                              {item}
                            </option>
                          ) : (
                            <option key={item}>{item}</option>
                          )
                        )}
                    </select>
                  </span>
                </div>
              </div>
              <div className="py-2">
                {savingError && (
                  <div className="info has-text-error is-size-7 mt-2">
                    {savingError.message} <br />
                    Please retry
                  </div>
                )}
              </div>
              {!saving && (
                <button className="button is-dark is-fullwidth">Edit</button>
              )}
              {saving && (
                <div className="is-dark is-fullwidth mt-6">
                  <div className="columns is-fullwidth is-centered">
                    <div className="column is-one-third" />
                    <div className="column is-one-fifth mt-2">
                      <div className="control is-loading"></div>
                    </div>
                    <div className="column is-one-third" />
                  </div>
                </div>
              )}
              <button
                className="button is-fullwidth mt-5"
                onClick={() => {
                  switchMode();
                }}
              >
                Cancel
              </button>
            </form>
          </div>
        </section>
      </div>
    </div>
  );
};

export default CreateUserStoryModal;
