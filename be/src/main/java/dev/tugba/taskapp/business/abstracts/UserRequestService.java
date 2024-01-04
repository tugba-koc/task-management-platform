package dev.tugba.taskapp.business.abstracts;

import dev.tugba.taskapp.business.responses.GetAllUserDataResponse;

public interface UserRequestService {
    GetAllUserDataResponse getAllUserData(String bearerToken, String requestId);
}
