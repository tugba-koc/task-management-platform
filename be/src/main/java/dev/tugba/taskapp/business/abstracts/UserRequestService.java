package dev.tugba.taskapp.business.abstracts;

import dev.tugba.taskapp.business.requests.UpdateUserEmailAddressRequest;
import dev.tugba.taskapp.business.responses.GetAllUserDataResponse;
import dev.tugba.taskapp.business.responses.UpdateUserEmailAddressResponse;

public interface UserRequestService {
    GetAllUserDataResponse getAllUserData(String bearerToken, String requestId);
    UpdateUserEmailAddressResponse updateUserEmailAddress(String bearerToken, UpdateUserEmailAddressRequest updateUserEmailAddressRequest);
}
