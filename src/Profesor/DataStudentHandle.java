/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Profesor;

import java.sql.Date;

/**
 *
 * @author Alejandro Sanz Mediavilla
 */

//Gestiona la tabla teacher_student
public class DataStudentHandle {

    private String teacherID;
    private String studentID;
    private String name;
    private String gender;
    private String course;
    private String year;
    private String semester;
    private Double primer_trim;
    private Double segundo_trim;
    private Double nota_final;
    private Date dateInsert;
    private Date dateDelete;
    private String status;

    public DataStudentHandle(String studentID, String name, String gender,
            String course, String year, String semester, Double primer_trim, Double segundo_trim, Double nota_final, Date dateInsert,
            Date dateDelete, String status) {
        this.studentID = studentID;
        this.name = name;
        this.gender = gender;
        this.course = course;
        this.year = year;
        this.semester = semester;
        this.primer_trim = primer_trim;
        this.segundo_trim = segundo_trim;
        this.nota_final = nota_final;
        this.dateInsert = dateInsert;
        this.dateDelete = dateDelete;
        this.status = status;
    }

    //
    public DataStudentHandle(String teacherID, String studentID
            , String course, String semester,  String year) {
        this.teacherID = teacherID;
        this.studentID = studentID;
        this.course = course;
        this.semester = semester;
        this.year = year;
    }
    

    
    public DataStudentHandle(String teacherID, String studentID, String year, String course,  Double primer_trim, Double segundo_trim, Double nota_final) {
        this.teacherID = teacherID;
        this.studentID = studentID;
        this.year = year;
        this.course = course;
        this.primer_trim = primer_trim;
        this.segundo_trim = segundo_trim;
        this.nota_final = nota_final;
    }

    public String getTeacherID() {
        return teacherID;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getCourse() {
        return course;
    }

    public String getYear() {
        return year;
    }

    public String getSemester() {
        return semester;
    }
    
     public Double getPrimer_trim() {
        return primer_trim;
    }

    public Double getSegundo_trim() {
        return segundo_trim;
    }

    public Double getNota_final() {
        return nota_final;
    }

    public Date getDateInsert() {
        return dateInsert;
    }

    public Date getDateDelete() {
        return dateDelete;
    }

    public String getStatus() {
        return status;
    }

}