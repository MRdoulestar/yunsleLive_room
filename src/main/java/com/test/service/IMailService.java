package com.test.service;

/**
 * Created by Doublestar on 2017/12/1 8:53).
 */
public interface IMailService {
    /**
     *
     * @author yuxx
     * @param adress,content
     * @return boolean
     */
    public boolean sendMail(String adress, String content);
}
