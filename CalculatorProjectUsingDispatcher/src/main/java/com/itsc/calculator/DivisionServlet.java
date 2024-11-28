@WebServlet("/DivisionServlet")
public class DivisionServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int num1 = Integer.parseInt(request.getParameter("num1"));
        int num2 = Integer.parseInt(request.getParameter("num2"));

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (num2 == 0) {
            out.println("<h1>Division by zero is not allowed!</h1>");
        } else {
            double result = (double) num1 / num2;
            out.println("<h1>Result: " + num1 + " / " + num2 + " = " + result + "</h1>");
        }
    }
}
