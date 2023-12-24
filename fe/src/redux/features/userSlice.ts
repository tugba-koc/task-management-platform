import { createSlice } from '@reduxjs/toolkit';

const userSlice = createSlice({
  name: 'user',
  initialState: {
    token: '',
  },
  reducers: {
    tokenAdded(state: userInitialState, action) {
      return {
        ...state,
        token: action.payload,
      };
    },
  },
});

export const { tokenAdded } = userSlice.actions;
export default userSlice.reducer;
