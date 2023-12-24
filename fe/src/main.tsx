import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App.tsx';
import './index.css';
import { Provider } from 'react-redux';
import { store } from './redux/store/index.ts';
import { RouterProvider } from 'react-router-dom';
import { router } from './route/router.tsx';

ReactDOM.createRoot(document.getElementById('root')!).render(
  <Provider store={store}>
    <RouterProvider router={router} />
  </Provider>
);
