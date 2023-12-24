import { createBrowserRouter } from 'react-router-dom';
import App from '../App';
import EditTask from '../layout/EditTask/EditTask';
import Error from '../layout/Error/Error';
import Login from '../layout/Login/Login';
import MidDirection from '../layout/MidDirection/MidDirection';
import Register from '../layout/Register/Register';
import Task from '../layout/Task/Task';
import UserSettings from '../layout/UserSettings/UserSettings';

export const router = createBrowserRouter([
  {
    path: '/',
    element: <App />,
    errorElement: <Error />,
  },
  {
    path: 'login',
    element: <Login />,
    errorElement: <Error />,
  },
  {
    path: 'register',
    element: <Register />,
    errorElement: <Error />,
  },
  {
    path: 'direction',
    element: <MidDirection />,
    errorElement: <Error />,
  },
  {
    path: 'settings',
    element: <UserSettings />,
    errorElement: <Error />,
  },
  {
    path: 'tasks',
    element: <Task />,
    errorElement: <Error />,
  },
  {
    path: 'tasks/task:taskId',
    element: <EditTask />,
    errorElement: <Error />,
  },
]);
