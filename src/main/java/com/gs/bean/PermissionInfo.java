package com.gs.bean;

/**
 * Created by Xiao-Qiang on 2017/4/19.
 */
public class PermissionInfo {

    private String permissionId;
    private String permissionName;
    private int status;

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
