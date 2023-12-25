package com.example.demo.app.controller.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AddUserApiRequest {
    private String userName;
    private String birthday;
}
