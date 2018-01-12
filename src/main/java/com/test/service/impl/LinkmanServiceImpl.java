package com.test.service.impl;

import com.test.dao.CustomerDao;
import com.test.dao.LinkmanDao;
import com.test.entity.Customer;
import com.test.entity.Linkman;
import com.test.entity.StatusMessage;
import com.test.service.ICustomerService;
import com.test.service.ILinkmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Doublestar on 2017/12/5 21:06).
 */
@Service
@Transactional
public class LinkmanServiceImpl implements ILinkmanService {
    @Autowired
    LinkmanDao linkmanDao;

    @Override
    public List<Linkman> findLinkmanByName(String name) {
        return linkmanDao.findLinkmanByName(name);
    }

    @Override
    public List<Linkman> findLinkmanByCid(int cid) {
        return linkmanDao.findLinkmanByCid(cid);
    }

    @Override
    public boolean updateLinkman(int id, Linkman linkman) {
        linkman.setId(id);
        if(linkmanDao.updateLinkman(linkman) > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public StatusMessage insertLinkman(int cid, Linkman linkman) {
        linkman.setCid(cid);
        if(linkmanDao.insertLinkman(linkman) > 0) {
            int id = linkman.getId();
            return new StatusMessage(200, "success", String.valueOf(id));
        }else {
            return new StatusMessage(500, "error", "联系人添加失败！");
        }
    }

    @Override
    public boolean deleteLinkman(int id) {
        if(linkmanDao.deleteLinkman(id) > 0)  {
            return true;
        } else {
            return false;
        }
    }
}
