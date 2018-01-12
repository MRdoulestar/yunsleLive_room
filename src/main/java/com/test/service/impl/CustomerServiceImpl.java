package com.test.service.impl;

import com.test.dao.CustomerDao;
import com.test.dao.LinkmanDao;
import com.test.entity.Customer;
import com.test.entity.Linkman;
import com.test.entity.StatusMessage;
import com.test.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Doublestar on 2017/12/5 21:06).
 */
@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService{
    @Autowired(required = false)
    CustomerDao customerDao;
    @Autowired(required = false)
    LinkmanDao linkmanDao;

    //获得所有客户信息
    @Override
    public List<Customer> getAllCustomer() {
        return customerDao.getAllCustomer();
    }

    //根据条件查找客户
    @Override
    public List<Customer> findCustomer(Customer customer) {
        List<Customer> list = customerDao.findCustomerByName(customer.getName());
        list.addAll(customerDao.findCustomerByProfession(customer.getProfession()));
        list.addAll(customerDao.findCustomerByProvince(customer.getProvince()));
        list.addAll(customerDao.findCustomerByUptime(customer.getUptime()));
        list.addAll(customerDao.findCustomerByFunds(customer.getFunds()));
        list.addAll(customerDao.findCustomerByCorporation(customer.getCorporation()));
        List<Customer> customers = new ArrayList<>();
        // 对象List去重
        list.stream().forEach(
                c -> {
                    if (!customers.contains(c)) {
                        customers.add(c);
                    }
                }
        );
        return customers;
    }

    //根据Id查找客户
    @Override
    public Customer getCustomerBeforeChange(int id) {
        return customerDao.findCustomerById(id);
    }

    //更新客户信息
    @Override
    public boolean updateCustomer(int id, Customer customer) {
        customer.setId(id);
        if(customerDao.updateCustomer(customer) > 0) {
            return true;
        }else {
            return false;
        }
    }

    //添加客户
    @Override
    public StatusMessage insertCustomer(Customer customer) {
        if(customerDao.insertCustomer(customer) > 0) {
            int id = customer.getId();
            return new StatusMessage(200, "success", String.valueOf(id));
        }else {
            return new StatusMessage(500, "error", "客户添加失败！");
        }
    }

    //删除客户，同时清空该客户关联的联系人信息
    @Override
    public boolean deleteCustomer(int id) {
//        if(customerDao.deleteCustomerById(id) > 0 && linkmanDao.deleteLinkmanByCid(id) > 0) {
        if(customerDao.deleteCustomerById(id) > 0){
            return true;
        }else {
            return false;
        }
    }
}
