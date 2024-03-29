import React, { useState } from 'react';
import {
  useGetUserDataQuery,
  useUpdateUserEmailMutation,
} from '../../redux/services/userApi';
import { useSelector } from 'react-redux';
import { selectUserData } from '../../redux/features/userSlice';
import { useNavigate } from 'react-router-dom';

const UserSettings = () => {
  const navigate = useNavigate();

  const [updateEmail, setUpdateEmail] = useState(false);
  const [updatedEmailData, setUpdatedEmailData] = useState();

  const userData = useSelector(selectUserData);

  const { isError, error, isLoading } = useGetUserDataQuery();
  const [updateUserEmail] = useUpdateUserEmailMutation();

  const handleChange = (e) => {
    setUpdatedEmailData(e.target.value);
  };

  const handleUpdate = () => {
    if (!updateEmail) {
      // click change
      setUpdateEmail(true);
    } else {
      // click save
      setUpdateEmail(false);
      console.log(updatedEmailData, 'updatedEmailData');
      console.log(userData.email, 'userData.email');

      if (updatedEmailData !== userData.email) {
        updateUserEmail(updatedEmailData);
      }
    }
  };

  const handleLogout = () => {
    navigate('/', { replace: true });
    window.location.reload();
    localStorage.removeItem('jwt');
  };

  if (isLoading) {
    return <>Loading....</>;
  }
  if (isError) {
    return <>Error : {error}</>;
  }
  return (
    <>
      <div className='group'>
        <p>{userData?.firstname}</p>
        <button onClick={() => handleLogout()}>logout</button>{' '}
      </div>
      <form onSubmit={(e) => e.preventDefault()}>
        <label htmlFor='firstname'>firstname</label>
        <input
          type='text'
          name='firstname'
          id='firstname'
          value={userData.firstname}
          disabled
        />
        <label htmlFor='lastname'>lastname</label>
        <input
          type='text'
          name='lastname'
          id='lastname'
          value={userData.lastname}
          disabled
        />
        <label htmlFor='email'>email</label>
        <input
          onChange={(e) => handleChange(e)}
          required
          type='email'
          name='email'
          id='email'
          disabled={updateEmail ? false : true}
          value={updatedEmailData ? updatedEmailData : userData.email}
        />

        {/* {errorText ? <p className='error'>{errorText}</p> : null} */}
        <button onClick={() => handleUpdate()} type='submit'>
          {updateEmail ? 'save' : 'change'}
        </button>
      </form>
    </>
  );
};

export default UserSettings;
