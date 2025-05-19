import { configureStore } from '@reduxjs/toolkit'
import { loginSlice } from '../redux/slice/loginSlice';
import { pageSlice } from '../redux/slice/pageSlice';
export default configureStore({
    reducer:{
        login:loginSlice.reducer,
        page:pageSlice.reducer
    }
});
