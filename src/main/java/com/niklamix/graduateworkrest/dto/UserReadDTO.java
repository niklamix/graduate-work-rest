package com.niklamix.graduateworkrest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserReadDTO extends EntityDetailDTO {
    private String name;
    private String surname;
    private String email;
    private String country;
    private String city;
    private String phone;
    private String login;
    private String photo;
    private boolean adminFlag;
    private boolean enabled;
}
