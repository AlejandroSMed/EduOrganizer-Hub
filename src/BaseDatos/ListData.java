/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatos;

import java.sql.Date;

/**
 *
 * @author Alejandro Sanz Mediavilla
 */
public class ListData {

    public static String admin_username;
    
    public static String teacher_username;
    
    public static String student_username;
    
    public static String[] role = {"Administrador", "Profesor", "Estudiante"};

    public static String[] year = {"1er Año", "2º Año", "3er Año", "4º Año"};

    public static String[] course = {"BSCS", "BSIT", "BS"};

    public static String[] section = {"A", "B", "C", "D", "E"};

    public static String[] paymentStatus = {"Pagado", "Pendiente"};

    public static String[] status = {"Activo", "Inactivo", "Pendiente"};

    public static String[] semester = {"1er Semestre", "2º Semestre"};

    public static String[] gender = {"Masculino", "Femenino", "No binario"};

    public static String[] yearExperience = {"1 año", "2 años", "3 años", "4 años o más"};
    
    public static String[] department = {"Department of BSIT", "Department of BSCS"};
    
    public static String[] statusA = {"Disponible", "No disponible"};

    public static String path;

    public static String temp_studentNumber;
    public static String temp_studentName;
    public static Date temp_studentBirthDate;
    public static String temp_studentYear;
    public static String temp_studentCourse;
    public static String temp_studentSection;
    public static String temp_studentSubject;
    public static String temp_studentGender;
    public static String temp_studentSemester;
    public static Double temp_studentPay;
    public static String temp_studentPaymentStatus;
    public static String temp_studentStatus;

}