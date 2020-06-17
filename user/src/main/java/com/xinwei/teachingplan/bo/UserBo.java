package com.xinwei.teachingplan.bo;

/**
 * @ClassName: UserBo
 * @Author chengfei
 * @Date 2020/6/17 20:51
 * @Version 1.0
 * @Description: TODO
 **/
public class UserBo {
    private int id;
    private String name;
    private String phone;
    private String password;
    private int userType;
    private int delIs;
    private String createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getDelIs() {
        return delIs;
    }

    public void setDelIs(int delIs) {
        this.delIs = delIs;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "UserBo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", userType=" + userType +
                ", delIs=" + delIs +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
