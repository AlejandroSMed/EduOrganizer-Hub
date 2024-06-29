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
public class DataSubjectHandle {

    private Integer id;
    private String teacherID;
    private String subjectCode;
    private String subject;
    private Date insertData;
    private String status;

    public DataSubjectHandle(Integer id, String teacherID, String subjectCode,
            String subject, Date insertData, String status) {
        this.id = id;
        this.teacherID = teacherID;
        this.subjectCode = subjectCode;
        this.subject = subject;
        this.insertData = insertData;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }
    
    public String getTeacherID() {
        return teacherID;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public String getSubject() {
        return subject;
    }

    public Date getInsertData() {
        return insertData;
    }

    public String getStatus() {
        return status;
    }

}