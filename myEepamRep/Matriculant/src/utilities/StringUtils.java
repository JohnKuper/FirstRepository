package utilities;

import models.MatriculantDTO;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: VirtBox
 * Date: 25.06.14
 * Time: 22:53
 * To change this template use File | Settings | File Templates.
 */
public class StringUtils {
    // Конвертирует из формата iso_8859_1 в utf-8
    public static String convertISOToUTF(String valueToUTF) throws UnsupportedEncodingException {
        return new String(valueToUTF.getBytes("ISO_8859_1"),"utf-8");
    }
    // Проверяет на корректность Имя
    public static boolean isCorrectName(String name){

        boolean isErr=false;
        String[] fullFio = name.split(" ");
        if(fullFio.length < 3) {
            return false;
        }
        // Проверим на корректность фио (БеЗНосоВ иГоРь СерГеевич).
        Pattern p = Pattern.compile("([А-ЯЁ][а-яё]+[\\-\\s]?){3,}");
        Pattern p2 = Pattern.compile("([A-Z][a-z]+[\\-\\s]?){3,}");
        Matcher m = p.matcher(name);
        Matcher m2 = p2.matcher(name);
        if(!m.matches()){
            if(!m2.matches()) {
                return false;
            }
        }
        return true;
    }
    // Проверяет на корректность Баллы
    public static boolean isCorrectBall(String ballance){
        if(ballance.equals("")) return true;
        try{
            Float.parseFloat(ballance);
        }   catch (Exception e){
            return false;
        }
        return true;
    }
    // Получает карту ключ\значение
    public static Map<String, String[]> getFields(HttpServletRequest request) throws UnsupportedEncodingException {
        Map<String, String[]> map = request.getParameterMap();
        for(Map.Entry<String, String[]> entry: map.entrySet()){
            String[] buf=entry.getValue();
            for(int i=0;i<buf.length;i++){
                buf[i]= convertISOToUTF(buf[i]);
            }
        }
     return map;
    }
    // Получат комманду из формы
    public static String getCommand(HttpServletRequest request){
        return request.getParameter("command");
    }

    public static MatriculantDTO getMatriculant(Map<String, String[]> map) {
        return new MatriculantDTO(map.get("faculty")[0],map.get("matriculant")[0],map.get("ball")[0],map.get("yaers")[0],map.get("notes")[0]);
    }
}
