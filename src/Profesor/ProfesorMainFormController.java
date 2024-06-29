/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Profesor;

import BaseDatos.ListData;
import BaseDatos.Database;
import Login.MensajeAlerta;

import Estudiante.StudentData;

import java.net.URL;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.*;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Alejandro Sanz Mediavilla
 */
public class ProfesorMainFormController implements Initializable {

    @FXML
    private AnchorPane main_form;

    @FXML
    private Label greet_name;

    @FXML
    private Label teacher_id;

    @FXML
    private Button home_btn;
    
    @FXML
    private Button addStudent_btn;

    @FXML
    private Button subjectHandle_btn;

    @FXML
    private Button logout_btn;

    @FXML
    private Label current_form;

    @FXML
    private AnchorPane addStudents_form;

    @FXML
    private ComboBox<String> addStudents_course;

    @FXML
    private ComboBox<String> addStudents_year;

    @FXML
    private Label addStudents_label_fullName;

    @FXML
    private Label addStudents_label_gender;

    @FXML
    private ComboBox<String> addStudents_studentID;

    @FXML
    private Label addStudents_label_semester;

    @FXML
    private Label addStudents_label_year;

    @FXML
    private Label addStudents_label_course;

    @FXML
    private Button addStudents_addBtn;

    @FXML
    private Button addStudents_clearBtn;

    @FXML
    private Button addStudents_removeBtn;

    @FXML
    private TableView<DataStudentHandle> addStudents_tableView;

    @FXML
    private TableColumn<DataStudentHandle, String> addStudents_col_studentID;

    @FXML
    private TableColumn<DataStudentHandle, String> addStudents_col_name;

    @FXML
    private TableColumn<DataStudentHandle, String> addStudents_col_gender;

    @FXML
    private TableColumn<DataStudentHandle, String> addStudents_col_course;

    @FXML
    private TableColumn<DataStudentHandle, String> addStudents_col_year;

    @FXML
    private TableColumn<DataStudentHandle, String> addStudents_col_semester;

    @FXML
    private TableColumn<DataStudentHandle, String> addStudents_col_date;

    @FXML
    private AnchorPane home_form;
    
    @FXML
    private Label home_totalEnrolled;

    @FXML
    private Label home_totalMujeres;

    @FXML
    private Label home_totalMale;

    @FXML
    private BarChart<?, ?> home_totalEnrolledChart;

    @FXML
    private AreaChart<?, ?> home_totalFemaleChart;

    @FXML
    private LineChart<?, ?> home_totalMaleChart;
    
    @FXML
    private AnchorPane subjectHandle_form;

    @FXML
    private ComboBox<String> subjecthandle_subject;

    @FXML
    private Button subjecthandle_addBtn;

    @FXML
    private Button subjecthandle_clearBtn;

    @FXML
    private Button subjecthandle_removeBtn;

    @FXML
    private ComboBox<String> subjecthandle_code;

    @FXML
    private ComboBox<String> subjecthandle_status;

    @FXML
    private TableView<DataSubjectHandle> subjecthandle_tableView;

    @FXML
    private TableColumn<DataSubjectHandle, String> subjecthandle_col_subjectCode;

    @FXML
    private TableColumn<DataSubjectHandle, String> subjecthandle_col_subjectName;

    @FXML
    private TableColumn<DataSubjectHandle, String> subjecthandle_col_dateInsert;

    @FXML
    private TableColumn<DataSubjectHandle, String> subjecthandle_col_status;
    
    //Formulario notas estudiante
    @FXML
    private AnchorPane notasEstudiante_form;
    
    @FXML
    private Button notasEstudiante_btn;
    
    @FXML
    private TextField studentGrade_studentNum;
    
    @FXML
    private Label studentGrade_year;

    @FXML
    private Label studentGrade_course;
    
    @FXML
    private TextField studentGrade_primerTrim;

    @FXML
    private TextField studentGrade_segundoTrim;

    @FXML
    private Button studentGrade_updateBtn;

    @FXML
    private Button studentGrade_clearBtn;

    @FXML
    private TableView<DataStudentHandle> studentGrade_tableView;

    @FXML
    private TableColumn<DataStudentHandle, String> studentGrade_col_studentNum;

    @FXML
    private TableColumn<DataStudentHandle, String> studentGrade_col_year;

    @FXML
    private TableColumn<DataStudentHandle, String> studentGrade_col_course;

    @FXML
    private TableColumn<DataStudentHandle, String> studentGrade_col_primerTrim;

    @FXML
    private TableColumn<DataStudentHandle, String> studentGrade_col_segundoTrim;

    @FXML
    private TableColumn<DataStudentHandle, String> studentGrade_col_final;

    //Buscadores
    @FXML
    private TextField studentGrade_search;
    
    @FXML
    private TextField estudiantesProfe_search;
    
    @FXML
    private TextField asignaturasProfe_search;
    

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;

    private MensajeAlerta alert = new MensajeAlerta();

    //BOTON HOME
    
