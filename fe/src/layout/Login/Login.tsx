import React, { useState } from 'react';
import { useAuthenticateMutation } from '@redux/services/userApi';
import { useNavigate } from 'react-router-dom';

const Login = () => {
  const [user, setUser] = useState({ username: '', password: '' });
  const [errorText, setErrorText] = useState();

  const [authenticate] = useAuthenticateMutation();

  const navigate = useNavigate();

  const handleChange = (e) => {
    setErrorText();
    const { name, value } = e.target;
    setUser({ ...user, [name]: value });
  };

  const handleLogin = async () => {
    try {
      if (localStorage.getItem('jwt')) {
        localStorage.removeItem('jwt');
      }
      const res = await authenticate(user);

      if (res.error) {
        if (res.error.data.statusCode === 422) {
          setErrorText('Kullanıcı kimliği uyuşmuyor.');
        }
      } else {
        navigate('/direction');
      }
    } catch (error) {
      setErrorText(error.message);
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

        {errorText ? <p className='error'>{errorText}</p> : null}
        <button
          onClick={() => handleLogin()}
          className={errorText ? 'button_disabled' : ''}
          disabled={errorText}
          type='submit'
        >
          login
        </button>
      </form>
    </>
  );
};

export default Login;
