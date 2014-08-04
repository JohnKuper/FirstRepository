package view;

import models.ErrorPageDTO;
import models.InstructorDTO;
import models.MatriculantDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: VirtBox
 * Date: 25.06.14
 * Time: 19:15
 * To change this template use File | Settings | File Templates.
 */
public class ViewJSP implements View {

    @Override
    public void printErrorPage(HttpServletRequest request,HttpServletResponse response) {
        try {
            new ErrorPageDTO(request, response).printErrorPage();
        } catch (ServletException e) {

        } catch (IOException e) {
        }

    }

    @Override
    public void errorLoginOrPassword(HttpServletRequest request, InstructorDTO instructorDTO){
        request.setAttribute("DTO",instructorDTO);
    }

    @Override
    public void errorIncorrectly(HttpServletRequest request, MatriculantDTO matriculantDTO) {
        matriculantDTO.addResult("errorIncorrectly");
        request.setAttribute("DTO", matriculantDTO);
    }

    @Override
    public void isMatriculantExists(HttpServletRequest request, MatriculantDTO matriculantDTO) {
        matriculantDTO.addResult("isMatriculantExists");
        request.setAttribute("DTO", matriculantDTO);
    }

    @Override
    public void notMatriculantExists(HttpServletRequest request, MatriculantDTO matriculantDTO) {
        matriculantDTO.addResult("notMatriculantExists");
        request.setAttribute("DTO", matriculantDTO);
    }

    @Override
    public void addNewMatriculant(HttpServletRequest request, MatriculantDTO matriculantDTO) {
        matriculantDTO.addResult("addNewMatriculant");
        request.setAttribute("DTO", matriculantDTO);
    }

    @Override
    public void deleteMatriculant(HttpServletRequest request, MatriculantDTO matriculantDTO) {
        matriculantDTO.addResult("deleteMatriculant");
        request.setAttribute("DTO", matriculantDTO);
    }

    @Override
    public void editNewMatriculant(HttpServletRequest request, MatriculantDTO matriculantDTO) {
        matriculantDTO.addResult("editNewMatriculant");
        request.setAttribute("DTO", matriculantDTO);
    }

    @Override
    public void redirPage(HttpServletResponse response, String url) throws IOException {
        response.sendRedirect(url);
    }

    @Override
    public void forwardPage(HttpServlet hs,HttpServletRequest request,HttpServletResponse response,String page) throws ServletException, IOException {
        hs.getServletContext().getRequestDispatcher(page).forward(request,response);
    }

    @Override
    public void forwardPage(HttpServlet hs, HttpServletRequest request, HttpServletResponse response, String page, MatriculantDTO matriculantDTO) throws ServletException, IOException {
        matriculantDTO.addResult("errorIncorrectly");
        request.setAttribute("DTO", matriculantDTO);
        hs.getServletContext().getRequestDispatcher(page).forward(request,response);
    }
}
