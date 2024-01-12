import { createApi, fetchBaseQuery } from '@reduxjs/toolkit/query/react';
import { setTaskData } from '../features/tasksSlice';
import { v4 as uuidv4 } from 'uuid';

export const tasksApi = createApi({
  reducerPath: 'tasksApi',
  baseQuery: fetchBaseQuery({
    baseUrl: import.meta.env.VITE_API_BASE_URL,
  }),
  endpoints: (builder) => ({
    getAllTasks: builder.query({
      query: () => ({
        url: '/task',
        method: 'Get',
        headers: {
          'Content-type': 'application/json',
          Authorization: `Bearer ${localStorage.getItem('jwt')}`,
        },
        params: {
          requestId: uuidv4(),
        },
      }),
      providesTags: ['AllTasks'],
      onQueryStarted: async (credentials, { dispatch, queryFulfilled }) => {
        try {
          const { data } = await queryFulfilled;
          dispatch(setTaskData(data.taskList));
        } catch (error) {
          window.location.href = '/';
          localStorage.removeItem('jwt');
        }
      },
    }),
    postTask: builder.mutation({
      query: (newTask) => ({
        url: '/task',
        method: 'POST',
        body: { ...newTask, requestId: uuidv4() },
        headers: {
          'Content-type': 'application/json',
          Authorization: `Bearer ${localStorage.getItem('jwt')}`,
        },
      }),
      invalidatesTags: ['AllTasks'],
    }),
    deleteTask: builder.mutation({
      query: (id) => ({
        url: '/task',
        method: 'DELETE',
        body: { taskId: id, requestId: uuidv4() },
        headers: {
          'Content-type': 'application/json',
          Authorization: `Bearer ${localStorage.getItem('jwt')}`,
        },
      }),
      invalidatesTags: ['AllTasks'],
    }),
    updateTask: builder.mutation({
      query: (task) => ({
        url: '/task',
        method: 'PATCH',
        body: { ...task, requestId: uuidv4() },
        headers: {
          'Content-type': 'application/json',
          Authorization: `Bearer ${localStorage.getItem('jwt')}`,
        },
      }),
      invalidatesTags: ['AllTasks'],
    }),
  }),
});

export const {
  useGetAllTasksQuery,
  useDeleteTaskMutation,
  usePostTaskMutation,
  useUpdateTaskMutation,
} = tasksApi;
