package com.trj.tlib.javabean;

/**
 * @author tong
 * @date 2018/4/25 17:30
 * 获取登录信息
 *
 */
public class LoginInfoJB extends TBaseJB {

    /**
     * code : H-0-991893369003180032-131072-4096-0
     * token : eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1MjYwMTIxMzIsInVzZXJJZCI6IkgtMC05OTE4OTMzNjkwMDMxODAwMzItMTMxMDcyLTQwOTYtMCIsInJvbGUiOiJVU0VSIn0.yE6uQpAvYl6s9bp1ruYFcr7kjyd_UfhbNA8Ov-FllRw
     * nickname : null
     * displayPhoto : null
     * sex : 0
     * publicKey : publicKeytest
     * alreadySetPassword : true
     * alreadySetPhoneNum : true
     * isQualificationApprove:-1
     * isHealthApprove:-1
     * isIdentityApprove:-1
     * phoneNum:15312345678
     *
     */

    private String code;
    private String token;
    private String nickname;
    private String displayPhoto;
    private int sex;
    private String publicKey;
    private boolean alreadySetPassword;
    private boolean alreadySetPhoneNum;

    private int isQualificationApprove;
    private int isHealthApprove;
    private int isIdentityApprove;
    private String phoneNum;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDisplayPhoto() {
        return displayPhoto;
    }

    public void setDisplayPhoto(String displayPhoto) {
        this.displayPhoto = displayPhoto;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public boolean isAlreadySetPassword() {
        return alreadySetPassword;
    }

    public void setAlreadySetPassword(boolean alreadySetPassword) {
        this.alreadySetPassword = alreadySetPassword;
    }

    public boolean isAlreadySetPhoneNum() {
        return alreadySetPhoneNum;
    }

    public void setAlreadySetPhoneNum(boolean alreadySetPhoneNum) {
        this.alreadySetPhoneNum = alreadySetPhoneNum;
    }

    public int getIsQualificationApprove() {
        return isQualificationApprove;
    }

    public void setIsQualificationApprove(int isQualificationApprove) {
        this.isQualificationApprove = isQualificationApprove;
    }

    public int getIsHealthApprove() {
        return isHealthApprove;
    }

    public void setIsHealthApprove(int isHealthApprove) {
        this.isHealthApprove = isHealthApprove;
    }

    public int getIsIdentityApprove() {
        return isIdentityApprove;
    }

    public void setIsIdentityApprove(int isIdentityApprove) {
        this.isIdentityApprove = isIdentityApprove;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
