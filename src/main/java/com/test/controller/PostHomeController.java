package com.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.entity.Customer;
import com.test.entity.Linkman;
import com.test.entity.StatusMessage;
import com.test.service.ICustomerService;
import com.test.service.ILinkmanService;
import jdk.nashorn.internal.objects.NativeJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

/**
 * Created by Doublestar on 2017/12/6 16:30).
 */
@RestController
@RequestMapping(value = "/home/post")
public class PostHomeController {
    @Autowired
    private ICustomerService customerService;

    @Autowired
    private ILinkmanService linkmanService;

    private StatusMessage customerJsonUtil(List<Customer> data) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(data);
            return new StatusMessage(200, "success", json);
        } catch(Exception e) {
            return new StatusMessage(200, "error", "错误:"+e);
        }
    }

    private StatusMessage customerJsonUtil(Customer data) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(data);
            return new StatusMessage(200, "success", json);
        } catch(Exception e) {
            return new StatusMessage(200, "error", "错误:"+e);
        }
    }

    private StatusMessage linkmanJsonUtil(List<Linkman> data) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(data);
            return new StatusMessage(200, "success", json);
        } catch(Exception e) {
            return new StatusMessage(200, "error", "错误:"+e);
        }
    }

    private StatusMessage linkmanJsonUtil(Linkman data) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(data);
            return new StatusMessage(200, "success", json);
        } catch(Exception e) {
            return new StatusMessage(200, "error", "错误:"+e);
        }
    }
//    ?name=testinsert&profession=1&province=2&uptime=2017-12-12&funds=100&corporation=3
    //客户操作

    // 获取所有客户
    @RequestMapping(value = "/getallcustomer", method = RequestMethod.POST)
    public StatusMessage getAllCustomer() {
        // 调用service层查找并返回数据
        return customerJsonUtil(customerService.getAllCustomer());
    }

    // 条件查询客户
    @RequestMapping(value = "/findcustomer", method = RequestMethod.POST)
    public StatusMessage findCustomer(@ModelAttribute("customer") Customer customer) {
        return customerJsonUtil(customerService.findCustomer(customer));
    }

    // 修改客户信息前获得该客户所有信息
    @RequestMapping(value = "/beforechange", method = RequestMethod.POST)
    public StatusMessage findCustomerBeforeChange(int id) {
        return customerJsonUtil(customerService.getCustomerBeforeChange(id));
    }

    // 增加客户
    @RequestMapping(value = "/insertcustomer", method = RequestMethod.POST)
    public StatusMessage insertCustomer(@ModelAttribute("customer") Customer customer) {
        return customerService.insertCustomer(customer);
    }

    // 修改客户信息
    @RequestMapping(value = "/updatecustomer", method = RequestMethod.POST)
    public StatusMessage updateCustomer(int id, @ModelAttribute("customer") Customer customer) {
        if(customerService.updateCustomer(id, customer)) {
            return new StatusMessage(200, "success", "客户信息修改成功！");
        }else {
            return new StatusMessage(500, "error", "客户信息修改失败！");
        }
    }

    // 删除客户
    @RequestMapping(value = "/deletecustomer", method = RequestMethod.POST)
    public StatusMessage deleteCustomer(int id) {
        if(customerService.deleteCustomer(id)) {
            return new StatusMessage(200, "success", "客户信息删除成功！");
        }else {
            return new StatusMessage(500, "error", "客户信息删除失败！");
        }
    }


    //联系人操作
//    ?cid=1&name=testinsert&sex=1&tel=2&partment=3&place=leader&birthday=2017-3-3&hobby=5
    // 根据客户Id获得联系人信息
    @RequestMapping(value = "/getlinkman", method = RequestMethod.POST)
    public StatusMessage findLinkmanByCid(int cid) {
        return linkmanJsonUtil(linkmanService.findLinkmanByCid(cid));
    }

    // 增加联系人
    @RequestMapping(value = "/insertlinkman", method = RequestMethod.POST)
//    public StatusMessage insertCustomer(String name, String profession, String province, Date uptime, long funds, String corporation) {
    public StatusMessage insertLinkman(int cid, @ModelAttribute("linkman") Linkman linkman) {
        return linkmanService.insertLinkman(cid, linkman);
    }

    // 修改联系人信息
    @RequestMapping(value = "/updatelinkman", method = RequestMethod.POST)
    public StatusMessage updateLinkman(int id, @ModelAttribute("linkman") Linkman linkman) {
        if(linkmanService.updateLinkman(id, linkman)) {
            return new StatusMessage(200, "success", "联系人信息修改成功！");
        }else {
            return new StatusMessage(500, "error", "联系人信息修改失败！");
        }
    }

    // 删除联系人
    @RequestMapping(value = "/deletelinkman", method = RequestMethod.POST)
    public StatusMessage deleteLinkman(int id) {
        if(linkmanService.deleteLinkman(id)) {
            return new StatusMessage(200, "success", "联系人信息删除成功！");
        }else {
            return new StatusMessage(500, "error", "联系人信息删除失败！");
        }
    }

}
