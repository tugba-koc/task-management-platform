import React, { useEffect, useState } from 'react';
import { useAuthenticateMutation } from '../../redux/services/userApi';
import { Navigate, useLocation, useNavigate } from 'react-router-dom';
import { useSelector } from 'react-redux';
import { selectIsLoggedIn } from '../../redux/features/userSlice';
import { useVerifySessionMutation } from '../../redux/services/userApi';

const Login = () => {
  const isLoggedIn = useSelector(selectIsLoggedIn);

  const location = useLocation();

  const [user, setUser] = useState({ username: '', password: '' });
  const [errorText, setErrorText] = useState();

  const [authenticate] = useAuthenticateMutation();
  const [verifySession] = useVerifySessionMutation();

  useEffect(() => {
    if (localStorage.getItem('jwt')) verifySession();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

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
        if (res.error.status === 422) {
          setErrorText('Kullanıcı kimliği uyuşmuyor.');
        }
      } else {
        navigate('/');
      }
    } catch (error) {
      setErrorText(error.message);
    } finally {
      setUser({ username: '', password: '' });
    }
  };

  return (
    <>
      {isLoggedIn && <Navigate to='/' replace={true} />}
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
          autoComplete='password'
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
      {location.pathname == '/admin' ? (
        <button onClick={() => navigate('/admin/register')}>register</button>
      ) : null}
    </>
  );
};

export default Login;
