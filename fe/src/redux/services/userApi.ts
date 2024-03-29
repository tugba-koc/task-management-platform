import { createApi, fetchBaseQuery } from '@reduxjs/toolkit/query/react';
import {
  setIsLoggedIn,
  setUserData,
  updateUserData,
} from '../features/userSlice';
import { v4 as uuidv4 } from 'uuid';

export const userApi = createApi({
  reducerPath: 'userApi',
  baseQuery: fetchBaseQuery({
    baseUrl: import.meta.env.VITE_API_BASE_URL,
  }),
  endpoints: (builder) => ({
    register: builder.mutation({
      query: (data) => ({
        url: `/auth/register`,
        method: 'POST',
        body: { ...data.user, role: data.role },
      }),
      onQueryStarted: async (credentials, { queryFulfilled }) => {
        try {
          const { data } = await queryFulfilled;
          localStorage.setItem('jwt', data.token);
          dispatch(setIsLoggedIn(true));
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
      onQueryStarted: async (credentials, { queryFulfilled, dispatch }) => {
        try {
          const { data } = await queryFulfilled;
          localStorage.setItem('jwt', data.token);
          dispatch(setIsLoggedIn(true));
        } catch (error) {
          console.log('an error occured');
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
      providesTags: ['GetUserData'],
      onQueryStarted: async (credentials, { dispatch, queryFulfilled }) => {
        try {
          const { data } = await queryFulfilled;
          dispatch(
            setUserData({
              firstname: data.firstname,
              lastname: data.lastname,
              turkishId: data.turkishId,
              email: data.email,
            })
          );
        } catch (error) {
          window.location.href = '/';
          localStorage.removeItem('jwt');
        }
      },
    }),
    verifySession: builder.mutation({
      query: () => ({
        url: '/auth/verifySession',
        method: 'POST',
        headers: {
          'Content-type': 'application/json',
          Authorization: `Bearer ${localStorage.getItem('jwt')}`,
        },
      }),
      onQueryStarted: async (credentials, { queryFulfilled, dispatch }) => {
        try {
          const { data } = await queryFulfilled;
          if (data.status === 'SUCCESS') {
            dispatch(setIsLoggedIn(true));
          } else {
            dispatch(setIsLoggedIn(false));
          }
        } catch (error) {
          window.location.href = '/';
          localStorage.removeItem('jwt');
        }
      },
    }),
    updateUserEmail: builder.mutation({
      query: (email) => ({
        url: '/user/update',
        method: 'PATCH',
        body: {
          email: email,
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
          dispatch(
            updateUserData({
              name: 'email',
              value: data.email,
            })
          );
          localStorage.setItem('jwt', data.token);
        } catch (error) {
          window.location.href = '/';
          localStorage.removeItem('jwt');
        }
      },
    }),
  }),
});

export const {
  useAuthenticateMutation,
  useRegisterMutation,
  useGetUserDataQuery,
  useUpdateUserEmailMutation,
  useVerifySessionMutation,
} = userApi;
