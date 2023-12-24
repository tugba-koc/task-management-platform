import React, { useState } from 'react';
import { userApi } from '../../redux/services/userApi';
import { useDispatch } from 'react-redux';
import { tokenAdded } from '../../redux/features/userSlice';
import { useNavigate } from 'react-router-dom';

const Login = () => {
  const [user, setUser] = useState({ username: '', password: '' });
  const [error, setError] = useState();

  const [trigger] = userApi.endpoints.authenticate.useMutation();

  const navigate = useNavigate();
  const dispatch = useDispatch();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setUser({ ...user, [name]: value });
  };

  const handleLogin = async () => {
    try {
      const res = await trigger(user);
      await dispatch(tokenAdded(res?.data.token));
      navigate('/direction');
    } catch (error) {
      setError(error.message);
    } finally {
      setUser({ username: '', password: '' });
    }
  };

  return (
    <>
      <h3>Login</h3>
      <form onSubmit={(e) => e.preventDefault()}>
        <label htmlFor='username'>Username</label>
        <input
          onChange={(e) => handleChange(e)}
          required
          name='username'
          id='username'
          value={user.username}
        />

        <label htmlFor='password'>Password</label>
        <input
          onChange={(e) => handleChange(e)}
          required
          type='password'
          name='password'
          id='password'
          value={user.password}
        />

        {error ? <p className='error'>{error}</p> : null}
        <button
          onClick={() => handleLogin()}
          className={error ? 'button_disabled' : ''}
          disabled={error}
          type='submit'
        >
          login
        </button>
      </form>
    </>
  );
};

export default Login;
