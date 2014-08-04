package controller;

import matriculantDAO.MatriculantDAO;
import models.InstructorDTO;
import models.MatriculantDTO;
import utilities.StringUtils;
import view.ViewJSP;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


public class MatriculantController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ViewJSP view = new ViewJSP();
        try{
            response.setContentType("text/html;charset=UTF-8");
            // Если другая сессия то перейдем на страницу авторизации
            String isLogin = (String)request.getSession().getAttribute("isLogin");
            InstructorDTO instructorDTO = (InstructorDTO)request.getSession().getAttribute("DTO");
            if(isLogin!=null){
                if(!isLogin.equals(request.getSession().getId())){
                    view.redirPage(response, "/index.jsp");
                    return;
                }
            }
            // Если нет DTO логина и пароля то перейдем на страницу авторизации
            if(instructorDTO==null){
                request.getSession().removeAttribute("isLogin");
                view.redirPage(response, "/index.jsp");
                return;
            }
            String command = StringUtils.getCommand(request);

            Map<String, String[]> map = StringUtils.getFields(request);
            MatriculantDTO matriculantDTO = StringUtils.getMatriculant(map);
            // Если мы Не выходим, то проверяем на корректность введенных данных
            if(!command.equals("exit")){
                if(!StringUtils.isCorrectName(map.get("matriculant")[0]) || !StringUtils.isCorrectBall(map.get("ball")[0])){
                    view.forwardPage(this,request,response,"/matriculant.jsp");
                    return;
                }
            }

            MatriculantDTO matriculantDTOisExist;
            switch (command)
            {
                case "add":
                    matriculantDTOisExist = MatriculantDAO.findMatriculant(instructorDTO,matriculantDTO);
                    if(matriculantDTOisExist!=null) {
                        view.isMatriculantExists(request,matriculantDTOisExist);
                        view.forwardPage(this,request,response,"/matriculant.jsp");;
                        break;
                    }   else {
                        MatriculantDAO.addNewMatriculant(instructorDTO,matriculantDTO);
                        view.addNewMatriculant(request,matriculantDTO);
                        view.forwardPage(this,request,response,"/matriculant.jsp");
                    }
                    break;
                case "check":
                    matriculantDTOisExist = MatriculantDAO.findMatriculant(instructorDTO,matriculantDTO);
                    if(matriculantDTOisExist==null){
                        view.notMatriculantExists(request, matriculantDTO);
                    }   else {
                        view.isMatriculantExists(request,matriculantDTOisExist);
                    }

                    view.forwardPage(this,request,response,"/matriculant.jsp");
                    break;
                case "del":
                    MatriculantDAO.removeMatriculant(instructorDTO,matriculantDTO);
                    view.deleteMatriculant(request, matriculantDTO);
                    view.forwardPage(this,request,response,"/matriculant.jsp");
                    break;
                case"edit":
                    matriculantDTO =  MatriculantDAO.editMatriculant(instructorDTO,matriculantDTO);
                    view.editNewMatriculant(request,matriculantDTO);
                    view.forwardPage(this,request,response,"/matriculant.jsp");
                    break;
                case "exit":
                    request.getSession().removeAttribute("isLogin");
                    request.getSession().removeAttribute("DTO");
                    view.redirPage(response,"/index.jsp");
                    break;
            }


        } catch(Exception e){
            view.printErrorPage(request,response);
        }
    }
}
