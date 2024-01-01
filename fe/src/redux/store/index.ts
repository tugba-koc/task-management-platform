import tasksReducer from '../features/tasksSlice';
import userReducer from '../features/userSlice';
import { configureStore } from '@reduxjs/toolkit';
import { tasksApi } from '../services/tasksApi';
import { userApi } from '../services/userApi';

export const store = configureStore({
  reducer: {
    tasks: tasksReducer,
    user: userReducer,
    [tasksApi.reducerPath]: tasksApi.reducer,
    [userApi.reducerPath]: userApi.reducer,
  },
  middleware: (gDM) => gDM().concat(tasksApi.middleware, userApi.middleware),
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
