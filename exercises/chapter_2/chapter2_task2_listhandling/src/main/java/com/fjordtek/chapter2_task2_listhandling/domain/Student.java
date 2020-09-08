// Pekka Helenius <fincer89@hotmail.com>, Fjordtek 2020

package com.fjordtek.chapter2_task2_listhandling.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Student {

    /* 
     * NOTE: These constraints are not used because
     * we use hard-coded, pre-defined list with correct values
     * already supplied. These would be useful for user input
     * fields in a web page.
    */
    @NotNull
    @Pattern(regexp="^[a-zA-Z]+$")
    private String firstName;
    
    @NotNull
    @Pattern(regexp="^[a-zA-Z]+$")
    private String lastName;

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName  = lastName;
    }

    public String getfirstName() {
        return firstName;
    }
    public String getlastName() {
        return lastName;
    }
    
    @Override
    public String toString() {
        return this.firstName + " " + this.lastName;
    }
}
