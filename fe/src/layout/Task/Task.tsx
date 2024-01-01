import React, { useState } from 'react';
import { useGetUserDataQuery } from '@redux/services/userApi';
import {
  useGetAllTasksQuery,
  usePostTaskMutation,
} from '../../redux/services/tasksApi';
import { useDispatch } from 'react-redux';
import { setTaskData } from '../../redux/features/tasksSlice';

const Task = () => {
  const dispatch = useDispatch();
  const {
    data: userData,
    isLoading,
    isError,
    isSuccess,
    error,
  } = useGetUserDataQuery();

  const { data: tasks } = useGetAllTasksQuery({
    pollingInterval: 3000,
    refetchOnMountOrArgChange: true,
    skip: false,
  });

  const [postTask] = usePostTaskMutation();

  const [task, setTask] = useState({ title: '', body: '' });
  const [errorText, setErrorText] = useState();

  const handleChange = (e) => {
    setErrorText();
    const { name, value } = e.target;
    setTask({ ...task, [name]: value });
  };

  const handleSubmit = async () => {
    try {
      const res = await postTask(task);
      /* dispatch(setTaskData(res.data)); */
      if (res.error) {
        if (res.error.data.statusCode === 422) {
          setErrorText('Kullanıcı kimliği uyuşmuyor.');
        }
      } else {
        /* navigate('/direction'); */
      }
    } catch (error) {
      setErrorText(error.message);
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
                return <p key={task.id}>{task.title}</p>;
              })
            : ''}
        </div>
      </>
    );
  }
};

export default Task;
