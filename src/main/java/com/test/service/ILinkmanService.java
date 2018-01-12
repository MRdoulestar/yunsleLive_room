package com.test.service;

import com.test.entity.Customer;
import com.test.entity.Linkman;
import com.test.entity.StatusMessage;

import java.util.List;

/**
 * Created by Doublestar on 2017/12/5 21:06).
 */
public interface ILinkmanService {

    public List<Linkman> findLinkmanByName(String name);

    public List<Linkman> findLinkmanByCid(int cid);

    public boolean updateLinkman(int id, Linkman linkman);

    public StatusMessage insertLinkman(int cid, Linkman linkman);

    public boolean deleteLinkman(int id);
}
