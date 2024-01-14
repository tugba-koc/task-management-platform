import React, { useEffect, useState } from 'react';
import './Home.css';
import { useNavigate } from 'react-router-dom';
import { useVerifySessionMutation } from '../../redux/services/userApi';
import { useSelector } from 'react-redux';
import { selectIsLoggedIn } from '../../redux/features/userSlice';

const Home = () => {
  const isLoggedIn = useSelector(selectIsLoggedIn);
  
  const navigate = useNavigate();

  const [verifySession] = useVerifySessionMutation();

  useEffect(() => {
    if (localStorage.getItem('jwt') && !isLoggedIn) {
      verifySession().then((res) => {
        if (res.data.status === 'SUCCESS') {
          navigate('/');
          setIsMounted(true);
        } else if (res.data.status === 'FAILED') {
          navigate('/');
        }
      });
    }
  }, [isLoggedIn, navigate, verifySession]);

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
              Settings
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
