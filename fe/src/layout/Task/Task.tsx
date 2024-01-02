import React, { useState } from 'react';
import { useGetUserDataQuery } from '@redux/services/userApi';
import {
  useDeleteTaskMutation,
  useGetAllTasksQuery,
  usePostTaskMutation,
} from '../../redux/services/tasksApi';

const Task = () => {
  const { data: userData, isError, isSuccess, error } = useGetUserDataQuery();

  const { data: tasks, isLoading } = useGetAllTasksQuery({
    pollingInterval: 3000,
    refetchOnMountOrArgChange: true,
    skip: false,
  });

  const [postTask] = usePostTaskMutation();
  const [deleteTask] = useDeleteTaskMutation();

  const [task, setTask] = useState({ title: '', body: '' });
  const [errorText, setErrorText] = useState();

  const handleChange = (e) => {
    setErrorText();
    const { name, value } = e.target;
    setTask({ ...task, [name]: value });
  };

  const handleSubmit = async () => {
    try {
      await postTask(task);
    } catch (err) {
      setErrorText(err);
    } finally {
      setTask({ title: '', body: '' });
    }
  };

  const handleDelete = async (id) => {
    try {
      await deleteTask(id);
    } catch (err) {
      setErrorText(err);
    } finally {
      setTask({ title: '', body: '' });
    }
  };

  if (isLoading) {
    return <>Loading....</>;
  }
  if (isError) {
    return <>Error : {error}</>;
  }
  if (isSuccess) {
    return (
      <>
        <p>hello {userData['firstname']} !</p>
        <form onSubmit={(e) => e.preventDefault()}>
          <label htmlFor='title'>title</label>
          <input
            onChange={(e) => handleChange(e)}
            required
            name='title'
            id='title'
            value={task.title}
          />

          <label htmlFor='body'>body</label>
          <input
            onChange={(e) => handleChange(e)}
            required
            type='body'
            name='body'
            id='body'
            value={task.body}
          />

          {errorText ? <p className='error'>{errorText}</p> : null}
          <button
            onClick={() => handleSubmit()}
            className={errorText ? 'button_disabled' : ''}
            disabled={errorText}
            type='submit'
          >
            add
          </button>
        </form>
        <div>
          {tasks
            ? tasks?.map((task) => {
                return (
                  <div className='group'>
                    <p key={task.id}>{task.title}</p>{' '}
                    <button
                      onClick={() => handleDelete(task.id)}
                      className='small'
                    >
                      -
                    </button>
                    <button className='small'>edit</button>
                  </div>
                );
              })
            : ''}
        </div>
      </>
    );
  }
};

export default Task;
