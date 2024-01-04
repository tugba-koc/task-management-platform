package dev.tugba.taskapp.business.responses;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAllUserDataResponse implements Serializable {
    private String firstname;
    private String lastname;
    private String turkishId;
    private String email;
    private String status;
    private Date datetime;
    private String requestId;
}