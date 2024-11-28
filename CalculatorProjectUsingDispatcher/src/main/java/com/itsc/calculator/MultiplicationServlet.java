@WebServlet("/MultiplicationServlet")
public class MultiplicationServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int num1 = Integer.parseInt(request.getParameter("num1"));
        int num2 = Integer.parseInt(request.getParameter("num2"));
        int result = num1 * num2;

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>Result: " + num1 + " * " + num2 + " = " + result + "</h1>");
    }
}
