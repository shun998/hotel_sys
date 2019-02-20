package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.DinnerTable;
import factory.BeanFactory;
import service.IDinnerTableService;

public class DinnerTableServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IDinnerTableService service = BeanFactory.getInstance("dinnerTableService", IDinnerTableService.class);

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		List<DinnerTable> list = service.query();
		config.getServletContext().setAttribute("table", list);
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
		}

	}

	private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyword = request.getParameter("keyword");
		if (keyword != null) {
			List<DinnerTable> list = service.query(keyword);
			request.setAttribute("list", list);
			request.getRequestDispatcher("/sys/board/boardList.jsp").forward(request, response);
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		service.delete(Integer.parseInt(id));
		list(request, response);

	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		service.changeState(Integer.parseInt(id));
		list(request, response);
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<DinnerTable> list = service.query();
		request.setAttribute("list", list);

		// 将餐桌列表存到context里传到前台显示
		request.getServletContext().setAttribute("table", list);

		request.getRequestDispatcher("/sys/board/boardList.jsp").forward(request, response);
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tableName = request.getParameter("tableName");
		if (tableName != null) {
			DinnerTable dt = new DinnerTable();
			dt.setTableName(tableName);
			service.add(dt);
			list(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
