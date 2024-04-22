package vn.iback.studentmanager.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String fullName;
    @NotBlank
    @Size(min = 6)
    private String passWord;
    @Email
    private String email;
}
