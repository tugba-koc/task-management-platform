import React, { useState } from 'react';
import {
  useGetUserDataQuery,
  useUpdateUserEmailMutation,
} from '../../redux/services/userApi';

const UserSettings = () => {
  const [updateEmail, setUpdateEmail] = useState(false);
  const { data: userData, isError, error, isLoading } = useGetUserDataQuery();
  const [updateUserEmail] = useUpdateUserEmailMutation();

  const [updatedEmailData, setUpdatedEmailData] = useState();

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
      if (updatedEmailData !== userData.email) {
        updateUserEmail(updatedEmailData);
      }
    }
  };

  if (isLoading) {
    return <>Loading....</>;
  }
  if (isError) {
    return <>Error : {error}</>;
  }
  return (
    <>
      <div>{userData?.firstname}</div>
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
        <button
          onClick={() => handleUpdate()}
          type='submit'
        >
          {updateEmail ? 'save' : 'change'}
        </button>
      </form>
    </>
  );
};

export default UserSettings;
