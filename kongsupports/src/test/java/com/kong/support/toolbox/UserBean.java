package com.kong.support.toolbox;

import java.util.Objects;

public class UserBean {

    private String name;
    private int age;
    private Boolean vip;
    private String gender;
    private String email ;
    private String mobile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Boolean getVip() {
        return vip;
    }

    public void setVip(Boolean vip) {
        this.vip = vip;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", vip=" + vip +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserBean userBean = (UserBean) o;
        return age == userBean.age &&
                Objects.equals(name, userBean.name) &&
                Objects.equals(vip, userBean.vip) &&
                Objects.equals(gender, userBean.gender) &&
                Objects.equals(email, userBean.email) &&
                Objects.equals(mobile, userBean.mobile);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, age, vip, gender, email, mobile);
    }
}
