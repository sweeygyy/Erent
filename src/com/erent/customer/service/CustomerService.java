package com.erent.customer.service;

import com.erent.customer.dao.CustomerDao;
import com.erent.customer.domain.Customer;

public class CustomerService {
	private CustomerDao dao = new CustomerDao();
	
	/**
	 * Customer注册逻辑
	 * @param form
	 * @throws CustomerException
	 */
	public Customer regist(Customer form) throws CustomerException{	
		Customer customer = dao.findCustomerByPNum(form.getCustomer_id());
		if(customer != null) {
			throw new CustomerException("用户名已存在", 150);
		}
		customer = dao.findCustomerByTel(form.getTel());
		if(customer != null) {
			throw new CustomerException("手机号已被注册", 151);
		}
		//补全默认信息
		form.setName("用户" + form.getCustomer_id());
		form.setSchool("");
		form.setDepartment("");
		
		dao.add(form);
		return dao.findCustomerByPNum(form.getCustomer_id());
	}
	
	/**
	 * 登录逻辑
	 * @param form
	 * @return
	 * @throws CustomerException
	 */
	public Customer login(Customer form) throws CustomerException {
		Customer customer = dao.findCustomerByPNum(form.getCustomer_id());
		if(customer == null) throw new CustomerException("用户不存在。", 101);
		if(!customer.getPassword().equals(form.getPassword())) throw new CustomerException("用户密码错误", 100);
		
		return customer;
	}
	
	/**
	 * 获取用户信息逻辑
	 * @param form
	 * @return
	 * @throws CustomerException
	 */
	public Customer getCustomerInfo(Customer form) throws CustomerException {
		Customer customer = dao.findCustomerByPNum(form.getCustomer_id());
		if(customer == null) throw new CustomerException("用户不存在", 101);
		//获取用户信息没有必要获取密码，将密码置空
		customer.setPassword("");
		return customer;
	}
	
	/**
	 * 修改用户普通信息逻辑
	 * @param form
	 * @throws CustomerException
	 */
	public Customer modify(Customer form) throws CustomerException {
		Customer customer = dao.findCustomerByPNum(form.getCustomer_id());
		if(customer == null) throw new CustomerException("用户不存在", 101);
		//修改密码独立于用户信息修改，因此还原密码
		form.setPassword(customer.getPassword());
		//修改手机独立于用户信息修改
		form.setTel(customer.getTel());
		//信誉度不可由用户修改
		form.setXYD(customer.getXYD());
		//保留原照片
		form.setpImage(customer.getpImage());
		
		dao.update(form);
		return getCustomerInfo(form);
	}
	
	/**
	 * 修改用户手机逻辑
	 * @param form
	 * @return
	 * @throws CustomerException
	 */
	public Customer alterTel(Customer form, String newTel) throws CustomerException {
		Customer customer = dao.findCustomerByPNum(form.getCustomer_id());
		if(customer == null) throw new CustomerException("用户不存在", 101);
		if(!customer.getTel().equals(form.getTel())) throw new CustomerException("原手机号错误", 104);
		if(dao.findCustomerByTel(newTel) != null) {throw new CustomerException("手机号已被注册", 151);}
		customer.setTel(newTel);
		dao.update(customer);
		return getCustomerInfo(form);
	}

	/**
	 * 修改用户密码逻辑
	 * @param form
	 * @param newPassword
	 * @return
	 * @throws CustomerException
	 */
	public Customer alterPassword(Customer form, String newPassword) throws CustomerException {
		System.out.println(form.getCustomer_id());
		Customer customer = dao.findCustomerByPNum(form.getCustomer_id());
		if(customer == null) throw new CustomerException("用户不存在", 101);
		if(!customer.getPassword().equals(form.getPassword())) throw new CustomerException("原密码错误", 103);
		customer.setPassword(newPassword);
		dao.update(customer);
		return getCustomerInfo(form);
	}
	
	/**
	 * 修改用户头像逻辑
	 * @param form
	 * @return
	 * @throws CustomerException
	 */
	public Customer alterImage(Customer form) throws CustomerException {
		Customer customer = dao.findCustomerByPNum(form.getCustomer_id());
		if(customer == null) throw new CustomerException("用户不存在", 101);
		customer.setpImage(form.getpImage());
		dao.update(customer);
		return getCustomerInfo(form);
	}
	
}
