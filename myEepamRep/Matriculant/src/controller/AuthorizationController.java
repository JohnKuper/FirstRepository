package controller;

import matriculantDAO.MatriculantDAO;
import models.InstructorDTO;
import view.ViewJSP;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AuthorizationController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ViewJSP view = new ViewJSP();
        try{
            if(!MatriculantDAO.login(request)){
                view.errorLoginOrPassword(request, new InstructorDTO(false));
                view.forwardPage(this,request,response, "/index.jsp");
                return;
            }  else {
                request.getSession().setAttribute("isLogin",request.getSession().getId());
                request.getSession().setAttribute("DTO",new InstructorDTO(true,request.getParameter("login"),request.getParameter("password"),"jdbc:mysql://"+request.getParameter("host")+"/matriculant"));
                view.redirPage(response,"/matriculant.jsp");
                return;
            }
        } catch (Exception e){
            view.printErrorPage(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
