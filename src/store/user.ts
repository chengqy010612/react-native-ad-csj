import {createSlice} from '@reduxjs/toolkit';


export interface UserState {
  isLogin: boolean;
  user: Record<string, any>;
}

const initialState: UserState = {
  isLogin: true,
  user: {},
};

const userSlice = createSlice({
  name: 'user',
  initialState: initialState,
  reducers: {
    login: (state, {payload}) => {
      state.isLogin = true;
      state.user = payload;
    },
    loginOut: (state) => {
      state.isLogin = false;
      state.user = {};
    },
  },
});

export const {login,loginOut} = userSlice.actions;
export default userSlice.reducer;
