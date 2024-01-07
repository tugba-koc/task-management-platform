import { createSlice } from '@reduxjs/toolkit';

export const tasksSlice = createSlice({
  name: 'tasks',
  initialState: {
    taskList: [],
  },
  reducers: {
    setTaskData(state: initialState, action) {
      return {
        ...state,
        taskList: action.payload,
      };
    },
  },
});

export const { setTaskData } = tasksSlice.actions;

export default tasksSlice.reducer;
