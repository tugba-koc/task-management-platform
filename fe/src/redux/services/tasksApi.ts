import { createApi, fetchBaseQuery } from '@reduxjs/toolkit/query/react';
import { setTaskData } from '../features/tasksSlice';
import { v4 as uuidv4 } from 'uuid';

const apiBaseUrl = 'http://localhost:8080/api/v1';

export const tasksApi = createApi({
  reducerPath: 'tasksApi',
  baseQuery: fetchBaseQuery({
    baseUrl: apiBaseUrl,
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
      }),
      providesTags: ['AllTasks'],
      onQueryStarted: async (credentials, { dispatch, queryFulfilled }) => {
        try {
          const { data } = await queryFulfilled;
          dispatch(setTaskData(data));
        } catch (error) {
          dispatch(setTaskData([]));
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
    /*     editTask: builder.mutation({
      query: (edittedTask) => ({
        url: 'task',
        method: 'PATCH',
        body: edittedTask,
      }),
    }),  */
    /*     getTaskById: builder.query({
      query: (id) => `task/${id}`,
    }),*/
  }),
});

export const {
  useGetAllTasksQuery,
  useGetTaskByIdQuery,
  usePostTaskMutation,
  useEditTaskMutation,
} = tasksApi;
