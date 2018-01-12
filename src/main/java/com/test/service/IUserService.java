package com.test.service;

import com.test.entity.StatusMessage;

import javax.servlet.http.HttpSession;

/**
 * Created by Doublestar on 2017/11/29 16:26).
 */
public interface IUserService {

    /**
     *isLogin
     * @author yuxx
     * @param name
     * @return boolean
     */
    public boolean isLogin(String name);

    /**
     *userLogin
     * @author yuxx
     * @param session,name,password
     * @return StatusMessage
     */
    public StatusMessage userLogin(HttpSession session, String name, String password, String authcode);

    /**
     *userLogin
     * @author yuxx
     * @param name,password,repassword
     * @return StatusMessage
     */
    public StatusMessage userResistor(String name, String password, String repassword);

    /**
     * @author yuxx
     * @param session,name
     * @return StatusMessage
     */
    public StatusMessage userLogoutAjax(HttpSession session, String name);

    /**
     * @author yuxx
     * @param session
     * @return boolean
     */
    public boolean userLogout(HttpSession session);

    /**
     * @author yuxx
     * @param name
     * @param address
     * @return StatusMessage
     */
    public StatusMessage forgetPassword(String name, String address);
}
