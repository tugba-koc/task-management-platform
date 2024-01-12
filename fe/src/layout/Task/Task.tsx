import React, { useState } from 'react';
import { useGetUserDataQuery } from '@redux/services/userApi';
import {
  useDeleteTaskMutation,
  useGetAllTasksQuery,
  usePostTaskMutation,
  useUpdateTaskMutation,
} from '../../redux/services/tasksApi';

const Task = () => {
  const [update, setUpdate] = useState<boolean>(false);
  const [prevData, setPrevData] = useState({ id: null, title: '', body: '' });

  const {
    data: userData,
    isSuccess,
    error: errorGetUserData,
  } = useGetUserDataQuery();

  const { data: tasks, isLoading } = useGetAllTasksQuery();

  const [postTask, { error: errorPostTask }] = usePostTaskMutation();
  const [deleteTask] = useDeleteTaskMutation();
  const [updateTaskData] = useUpdateTaskMutation();

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

  const handleDelete = async (id: number) => {
    try {
      await deleteTask(id);
    } catch (err) {
      setErrorText(err);
    } finally {
      setTask({ title: '', body: '' });
    }
  };

  // taskVal : the task is to be editted
  const handleUpdate = (taskVal) => {
    setUpdate(true);
    setTask({ title: taskVal.title, body: taskVal.body }); // to show data on input
    setPrevData({ id: taskVal.id, title: taskVal.title, body: taskVal.body });
  };

  const sendUpdate = () => {
    if (prevData.title != task.title && prevData.body != task.body) {
      updateTaskData({
        taskId: prevData.id,
        title: task.title,
        body: task.body,
      });
    } else if (prevData.title == task.title && prevData.body != task.body) {
      updateTaskData({ taskId: prevData.id, body: task.body });
    } else if (prevData.title != task.title && prevData.body == task.body) {
      updateTaskData({ taskId: prevData.id, title: task.title });
    }
    setUpdate(false);
    setTask({ title: '', body: '' });
  };

  if (isLoading) {
    return <>Loading....</>;
  }
  if (errorPostTask || errorGetUserData) {
    console.log('errorGetUserData', errorGetUserData);

    window.location.href = '/';
    localStorage.removeItem('jwt');
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
            onClick={() => (update && task ? sendUpdate() : handleSubmit())}
            className={errorText ? 'button_disabled' : ''}
            disabled={errorText}
            type='submit'
            disabled={!task.title || !task.body}
          >
            {update ? 'update' : 'add'}
          </button>
        </form>
        <div>
          {tasks
            ? tasks?.taskList.map((taskVal, index) => {
                return (
                  <div key={index} className='group'>
                    <p>{taskVal.title}</p>
                    <p>{taskVal.body}</p>
                    <button
                      onClick={() => handleDelete(taskVal.id)}
                      className='small'
                    >
                      -
                    </button>
                    <button
                      onClick={() => handleUpdate(taskVal)}
                      className='small'
                    >
                      edit
                    </button>
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
