package com.hsport.wxprogram.domain.vo;

public class CoachGenVo {
    private String phone;
    private String password;
    private String testcode;
    private String employmentTime;
    private String coachName;
    private Integer gymID;
    private String birthday;

    public String getEmploymentTime() {
        return employmentTime;
    }

    public void setEmploymentTime(String employmentTime) {
        this.employmentTime = employmentTime;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }


    public Integer getGymID() {
        return gymID;
    }

    public void setGymID(Integer gymID) {
        this.gymID = gymID;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTestcode() {
        return testcode;
    }

    public void setTestcode(String testcode) {
        this.testcode = testcode;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
