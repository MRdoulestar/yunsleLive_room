package com.test.dao;
import com.test.entity.Linkman;
import com.test.entity.User;

import java.util.List;

/**
 * Created by Doublestar on 2017/11/24 11:16).
 */

/**
 * DAO接口,mybatis动态完成Impl
 */

public interface LinkmanDao {
    // 根据联系人姓名查找联系人相关信息
    public List<Linkman> findLinkmanByName(String name);

    // 根据联系人客户id查找
    public List<Linkman> findLinkmanByCid(int cid);

    // 修改联系人
    public int updateLinkman(Linkman linkman);

    // 删除联系人
    public int deleteLinkman(int id);

    //根据客户Id删除联系人
    public int deleteLinkmanByCid(int id);

    // 添加联系人
    public int insertLinkman(Linkman linkman);

}
