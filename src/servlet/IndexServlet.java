package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Food;
import entity.FoodType;
import utils.Condition;
import utils.PageBean;

public class IndexServlet extends BaseServlet {

	public Object getMenu(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Object uri = null;
		HttpSession session = request.getSession();// 用于存储订单信息
		// 获取session里的值
		Object obj = session.getAttribute("table_id");

		String table_id = request.getParameter("table_id");// 桌的id
		if (table_id != null) {
			tableService.changeState(Integer.parseInt(table_id));
			if (obj == null) {
				session.setAttribute("table_id", table_id);// 存放桌id以备订单用
			}
		}

		// 查询菜系信息
		List<FoodType> foodtypes = foodTypeService.query();
		request.setAttribute("foodtypes", foodtypes);

		// 获取菜单页面信息
		PageBean<Food> pb = new PageBean<Food>();

		Condition con = new Condition();
		// 获取页面得到的参数
		String foodtype = request.getParameter("foodtype");
		String foodName = request.getParameter("foodName");
		if (foodtype != null && !foodtype.isEmpty()) {
			con.setFoodType_id(Integer.parseInt(foodtype));
			pb.setCondition(con);
		}
		if (foodName != null && !foodName.isEmpty()) {
			con.setFoodName(foodName);
			pb.setCondition(con);
		}

		pb.setPageCount(6);
		String curPage = request.getParameter("currentPage");// 获取当前页
		if (curPage == null || curPage.isEmpty()) {
			pb.setCurrentPage(1);
		}
		if (curPage != null && !curPage.isEmpty()) {
			int currentPage = Integer.parseInt(curPage);
			pb.setCurrentPage(currentPage);
		}

		foodService.getAll(pb);

		request.setAttribute("pageBean", pb);
		// 跳转
		uri = request.getRequestDispatcher("/app/detail/caidan.jsp");

		return uri;
	}

	public Object searchFood(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Object uri = null;

		PageBean<Food> pb = new PageBean<Food>();
		Condition condition = new Condition();

		String keyword = request.getParameter("keyword");// 设置关键词
		if (keyword != null && !keyword.isEmpty()) {
			condition.setFoodName(keyword);
		}
		if (condition != null) {
			pb.setCondition(condition);
		}

		pb.setCondition(condition);

		foodService.getAll(pb);

		request.setAttribute("pageBean", pb);
		// 跳转
		uri = request.getRequestDispatcher("/app/detail/caidan.jsp");

		return uri;
	}

	public Object getFoodDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Object uri = null;
		String id = request.getParameter("food");// 获取食物id
		Food food = foodService.findById(Integer.parseInt(id));
		List<FoodType> foodtypes = foodTypeService.query();
		request.setAttribute("food", food);
		request.setAttribute("foodtypes", foodtypes);
		uri = request.getRequestDispatcher("/app/detail/caixiangxi.jsp");

		return uri;
	}

	// 下面是关于订单的方法，这些方法在indexServlet中实现
	/*
	 * public Object putInCar(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { HttpSession session =
	 * request.getSession(); Object uri =null; Map<Food,Integer> map = new
	 * LinkedHashMap<Food,Integer>();
	 * 
	 * //获取食物id String id = request.getParameter("food_id"); Food food =
	 * foodService.findById(Integer.parseInt(id)); Map<Food,Integer> m =(Map<Food,
	 * Integer>) session.getAttribute("foods");
	 * 
	 * if(m!=null){ if(m.containsKey(food)){ Integer count = m.get(food); count++;
	 * m.put(food, count); }else{ m.put(food, 1); } }else { map.put(food, 1); }
	 * 
	 * if(m!=null){ session.setAttribute("foods", m); }else{
	 * session.setAttribute("foods", map); } uri="/app/detail/clientCart.jsp";
	 * 
	 * return uri; }
	 * 
	 * public Object removeOrder(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { Object uri=null; String id =
	 * request.getParameter("gid"); Food food =
	 * foodService.findById(Integer.parseInt(id)); HttpSession session =
	 * request.getSession(); Map<Food,Integer> m=
	 * (Map<Food,Integer>)session.getAttribute("foods"); m.remove(food);
	 * session.setAttribute("foods", m); uri="/app/detail/clientCart.jsp"; return
	 * uri; }
	 * 
	 * public Object alterSorder(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { Object uri=null; String id =
	 * request.getParameter("gid"); Food food =
	 * foodService.findById(Integer.parseInt(id));
	 * 
	 * //获取修改狗的数量 String num = request.getParameter("snumber"); HttpSession session
	 * = request.getSession(); Map<Food,Integer> m=
	 * (Map<Food,Integer>)session.getAttribute("foods"); m.put(food,
	 * Integer.parseInt(num)); session.setAttribute("foods", m);
	 * 
	 * uri="/app/detail/clientCart.jsp"; return uri; }
	 * 
	 * public Object takeOrder(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { Object uri=null;
	 * 
	 * HttpSession session = request.getSession(); Map<Food,Integer> m =
	 * (Map<Food,Integer>)session.getAttribute("foods"); String table_id
	 * =(String)session.getAttribute("table_id");
	 * 
	 * 
	 * //新建订单对象 Orders order = new Orders();
	 * order.setTable_id(Integer.parseInt(table_id));
	 * 
	 * Set<Entry<Food,Integer>> entrySet = m.entrySet();
	 * 
	 * //定义总价钱 int sum =0; for (Entry<Food, Integer> entry : entrySet) { Food food =
	 * entry.getKey(); Integer count = entry.getValue(); order.setId(food.getId());
	 * sum += food.getPrice()*count; order.setOrderDate(new Date()); }
	 * order.setTotalPrice(sum);
	 * 
	 * //创建订单详细对象
	 * 
	 * 
	 * 
	 * uri="/app/detail/clientCart.jsp"; return uri;
	 * 
	 * }
	 */
}
