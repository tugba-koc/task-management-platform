package dev.tugba.taskapp.business.abstracts;

import dev.tugba.taskapp.business.responses.GetAllUserDataResponse;

public interface UserService {
    GetAllUserDataResponse getAllUserData(String bearerToken);
}
