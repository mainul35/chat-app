package com.mainul35.chatapp.viewmodel;

import com.mainul35.chatapp.util.Patterns;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class MailVerification implements Serializable {

    @Email
    @NotBlank(message = "Please enter your email address")
    @Pattern(regexp = Patterns.EMAIL, message = "Invalid email address")
    private String email;
}
