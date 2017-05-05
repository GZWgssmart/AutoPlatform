package com.gs.common.util;

import com.gs.bean.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * Created by Xiao-Qiang on 2017/5/5.
 */
public class SessionGetUtil {

    public static User getUser() {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        User user = (User) session.getAttribute("user");
        return user;
    }
}
