package com.kong.web.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAliasType;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

@XStreamAliasType("account")
@XmlType(name = "AccountType")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlRootElement(name = "Account")
public class     Account {
    @XStreamAlias("accountName")
    @NotEmpty(message = "{account.name.empty}")
    private String name;
    @XStreamAlias("accountAge")
    @Range(max = 130,min = 0,message = "{account.age.range}")
    private int age;
    @XStreamAlias("accountBirthDay")
    private Date birthDay;

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

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }
}
