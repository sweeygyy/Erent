package com.erent.customer.web.servlte;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.erent.customer.domain.Customer;
import com.erent.customer.service.CustomerException;
import com.erent.customer.service.CustomerService;
import com.erent.utils.CommonUtils;
import com.erent.utils.TransformUtils;

/**
 * 处理修改用户头像请求
 * 
 * @author acer-pc
 * 
 */
public class ModifyServlet extends HttpServlet {
	CustomerService service = new CustomerService();

	public ModifyServlet() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		
		// 创建工程
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 获取解析器
		ServletFileUpload sfu = new ServletFileUpload(factory);
		// 用解析器解析request
		try {
			List<FileItem> fileItemList = sfu.parseRequest(request);

			Map<String, String> map = new HashMap<String, String>();

			// 头像的下标
			int iconIndex = -1;

			System.out.println(fileItemList.size());
			
			// 将表单项提取封装成Customer
			for (int i = 0; i < fileItemList.size(); i++) {
				if (fileItemList.get(i).isFormField()) {
					System.out.println(fileItemList.get(i).getFieldName()+ "," + fileItemList
							.get(i).getString("UTF-8"));
					map.put(fileItemList.get(i).getFieldName(), fileItemList
							.get(i).getString("UTF-8"));
				} else {
					// 标记文件项的下标
					iconIndex = i;
				}
			}
			Customer customer = CommonUtils.toBean(map, Customer.class);

			// 有上传图片
			if (iconIndex != -1 && fileItemList.get(iconIndex).getSize() > 0) {

				String savePath = this.getServletContext().getRealPath("/icon");

				// 获取后缀名
				String fileName = fileItemList.get(iconIndex).getName();

				if (!fileName.contains(".")) {
					JSONObject object = TransformUtils
							.packException(new CustomerException("上传文件缺少后缀名",
									100));
					response.getWriter().write(object.toString());
					return;
				}

				int index = fileName.lastIndexOf(".");
				fileName = fileName.substring(index);

				// 生成文件名
				fileName = CommonUtils.uuid() + "_icon" + fileName;

				// 哈希打散
				int hash = fileName.hashCode();
				String hex = Integer.toHexString(hash);
				File dirFile = new File(savePath, hex.charAt(0) + "//"
						+ hex.charAt(1));
				dirFile.mkdirs();

				File disFile = new File(dirFile, fileName);
				fileItemList.get(iconIndex).write(disFile);

				customer.setpImage("/icon/" + hex.charAt(0) + "/"
						+ hex.charAt(1) + "/" + fileName);

				// 删除原先头像
				Customer before = service.getCustomerInfo(customer);
				if (before.getpImage() != null
						&& !before.getpImage().trim().equals("")) {
					File oldFile = new File(this.getServletContext()
							.getRealPath(before.getpImage()));
					if (oldFile.exists()) {
						oldFile.delete();
					}
				}
			}

			// 调用service的修改方法
			Customer after = service.alterImage(customer);
			JSONObject object = TransformUtils.packObject(after);
			response.getWriter().print(object.toString());
		} catch (CustomerException e) {
			JSONObject object = TransformUtils.packException(e);
			response.getWriter().print(object.toString());
			e.printStackTrace();
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void init() throws ServletException {
		// 注册日期转换器
		ConvertUtils.register(new Converter() {
			public Object convert(Class arg0, Object arg1) {
				if (arg1 == null) {
					return null;
				}
				if(arg1 instanceof Date) {
					return arg1;
				}
				if (!(arg1 instanceof String)) {
					throw new ConversionException("只支持字符串转换 !");
				}
				String str = (String) arg1;
				if (str.trim().equals("")) {
					return null;
				}
				SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
				try {
					return sd.parse(str);
				} catch (ParseException e) {
					throw new RuntimeException(e);
				}

			}

		}, Date.class);
	}

}
