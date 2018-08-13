package com.erent.customer.service.admin;

import java.util.Date;

import com.erent.customer.dao.admin.AdminDao;
import com.erent.customer.domain.Customer;
import com.erent.customer.domain.admin.Administrator;
import com.erent.customer.service.CustomerException;

public class AdminService {
	private AdminDao dao = new AdminDao();

	
	/**
	 * 登录逻辑
	 * @param form
	 * @return
	 * @throws AdminException
	 */
	public Administrator login(Administrator form) throws AdminException {
		Administrator admin = dao.findAdministratorByName(form.getAdmin_name());
		if(admin == null) throw new AdminException("用户不存在。", 101);
		if(!admin.getAdmin_pwd().equals(form.getAdmin_pwd())) throw new AdminException("用户密码错误", 100);
		
		return admin;
	}
	
	/**
	 * 获取管理员信息逻辑
	 * @param form
	 * @return
	 * @throws CustomerException
	 */
	public Administrator getAdminInfo(Administrator form) throws AdminException {
		Administrator admin = dao.findAdministratorByName(form.getAdmin_name());
		if(admin == null) throw new AdminException("用户不存在", 101);
		//获取用户信息没有必要获取密码，将密码置空
		admin.setAdmin_pwd("");
		return admin;
	}
	
	/**
	 * 管理员权限修改逻辑
	 * @param form
	 * @throws CustomerException
	 */
	public Administrator alterPermission(String adminName, int permission) throws AdminException {
		Administrator admin = dao.findAdministratorByName(adminName);
		if(admin == null) throw new AdminException("用户不存在", 101);

		admin.setPermission(permission);

		dao.update(admin);
		return getAdminInfo(admin);
	}
	
	/**
	 * 更新管理员登录时间
	 * @param form
	 * @return
	 * @throws AdminException
	 */
	public Administrator updateLoginTime(String adminName) throws AdminException {
		Administrator admin = dao.findAdministratorByName(adminName);
		if(admin == null) throw new AdminException("用户不存在", 101);
		
		Date date = new Date();
		admin.setLast_login_time(date);
		
		dao.update(admin);
		return getAdminInfo(admin);
	}

	/**
	 * 修改用户密码逻辑
	 * @param form
	 * @param newPassword
	 * @return
	 * @throws AdminException
	 */
	public Administrator alterPassword(Administrator form, String newPassword) throws AdminException {
		Administrator admin = dao.findAdministratorByName(form.getAdmin_name());
		if(admin == null) throw new AdminException("用户不存在", 101);
		if(!admin.getAdmin_pwd().equals(form.getAdmin_pwd())) throw new AdminException("原密码错误", 103);
		admin.setAdmin_pwd(newPassword);
		dao.update(admin);
		return getAdminInfo(form);
	}
	
}
