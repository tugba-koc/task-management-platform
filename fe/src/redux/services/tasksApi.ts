import { createApi, fetchBaseQuery } from '@reduxjs/toolkit/query/react';

export const tasksApi = createApi({
  reducerPath: 'tasksApi',
  baseQuery: fetchBaseQuery({ baseUrl: 'http://localhost:8080/api/v1/' }),
  endpoints: (builder) => ({
    getAllTasks: builder.query({
      query: () => `task`,
    }),
    getTaskById: builder.query({
      query: (id) => `task/${id}`,
    }),
    postTask: builder.mutation({
      query: (newTask) => ({
        url: 'task',
        method: 'POST',
        body: newTask, // it should include userId, title, body
      }),
    }),
    editTask: builder.mutation({
      query: (edittedTask) => ({
        url: 'task',
        method: 'PATCH',
        body: edittedTask,
      }),
    }),
  }),
});

export const {
  useGetAllTasksQuery,
  useGetTaskByIdQuery,
  usePostTaskMutation,
  useEditTaskMutation,
} = tasksApi;
