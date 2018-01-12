package com.test.dao;
import com.test.entity.Customer;
import com.test.entity.User;

import java.util.Date;
import java.util.List;

/**
 * Created by Doublestar on 2017/11/24 11:16).
 */

/**
 * DAO接口,mybatis动态完成Impl
 */

public interface CustomerDao {

    // 添加客户
    public int insertCustomer(Customer customer);

    //删除客户
    public int deleteCustomerById(int id);

    public int deleteCustomerByName(String name);

    //修改客户
    public int updateCustomer(Customer customer);

    //查找所有用户
    public List<Customer> getAllCustomer();

    // 根据ID查找客户相关信息
    public Customer findCustomerById(int id);

    // 根据名字查找客户相关信息
    public List<Customer> findCustomerByName(String name);

    // 根据行业查找客户相关信息
    public List<Customer> findCustomerByProfession(String professtion);

    // 根据省份查找客户相关信息
    public List<Customer> findCustomerByProvince(String province);

    // 根据注册时间查找客户相关信息
    public List<Customer> findCustomerByUptime(Date uptime);

    // 根据资金查找客户相关信息
    public List<Customer> findCustomerByFunds(long funds);

    // 根据法人查找客户相关信息
    public List<Customer> findCustomerByCorporation(String corporation);


}
