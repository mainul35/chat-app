package com.mainul35.chatapp.viewmodel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class Registration implements Serializable {

    private String name;
    private String email;
    private String password;
    private String rePassword;
}
