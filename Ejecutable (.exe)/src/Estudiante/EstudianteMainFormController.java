
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estudiante;

import Profesor.DataStudentHandle;
import BaseDatos.*;
import Login.MensajeAlerta;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.collections.*;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Alejandro Sanz Mediavilla
 */
public class EstudianteMainFormController implements Initializable {
    
    @FXML
    private Label student_id;
    
    @FXML
    private Label greet_name;
    
    //Botonera
    @FXML
    private Button home_btn;
    
    @FXML
    private Button studentInformation_btn;
    
    @FXML
    private Button notasInformacion_btn;
    
    @FXML
    private Button logout_btn;
    
    //Pantallas
    @FXML
    private AnchorPane home_form;
    
    @FXML
    private AnchorPane profesores1_form;
    
    @FXML
    private AnchorPane profesores2_form;
    
    @FXML
    private AnchorPane notasEstudiante_form;
    
    //Etiqueta superior
    @FXML
    private Label current_form;
    
    //Displays
    @FXML
    private Label home_totalMatriculadas;

    @FXML
    private Label home_totalAprobadas;

    @FXML
    private Label home_totalSuspensos;
    
    
    //Grafica
    @FXML
    private PieChart home_notasGrafica;
    
    @FXML
    private TableView<DataStudentHandle> table_view;
    
    @FXML
    private TableColumn<DataStudentHandle, String> studentInfo_col_teacherID;
    
    @FXML
    private TableColumn<DataStudentHandle, String> studentInfo_col_name;
    
    @FXML
    private TableColumn<DataStudentHandle, String> studentInfo_col_gender;
    
    @FXML
    private TableColumn<DataStudentHandle, String> studentInfo_col_YE;
    
    @FXML
    private Circle circle_image;
    
    @FXML
    private Label teacher_id;
    
    @FXML
    private Label teacher_name;
    
    @FXML
    private Label teacher_gender;
    
    @FXML
    private Label teacher_date;
    
    //Pantalla notas estudiantes
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
    
    //Formulario notas estudiantes
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
    private Label labelAprobadoSuspenso;
    
    //Buscadores notas estudiantes
    @FXML
    private TextField studentGrade_search;
    
    
    
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    
    MensajeAlerta alert = new MensajeAlerta();
    
    
    //PANTALLA GRÁFICOS
    
    /*
    homeDisplayTotalMatriculadas
    Muestra en el primer display todas las asignaturas de las que se ha matriculado el estudiante
    */
    public void homeDisplayTotalMatriculadas(){
        
        //String sql = "SELECT COUNT(id) AS total FROM student WHERE date_delete IS NULL";
        String sql = "SELECT COUNT(date_insert) AS total FROM teacher_student WHERE stud_studentID = '"
                + student_id.getText() + "' AND date_delete IS NULL AND status = 'Activo'";


        connect = Database.connectDB();

        int countEnrolled = 0;

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                countEnrolled = result.getInt("total");
            }

