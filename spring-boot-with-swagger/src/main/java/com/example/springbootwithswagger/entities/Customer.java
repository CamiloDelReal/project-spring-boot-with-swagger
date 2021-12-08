package com.example.springbootwithswagger.entities;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;

    public void set(Customer other) {
        this.firstName = other.firstName;
        this.lastName = other.lastName;
        this.email = other.email;
    }
}
