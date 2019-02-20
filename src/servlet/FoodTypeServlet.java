package servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import entity.FoodType;
import factory.BeanFactory;
import service.IFoodTypeService;

public class FoodTypeServlet extends HttpServlet {

	private IFoodTypeService service = BeanFactory.getInstance("foodTypeService", IFoodTypeService.class);
	private Object uri;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		List<FoodType> list = service.query();
		config.getServletContext().setAttribute("foodtype", list);
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
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String name = request.getParameter("name");
			FoodType foodtype = new FoodType();
			foodtype.setTypeName(name);
			service.add(foodtype);
			list(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			uri = "/error/error.jsp";
		}
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<FoodType> list = service.query();
			request.setAttribute("list", list);
			request.getServletContext().setAttribute("foodtype", list);
			uri = request.getRequestDispatcher("/sys/foodtype/cuisineList.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			uri = "/error/error.jsp";
		} finally {
			goTo(request, response, uri);
		}
	}

	private void goTo(HttpServletRequest request, HttpServletResponse response, Object uri)
			throws ServletException, IOException {
		if (uri instanceof RequestDispatcher) {
			((RequestDispatcher) uri).forward(request, response);

		} else {
			response.sendRedirect(request.getContextPath() + (String) uri);

		}
	}

	// 在菜系更新中显示菜系对应的类型的名称
	private void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			String id = request.getParameter("id");
			FoodType type = service.findById(Integer.parseInt(id));
			request.setAttribute("type", type);
			uri = request.getRequestDispatcher("/sys/foodtype/updateCuisine.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			uri = "/error/error.jsp";
		} finally {
			goTo(request, response, uri);
		}
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			FoodType type = new FoodType();
			Map<String, String[]> map = request.getParameterMap();
			BeanUtils.populate(type, map);
			service.update(type);
			list(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			uri = "/error/error.jsp";

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
				List<FoodType> list = service.query(keyword);
				request.setAttribute("list", list);
				uri = request.getRequestDispatcher("/sys/foodtype/cuisineList.jsp");
			}
		} catch (Exception e) {
			uri = "/error/error.jsp";
			e.printStackTrace();
		}
		goTo(request, response, uri);
	}
}