            home_totalMatriculadas.setText("" + countEnrolled);
            

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*
    homeDisplayAprobadas
    Muestra en el 2º display las asignaturas aprobadas por el estudiante
    */
    public void homeDisplayAprobadas() {

        String sql = "SELECT COUNT(nota_final) AS total FROM teacher_student WHERE stud_studentID = '"
                + student_id.getText() + "' AND date_delete IS NULL AND status = 'Activo' AND nota_final IS NOT NULL AND nota_final >= 5";

        connect = Database.connectDB();
        int countMatriculadas = 0;
        try {
            

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                countMatriculadas = result.getInt("total");
            }

            home_totalAprobadas.setText(String.valueOf(countMatriculadas));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*
    homeDisplaySuspensos
    Muestra en el 3er gráfico las asignaturas suspensas por el estudiante
    */
    public void homeDisplaySuspensos() {

         String sql = "SELECT COUNT(nota_final) AS total FROM teacher_student WHERE stud_studentID = '"
                + student_id.getText() + "' AND date_delete IS NULL AND status = 'Activo' AND nota_final IS NOT NULL AND nota_final < 5";
        
        connect = Database.connectDB();
        int countSuspensos = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                countSuspensos = result.getInt("total");
            }
            home_totalSuspensos.setText(String.valueOf(countSuspensos));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    
    /*
    homeDisplayNotasGrafica
    Muestra un gráfico con las notas
    */
    public void homeDisplayNotasGrafica() {
        home_notasGrafica.getData().clear();

        String sqlTotalNotas = "SELECT COUNT(nota_final) AS total FROM teacher_student WHERE stud_studentID = '"
                + student_id.getText() + "' AND date_delete IS NULL AND status = 'Activo' AND nota_final IS NOT NULL";

        String sqlNotasAprobadas = "SELECT COUNT(nota_final) AS total FROM teacher_student WHERE stud_studentID = '"
                + student_id.getText() + "' AND date_delete IS NULL AND status = 'Activo' AND nota_final IS NOT NULL AND nota_final >= 5";

        connect = Database.connectDB();

        try {
            // Obtener el total de notas
            prepare = connect.prepareStatement(sqlTotalNotas);
            result = prepare.executeQuery();

            int totalNotas = 0;
            if (result.next()) {
                totalNotas = result.getInt("total");
            }

            // Obtener el número de notas aprobadas
            prepare = connect.prepareStatement(sqlNotasAprobadas);
            result = prepare.executeQuery();

            int notasAprobadas = 0;
            if (result.next()) {
                notasAprobadas = result.getInt("total");
            }

            int notasSuspensas = totalNotas - notasAprobadas;

            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

            pieChartData.add(new PieChart.Data("Aprobadas", (double) notasAprobadas / totalNotas * 100));
            pieChartData.add(new PieChart.Data("Suspensas", (double) notasSuspensas / totalNotas * 100));

            home_notasGrafica.setData(pieChartData);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // PANTALLA PROFESORES
    
    /*
    teacherSetData
    Devuelve una lista observable de objetos DataStudentHandle que representan los datos de los profesores asociados al estudiante actual
    */
    public ObservableList<DataStudentHandle> teacherSetData() {
        
        ObservableList<DataStudentHandle> listData = FXCollections.observableArrayList();
        
        String sql = "SELECT * FROM teacher_student WHERE stud_studentID = '"
                + student_id.getText() + "' AND date_delete IS NULL AND status = 'Activo'";
        
        connect = Database.connectDB();
        
        try {
            
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            DataStudentHandle dsh;
            
            while (result.next()) {

                dsh = new DataStudentHandle(result.getString("teacher_id"),
                        result.getString("stud_studentID"),
                        result.getString("stud_course"),
                        result.getString("stud_semester"),
                        result.getString("stud_year"));
                listData.add(dsh);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listData;
    }
    
    
    private ObservableList<DataStudentHandle> teacherListData;
    
     /*
    teacherDisplay
    Muestra en la tabla varios campos de los profesores asignados al estudiante
    */
    public void teacherDisplayData() {
        teacherListData = teacherSetData();
        
        studentInfo_col_teacherID.setCellValueFactory(new PropertyValueFactory<>("teacherID"));
        studentInfo_col_name.setCellValueFactory(new PropertyValueFactory<>("course"));
        studentInfo_col_gender.setCellValueFactory(new PropertyValueFactory<>("semester"));
        studentInfo_col_YE.setCellValueFactory(new PropertyValueFactory<>("year"));
        
        table_view.setItems(teacherListData);
    }
    
    private Image image;
    
    /*
    teacherSelectData
    Llena los campos del formulario con los datos del estudiante seleccionado en la tabla
    */
    public void teacherSelectData() {
    DataStudentHandle dsh = table_view.getSelectionModel().getSelectedItem();
    
    if (dsh == null) {
        return; // No hay profesor seleccionado, no hacer nada
    }

    String teacherID = dsh.getTeacherID();
    if (teacherID == null || teacherID.isEmpty()) {
        return; // El profesor no tiene ID válido, no hacer nada
    }

    String sql = "SELECT * FROM teacher WHERE teacher_id = '" + teacherID + "'";

    connect = Database.connectDB();

    try {
        prepare = connect.prepareStatement(sql);
        result = prepare.executeQuery();

        if (result.next()) {

            String path = "File:" + result.getString("image");

            image = new Image(path, 164, 73, false, true);
            circle_image.setFill(new ImagePattern(image));

            teacher_id.setText(result.getString("teacher_id"));
            teacher_name.setText(result.getString("full_name"));
            teacher_gender.setText(result.getString("gender"));
            teacher_date.setText(result.getString("date_insert"));
        }

    } catch (SQLException e) {
        e.printStackTrace();
        }

    }

    //PANTALLA NOTAS ESTUDIANTES
    
    /*
    studentGradesListData
    Recupera los datos de los estudiantes de la base de datos y los devuelve en una lista observable, 
    lo que permite que los cambios en la lista se reflejen automáticamente en la interfaz de usuario
    */
    public ObservableList<DataStudentHandle> studentGradesListData() {
    ObservableList<DataStudentHandle> listData = FXCollections.observableArrayList();

    String sql = "SELECT * FROM teacher_student WHERE stud_studentID= '"
        + student_id.getText() + "' AND date_delete IS NULL and status = 'Activo'";


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
    Muestra un label con "Aprobado" o "Suspenso" en función de la nota de la asignatura
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

    // Mostrar "Aprobado" o "Suspenso" en el label labelAprobadoSuspenso
    StringBinding estadoNotaBinding = Bindings.createStringBinding(() -> {
        DataStudentHandle studentD = studentGrade_tableView.getSelectionModel().getSelectedItem();
        if (studentD != null) {
            double notaFinal = studentD.getNota_final();
            return (notaFinal >= 5.0) ? "Aprobado" : "Suspenso";
        } else {
            return "";
        }
    }, studentGrade_tableView.getSelectionModel().selectedIndexProperty());

    labelAprobadoSuspenso.textProperty().bind(estadoNotaBinding);

    // Cambiar el color del texto del label según el estado del estudiante
    labelAprobadoSuspenso.styleProperty().bind(Bindings.createStringBinding(() -> {
        DataStudentHandle studentD = studentGrade_tableView.getSelectionModel().getSelectedItem();
        if (studentD != null) {
            double notaFinal = studentD.getNota_final();
            return (notaFinal >= 5.0) ? "-fx-text-fill: green;" : "-fx-text-fill: red;";
        } else {
            return "-fx-text-fill: black;";
            }
        }, studentGrade_tableView.getSelectionModel().selectedIndexProperty()));
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
    imprimirNotas()
    Imprime en un fichero .jasper todas las notas del estudiante actual
    */
    public void imprimirNotas(){
        
    connect = Database.connectDB();
    
    try{
        String studentID = student_id.getText(); // Obtener el ID del estudiante actual
        
        // Consulta SQL para seleccionar las notas del estudiante actual
        String query = "SELECT teacher_student.stud_studentID AS teacher_student_stud_studentID, " +
                        "teacher_student.stud_name AS teacher_student_stud_name, " +
                        "teacher_student.stud_course AS teacher_student_stud_course, " +
                        "teacher_student.stud_semester AS teacher_student_stud_semester, " +
                        "teacher_student.primer_trim AS teacher_student_primer_trim, " +
                        "teacher_student.segundo_trim AS teacher_student_segundo_trim, " +
                        "teacher_student.nota_final AS teacher_student_nota_final " +
                        "FROM teacher_student " +
                        "WHERE teacher_student.stud_studentID = ?";
        
        // Crear una sentencia preparada
        PreparedStatement preparedStatement = connect.prepareStatement(query);
        preparedStatement.setString(1, studentID); // Establecer el ID del estudiante en la consulta
        
        // Ejecutar la consulta
        ResultSet resultSet = preparedStatement.executeQuery();
        
        // Cargar el diseño del informe Jasper desde el archivo .jrxml
        JasperDesign jDesign = JRXmlLoader.load("C:\\Users\\alex_\\OneDrive\\Documentos\\NetBeansProjects\\EduOrganizerHub\\src\\Informes\\notasEstudiante.jrxml");
        
        // Compilar el informe Jasper
        JasperReport jReport = JasperCompileManager.compileReport(jDesign);
        
        // Llenar el informe Jasper con los datos del ResultSet
        JasperPrint jPrint = JasperFillManager.fillReport(jReport, null, new JRResultSetDataSource(resultSet));
        
        // Mostrar el informe Jasper en un visor
        JasperViewer jViewer = new JasperViewer(jPrint, false);
        jViewer.setTitle("Notas del curso 2023/2024 | EduOrganizer Hub");
        jViewer.show();

    } catch(Exception e){
        e.printStackTrace();
    } finally {
        try {
            if (connect != null) {
                connect.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }
    
    /*
    studentIDDisplay
    Muestra el mensaje "Bienvenido..." y el nombre del estudiante e
    ID de acceso
    */
    public void studentIDDisplay() {
        
        String sql = "SELECT * FROM users WHERE username = '"
                + ListData.student_username + "'";
        
        connect = Database.connectDB();
        
        String temp_studentName = "";
        String temp_studentID = "";
        
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                temp_studentID = result.getString("student_id");
            }

            String selectData = "SELECT * FROM student WHERE student_id = '"
                    + temp_studentID + "'";

            statement = connect.createStatement();
            result = statement.executeQuery(selectData);

            if (result.next()) {
                temp_studentName = result.getString("full_name");
            }
            
            greet_name.setText("Bienvenido, " + temp_studentName);
            student_id.setText(temp_studentID);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    
    /*
    switchForm
    Dependiendo de la acción, nos lleva a una vista o a otra
    */
    public void switchForm(ActionEvent event) {

        if (event.getSource() == home_btn) {
            home_form.setVisible(true);
            profesores1_form.setVisible(false);
            profesores2_form.setVisible(false);
            notasEstudiante_form.setVisible(false);

            homeDisplayTotalMatriculadas();
            homeDisplaySuspensos();
            homeDisplayAprobadas();
           
            homeDisplayNotasGrafica();
            
            current_form.setText("Estadísticas Estudiante - EduOrganizer Hub");

        } else if (event.getSource() == studentInformation_btn) {
            home_form.setVisible(false);
            profesores1_form.setVisible(true);
            profesores2_form.setVisible(true);
            notasEstudiante_form.setVisible(false);
            
            teacherDisplayData();

            current_form.setText("Mis Profesores - EduOrganizer Hub");
            
        } else if (event.getSource() == notasInformacion_btn) {
            home_form.setVisible(false);
            profesores1_form.setVisible(false);
            profesores2_form.setVisible(false);
            notasEstudiante_form.setVisible(true);
            
            studentGradesShowListData();
            studentGradesSearch();

            current_form.setText("Mis Notas - EduOrganizer Hub");
            
        }

    }
    
    /*
    logoutBtn
    Botón para cerrar sesión y salir a la pantalla de login
    */
    public void logoutBtn() {
        
        try {
            if (alert.confirmMessage("¿Seguro que quieres salir?")) {
                Parent root = FXMLLoader.load(getClass().getResource("/Vistas/LoginForm.fxml"));
                
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                
                stage.setScene(scene);
                stage.show();
                
                logout_btn.getScene().getWindow().hide();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        teacherDisplayData();
        studentIDDisplay();
        
    }
    
}