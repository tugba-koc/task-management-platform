import React from 'react';
import { useGetUserDataQuery } from '../../redux/services/userApi';

const UserSettings = () => {
  const { data: userData, isError, error, isLoading } = useGetUserDataQuery();

  if (isLoading) {
    return <>Loading....</>;
  }
  if (isError) {
    return <>Error : {error}</>;
  }
  return <div>{userData?.firstname}</div>;
};

export default UserSettings;
