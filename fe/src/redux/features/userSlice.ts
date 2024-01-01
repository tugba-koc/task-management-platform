import { createSlice } from '@reduxjs/toolkit';

const userSlice = createSlice({
  name: 'user',
  initialState: {
    userData: {
      firstname: '',
      lastname: '',
      turkishId: '',
      email: '',
    },
  },
  reducers: {
    setUserData(state: initialState, action) {
      return {
        ...state,
        userData: action.payload,
      };
    },
  },
  selectors: {
    selectUserData: (state) => state.userData,
  },
});

export const { setUserData } = userSlice.actions;
export const { selectUserData } = userSlice.selectors;
export default userSlice.reducer;
