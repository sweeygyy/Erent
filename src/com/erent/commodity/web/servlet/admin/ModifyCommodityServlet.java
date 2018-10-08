package com.erent.commodity.web.servlet.admin;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.erent.category.domain.Category;
import com.erent.category.service.CategoryException;
import com.erent.category.service.CategoryService;
import com.erent.commodity.domain.Commodity;
import com.erent.commodity.service.CommodityService;
import com.erent.customer.dao.CustomerDao;
import com.erent.customer.domain.Customer;
import com.erent.customer.service.CustomerException;
import com.erent.utils.CommonUtils;
import com.erent.utils.TransformUtils;

import net.sf.json.JSONObject;

public class ModifyCommodityServlet extends HttpServlet {
	private CommodityService service = new CommodityService();
	private CategoryService cates = new CategoryService();
	private CustomerDao custd = new CustomerDao();

	/**
	 * Constructor of the object.
	 */
	public ModifyCommodityServlet() {
		super();
	}

	public void destroy() {
		super.destroy();

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		// 创建工程
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 获取解析器
		ServletFileUpload sfu = new ServletFileUpload(factory);
		// 用解析器解析request
		try {
			List<FileItem> fileItemList = sfu.parseRequest(request);

			Map<String, String> map = new HashMap<String, String>();

			System.out.println(fileItemList.size());
			// 头像的下标
			int iconIndex = -1;

			// 将表单项提取封装成Commodity
			for (int i = 0; i < fileItemList.size(); i++) {
				if (fileItemList.get(i).isFormField()) {
					map.put(fileItemList.get(i).getFieldName(), fileItemList.get(i).getString("UTF-8"));
				} else {
					// 标记文件项的下标
					iconIndex = i;
				}
			}
			Category cate = cates.findCategoryById(map.remove("category"));
			if (cate == null) {
				throw new CategoryException("分类不存在", 601);
			}
			Customer seller = custd.findCustomerByPNum(map.remove("seller"));
			if (seller == null) {
				throw new CustomerException("用户不存在", 101);
			}

			Commodity commodity = CommonUtils.toBean(map, Commodity.class);

			commodity.setCategory(cate);
			commodity.setSeller(seller);

			// 有上传图片
			if (iconIndex != -1 && fileItemList.get(iconIndex).getSize() > 0) {

				String savePath = this.getServletContext().getRealPath("/commodity");

				// 获取后缀名
				String fileName = fileItemList.get(iconIndex).getName();

				if (!fileName.contains(".")) {
					JSONObject object = TransformUtils.packException(new CustomerException("上传文件缺少后缀名", 100));
					response.getWriter().write(object.toString());
					return;
				}

				int index = fileName.lastIndexOf(".");
				fileName = fileName.substring(index);

				// 生成文件名
				fileName = CommonUtils.uuid() + "_commodity" + fileName;

				// 哈希打散
				int hash = fileName.hashCode();
				String hex = Integer.toHexString(hash);
				File dirFile = new File(savePath, hex.charAt(0) + "//" + hex.charAt(1));
				dirFile.mkdirs();

				File disFile = new File(dirFile, fileName);
				fileItemList.get(iconIndex).write(disFile);

				commodity.setCom_image("/commodity/" + hex.charAt(0) + "/" + hex.charAt(1) + "/" + fileName);

				// 删除原先头像
				Commodity before = service.getCommodityInfo(commodity.getCommodity_id());
				if (before.getCom_image() != null && !before.getCom_image().trim().equals("")) {
					File oldFile = new File(this.getServletContext().getRealPath(before.getCom_image()));
					System.out.println(oldFile.getPath());
					if (oldFile.exists()) {
						oldFile.delete();
					}
				}
			}

			// 调用service的修改方法
			Commodity after = service.modify(commodity);
			JSONObject object = TransformUtils.packObject(after);
			response.getWriter().print(object.toString());
		} catch (CustomerException e) {
			JSONObject object = TransformUtils.packException(e);
			response.getWriter().print(object.toString());
			e.printStackTrace();
		} catch (CategoryException e) {
			JSONObject object = TransformUtils.packException(e);
			response.getWriter().print(object.toString());
			e.printStackTrace();
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
