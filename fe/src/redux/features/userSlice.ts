import { createSlice } from '@reduxjs/toolkit';

const userSlice = createSlice({
  name: 'user',
  initialState: {
    isLoggedIn: false,
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
    setIsLoggedIn(state: initialState, action) {
      return {
        ...state,
        isLoggedIn: action.payload,
      };
    },
    updateUserData(state, action) {
      const { name, value } = action.payload;
      return {
        ...state,
        userData: {
          ...state.userData,
          [name]: value,
        },
      };
    },
  },
  selectors: {
    selectUserData: (state) => state.userData,
    selectIsLoggedIn: (state) => state.isLoggedIn,
  },
});

export const { setUserData, updateUserData, setIsLoggedIn } = userSlice.actions;
export const { selectUserData, selectIsLoggedIn } = userSlice.selectors;
export default userSlice.reducer;
