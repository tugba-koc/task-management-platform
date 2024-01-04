import { createApi, fetchBaseQuery } from '@reduxjs/toolkit/query/react';
import { setUserData } from '../features/userSlice';
import { v4 as uuidv4 } from 'uuid';

export const userApi = createApi({
  reducerPath: 'userApi',
  baseQuery: fetchBaseQuery({
    baseUrl: import.meta.env.VITE_API_BASE_URL,
  }),
  endpoints: (builder) => ({
    register: builder.mutation({
      query: (data) => ({
        url: '/auth/register',
        method: 'POST',
        body: data,
      }),
      onQueryStarted: async (credentials, { queryFulfilled }) => {
        try {
          const { data } = await queryFulfilled;
          localStorage.setItem('jwt', data.token);
        } catch (error) {
          console.log('error');
        }
      },
    }),
    authenticate: builder.mutation({
      query: (user) => ({
        url: '/auth/authenticate',
        method: 'POST',
        body: {
          accountcode: user.username,
          password: user.password,
          requestId: uuidv4(),
        },
      }),
      onQueryStarted: async (credentials, { queryFulfilled }) => {
        try {
          const { data } = await queryFulfilled;
          localStorage.setItem('jwt', data.token);
        } catch (error) {
          console.log('error');
        }
      },
    }),
    getUserData: builder.query({
      query: () => ({
        url: '/user',
        method: 'GET',
        params: {
          requestId: uuidv4(),
        },
        headers: {
          'Content-type': 'application/json',
          Authorization: `Bearer ${localStorage.getItem('jwt')}`,
        },
      }),
      onQueryStarted: async (credentials, { dispatch, queryFulfilled }) => {
        try {
          const { data } = await queryFulfilled;
          dispatch(setUserData(data));
        } catch (error) {
          dispatch(setUserData({}));
        }
      },
    }),
  }),
});

export const {
  useAuthenticateMutation,
  useRegisterMutation,
  useGetUserDataQuery,
} = userApi;
