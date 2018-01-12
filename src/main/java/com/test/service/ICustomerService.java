package com.test.service;

import com.test.dao.CustomerDao;
import com.test.entity.Customer;
import com.test.entity.StatusMessage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Doublestar on 2017/12/5 21:06).
 */
public interface ICustomerService {

    /**
     * 获得所有客户信息
     * @return
     */
    public List<Customer> getAllCustomer();

    /**
     * 查找客户
     * @param customer
     * @return
     */
    public List<Customer> findCustomer(Customer customer);

    /**
     * 根据ID得到客户信息
     * @param id
     * @return
     */
    public Customer getCustomerBeforeChange(int id);

    /**
     * 更新客户信息
     * @param id,customer
     * @return
     */
    public boolean updateCustomer(int id, Customer customer);

    /**
     * 添加客户信息
     * @param customer
     * @return
     */
    public StatusMessage insertCustomer(Customer customer);

    /**
     * 删除客户信息
     * @param id
     * @return
     */
    public boolean deleteCustomer(int id);
}
