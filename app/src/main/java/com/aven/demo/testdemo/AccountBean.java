package com.aven.demo.testdemo;

/**
 * author: create by mak on 2019/2/26
 * E-mail: 8401*****@qq.com
 */
public class AccountBean {

    private String mIcon;
    private long mId;
    private long mId2;
    private String mPassword;
    private String mName;
    private boolean isSelected;

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        this.mIcon = icon;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        this.mId = id;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        this.mPassword = password;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public long getId2() {
        return mId2;
    }

    public void setId2(long id2) {
        mId2 = id2;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
