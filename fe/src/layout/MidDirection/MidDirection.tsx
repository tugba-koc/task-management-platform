import React from 'react';
import { useNavigate } from 'react-router-dom';

const MidDirection = () => {
  const navigate = useNavigate();

  return (
    <div>
      <button onClick={() => navigate('/user/profile')}>Go to settings</button>
      <button onClick={() => navigate('/tasks')}>Go to Task Page</button>
    </div>
  );
};

export default MidDirection;