    /*
    homeDisplayTotalEnrolledStudents
    Muestra en el primer display todos los estudiantes asignados al profesor (número)
    */
    public void homeDisplayTotalEnrolledStudents(){
        
        //String sql = "SELECT COUNT(id) AS total FROM student WHERE date_delete IS NULL";
        String sql = "SELECT COUNT(stud_studentID) AS total FROM teacher_student WHERE teacher_id = '"
                + teacher_id.getText() + "' AND date_delete IS NULL AND status = 'Activo'";


        connect = Database.connectDB();

        int countEnrolled = 0;

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                countEnrolled = result.getInt("total");
            }

            home_totalEnrolled.setText("" + countEnrolled);
            

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*
    homeDisplayFemaleEnrolled
    Muestra en el 2º gráfico los estudiantes chicas asignados al profesor
    */
    public void homeDisplayFemaleEnrolled() {

        String sql = "SELECT COUNT(stud_studentID) AS total FROM teacher_student WHERE teacher_id = '"
                + teacher_id.getText() + "' AND date_delete IS NULL AND status = 'Activo' AND stud_gender = 'Femenino'";

        connect = Database.connectDB();
        int countFemale = 0;
        try {
            

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                countFemale = result.getInt("total");
            }

            home_totalMujeres.setText(String.valueOf(countFemale));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*
    homeDisplayMaleEnrolled
    Muestra en el 3er gráfico los estudiantes chicos asignados al profesor
    */
    public void homeDisplayMaleEnrolled() {

        String sql = "SELECT COUNT(stud_studentID) AS total FROM teacher_student WHERE teacher_id = '"
                + teacher_id.getText() + "' AND date_delete IS NULL AND status = 'Activo' AND stud_gender = 'Masculino'";
        
        connect = Database.connectDB();
        int countMale = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                countMale = result.getInt("total");
            }
            home_totalMale.setText(String.valueOf(countMale));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    /*
    homeDisplayTotalEnrolledChart
    Primer gráfico. Muestra los estudiantes matriculados en cada fecha al profesor asignado
    */
     public void homeDisplayTotalEnrolledChart() {

        home_totalEnrolledChart.getData().clear();

        String sql = "SELECT date_insert, COUNT(id) FROM teacher_student WHERE teacher_id = '"
                + teacher_id.getText() + "' AND date_delete IS NULL AND status = 'Activo' GROUP BY TIMESTAMP(date_insert) ASC LIMIT 10";

        connect = Database.connectDB();

        try {
            XYChart.Series chart = new XYChart.Series();

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));
            }

