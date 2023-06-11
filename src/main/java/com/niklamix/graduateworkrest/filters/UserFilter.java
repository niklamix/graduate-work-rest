package com.niklamix.graduateworkrest.filters;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserFilter extends PageFilter {
    private String name;
    private String surname;
    private String login;


}
