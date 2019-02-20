package servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import entity.Food;
import entity.FoodType;
import factory.BeanFactory;
import service.IFoodService;
import service.IFoodTypeService;
import utils.PageBean;

public class FoodServlet extends HttpServlet {
	private IFoodService service = BeanFactory.getInstance("foodService", IFoodService.class);
	private IFoodTypeService ifs = BeanFactory.getInstance("foodTypeService", IFoodTypeService.class);
	private Object uri;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		PageBean<Food> pageBean = new PageBean<Food>();
		pageBean.setPageCount(6);
		service.getAll(pageBean);
		List<Food> list = service.query();
		config.getServletContext().setAttribute("food", list);
		config.getServletContext().setAttribute("pb", pageBean);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");

		if ("add".equals(method)) {
			add(request, response);
		} else if ("list".equals(method)) {
			list(request, response);
		} else if ("update".equals(method)) {
			update(request, response);
		} else if ("delete".equals(method)) {
			delete(request, response);
		} else if ("search".equals(method)) {
			search(request, response);
		} else if ("show".equals(method)) {
			show(request, response);
		} else if ("findFoodType".equals(method)) {
			findFoodType(request, response);
			uri = request.getRequestDispatcher("/sys/food/saveFood.jsp");
			goTo(request, response, uri);
		} else if ("query".equals(method)) {
			query(request, response);
		} else if ("getMenu".equals(method)) {
			getMenu(request, response);
		}
	}

	private void getMenu(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			// 1. 获取“当前页”参数； (第一次访问当前页为null)
			String currPage = request.getParameter("currentPage");
			// 判断
			if (currPage == null || "".equals(currPage.trim())) {
				currPage = "1"; // 第一次访问，设置当前页为1;
			}
			// 转换
			int currentPage = Integer.parseInt(currPage);

			// 2. 创建PageBean对象，设置当前页参数； 传入service方法参数
			PageBean<Food> pageBean = new PageBean<Food>();
			pageBean.setCurrentPage(currentPage);

			// 3. 调用service
			service.getAll(pageBean); // 【pageBean已经被dao填充了数据】
			// 4. 保存pageBean对象，到request域中

			List<Food> list = pageBean.getPageData();
			// 获得食物类别的方法
			List<FoodType> types = new ArrayList<FoodType>();

			if (list != null) {
				for (Food food : list) {
					FoodType foodtype = ifs.findById(food.getFoodType_id());
					types.add(foodtype);
				}
			}
			request.setAttribute("types", types);

			request.setAttribute("pageBean", pageBean);
			request.setAttribute("list", list);
			uri = request.getRequestDispatcher("/sys/food/foodList.jsp");
		} catch (Exception e) {
			e.printStackTrace(); // 测试使用
			// 出现错误，跳转到错误页面；给用户友好提示
			uri = "/error/error.jsp";
		}
		goTo(request, response, uri);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public void findFoodType(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<FoodType> foodtypes = ifs.query();
		request.setAttribute("foodtypes", foodtypes);
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setFileSizeMax(10 * 1024 * 1024); // 单个文件大小限制
			upload.setSizeMax(50 * 1024 * 1024); // 总文件大小限制
			upload.setHeaderEncoding("UTF-8"); // 对中文文件编码处理

			if (upload.isMultipartContent(request)) {

				Food food = new Food();
				List<FileItem> list = upload.parseRequest(request);
				for (FileItem item : list) {

					if (item.isFormField()) {// 普通本文内容
						String name = item.getFieldName();
						// 获取值
						String value = item.getString();
						value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
						BeanUtils.setProperty(food, name, value);
					} else {// 上传内容
						String fieldName = item.getFieldName();
						String path = getServletContext().getRealPath("/upload");
						File f = new File(path);
						if (!f.exists()) {
							f.mkdir();
						}
						// 全部绝对路径
						String name = item.getName();

						BeanUtils.setProperty(food, fieldName, "upload/" + name);

						// a2. 拼接文件名
						File file = new File(path, name);
						// d. 上传
						if (!file.isDirectory()) {
							item.write(file);
						}
						item.delete(); // 删除组件运行时产生的临时文件
					}
				}
				service.add(food);

			} else {

			}
			list(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			uri = "/error/error.jsp";
			goTo(request, response, uri);
		}
	}

	private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Food> list = service.query();
			request.setAttribute("list", list);

			// 获得食物类别的方法
			List<FoodType> types = new ArrayList<FoodType>();
			IFoodTypeService ifs = BeanFactory.getInstance("foodTypeService", IFoodTypeService.class);
			for (Food food : list) {
				FoodType foodtype = ifs.findById(food.getFoodType_id());
				types.add(foodtype);
			}
			request.setAttribute("types", types);

			uri = request.getRequestDispatcher("/sys/food/foodList.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			uri = "/error/error.jsp";
		} finally {
			goTo(request, response, uri);
		}
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setFileSizeMax(10 * 1024 * 1024); // 单个文件大小限制
			upload.setSizeMax(50 * 1024 * 1024); // 总文件大小限制
			upload.setHeaderEncoding("UTF-8"); // 对中文文件编码处理

			if (upload.isMultipartContent(request)) {

				Food food = new Food();
				List<FileItem> list = upload.parseRequest(request);
				for (FileItem item : list) {

					if (item.isFormField()) {// 普通本文内容
						String name = item.getFieldName();
						// 获取值
						String value = item.getString();
						value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
						BeanUtils.setProperty(food, name, value);
					} else {// 上传内容
						String fieldName = item.getFieldName();
						String path = getServletContext().getRealPath("/upload");
						File f = new File(path);
						if (!f.exists()) {
							f.mkdir();
						}
						String name = item.getName();
						if (name != null && !"".equals(name.trim())) {
							BeanUtils.setProperty(food, fieldName, ("upload/" + name));

							// a2. 拼接文件名
							File file = new File(path, name);
							// d. 上传
							if (!file.isDirectory()) {
								item.write(file);
							}
							item.delete(); // 删除组件运行时产生的临时文件
						} else {
							int id = food.getId();
							String img = service.findById(id).getImg();
							BeanUtils.setProperty(food, "img", img);

						}
					}
				}
				service.update(food);

			} else {

			}
			list(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			uri = "/error/error.jsp";
			goTo(request, response, uri);
		}

	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String id = request.getParameter("id");
			service.delete(Integer.parseInt(id));
			list(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			uri = "/error/error.jsp";
			goTo(request, response, uri);
		}
	}

	private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String keyword = request.getParameter("keyword");
			if (keyword != null) {
				List<Food> list = service.query(keyword);
				List<FoodType> types = new ArrayList<FoodType>();

				if (list != null) {
					for (Food food : list) {
						FoodType foodtype = ifs.findById(food.getFoodType_id());
						types.add(foodtype);
					}
				}
				request.setAttribute("types", types);
				request.setAttribute("list", list);
				uri = request.getRequestDispatcher("/sys/food/foodList.jsp");
			}
		} catch (Exception e) {
			uri = "/error/error.jsp";
			e.printStackTrace();
		}
		goTo(request, response, uri);
	}

	// 这个方法没用
	private void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		findFoodType(request, response);
		String id = request.getParameter("id");
		Food food = service.findById(Integer.parseInt(id));

		request.setAttribute("food", food);
		// 得到食物里面的食物类型ID
		int foodType_id = food.getFoodType_id();

		// 通过
		FoodType type = ifs.findById(foodType_id);
		request.setAttribute("type", type);

		uri = request.getRequestDispatcher("/sys/food/updateFood.jsp");
		goTo(request, response, uri);

	}

	private void goTo(HttpServletRequest request, HttpServletResponse response, Object uri)
			throws ServletException, IOException {
		if (uri instanceof RequestDispatcher) {
			((RequestDispatcher) uri).forward(request, response);

		} else {
			response.sendRedirect(request.getContextPath() + (String) uri);
		}
	}
}
