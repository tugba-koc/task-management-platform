import { createApi, fetchBaseQuery } from '@reduxjs/toolkit/query/react';

export const userApi = createApi({
  reducerPath: 'userApi',
  baseQuery: fetchBaseQuery({ baseUrl: 'http://localhost:8080/api/v1/auth' }),
  endpoints: (builder) => ({
    authenticate: builder.mutation({
      query: (data) => ({
        url: 'authenticate',
        method: 'POST',
        body: data,
      }),
    }),
    /*     postTask: builder.mutation({
        query: (newTask) => ({
          url: 'task',
          method: 'POST',
          body: newTask, // it should include userId, title, body
        }),
      }), */
  }),
});

export const { useAuthenticateMutation } = userApi;
