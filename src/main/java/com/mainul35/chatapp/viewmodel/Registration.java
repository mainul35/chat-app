package com.mainul35.chatapp.viewmodel;

import com.mainul35.chatapp.util.Patterns;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class Registration implements Serializable {
    @NotEmpty(message = "Please enter your name")
    private String name;
    @NotEmpty(message = "Please provide an email address")
    @Pattern(regexp = Patterns.EMAIL, message = "Invalid email address")
    private String email;
    @NotEmpty(message = "Please provide a password")
    @Pattern(regexp = Patterns.PASSWORD, message = "Invalid password")
    private String password;
}
