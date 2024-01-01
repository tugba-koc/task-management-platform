import { createSlice } from '@reduxjs/toolkit';

export const tasksSlice = createSlice({
  name: 'tasks',
  initialState: {
    tasks: [],
  },
  reducers: {
    setTaskData(state: initialState, action) {
      return {
        ...state,
        tasks: action.payload,
      };
    },
  },
});

export const { setTaskData } = tasksSlice.actions;

export default tasksSlice.reducer;
