package models;

import java.util.ArrayList;


import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: VirtBox
 * Date: 25.06.14
 * Time: 19:22
 * To change this template use File | Settings | File Templates.
 */
public class MatriculantDTO {
    String faculty;
    String name;
    String ball;
    String year;
    String comment;

    ArrayList<String> result = new ArrayList<>();

    public MatriculantDTO(String faculty, String name, String ball, String god, String comment) {
        this.faculty = faculty;
        this.name = name;
        this.ball = ball;
        this.year = god;
        this.comment = comment;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getName() {
        return name;
    }

    public String getBall() {
        return ball;
    }

    public String getYear() {
        return year;
    }

    public String getComment() {
        return comment;
    }
    public boolean isResult(String error){
        return result.contains(error);
    }
    public void addResult(String result){
        this.result.add(result);
    }

    @Override
    public String toString() {
        return "MatriculantDTO{" +
                "faculty='" + faculty + '\'' +
                ", name='" + name + '\'' +
                ", ball='" + ball + '\'' +
                ", year='" + year + '\'' +
                ", comment='" + comment + '\'' +
                ", result=" + result +
                '}';
    }
}
