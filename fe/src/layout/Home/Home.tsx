import React from 'react';
import './Home.css';
import { useNavigate } from 'react-router-dom';

const Home = () => {
  const navigate = useNavigate();
  return (
    <div className='container'>
      <div className='button-group'>
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
      </div>
      <h2>Welcome</h2>
    </div>
  );
};

export default Home;
