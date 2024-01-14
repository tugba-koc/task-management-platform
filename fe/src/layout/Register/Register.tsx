import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useRegisterMutation } from '../../redux/services/userApi';

const Register = () => {
  const [user, setUser] = useState({
    firstname: '',
    lastname: '',
    email: '',
    turkishId: '',
    password: '',
  });
  const [errorText, setErrorText] = useState();

  const [trigger] = useRegisterMutation();

  const navigate = useNavigate();

  const handleChange = (e) => {
    setErrorText();
    const { name, value } = e.target;
    setUser({ ...user, [name]: value });
  };

  const handleRegister = async () => {
    try {
      const res = await trigger(user);
      if (res.error) {
        if (res.error.status === 400) {
          setErrorText('Hatalı giriş yaptınız.');
        } else if (res.error.status === 422) {
          setErrorText('Turkish id is already saved in our db.');
        }
      } else {
        navigate('/');
      }
    } catch (error) {
      setErrorText(error.message);
    } finally {
      /*     setUser({
        firstname: '',
        lastname: '',
        email: '',
        turkishId: '',
        password: '',
      }); */
    }
  };

  return (
    <form onSubmit={(e) => e.preventDefault()}>
      <label htmlFor='firstname'>firstname</label>
      <input
        onChange={(e) => handleChange(e)}
        value={user.firstname}
        required
        type='text'
        name='firstname'
        id='firstname'
      />

      <label htmlFor='lastname'>lastname</label>
      <input
        onChange={(e) => handleChange(e)}
        required
        type='text'
        name='lastname'
        id='lastname'
        value={user.lastname}
      />

      <label htmlFor='email'>email</label>
      <input
        onChange={(e) => handleChange(e)}
        required
        type='email'
        name='email'
        id='email'
        value={user.email}
      />

      <label htmlFor='turkishId'>turkishId</label>
      <input
        onChange={(e) => handleChange(e)}
        required
        type='number'
        name='turkishId'
        id='turkishId'
        pattern='^\\d*\\.?\\d+$'
        value={user.turkishId}
      />

      <label htmlFor='password'>password</label>
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
        className={errorText ? 'button_disabled' : ''}
        disabled={errorText}
        onClick={() => handleRegister()}
        type='submit'
      >
        register
      </button>
    </form>
  );
};

export default Register;