            home_totalEnrolledChart.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*
    homeDisplayFemaleEnrolledChart
     2ª gráfica
     Muestra los estudiantes femeninos matriculados
    */
    public void homeDisplayFemaleEnrolledChart() {

        home_totalFemaleChart.getData().clear();

        //String sql = "SELECT date_insert, COUNT(id) FROM teacher WHERE date_delete IS NULL GROUP BY TIMESTAMP(date_insert) ORDER BY DATE(date_insert) ASC LIMIT 10";
        String sql = "SELECT date_insert, COUNT(id) FROM teacher_student WHERE teacher_id = '"
                + teacher_id.getText() + "' AND date_delete IS NULL AND status = 'Activo' AND stud_gender = 'Femenino' GROUP BY TIMESTAMP(date_insert) ASC LIMIT 10";
        
        connect = Database.connectDB();

        try {
            XYChart.Series chart = new XYChart.Series();

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));
            }

            home_totalFemaleChart.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*
    homeDisplayEnrolledMaleChart
    3ª gráfica
    Muestra el número de estudiantes masculinos asignados al profesor
    */
    public void homeDisplayEnrolledMaleChart() {

        home_totalMaleChart.getData().clear();

        String sql = "SELECT date_insert, COUNT(id) FROM teacher_student WHERE teacher_id = '"
                + teacher_id.getText() + "' AND date_delete IS NULL AND status = 'Activo' AND stud_gender = 'Masculino' GROUP BY TIMESTAMP(date_insert) ASC LIMIT 10";

        connect = Database.connectDB();

        try {
            XYChart.Series chart = new XYChart.Series();

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));
            }

            home_totalMaleChart.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    
    //BOTÓN ADMINISTRAR ESTUDIANTES
    
    /*
    addStudentsAddBtn
    Se encarga de agregar un nuevo estudiante a la base de datos, a la tabla teacher_student
    Asigna el estudiante al profesor y lo muestra en la tabla
    */
    
    public void addStudentsAddBtn() {

        if (addStudents_course.getSelectionModel().getSelectedItem() == null
                || addStudents_year.getSelectionModel().getSelectedItem() == null
                || addStudents_studentID.getSelectionModel().getSelectedItem() == null) {
            alert.errorMessage("Por favor, completa todos los campos de los desplegables");
        } else {

            String studentID = addStudents_studentID.getSelectionModel().getSelectedItem();

            // Verificar si el estudiante ya ha sido agregado
            boolean isStudentAdded = addStudentGetData.stream()
                    .anyMatch(student -> student.getStudentID().equals(studentID));

            if (isStudentAdded) {
                alert.errorMessage("No se puede añadir el estudiante. Este estudiante ya está asignado al profesor.");
                return;
            }

            String insertData = "INSERT INTO teacher_student "
                    + "(teacher_id, stud_studentID, stud_name, stud_gender"
                    + ", stud_year, stud_course, stud_semester"
                    + ", date_insert, status) VALUES(?,?,?,?,?,?,?,?,?)";
            connect = Database.connectDB();

            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            try {
                prepare = connect.prepareStatement(insertData);
                prepare.setString(1, teacher_id.getText());
                prepare.setString(2, addStudents_studentID.getSelectionModel().getSelectedItem());
                prepare.setString(3, addStudents_label_fullName.getText());
                prepare.setString(4, addStudents_label_gender.getText());
                prepare.setString(5, addStudents_year.getSelectionModel().getSelectedItem());
                prepare.setString(6, addStudents_course.getSelectionModel().getSelectedItem());
                prepare.setString(7, addStudents_label_semester.getText());
                prepare.setString(8, String.valueOf(sqlDate));
                prepare.setString(9, "Activo");

                prepare.executeUpdate();

                addStudentDisplayData();

                alert.successMessage("¡Estudiante asignado correctamente!");

                addStudentClearBtn();

                // Remover al estudiante del ComboBox
                addStudents_studentID.getItems().remove(studentID);

            } catch (SQLException e) {
                alert.errorMessage("Error al agregar estudiante: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
    
    

    /*
    addStudentsRemoveBtn
    Al pulsar el botón Eliminar
    Elimina al estudiante de su asignación con el profesor, estableciendo una fecha de borrado y cambiando su
    estatus a Inactive
    */
    public void addStudentsRemoveBtn() {

        if (addStudents_course.getSelectionModel().getSelectedItem() == null
                || addStudents_year.getSelectionModel().getSelectedItem()== null
                || addStudents_studentID.getSelectionModel().getSelectedItem() == null) {
            alert.errorMessage("Por favor, primero selecciona un estudiante");
        } else {
            if (alert.confirmMessage("¿Seguro que quieres eliminar el ID Estudiante: "
                    + addStudents_studentID.getSelectionModel().getSelectedItem())) {
                String insertData = "UPDATE teacher_student SET date_delete = ?, status = ? "
                        + "WHERE stud_studentID = ?";
                connect = Database.connectDB();

                Date date = new Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                try {
                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, String.valueOf(sqlDate));
                    prepare.setString(2, "Inactivo");
                    prepare.setString(3, addStudents_studentID.getSelectionModel().getSelectedItem());

                    prepare.executeUpdate();

                    addStudentDisplayData();

                    alert.successMessage("Estudiante eliminado correctamente");

                    addStudentClearBtn();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                alert.errorMessage("Cancelado");
            }

        }
    }

    /*
    addStudentClearBtn
    Vacía los campos asignados al estudiante y los inicializa
    */
    public void addStudentClearBtn() {
             
        addStudents_course.getSelectionModel().clearSelection();
        addStudents_year.getSelectionModel().clearSelection();
        addStudents_studentID.getSelectionModel().clearSelection();

        addStudents_label_fullName.setText("----------");
        addStudents_label_gender.setText("----------");
        addStudents_label_semester.setText("----------");
        addStudents_label_year.setText("----------");
        addStudents_label_course.setText("----------");
    }

    /*
    addStudentListData
    Devuelve una lista observable de objetos DataStudentHandle que representan los datos de los estudiantes asociados al profesor actual
    */
    public ObservableList<DataStudentHandle> addStudentListData() {

        ObservableList<DataStudentHandle> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM teacher_student WHERE teacher_id = '"
                + teacher_id.getText() + "' AND date_delete IS NULL AND status = 'Activo'";

        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            DataStudentHandle dshData;

            while (result.next()) {
                dshData = new DataStudentHandle(result.getString("stud_studentID"),
                        result.getString("stud_name"),
                        result.getString("stud_gender"),
                        result.getString("stud_course"),
                        result.getString("stud_year"),
                        result.getString("stud_semester"),
                        result.getDouble("primer_trim"),
                        result.getDouble("segundo_trim"),
                        result.getDouble("nota_final"),
                        result.getDate("date_insert"),
                        result.getDate("date_delete"),
                        result.getString("status"));

                listData.add(dshData);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listData;
    }

    public ObservableList<DataStudentHandle> addStudentGetData;

    /*
    addStudentDisplayData
    Muestra en la tabla varios campos de los estudiantes asignados al profesor
    */
    public void addStudentDisplayData() {
        addStudentGetData = addStudentListData();

        addStudents_col_studentID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        addStudents_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        addStudents_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        addStudents_col_course.setCellValueFactory(new PropertyValueFactory<>("course"));
        addStudents_col_year.setCellValueFactory(new PropertyValueFactory<>("year"));
        addStudents_col_semester.setCellValueFactory(new PropertyValueFactory<>("semester"));
        addStudents_col_date.setCellValueFactory(new PropertyValueFactory<>("dateInsert"));

        addStudents_tableView.setItems(addStudentGetData);
    }

    /*
    addStudentSelectitem
    Llena los campos del formulario con los datos del estudiante seleccionado en la tabla
    */
    public void addStudentSelectitem() {
        DataStudentHandle dshData = addStudents_tableView.getSelectionModel().getSelectedItem();
        int num = addStudents_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        addStudents_course.getSelectionModel().select(dshData.getCourse());
        addStudents_year.getSelectionModel().select(dshData.getYear());
        addStudents_studentID.getSelectionModel().select(dshData.getStudentID());

        addStudents_label_fullName.setText(dshData.getName());
        addStudents_label_gender.setText(dshData.getGender());
        addStudents_label_semester.setText(dshData.getSemester());
        addStudents_label_year.setText(dshData.getYear());
        addStudents_label_course.setText(dshData.getCourse());
    }

    /*
    addStudentCourseList
    Muestra en el combobox de curso los cursos que no se han eliminado y que tienen status disponible
    */
    public void addStudentCourseList() {

        String sql = "SELECT * FROM course WHERE date_delete IS NULL AND status = 'Disponible'";

        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            ObservableList listData = FXCollections.observableArrayList();

            while (result.next()) {
                listData.add(result.getString("course"));
            }

            addStudents_course.setItems(listData);

        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Error al cargar la lista de cursos");
        }
    }

    /*
    addStudentsYearList
    Añade al ComboBox de años (addStudents_year) con una lista de años disponibles en ListData
    */
    public void addStudentsYearList() {

        List<String> listY = new ArrayList<>();

        for (String data : ListData.year) {
            listY.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listY);
        addStudents_year.setItems(listData);

        addStudentsStudentNumber();

    }

    /*
    addStudentsStudentNumber
    Llena el ComboBox addStudents_studentID con los números de estudiante correspondientes al curso y año seleccionados
    */
    public void addStudentsStudentNumber() {

        String sql = "SELECT * FROM student WHERE course = '"
                + addStudents_course.getSelectionModel().getSelectedItem() + "' AND year = '"
                + addStudents_year.getSelectionModel().getSelectedItem() + "' AND date_delete IS NULL";

        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            ObservableList listData = FXCollections.observableArrayList();

            while (result.next()) {
                listData.add(result.getString("student_id"));
            }

            addStudents_studentID.setItems(listData);

            addStudentsDisplayFields();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    addStudentDisplayFields
    Actualiza los campos de visualización de información del estudiante en la interfaz gráfica.
    Actualiza los campos de visualización de nombre completo, género, semestre, año y curso con la información obtenida de la base de datos.
    */
    public void addStudentsDisplayFields() {

        String sql = "SELECT * FROM student WHERE student_id = '"
                + addStudents_studentID.getSelectionModel().getSelectedItem() + "'";

        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                addStudents_label_fullName.setText(result.getString("full_name"));
                addStudents_label_gender.setText(result.getString("gender"));
                addStudents_label_semester.setText(result.getString("semester"));
                addStudents_label_year.setText(result.getString("year"));
                addStudents_label_course.setText(result.getString("course"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    
    /*
    estudiantesProfesorBuscar
    Buscador de estudiantes, que muestra resultados en la tabla
    */
    public void estudiantesProfesorBuscar() {

        FilteredList<DataStudentHandle> filter = new FilteredList<>(addStudentGetData, e -> true);

        estudiantesProfe_search.textProperty().addListener((Observable, oldValue, newValue) -> {

            filter.setPredicate(predicateStudentData -> {

                if (newValue.isEmpty() || newValue == null) {
                    return true;
                }
                String searchKey = newValue.toLowerCase();

                if (predicateStudentData.getStudentID().toString().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getName().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getGender().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getCourse().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getYear().toString().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getSemester().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getDateInsert().toString().contains(searchKey)) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<DataStudentHandle> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(addStudents_tableView.comparatorProperty());
        addStudents_tableView.setItems(sortList);

    }
    
    //ASIGNATURAS
    
    /*
    subjectHandleAddBtn
    Agrega una nueva entrada a la base de datos en la tabla teacher_handle
    Permite asociar la asignatura y el profesor
    */
    
    /*
    subjectHandleAddBtn
    Agrega una nueva entrada a la base de datos en la tabla teacher_handle
    Permite asociar la asignatura y el profesor
    */
    public void subjectHandleAddBtn() {

        if (subjecthandle_code.getSelectionModel().getSelectedItem() == null
                || subjecthandle_subject.getSelectionModel().getSelectedItem() == null
                || subjecthandle_status.getSelectionModel().getSelectedItem() == null) {
            alert.errorMessage("Por favor, completa todos los campos");
        } else {
            // Verificar si la asignatura ya está asignada al profesor
            if (isSubjectAssigned(subjecthandle_code.getSelectionModel().getSelectedItem())) {
                alert.errorMessage("La asignatura ya está asignada al profesor");
                return;
            }

            String insertData = "INSERT INTO teacher_handle (teacher_id, subject_code, subject, date, status) "
                    + "VALUES(?,?,?,?,?)";
            connect = Database.connectDB();

            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            try {
                prepare = connect.prepareStatement(insertData);
                prepare.setString(1, teacher_id.getText());
                prepare.setString(2, subjecthandle_code.getSelectionModel().getSelectedItem());
                prepare.setString(3, subjecthandle_subject.getSelectionModel().getSelectedItem());
                prepare.setString(4, String.valueOf(sqlDate));
                prepare.setString(5, subjecthandle_status.getSelectionModel().getSelectedItem());

                prepare.executeUpdate();

                subjectHandleDisplayData();

                alert.successMessage("Asignatura asignada correctamente");

                subjectHandleClearBtn();

            } catch (SQLException e) {
                alert.errorMessage("Error al asignar la asignatura: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    /*
    isSubjectAssigned
    Verifica si la asignatura ya está asignada al profesor
    */
    private boolean isSubjectAssigned(String subjectCode) {
        String sql = "SELECT * FROM teacher_handle WHERE teacher_id = ? AND subject_code = ?";
        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, teacher_id.getText());
            prepare.setString(2, subjectCode);
            result = prepare.executeQuery();

            return result.next(); // Si hay resultados, la asignatura ya está asignada
        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Error al verificar la asignatura: " + e.getMessage());
            return true; // Por seguridad, retornamos true en caso de error
        }
    }

  
    /*
    subjectHandleEditBtn
    Edita la asignatura seleccionada
    */
    public void subjectHandleEditBtn() {
        DataSubjectHandle selectedData = subjecthandle_tableView.getSelectionModel().getSelectedItem();

        if (selectedData == null) {
            alert.errorMessage("Por favor, selecciona una asignatura para editar");
            return;
        }

        if (subjecthandle_code.getSelectionModel().getSelectedItem() == null
                || subjecthandle_subject.getSelectionModel().getSelectedItem() == null
                || subjecthandle_status.getSelectionModel().getSelectedItem() == null) {
            alert.errorMessage("Por favor, completa todos los campos");
        } else {
            String updateData = "UPDATE teacher_handle SET teacher_id = ?, subject_code = ?, subject = ?, date = ?, status = ? "
                    + "WHERE id = ?";
            connect = Database.connectDB();

            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            try {
                prepare = connect.prepareStatement(updateData);
                prepare.setString(1, teacher_id.getText());
                prepare.setString(2, subjecthandle_code.getSelectionModel().getSelectedItem());
                prepare.setString(3, subjecthandle_subject.getSelectionModel().getSelectedItem());
                prepare.setString(4, String.valueOf(sqlDate));
                prepare.setString(5, subjecthandle_status.getSelectionModel().getSelectedItem());
                prepare.setInt(6, selectedData.getId());

                prepare.executeUpdate();

                subjectHandleDisplayData();

                alert.successMessage("Asignatura editada correctamente");

                subjectHandleClearBtn();

            } catch (SQLException e) {
                alert.errorMessage("Error al editar la asignatura: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
    
    
    /*
    subjectHandleRemoveBtn
    Elimina una asignatura asociada a un profesor de la base de datos, de teacher_handle
    En este caso sí se elimina de la BD
    */
    public void subjectHandleRemoveBtn() {

        if (subjecthandle_code.getSelectionModel().getSelectedItem() == null
                || subjecthandle_subject.getSelectionModel().getSelectedItem() == null
                || subjecthandle_status.getSelectionModel().getSelectedItem() == null) {
            alert.errorMessage("Por favor, primero selecciona una asignatura");
        } else {

            if (alert.confirmMessage("¿Seguro que quieres eliminar el la asignatura con código: "
                    + subjecthandle_code.getSelectionModel().getSelectedItem() + "?")) {
                String deleteData = "DELETE FROM teacher_handle WHERE subject_code = '"
                        + subjecthandle_code.getSelectionModel().getSelectedItem() + "'";
                connect = Database.connectDB();

                try {
                    prepare = connect.prepareStatement(deleteData);

                    prepare.executeUpdate();

                    subjectHandleDisplayData();

                    alert.successMessage("Se ha eliminado correctamente la asignatura asignada");

                    subjectHandleClearBtn();

                } catch (SQLException e) {
                    e.printStackTrace();
                    alert.errorMessage("Error al eliminar la asignatura. Por favor, inténtelo de nuevo.");
                }
            } else {
                alert.errorMessage("Cancelado");
            }

        }
    }

    /*
    subjectHandleClearBtn
    Vacía los campos completados. Permite limpiarlos
    */
    public void subjectHandleClearBtn() {
        subjecthandle_code.getSelectionModel().clearSelection();
        subjecthandle_subject.getSelectionModel().clearSelection();
        subjecthandle_status.getSelectionModel().clearSelection();
        // También limpiamos el campo de código de asignatura
        subjecthandle_code.getEditor().clear();
    }

    /*
    subjectHandleGetData
    Recupera datos de la tabla teacher_handle de la base de datos y los devuelve como una lista de objetos DataSubjectHandle
        - Recupera todos los registros de la tabla teacher_handle.
        - Convierte cada registro en un objeto DataSubjectHandle.
        - Agrega cada objeto a una lista observable listData.
        - Retorna la lista de objetos DataSubjectHandle.
    */
    public ObservableList<DataSubjectHandle> subjectHandleGetData() {

        ObservableList<DataSubjectHandle> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM teacher_handle WHERE teacher_id = '"
                + teacher_id.getText() + "'";

        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            DataSubjectHandle dshData;

            while (result.next()) {
                dshData = new DataSubjectHandle(result.getInt("id"),
                        result.getString("teacher_id"),
                        result.getString("subject_code"),
                        result.getString("subject"),
                        result.getDate("date"), 
                        result.getString("status"));

                listData.add(dshData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Error al recuperar datos de asignaturas. Por favor, inténtelo de nuevo.");
        }
        return listData;
    }

    private ObservableList<DataSubjectHandle> subjectHandleListData;
    
    /*
    subjectHandleDisplayData
    Muestra los datos recuperados de la base de datos en un TableView
    */
    public void subjectHandleDisplayData() {
        subjectHandleListData = subjectHandleGetData();

        subjecthandle_col_subjectCode.setCellValueFactory(new PropertyValueFactory<>("subjectCode"));
        subjecthandle_col_subjectName.setCellValueFactory(new PropertyValueFactory<>("subject"));
        subjecthandle_col_dateInsert.setCellValueFactory(new PropertyValueFactory<>("insertData"));
        subjecthandle_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        subjecthandle_tableView.setItems(subjectHandleListData);
    }

    /*
    subjectHandleSelectItem
    Selecciona los elementos de las listas desplegables en la interfaz gráfica de usuario de acuerdo con la fila seleccionada en un TableView
    */
    public void subjectHandleSelectItem() {

        DataSubjectHandle dshData = subjecthandle_tableView.getSelectionModel().getSelectedItem();
        int num = subjecthandle_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        subjecthandle_code.getSelectionModel().select(dshData.getSubjectCode());
        subjecthandle_subject.getSelectionModel().select(dshData.getSubject());
        subjecthandle_status.getSelectionModel().select(dshData.getStatus());

    }

    /*
    subjectHandleSubjectCodeList
    Recupera la lista de códigos de asignatura de la base de datos y la muestra en un ComboBox subjecthandle_code
    Se deben seleccionar solo los registros donde:
        -En la tabla subject, el campo date_delete es nulo.
        -En la tabla subject, el campo status tiene el valor "Disponible".
        -En la tabla subject_handle, no existe ese código de asignatura
    De esta forma, cada asignatura tiene SOLO UN PROFESOR
    */
    public void subjectHandleSubjectCodeList() {
       //String sql = "SELECT * FROM subject WHERE date_delete IS NULL AND status = 'Disponible'";
       String sql = "SELECT s.subject_code FROM subject s " +
             "LEFT JOIN teacher_handle th ON s.subject_code = th.subject_code " +
             "WHERE s.date_delete IS NULL " +
             "AND s.status = 'Disponible' " +
             "AND th.subject_code IS NULL";
       
        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            ObservableList listData = FXCollections.observableArrayList();

            while (result.next()) {
                listData.add(result.getString("subject_code"));
            }

            subjecthandle_code.setItems(listData);

            subjectHandleSubjectList();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
    subjectHandleSubjectList
    Realiza una consulta a la base de datos para obtener los nombres de las asignaturas que cumplen con ciertas condiciones
    
    Sselecciona los nombres de las asignaturas de la tabla subject que no han sido eliminadas (date_delete IS NULL) y cuyo código de asignatura (subject_code) 
    coincide con el seleccionado en el ComboBox de códigos de asignatura (subjecthandle_code
    */
    public void subjectHandleSubjectList() {
        String sql = "SELECT * FROM subject WHERE date_delete IS NULL AND status='Disponible' AND "
                + "subject_code = '"
                + subjecthandle_code.getSelectionModel().getSelectedItem() + "'";

        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            ObservableList listData = FXCollections.observableArrayList();

            while (result.next()) {
                listData.add(result.getString("subject"));
            }

            subjecthandle_subject.setItems(listData);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
    subjectHandleStatusList
    Recupera el estatus de las asignaturas (disponible o no disponible)
    */
    public void subjectHandleStatusList() {

        List<String> listS = new ArrayList<>();

        for (String data : ListData.status) {
            listS.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listS);
        subjecthandle_status.setItems(listData);

    }
    
    /*
    asignaturasProfesorBuscar
    Buscador de asignaturas, que muestra resultados en la tabla
    */
    public void asignaturasProfesorBuscar() {

        FilteredList<DataSubjectHandle> filter = new FilteredList<>(subjectHandleListData, e -> true);

        asignaturasProfe_search.textProperty().addListener((Observable, oldValue, newValue) -> {

            filter.setPredicate(predicateStudentData -> {

                if (newValue.isEmpty() || newValue == null) {
                    return true;
                }
                String searchKey = newValue.toLowerCase();

                if (predicateStudentData.getSubjectCode().toString().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getSubject().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getInsertData().toString().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getStatus().toLowerCase().contains(searchKey)) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<DataSubjectHandle> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(subjecthandle_tableView.comparatorProperty());
        subjecthandle_tableView.setItems(sortList);

    }
    
    //////////////////////////////////////
    //PANTALLA NOTAS ESTUDIANTES
    
    /*
    studentGradesUpdate
    Actualiza las calificaciones de un estudiante en la base de datos y refleja los cambios en una tabla de visualización
    */
    public void studentGradesUpdate() {
        double primerTrim = 0;
        double segundoTrim = 0;

        // Verificar si se ha ingresado una calificación para el primer trimestre
        if (!studentGrade_primerTrim.getText().isEmpty()) {
            primerTrim = Double.parseDouble(studentGrade_primerTrim.getText());
        }

        // Verificar si se ha ingresado una calificación para el segundo trimestre
        if (!studentGrade_segundoTrim.getText().isEmpty()) {
            segundoTrim = Double.parseDouble(studentGrade_segundoTrim.getText());
        }

        // Calcular la nota final
        double finalResult = 0;

        // Si solo se ha ingresado una calificación, establecer la nota final en 0
        if (primerTrim == 0 || segundoTrim == 0) {
            finalResult = 0;
        } else { // Calcular la media de las calificaciones
            finalResult = (primerTrim + segundoTrim) / 2;
        }

        // Construir la consulta para actualizar las calificaciones en la base de datos
        String updateData = "UPDATE teacher_student SET "
                + " stud_year = '" + studentGrade_year.getText()
                + "', stud_course = '" + studentGrade_course.getText()
                + "', primer_trim = '" + primerTrim
                + "', segundo_trim = '" + segundoTrim
                + "', nota_final = '" + finalResult + "' WHERE stud_studentID = '"
                + studentGrade_studentNum.getText() + "'";

        MensajeAlerta alert = new MensajeAlerta();

        if (studentGrade_studentNum.getText().isEmpty()
                || studentGrade_year.getText().isEmpty()
                || studentGrade_course.getText().isEmpty()) {

            alert.errorMessage("Por favor, primero selecciona un estudiante");

        } else {

            if (alert.confirmMessage("¿Estás seguro de que quieres editar la calificación del estudiante con ID: " + studentGrade_studentNum.getText() + "?")) {
                connect = Database.connectDB();

                try {
                    statement = connect.createStatement();
                    statement.executeUpdate(updateData);

                    alert.successMessage("¡Calificación actualizada correctamente!");

                    // ACTUALIZAR EL TABLEVIEW
                    studentGradesShowListData();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        connect.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                return;
            }
        }
    }

    
     /*
    studentGradesClearBtn
    Vacía los campos asignados al estudiante y los inicializa
    */
    public void notasEstudiantesLimpiar() {
        studentGrade_studentNum.setText("");
        studentGrade_year.setText("");
        studentGrade_course.setText("");
        studentGrade_primerTrim.setText("");
        studentGrade_segundoTrim.setText("");
    }
    
    /*
    studentGradesListData
    Recupera los datos de los estudiantes de la base de datos y los devuelve en una lista observable, 
    lo que permite que los cambios en la lista se reflejen automáticamente en la interfaz de usuario
    */
    public ObservableList<DataStudentHandle> studentGradesListData() {
    ObservableList<DataStudentHandle> listData = FXCollections.observableArrayList();

    String sql = "SELECT * FROM teacher_student WHERE teacher_id= '"
        + teacher_id.getText() + "' AND date_delete IS NULL and status = 'Activo'";


    connect = Database.connectDB();

    try {  
        prepare = connect.prepareStatement(sql);
        result = prepare.executeQuery();
        DataStudentHandle studentD;

        while (result.next()) {
            studentD = new DataStudentHandle(result.getString("stud_studentID"),
                    result.getString("stud_name"),
                    result.getString("stud_gender"),
                    result.getString("stud_course"),
                    result.getString("stud_year"),
                    result.getString("stud_semester"),
                    result.getDouble("primer_trim"),
                    result.getDouble("segundo_trim"),
                    result.getDouble("nota_final"),
                    result.getDate("date_insert"),
                    result.getDate("date_delete"),
                    result.getString("status"));

            listData.add(studentD);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return listData;
}


    private ObservableList<DataStudentHandle> studentGradesList;

    /*
    studentGradesShowListData
    Completa los datos que se visualizan en la tabla
    */
    
    public void studentGradesShowListData() {
    
    studentGradesList = studentGradesListData();

    studentGrade_col_studentNum.setCellValueFactory(new PropertyValueFactory<>("studentID"));
    studentGrade_col_year.setCellValueFactory(new PropertyValueFactory<>("year"));
    studentGrade_col_course.setCellValueFactory(new PropertyValueFactory<>("course"));
    studentGrade_col_primerTrim.setCellValueFactory(new PropertyValueFactory<>("primer_trim"));
    studentGrade_col_segundoTrim.setCellValueFactory(new PropertyValueFactory<>("segundo_trim"));
    studentGrade_col_final.setCellValueFactory(new PropertyValueFactory<>("nota_final"));


    studentGrade_tableView.setItems(studentGradesList);

    }
    

    /*
    studentGradesSelect
    Se ejecuta cuando se selecciona un elemento en el TableView de calificaciones de estudiantes
    Muestra los detalles del estudiante seleccionado en los campos correspondientes de la interfaz de usuario.
    */
    public void studentGradesSelect() {

        DataStudentHandle studentD = studentGrade_tableView.getSelectionModel().getSelectedItem();
        int num = studentGrade_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        studentGrade_studentNum.setText(String.valueOf(studentD.getStudentID()));
        studentGrade_year.setText(studentD.getYear());
        studentGrade_course.setText(studentD.getCourse());
        studentGrade_primerTrim.setText(String.valueOf(studentD.getPrimer_trim()));
        studentGrade_segundoTrim.setText(String.valueOf(studentD.getSegundo_trim()));
    }


    /*
    studentGradesSearch
    Buscador de estudiantes, que muestra resultados en la tabla
    */
    public void studentGradesSearch() {

        FilteredList<DataStudentHandle> filter = new FilteredList<>(studentGradesList, e -> true);

        studentGrade_search.textProperty().addListener((Observable, oldValue, newValue) -> {

            filter.setPredicate(predicateStudentData -> {

                if (newValue.isEmpty() || newValue == null) {
                    return true;
                }
                String searchKey = newValue.toLowerCase();

                if (predicateStudentData.getStudentID().toString().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getYear().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getCourse().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getPrimer_trim().toString().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getSegundo_trim().toString().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getNota_final().toString().contains(searchKey)) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<DataStudentHandle> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(studentGrade_tableView.comparatorProperty());
        studentGrade_tableView.setItems(sortList);

    }

    /*
    switchForm
    Dependiendo de la acción, nos lleva a una vista o a otra
    */
    public void switchForm(ActionEvent event) {

        if (event.getSource() == home_btn) {
            home_form.setVisible(true);
            addStudents_form.setVisible(false);
            subjectHandle_form.setVisible(false);
            notasEstudiante_form.setVisible(false);

            homeDisplayTotalEnrolledStudents();
            homeDisplayMaleEnrolled();
            homeDisplayFemaleEnrolled();
            homeDisplayEnrolledMaleChart();
            homeDisplayFemaleEnrolledChart();
            homeDisplayTotalEnrolledChart();
            
            current_form.setText("Estadísticas Profesor - EduOrganizer Hub");

        } else if (event.getSource() == addStudent_btn) {
            home_form.setVisible(false);
            addStudents_form.setVisible(true);
            subjectHandle_form.setVisible(false);
            notasEstudiante_form.setVisible(false);

            addStudentCourseList();
            addStudentsYearList();
            addStudentDisplayData();
            estudiantesProfesorBuscar();

            current_form.setText("Formulario de Gestión de Estudiantes - EduOrganizer Hub");
            
        } else if (event.getSource() == subjectHandle_btn) {
            home_form.setVisible(false);
            addStudents_form.setVisible(false);
            subjectHandle_form.setVisible(true);
            notasEstudiante_form.setVisible(false);

            subjectHandleSubjectCodeList();
            subjectHandleStatusList();
            subjectHandleDisplayData();
            asignaturasProfesorBuscar();

            current_form.setText("Formulario de Gestión de Asignaturas - EduOrganizer Hub");
        
        } else if (event.getSource() == notasEstudiante_btn) {
            home_form.setVisible(false);
            addStudents_form.setVisible(false);
            subjectHandle_form.setVisible(false);
            notasEstudiante_form.setVisible(true);
            
            studentGradesShowListData();
            studentGradesSearch();


            current_form.setText("Formulario de Notas - EduOrganizer Hub");
        } 

    }

    /*
    logoutBtn
    Permite cerrar sesión y volver al login
    */
    public void logoutBtn() {
        try {
            if (alert.confirmMessage("¿Seguro que quieres salir?")) {
                // Muestra el formulario de login
                Parent root = FXMLLoader.load(getClass().getResource("/Vistas/LoginForm.fxml"));
                Stage stage = new Stage();

                stage.setScene(new Scene(root));
                stage.show();

                // Esconde el formulario de login
                logout_btn.getScene().getWindow().hide();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    displayTeacher
    Nos muestra el profesor que ha iniciado sesión y le da la bienvenida
    */
    public void displayTeacher() {

        String sql = "SELECT * FROM users WHERE username = '"
                + ListData.teacher_username + "'";

        connect = Database.connectDB();

        String temp_teacherName = "";
        String temp_teacherID = "";

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                temp_teacherID = result.getString("teacher_id");
            }

            String selectData = "SELECT * FROM teacher WHERE teacher_id = '"
                    + temp_teacherID + "'";

            statement = connect.createStatement();
            result = statement.executeQuery(selectData);

            if (result.next()) {
                temp_teacherName = result.getString("full_name");
            }

            greet_name.setText("Bienvenido, " + temp_teacherName);
            teacher_id.setText(temp_teacherID);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Gráficos
        homeDisplayTotalEnrolledStudents();
        homeDisplayMaleEnrolled();
        homeDisplayFemaleEnrolled();
        homeDisplayEnrolledMaleChart();
        homeDisplayFemaleEnrolledChart();
        homeDisplayTotalEnrolledChart();
        
        displayTeacher();

        addStudentCourseList();
        addStudentsYearList();
        addStudentDisplayData();


    }

}