// Pekka Helenius <fincer89@hotmail.com>, Fjordtek 2020

package com.fjordtek.chapter2_task3_friendlist.domain;

/*
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;
*/

public class Friend {

    //@Size(min=2, max=40)
    //@Pattern(regexp="^[a-zA-Z]+ [a-zA-Z]+$")
    private String fullName;

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public Friend() {
    }

    public Friend(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return this.fullName;
    }
}
