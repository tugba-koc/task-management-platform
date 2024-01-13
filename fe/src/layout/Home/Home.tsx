import React, { useEffect } from 'react';
import './Home.css';
import { useNavigate } from 'react-router-dom';
import { useVerifySessionMutation } from '../../redux/services/userApi';

const Home = () => {
  const navigate = useNavigate();

  const [verifySession] = useVerifySessionMutation();

  useEffect(() => {
    if (localStorage.getItem('jwt')) {
      verifySession();
    }
  }, []);

  return (
    <div className='container'>
      <div className='button-group'>
        {localStorage.getItem('jwt') ? (
          <>
            <button
              onClick={() => {
                navigate('tasks');
              }}
            >
              Tasks
            </button>
            <button
              onClick={() => {
                navigate('user/profile');
              }}
            >
              settings
            </button>
          </>
        ) : (
          <>
            <button
              onClick={() => {
                navigate('login');
              }}
            >
              Login
            </button>
            <button
              onClick={() => {
                navigate('register');
              }}
            >
              Register
            </button>
          </>
        )}
      </div>
      <h2>Welcome</h2>
    </div>
  );
};

export default Home;
