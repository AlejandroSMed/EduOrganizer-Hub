
package Administrador;

 /**
 *
 * @author Alejandro Sanz Mediavilla
 */

import BaseDatos.*;
import Login.*;

import java.io.File;
import java.net.URL;
import java.nio.file.*;
import java.sql.*;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.Date;

import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.image.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

import javafx.scene.chart.XYChart;
import Curso.CourseData;
import Profesor.SalaryData;
import Estudiante.StudentData;
import java.awt.event.KeyEvent;
import Asignatura.SubjectData;
import Profesor.DataStudentHandle;
import Profesor.TeacherData;
        
import java.io.IOException;
import java.time.LocalDate;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

   
public class AdminMainFormController implements Initializable {

    // Elementos de la barra de navegación y saludo
    @FXML
    private Label greet_username;

    @FXML
    private Button dashboard_btn;

    @FXML
    private Button addStudent_btn;

    @FXML
    private Button addTeacher_btn;

    @FXML
    private Button addCourse_btn;

    @FXML
    private Button addSubject_btn;

    @FXML
    private Button payment_btn;

    @FXML
    private Button salary_btn;
    
    @FXML
    private Button logout_btn;

    // Formularios de añadir estudiante, profesor, curso, asignatura y pagos
    @FXML
    private AnchorPane addStudent_form;
    
    @FXML
    private AnchorPane addTeacher_form;

    // Tabla de estudiantes
    @FXML
    private TableView<StudentData> addStudent_tableView;

    @FXML
    private TableColumn<StudentData, String> addStudent_col_studentNumber;

    @FXML
    private TableColumn<StudentData, String> addStudent_col_name;

    @FXML
    private TableColumn<StudentData, String> addStudent_col_year;

    @FXML
    private TableColumn<StudentData, String> addStudent_col_course;

    @FXML
    private TableColumn<StudentData, String> addStudent_col_section;

    @FXML
    private TableColumn<StudentData, String> addStudent_col_pay;

    @FXML
    private TableColumn<StudentData, String> addStudent_col_statusPayment;

    @FXML
    private TableColumn<StudentData, String> addStudent_col_dateInsert;

    @FXML
    private TableColumn<StudentData, String> addStudent_col_status;

    //Alta Estudiantes
    @FXML
    private Button addStudent_deleteBtn;

    @FXML
    private Button addStudent_updateBtn;
    
    @FXML
    private Button addStudent_refreshBtn;

    @FXML
    private Button addStudent_addBtn;

    // Tabla de profesores
    @FXML
    private TableView<TeacherData> addTeacher_tableView;

    @FXML
    private TableColumn<TeacherData, String> addTeacher_col_teacherID;

    @FXML
    private TableColumn<TeacherData, String> addTeacher_col_name;

    @FXML
    private TableColumn<TeacherData, String> addTeacher_col_gender;

    @FXML
    private TableColumn<TeacherData, String> addTeacher_col_yearExperience;

    @FXML
    private TableColumn<TeacherData, String> addTeacher_col_experience;

    @FXML
    private TableColumn<TeacherData, String> addTeacher_col_department;

    @FXML
    private TableColumn<TeacherData, String> addTeacher_col_salary;

    @FXML
    private TableColumn<TeacherData, String> addTeacher_col_dateInsert;

    @FXML
    private TableColumn<TeacherData, String> addTeacher_col_status;

    // Formulario de añadir profesor
    @FXML
    private TextField addTeacher_teacherID;

    @FXML
    private TextField addTeacher_fullName;

    @FXML
    private ComboBox<String> addTeacher_gender;

    @FXML
    private ComboBox<String> addTeacher_experienceYear;

    @FXML
    private TextField addTeacher_experience;

    @FXML
    private ComboBox<String> addTeacher_department;

    @FXML
    private TextField addTeacher_salary;

    @FXML
    private ComboBox<String> addTeacher_status;

    @FXML
    private ImageView addTeacher_imageView;

    @FXML
    private Button addTeacher_importBtn;

    @FXML
    private Button addTeacher_addBtn;

    @FXML
    private Button addTeacher_updateBtn;

    @FXML
    private Button addTeacher_clearBtn;

    @FXML
    private Button addTeacher_deleteBtn;

    @FXML
    private TextField addCourse_course;

    @FXML
    private TextField addCourse_department;

    @FXML
    private TextField addCourse_price;

    @FXML
    private ComboBox<String> addCourse_status;

    @FXML
    private Button addCourse_addBtn;

    @FXML
    private Button addCourse_updateBtn;

    @FXML
    private Button addCourse_clearBtn;

    @FXML
    private Button addCourse_deleteBtn;

    // Tabla de cursos
    @FXML
    private TableView<CourseData> addCourse_tableView;

    @FXML
    private TableColumn<CourseData, String> addCourse_col_course;

    @FXML
    private TableColumn<CourseData, String> addCourse_col_price;

    @FXML
    private TableColumn<CourseData, String> addCourse_col_department;

    @FXML
    private TableColumn<CourseData, String> addCourse_col_dateInsert;

    @FXML
    private TableColumn<CourseData, String> addCourse_col_status;

    // Formulario de añadir curso
    @FXML
    private AnchorPane addCourse_form;

    @FXML
    private AnchorPane addSubject_form;

    @FXML
    private TextField addSubject_code;

    @FXML
    private TextField addSubject_subject;

    @FXML
    private ComboBox<String> addSubject_course;

    @FXML
    private ComboBox<String> addSubject_status;

    @FXML
    private Button addSubject_addBtn;

    @FXML
    private Button addSubject_updateBtn;

    @FXML
    private Button addSubject_clearBtn;

    @FXML
    private Button addSubject_deleteBtn;

    // Tabla de asignaturas
    @FXML
    private TableView<SubjectData> addSubject_tableView;

    @FXML
    private TableColumn<SubjectData, String> addSubject_col_code;

    @FXML
    private TableColumn<SubjectData, String> addSubject_col_subject;

    @FXML
    private TableColumn<SubjectData, String> addSubject_col_course;

    @FXML
    private TableColumn<SubjectData, String> addSubject_col_dateInsert;

    @FXML
    private TableColumn<SubjectData, String> addSubject_col_status;

    @FXML
    private AnchorPane payment_form;

    @FXML
    private TextField payment_studentID;

    @FXML
    private TextField payment_name;

    @FXML
    private TextField payment_year;

    @FXML
    private TextField payment_section;

    @FXML
    private TextField payment_semester;

    @FXML
    private TextField payment_payment;

    @FXML
    private ComboBox<String> payment_status;
    
    @FXML
    private Button imprimir;

    @FXML
    private ComboBox<String> addTeacher_salaryStatus;

    @FXML
    private ImageView payment_imageView;

    @FXML
    private Button payment_payBtn;

    @FXML
    private Button payment_clearBtn;

    @FXML
    private TableView<StudentData> payment_tableView;

    @FXML
    private TableColumn<String, StudentData> payment_col_studentID;

    @FXML
    private TableColumn<String, StudentData> payment_col_name;

    @FXML
    private TableColumn<String, StudentData> payment_col_year;

    @FXML
    private TableColumn<String, StudentData> payment_col_section;

    @FXML
    private TableColumn<String, StudentData> payment_col_semester;

    @FXML
    private TableColumn<String, StudentData> payment_col_payment;

    @FXML
    private TableColumn<String, StudentData> payment_col_statusPayment;

    @FXML
    private AnchorPane salary_form;

    @FXML
    private DatePicker salary_fromDate;

    @FXML
    private DatePicker salary_toDate;

    @FXML
    private TextField salary_teacherID;

    @FXML
    private TextField salary_name;

    @FXML
    private TextField salary_salaryPerDay;

    @FXML
    private ComboBox<String> salary_status;

    @FXML
    private Label salary_totalDays;

    @FXML
    private Label salary_salary;

    @FXML
    private Button salary_payBtn;

    @FXML
    private Button salary_clearBtn;

    @FXML
    private TableView<TeacherData> salary_tableView;

    @FXML
    private TableColumn<TeacherData, String> salary_col_teacherID;

    @FXML
    private TableColumn<TeacherData, String> salary_col_fullName;

    @FXML
    private TableColumn<TeacherData, String> salary_col_salaryPerDay;

    @FXML
    private TableColumn<TeacherData, String> salary_col_gender;

    @FXML
    private TableColumn<TeacherData, String> salary_col_dateInsert;

    @FXML
    private TableColumn<TeacherData, String> salary_col_dateUpdate;

    @FXML
    private TableColumn<TeacherData, String> salary_col_status;

    @FXML
    private TableView<SalaryData> salary_SP_tableView;

    @FXML
    private TableColumn<SalaryData, String> salary_SP_col_teacherID;

    @FXML
    private TableColumn<SalaryData, String> salary_SP_col_salaryPaid;

    @FXML
    private TableColumn<SalaryData, String> salary_SP_col_datePaid;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private Label dashboard_TS;

    @FXML
    private Label dashboard_TT;

    @FXML
    private Label dashboard_SRT;

    @FXML
    private Label dashboard_TI;

    @FXML
    private AreaChart<?, ?> dashboard_chart_DS;

    @FXML
    private BarChart<?, ?> dashboard_chart_DT;

    @FXML
    private LineChart<?, ?> dashboard_chart_DI;
    
    //Buscadores
    @FXML
    private TextField adminCurso_search;
    
    @FXML
    private TextField adminAsignaturas_search;
    
    @FXML
    private TextField adminPagos_search;
    

   

    // HERRAMIENTAS BASE DE DATOS
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;

    private MensajeAlerta alert = new MensajeAlerta();

    private Image image;

    //VENTANA DASHBOARD
    
    /*
    VENTANA DASHBOARD: dashboardDisplayTS
    Muestra el nº total de estudiantes registrados (tanto que han pagado como que no, activos o pendientes de confirmar)
    en el dashboard de la aplicación
    */
    public void dashboardDisplayTS() {
    String sql = "SELECT COUNT(id) AS total FROM student WHERE date_delete IS NULL";
    // Establecer la conexión a la base de datos
    connect = Database.connectDB();
    int tempTS = 0;
    try {
        prepare = connect.prepareStatement(sql);
        result = prepare.executeQuery();

        if (result.next()) {
            tempTS = result.getInt("total");
        }
        dashboard_TS.setText("" + tempTS);
    } catch (Exception e) {
        e.printStackTrace();
        }
    }

    /*
    VENTANA DASHBOARD: dashboardDisplayTT
    Muestra la cantidad de profesores (activos y pendientes) en el 2º gráfico del panel de control
    */
    public void dashboardDisplayTT() {
    String sql = "SELECT COUNT(id) AS total FROM teacher WHERE date_delete IS NULL";
    connect = Database.connectDB();
    int tempTT = 0;
    try {
        prepare = connect.prepareStatement(sql);
        result = prepare.executeQuery();

        if (result.next()) {
            tempTT = result.getInt("total");
        }
        dashboard_TT.setText(String.valueOf(tempTT));
    } catch (Exception e) {
        e.printStackTrace();
        }
    }

    /*
    VENTANA DASHBOARD: dashboardDisplaySRT
    3er gráfico: muestra la cantidad de estudiantes registrados en el día actual (tanto validados como pendientes de validar)
    */
    
    public void dashboardDisplaySRT() {
    Date date = new Date();
    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
    String sql = "SELECT COUNT(id) AS total FROM student WHERE date_delete IS NULL AND date_insert = ?";
    connect = Database.connectDB();
    int tempSRT = 0;
    try {
        prepare = connect.prepareStatement(sql);
        prepare.setDate(1, sqlDate);
        result = prepare.executeQuery();

        if (result.next()) {
            tempSRT = result.getInt("total");
            dashboard_SRT.setText(String.valueOf(tempSRT));
        }
    } catch (Exception e) {
        e.printStackTrace();
        }
    }


    /*
    VENTANA DASHBOARD: dashboardDisplayTI
    4º Gráfico: muestra el total de ingresos obtenidos de los estudiantes que han realizado el pago
    */
    public void dashboardDisplayTI() {
    Date date = new Date();
    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
    String sql = "SELECT SUM(payment) AS totalPayment FROM student WHERE status_payment = 'Pagado' AND date_delete IS NULL";
    connect = Database.connectDB();
    double tempTI = 0;
    try {
        prepare = connect.prepareStatement(sql);
        result = prepare.executeQuery();

        if (result.next()) {
            tempTI = result.getDouble("totalPayment");
        }
        dashboard_TI.setText("$" + tempTI);
    } catch (Exception e) {
        e.printStackTrace();
        }
    }

    
    /*
    VENTANA DASHBOARD: dashboardDSChart
    1er gráfico: Muestra un gráfico de líneas que representa la cantidad de estudiantes registrados en los últimos 30 días
    Se indican los estudiantes registrados (aprobados o no por el admin) cada día
    */
    public void dashboardDSChart() {

        dashboard_chart_DS.getData().clear();

        String sql = "SELECT date_insert, COUNT(id) FROM student WHERE date_delete IS NULL GROUP BY TIMESTAMP(date_insert) ASC LIMIT 30";

        connect = Database.connectDB();

        try {
            XYChart.Series chart = new XYChart.Series<>();

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data<>(result.getString(1), result.getInt(2)));
            }

            dashboard_chart_DS.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    /*
    VENTANA DASHBOARD:
    2º gráfico: muestra el número de profesores registrados (validados y pendiente) por fecha
    */
    public void dashboardDTChart() {

        // Limpiar los datos anteriores del gráfico
        dashboard_chart_DT.getData().clear();

        String sql = "SELECT date_insert, COUNT(id) FROM teacher WHERE date_delete IS NULL GROUP BY TIMESTAMP(date_insert) ORDER BY DATE(date_insert) ASC LIMIT 10";

        connect = Database.connectDB();

        try {
            XYChart.Series chart = new XYChart.Series<>();

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data<>(result.getString(1), result.getInt(2)));
            }

            dashboard_chart_DT.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
  

    /*
    VENTANA DASHBOARD: 
    3er gráfico: ingresos realizados por los pagos que hacen los estudiantes
    */
    public void dashboardDIChart() {

        dashboard_chart_DI.getData().clear();
        String sql = "SELECT date_insert, SUM(payment) FROM student WHERE status_payment = 'Pagado' AND date_delete IS NULL GROUP BY DATE(date_insert) ORDER BY DATE(date_insert) ASC LIMIT 30";
        connect = Database.connectDB();

        try {
            XYChart.Series chart = new XYChart.Series<>();

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data<>(result.getString(1), result.getInt(2)));
            }

            dashboard_chart_DI.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    /*
    VENTANA ALTA ESTUDIANTES
    */
    
    /*
    Obtiene los datos de los estudiantes de la base de datos y los devuelve como una lista observable de objetos StudentData
    */
    public ObservableList<StudentData> addStudentGetData() {
    ObservableList<StudentData> listData = FXCollections.observableArrayList();
    String selectData = "SELECT id, student_id, full_name, year, course, section, semester, payment, status_payment, date_insert, status FROM student WHERE date_delete IS NULL";

    try (Connection connection = Database.connectDB();
         PreparedStatement preparedStatement = connection.prepareStatement(selectData);
         ResultSet resultSet = preparedStatement.executeQuery()) {

        while (resultSet.next()) {
            StudentData sData = new StudentData(
                    resultSet.getInt("id"),
                    resultSet.getString("student_id"),
                    resultSet.getString("full_name"),
                    resultSet.getString("year"),
                    resultSet.getString("course"),
                    resultSet.getString("section"),
                    resultSet.getString("semester"),
                    resultSet.getDouble("payment"),
                    resultSet.getString("status_payment"),
                    resultSet.getDate("date_insert"),
                    resultSet.getString("status")
            );
            listData.add(sData);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return listData;
}
    
    private ObservableList<StudentData> addStudentListData;

    /*
     Muestra los datos de los estudiantes en una tabla en la interfaz de usuario
    */
    public void addStudentDisplayData() {
        addStudentListData = addStudentGetData();

        addStudent_col_studentNumber.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        addStudent_col_name.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        addStudent_col_year.setCellValueFactory(new PropertyValueFactory<>("year"));
        addStudent_col_course.setCellValueFactory(new PropertyValueFactory<>("course"));
        addStudent_col_section.setCellValueFactory(new PropertyValueFactory<>("section"));
        addStudent_col_pay.setCellValueFactory(new PropertyValueFactory<>("payment"));
        addStudent_col_statusPayment.setCellValueFactory(new PropertyValueFactory<>("statusPayment"));
        addStudent_col_dateInsert.setCellValueFactory(new PropertyValueFactory<>("dateInsert"));
        addStudent_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        addStudent_tableView.setItems(addStudentListData);
    }

    /*
    Botón Alta Estudiante: Abre una nueva ventana para agregar un nuevo estudiante
    */
    
    public void addStudentAddBtn() {
    // Obtener el estudiante seleccionado
    StudentData selectedStudent = addStudent_tableView.getSelectionModel().getSelectedItem();

    // Verificar si el campo de número de estudiante está informado
    if (selectedStudent != null && selectedStudent.getStudentID() != null && !selectedStudent.getStudentID().isEmpty()) {
        alert.errorMessage("Debes pulsar en Editar, ya que el estudiante ya está registrado en EduOrganizer Hub");
    } else {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/AltaEstudiante.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Alta Estudiante");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }
    
   
    /*
    Carga los datos del estudiante seleccionado en la tabla addStudent_tableView 
    en la interfaz de usuario en la ventana de modificación de estudiantes AltaEstudiante.fxml
    */
    public void addStudentUpdateBtn() {

        StudentData sData = addStudent_tableView.getSelectionModel().getSelectedItem();
        int num = addStudent_tableView.getSelectionModel().getSelectedIndex();
        if ((num - 1) < -1) {
            alert.errorMessage("Por favor, primero selecciona un estudiante");
            return;
        } else {
            ListData.temp_studentNumber = sData.getStudentID();
            ListData.temp_studentName = sData.getFullName();
            ListData.temp_studentBirthDate = sData.getBirthDate();
            ListData.temp_studentYear = sData.getYear();
            ListData.temp_studentCourse = sData.getCourse();
            ListData.temp_studentGender = sData.getGender();
            ListData.temp_studentSemester = sData.getSemester();
            ListData.temp_studentSection = sData.getSection();
            ListData.temp_studentPay = sData.getPayment();
            ListData.temp_studentPaymentStatus = sData.getStatusPayment();
            ListData.temp_studentStatus = sData.getStatus();

            try {
                Parent root = FXMLLoader.load(getClass().getResource("/Vistas/AltaEstudiante.fxml"));
                Stage stage = new Stage();

                stage.setTitle("Modificar Estudiante");
                stage.setScene(new Scene(root));
                stage.show();

            } catch (IOException e) {
                alert.errorMessage("Error al cargar la ventana de modificación de estudiantes. Por favor, inténtelo de nuevo.");
            }
        }

    }
    
    //Método para refrescar la página
    public void addStudentRefreshBtn() {
         addStudentDisplayData();
    }

    /*
    Método para eliminar el estudiante seleccionado de la base de datos
    Realmente no lo elimina, sino que actualiza su fecha de borrado y status a inactive
    */
    
    public void addStudentDeleteBtn() {

        StudentData sData = addStudent_tableView.getSelectionModel().getSelectedItem();
        int num = addStudent_tableView.getSelectionModel().getSelectedIndex();

        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        if ((num - 1) < -1) {
            alert.errorMessage("Por favor, primero selecciona un estudiante");
            return;
        } else {
            if (alert.confirmMessage("¿Seguro que quieres eliminar el estudiante con ID: "
                    + sData.getStudentID() + "?")) {
                String deleteData = "UPDATE student SET date_delete = ?, status = 'Inactivo' WHERE student_id = ?";
                connect = Database.connectDB();

                try {
                    prepare = connect.prepareStatement(deleteData);
                    prepare.setString(1, String.valueOf(sqlDate));
                    prepare.setString(2, sData.getStudentID());
                    prepare.executeUpdate();
                    alert.successMessage("¡Eliminado correctamente!");
                } catch (SQLException e) {
                    e.printStackTrace();
                    alert.errorMessage("Error al eliminar el estudiante. Por favor, inténtelo de nuevo.");
                }
            } else {
                alert.errorMessage("¡Operación cancelada!");
            }
        }
        addStudentDisplayData();
    }
    

    // VENTANA ALTA PROFESORES
    
    /*
    addTeacherGetData
    Devuelve una lista de los profesores no eliminados
    */
    public ObservableList<TeacherData> addTeacherGetData() {
    ObservableList<TeacherData> listData = FXCollections.observableArrayList();

    String sql = "SELECT * FROM teacher WHERE date_delete IS NULL";

    try (Connection connect = Database.connectDB();
         PreparedStatement prepare = connect.prepareStatement(sql);
         ResultSet result = prepare.executeQuery()) {

        while (result.next()) {
            TeacherData tData = new TeacherData(result.getInt("id"),
                    result.getString("teacher_id"),
                    result.getString("full_name"), 
                    result.getString("gender"),
                    result.getString("year_experience"), 
                    result.getString("experience"),
                    result.getString("department"), 
                    result.getDouble("salary"),
                    result.getString("salary_status"),
                    result.getString("image"), 
                    result.getDate("date_insert"),
                    result.getDate("date_update"), 
                    result.getDate("date_delete"),
                    result.getString("status"));

            listData.add(tData);
        }

    } catch (SQLException e) {
        e.printStackTrace();
        }
    return listData;
    }


    private ObservableList<TeacherData> addTeacherListData;

    /*
    addTeacherDisplayData
    Muestra los datos de los profesores en una tabla
    */
    public void addTeacherDisplayData() {
        addTeacherListData = addTeacherGetData();

        addTeacher_col_teacherID.setCellValueFactory(new PropertyValueFactory<>("teacherID"));
        addTeacher_col_name.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        addTeacher_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        addTeacher_col_yearExperience.setCellValueFactory(new PropertyValueFactory<>("yearExperience"));
        addTeacher_col_experience.setCellValueFactory(new PropertyValueFactory<>("experience"));
        addTeacher_col_department.setCellValueFactory(new PropertyValueFactory<>("department"));
        addTeacher_col_salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        addTeacher_col_dateInsert.setCellValueFactory(new PropertyValueFactory<>("dateInsert"));
        addTeacher_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        addTeacher_tableView.setItems(addTeacherListData);
    }

    /*
    addTeacherSelectItems
    Configura los campos de texto y selección en función del profesor seleccionado en la tabla addTeacher_tableView
    */
    
    public void addTeacherSelectItems() {
    int selectedIndex = addTeacher_tableView.getSelectionModel().getSelectedIndex();
    if (selectedIndex < 0 || selectedIndex >= addTeacherListData.size()) {
        return;
    }

    TeacherData tData = addTeacherListData.get(selectedIndex);

    addTeacher_teacherID.setText(tData.getTeacherID());
    addTeacher_fullName.setText(tData.getFullName());
    addTeacher_gender.getSelectionModel().select(tData.getGender());
    addTeacher_experienceYear.getSelectionModel().select(tData.getYearExperience());
    addTeacher_experience.setText(tData.getExperience());
    addTeacher_department.getSelectionModel().select(tData.getDepartment());
    addTeacher_salary.setText(String.valueOf(tData.getSalary()));
    addTeacher_status.getSelectionModel().select(tData.getStatus());
    addTeacher_salaryStatus.getSelectionModel().select(tData.getSalaryStatus());

    ListData.path = tData.getImage();

    Image image = new Image("File:" + tData.getImage(), 90, 103, false, true);
    addTeacher_imageView.setImage(image);
    }

    /*
    addTeacherGenderList
    Inicializa el ComboBox addTeacher_gender con los elementos de la lista ListData.gender
    */
    public void addTeacherGenderList() {

        List<String> listG = new ArrayList<>();

        for (String data : ListData.gender) {
            listG.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(listG);
        addTeacher_gender.setItems(listData);
    }

    /*
    addTeacherEYList
    Obtiene los años de experiencia de ListData y los muestra en el desplegable
    */
    public void addTeacherEYList() {

        List<String> listEY = new ArrayList<>();

        for (String data : ListData.yearExperience) {
            listEY.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(listEY);
        addTeacher_experienceYear.setItems(listData);
    }
    
    /*
    addTeacherSSList
    Prepara una lista de opciones para el ComboBox addTeacher_salaryStatus utilizando los elementos almacenados en ListData.paymentStatus
    */
    public void addTeacherSSList() {

        List<String> listSS = new ArrayList<>();

        for (String data : ListData.paymentStatus) {
            listSS.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listSS);

        addTeacher_salaryStatus.setItems(listData);

    }

    /*
    addTeacherDepartmentList
    Establece las opciones para el ComboBox addTeacher_department recuperando los datos de la base de datos
    */
    public void addTeacherDepartmentList() {

        String sql = "SELECT * FROM course WHERE date_delete IS NULL";
        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            ObservableList listData = FXCollections.observableArrayList();

            while (result.next()) {
                listData.add(result.getString("department"));
            }
            addTeacher_department.setItems(listData);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
        // Cerrar la conexión
        try {
            if (result != null) result.close();
            if (prepare != null) prepare.close();
            if (connect != null) connect.close();
        } catch (SQLException e) {
            e.printStackTrace();
            }
        }

    }

    /*
    addTeacherStatusList
    Muestra los estados de un profesor en el desplegable (activo, inactivo, pendiente)
    */
    public void addTeacherStatusList() {

        List<String> listS = new ArrayList<>();

        for (String data : ListData.status) {
            listS.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(listS);
        addTeacher_status.setItems(listData);
    }

    private String teacherID;

    /*
    generateTeacherID
    Genera un ID único para un nuevo profesor basado en el ID máximo actual en la base de datos.
    */
    public void generateTeacherID() {

        String sql = "SELECT MAX(id) FROM teacher";

        connect = Database.connectDB();
        String temp_teacherID = "TID-";
        int temp_count = 0;

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                temp_count = result.getInt("MAX(id)");
            }

            if (temp_count == 0) {
                temp_count += 1;
                teacherID = temp_teacherID + temp_count;
            } else {
                teacherID = temp_teacherID + (temp_count + 1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

    /*
    addTeacherDisplayTeacherID
    Muestra el ID generado para un nuevo profesor en un campo de texto.
    */
    public void addTeacherDisplayTeacherID() {
        generateTeacherID();
        addTeacher_teacherID.setText(teacherID);
    }

    /*
    addTeacherADDBtn
    Agrega un nuevo profesor a la base de datos al pulsar el botón Añadir y completar los campos
    */
    
    public void addTeacherAddBtn() {
    // Verificar si hay un profesor seleccionado en la tabla
    TeacherData selectedTeacher = addTeacher_tableView.getSelectionModel().getSelectedItem();
    if (selectedTeacher != null && selectedTeacher.getTeacherID() != null && !selectedTeacher.getTeacherID().isEmpty()) {
        alert.errorMessage("Debes pulsar en Editar, ya que el profesor ya está registrado en EduOrganizer Hub");
        return; // Salir del método si hay un profesor seleccionado
    }

    if (!validateFields()) {
        alert.errorMessage("Por favor, completa todos los campos");
        return;
    }

    String insertData = "INSERT INTO teacher "
            + "(teacher_id, full_name, gender, year_experience, experience"
            + ", department, salary, salary_status, image, date_insert, status) "
            + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";

    try {
        connect = Database.connectDB();
        prepare = connect.prepareStatement(insertData);

        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        String path = ListData.path.replace("\\", "\\\\");

        prepare.setString(1, addTeacher_teacherID.getText());
        prepare.setString(2, addTeacher_fullName.getText());
        prepare.setString(3, addTeacher_gender.getSelectionModel().getSelectedItem());
        prepare.setString(4, addTeacher_experienceYear.getSelectionModel().getSelectedItem());
        prepare.setString(5, addTeacher_experience.getText());
        prepare.setString(6, addTeacher_department.getSelectionModel().getSelectedItem());
        prepare.setString(7, addTeacher_salary.getText());
        prepare.setString(8, addTeacher_salaryStatus.getSelectionModel().getSelectedItem());
        prepare.setString(9, path);
        prepare.setString(10, String.valueOf(sqlDate));
        prepare.setString(11, addTeacher_status.getSelectionModel().getSelectedItem());

        prepare.executeUpdate();

        addTeacherDisplayData();

        Path transfer = Paths.get(path);
        Path copy = Paths.get("C:\\Users\\alex_\\OneDrive\\Documentos\\NetBeansProjects\\EduOrganizerHub\\src\\Almacen_Profesor\\"
                + addTeacher_teacherID.getText() + ".jpg");

        Files.copy(transfer, copy, StandardCopyOption.REPLACE_EXISTING);

        alert.successMessage("¡Añadido correctamente!");

        addTeacherClearBtn();
    } catch (SQLException e) {
        e.printStackTrace();
        alert.errorMessage("Error al añadir el profesor: " + e.getMessage());
    } catch (IOException e) {
        e.printStackTrace();
        alert.errorMessage("Error al copiar la imagen: " + e.getMessage());
        }
    }


    /*
    validateFields
    Valida los campos del método anterior
    */
    private boolean validateFields() {
        return !addTeacher_teacherID.getText().isEmpty()
                && !addTeacher_fullName.getText().isEmpty()
                && addTeacher_gender.getSelectionModel().getSelectedItem() != null
                && !addTeacher_experience.getText().isEmpty()
                && addTeacher_experienceYear.getSelectionModel().getSelectedItem() != null
                && addTeacher_department.getSelectionModel().getSelectedItem() != null
                && !addTeacher_salary.getText().isEmpty()
                && addTeacher_status.getSelectionModel().getSelectedItem() != null
                && ListData.path != null && !ListData.path.isEmpty()
                && addTeacher_salaryStatus.getSelectionModel().getSelectedItem() != null;
    }

    
    
    /*
    addTeacherUpdateBtn
    Permite modificar un profesor al pulsar el botón Modificar
    */
    
    public void addTeacherUpdateBtn() {
    // Verifica si se ha seleccionado un profesor para editar
    TeacherData selectedTeacher = addTeacher_tableView.getSelectionModel().getSelectedItem();
    if (selectedTeacher == null) {
        alert.errorMessage("Por favor, primero selecciona un profesor");
        return;
    }

    // Verifica si todos los campos están completos
    if (!validateFields()) {
        alert.errorMessage("Por favor, completa todos los campos");
        return;
    }

    // Confirmación para modificar el profesor
    if (alert.confirmMessage("¿Seguro que quieres modificar el profesor con ID: " + addTeacher_teacherID.getText() + "?")) {
        String path = ListData.path.replace("\\", "\\\\");

        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        String updateData = "UPDATE teacher SET full_name = ?, gender = ?, experience = ?, year_experience = ?, department = ?, salary = ?, salary_status = ?, image = ?, date_update = ?, status = ? WHERE teacher_id = ?";

        try {
            connect = Database.connectDB();
            prepare = connect.prepareStatement(updateData);

            prepare.setString(1, addTeacher_fullName.getText());
            prepare.setString(2, addTeacher_gender.getSelectionModel().getSelectedItem());
            prepare.setString(3, addTeacher_experience.getText());
            prepare.setString(4, addTeacher_experienceYear.getSelectionModel().getSelectedItem());
            prepare.setString(5, addTeacher_department.getSelectionModel().getSelectedItem());
            prepare.setString(6, addTeacher_salary.getText());
            prepare.setString(7, addTeacher_salaryStatus.getSelectionModel().getSelectedItem());
            prepare.setString(8, path);
            prepare.setString(9, String.valueOf(sqlDate));
            prepare.setString(10, addTeacher_status.getSelectionModel().getSelectedItem());
            prepare.setString(11, addTeacher_teacherID.getText());

            prepare.executeUpdate();

            addTeacherDisplayData();

            Path transfer = Paths.get(path);
            Path copy = Paths.get("C:\\Users\\alex_\\OneDrive\\Documentos\\NetBeansProjects\\EduOrganizerHub\\src\\Almacen_Profesor\\" + addTeacher_teacherID.getText() + ".jpg");

            Files.copy(transfer, copy, StandardCopyOption.REPLACE_EXISTING);

            alert.successMessage("¡Modificado correctamente!");

            addTeacherClearBtn();
        } catch (Exception e) {
            e.printStackTrace();
            alert.errorMessage("Error al modificar el profesor: " + e.getMessage());
        }
    } else {
        alert.errorMessage("Cancelado");
        }
    }

    
    /*
    addTeacherDeleteBtn
    Permite eliminar profesores. Para ello, cambia su estado a Inactivo y añade fecha de borrado en la base de datos
    */
    
    public void addTeacherDeleteBtn() {    
        
    // Verificar si se ha seleccionado algún profesor en la tabla
    int selectedIndex = addTeacher_tableView.getSelectionModel().getSelectedIndex();
    if (selectedIndex < 0) {
        alert.errorMessage("Por favor, selecciona primero el profesor que deseas eliminar.");
        return;
    }


    if (alert.confirmMessage("¿Seguro que quieres eliminar el profesor con ID: " + addTeacher_teacherID.getText() + "?")) {
        String deleteData = "UPDATE teacher SET status = 'Inactivo', date_delete = ? WHERE teacher_id = ?";
        connect = Database.connectDB();

        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        try {
            prepare = connect.prepareStatement(deleteData);
            prepare.setString(1, "" + sqlDate);
            prepare.setString(2, addTeacher_teacherID.getText());

            prepare.executeUpdate();

            addTeacherDisplayData();

            alert.successMessage("¡Eliminado correctamente!");

            addTeacherClearBtn();
        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Error al eliminar el profesor: " + e.getMessage());
        } finally {
            try {
                if (prepare != null) {
                    prepare.close();
                }
                if (connect != null) {
                    connect.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                alert.errorMessage("Error al cerrar la conexión: " + ex.getMessage());
            }
        }
    } else {
        alert.errorMessage("Cancelado");
                }
    }

    /*
    addTeacherClearBtn
    Limpia los campos de texto de la ventana de profesores
    */
    public void addTeacherClearBtn() {
        addTeacherDisplayTeacherID();
        addTeacher_fullName.clear();
        addTeacher_gender.getSelectionModel().clearSelection();
        addTeacher_experienceYear.getSelectionModel().clearSelection();
        addTeacher_experience.clear();
        addTeacher_department.getSelectionModel().clearSelection();
        addTeacher_salary.clear();
        ListData.path = "";
        addTeacher_status.getSelectionModel().clearSelection();

        addTeacher_imageView.setImage(null);

    }

    /*
    addTeacherImportBtn
    Permite seleccionar una imágen y mostrarla en el imageView
    Importar foto del profesor
    */
    public void addTeacherImportBtn() {

        FileChooser open = new FileChooser();
        open.getExtensionFilters().add(new ExtensionFilter("Open Image", "*.jpg", "*.jpeg", "*.png"));

        File file = open.showOpenDialog(addTeacher_importBtn.getScene().getWindow());

        if (file != null) {
            ListData.path = file.getAbsolutePath();

            image = new Image(file.toURI().toString(), 90, 103, false, true);
            addTeacher_imageView.setImage(image);
        }
    }


    ///CURSOS
    
    /*
    addCourseGetData
    Recupera datos de la base de datos y los almacena en una lista de objetos CourseData
    */
    public ObservableList<CourseData> addCourseGetData() {

        ObservableList<CourseData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM course WHERE date_delete IS NULL";
        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            CourseData cData;

            while (result.next()) {
                cData = new CourseData(result.getInt("id"), result.getString("course"),
                        result.getString("department"), result.getDouble("price"),
                        result.getDate("date_insert"),
                        result.getDate("date_update"), result.getDate("date_delete"),
                        result.getString("status"));

                listData.add(cData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<CourseData> addCourseListData;

    /*
    addCourseDisplayData
    Recupera los datos de los cursos utilizando el método addCourseGetData().
    Configura las celdas de la tabla para mostrar los datos utilizando PropertyValueFactory.
    Establece los datos en la tabla.
    */
    public void addCourseDisplayData() {
        addCourseListData = addCourseGetData();

        addCourse_col_course.setCellValueFactory(new PropertyValueFactory<>("course"));
        addCourse_col_department.setCellValueFactory(new PropertyValueFactory<>("department"));
        addCourse_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        addCourse_col_dateInsert.setCellValueFactory(new PropertyValueFactory<>("dateInsert"));
        addCourse_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        addCourse_tableView.setItems(addCourseListData);
    }

    private int courseID = 0;

    /*
    addCourseSelectItem
    Seleccionar un elemento de la tabla de cursos y mostrar sus detalles en los campos de texto correspondientes. 
    También almacena el ID del curso seleccionado en la variable courseID
    */
    public void addCourseSelectItem() {
        CourseData cData = addCourse_tableView.getSelectionModel().getSelectedItem();
        int num = addCourse_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        addCourse_course.setText(cData.getCourse());
        addCourse_department.setText(cData.getDepartment());
        addCourse_price.setText("" + cData.getPrice());
        addCourse_status.getSelectionModel().select(cData.getStatus());

        courseID = cData.getId();
    }

    /*
    addCourseAddBtn
    Agrega un nuevo curso a la base de datos
    */
    
    public void addCourseAddBtn() {
    if (addCourse_course.getText().isEmpty()
            || addCourse_department.getText().isEmpty()
            || addCourse_price.getText().isEmpty()
            || addCourse_status.getSelectionModel().isEmpty()) {
        alert.errorMessage("Por favor, completa todos los campos en blanco");
    } else {
        try (Connection connect = Database.connectDB()) {
            String checkCourse = "SELECT * FROM course WHERE course = ? AND date_delete IS NULL";
            try (PreparedStatement checkStatement = connect.prepareStatement(checkCourse)) {
                checkStatement.setString(1, addCourse_course.getText());
                try (ResultSet result = checkStatement.executeQuery()) {
                    if (result.next()) {
                        alert.errorMessage(addCourse_course.getText() + " ya existe");
                    } else {
                        Date date = new Date();
                        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                        String insertData = "INSERT INTO course (course, department, price, date_insert, status) "
                                + "VALUES(?,?,?,?,?)";

                        try (PreparedStatement prepare = connect.prepareStatement(insertData)) {
                            prepare.setString(1, addCourse_course.getText());
                            prepare.setString(2, addCourse_department.getText());
                            prepare.setDouble(3, Double.parseDouble(addCourse_price.getText()));
                            prepare.setDate(4, sqlDate);
                            prepare.setString(5, addCourse_status.getSelectionModel().getSelectedItem());

                            prepare.executeUpdate();

                            addCourseDisplayData();

                            alert.successMessage("¡Añadido correctamente!");

                            addCourseClear();
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Error al agregar el curso: " + e.getMessage());
            }
        }
    }

    /*
    addCourseUpdateBtn
    Botón que permite modificar un curso ya creado en la base de datos
    */
    public void addCourseUpdateBtn() {
    if (addCourse_course.getText().isEmpty()
            || addCourse_department.getText().isEmpty()
            || addCourse_price.getText().isEmpty()
            || addCourse_status.getSelectionModel().isEmpty()) {
        alert.errorMessage("Por favor, completa todos los campos en blanco");
    } else {
        if (alert.confirmMessage("¿Seguro que quieres Modificar el curso "
                + addCourse_course.getText() + "?")) {
            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            String updateData = "UPDATE course SET course = ?, department = ?, price = ?, date_update = ?, status = ? WHERE id = ?";
            try (Connection connect = Database.connectDB();
                 PreparedStatement prepare = connect.prepareStatement(updateData)) {

                prepare.setString(1, addCourse_course.getText());
                prepare.setString(2, addCourse_department.getText());
                prepare.setDouble(3, Double.parseDouble(addCourse_price.getText()));
                prepare.setDate(4, sqlDate);
                prepare.setString(5, addCourse_status.getSelectionModel().getSelectedItem());
                prepare.setInt(6, courseID);

                prepare.executeUpdate();

                addCourseDisplayData();

                alert.successMessage("¡Modificado correctamente!");

                addCourseClear();

            } catch (SQLException e) {
                e.printStackTrace();
                alert.errorMessage("Error al modificar el curso: " + e.getMessage());
            }
        } else {
            alert.errorMessage("Cancelado");
            }
        }
    }

    /*
    addCourseDeleteBtn
    Botón eliminar curso: modifica en BD la fecha de delete, le cambia el estado a No Disponible y lo quita de la tabla
    */
    
    public void addCourseDeleteBtn() {
    if (courseID == 0) {
        alert.errorMessage("Por favor, selecciona primero un curso");
    } else {
        if (alert.confirmMessage("¿Seguro que quieres borrar el Curso "
                + addCourse_course.getText() + "?")) {
            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            String deleteData = "UPDATE course SET date_delete = ?, status = 'No disponible' WHERE id = ?";
            
            try (Connection connect = Database.connectDB();
                 PreparedStatement prepare = connect.prepareStatement(deleteData)) {
                
                prepare.setString(1, String.valueOf(sqlDate));
                prepare.setInt(2, courseID);

                prepare.executeUpdate();

                addCourseDisplayData();

                alert.successMessage("¡Eliminado correctamente!");

                addCourseClear();
            } catch (SQLException e) {
                e.printStackTrace();
                alert.errorMessage("Error al eliminar el curso: " + e.getMessage());
                }
            }
        }
    }

    /*
    addCourseClear
    Botón Limpiar
    Se encarga de limpiar los campos del formulario relacionados con la información de un curso
    */
    public void addCourseClear() {
        addCourse_course.clear();
        addCourse_department.clear();
        addCourse_price.clear();
        addCourse_status.getSelectionModel().clearSelection();
    }

    /*
    addCourseStatus
    Se encarga de cargar los diferentes estados de un curso en un ComboBox
    */
    public void addCourseStatus() {
        List<String> listS = new ArrayList<>();

        for (String data : ListData.statusA) {
            listS.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listS);
        addCourse_status.setItems(listData);
    }
    
    /*
    cursoSearch
    Buscador de cursos, que muestra resultados en la tabla
    */
    public void cursoSearch() {


        FilteredList<CourseData> filter = new FilteredList<>(addCourseListData, e -> true);

        adminCurso_search.textProperty().addListener((Observable, oldValue, newValue) -> {
            filter.setPredicate(predicateCourseData -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String searchKey = newValue.toLowerCase();

                if (predicateCourseData.getCourse().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateCourseData.getDepartment().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateCourseData.getPrice().toString().contains(searchKey)) {
                    return true;
                } else if (predicateCourseData.getStatus().toLowerCase().contains(searchKey)) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<CourseData> sortList = new SortedList<>(filter);
        sortList.comparatorProperty().bind(addCourse_tableView.comparatorProperty());
        addCourse_tableView.setItems(sortList);
    }

    ///Asignaturas
    
    /*
    addSubjectGetData
    Recupera los datos de las asignaturas de la base de datos y los almacena en una lista observable
    */
    
    public ObservableList<SubjectData> addSubjectGetData() {
    ObservableList<SubjectData> listData = FXCollections.observableArrayList();
    String sql = "SELECT * FROM subject WHERE date_delete IS NULL";

    try (Connection connect = Database.connectDB();
         PreparedStatement prepare = connect.prepareStatement(sql);
         ResultSet result = prepare.executeQuery()) {

        while (result.next()) {
            SubjectData sData = new SubjectData(
                    result.getInt("id"),
                    result.getString("subject_code"),
                    result.getString("subject"),
                    result.getString("course"),
                    result.getDate("date_insert"),
                    result.getDate("date_update"),
                    result.getDate("date_delete"),
                    result.getString("status"));

            listData.add(sData);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return listData;
    }


    private ObservableList<SubjectData> addSubjectListData;

    /*
    addSubjectDisplayData
    Muestra los datos de las asignaturas en una tabla
    */
    public void addSubjectDisplayData() {

        addSubjectListData = addSubjectGetData();

        addSubject_col_code.setCellValueFactory(new PropertyValueFactory<>("subjectCode"));
        addSubject_col_subject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        addSubject_col_course.setCellValueFactory(new PropertyValueFactory<>("course"));
        addSubject_col_dateInsert.setCellValueFactory(new PropertyValueFactory<>("dateInsert"));
        addSubject_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        addSubject_tableView.setItems(addSubjectListData);
    }

    private int subjectID = 0;

    /*
    addSubjectSelectItem
    Selecciona un elemento de la tabla de asignaturas y muestra sus detalles en los campos correspondientes
    */
    
    public void addSubjectSelectItem() {
    SubjectData sData = addSubject_tableView.getSelectionModel().getSelectedItem();
    int selectedIndex = addSubject_tableView.getSelectionModel().getSelectedIndex();

    if (selectedIndex < 0) {
        return;
    }

    addSubject_code.setText(sData.getSubjectCode());
    addSubject_subject.setText(sData.getSubject());
    addSubject_course.getSelectionModel().select(sData.getCourse());
    addSubject_status.getSelectionModel().select(sData.getStatus());

    subjectID = sData.getId();
    }

    /*
    addSubjectAddBtn
    Botón Añadir
    Permite agregar asignaturas en la BDD y mostrarlas en la tabla
    */
    
    public void addSubjectAddBtn() {
    if (addSubject_code.getText().isEmpty()
            || addSubject_subject.getText().isEmpty()
            || addSubject_course.getSelectionModel().isEmpty()
            || addSubject_status.getSelectionModel().isEmpty()) {
        alert.errorMessage("Por favor, completa todos los campos en blanco");
    } else {
        connect = Database.connectDB();

        String checkSubject = "SELECT * FROM subject WHERE subject_code = '"
                + addSubject_code.getText() + "' AND date_delete IS NULL";
        try {
            statement = connect.createStatement();
            result = statement.executeQuery(checkSubject);

            if (result.next()) {
                alert.errorMessage(addSubject_code.getText() + " ya existe");
            } else {
                Date date = new Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                String insertData = "INSERT INTO subject "
                        + "(subject_code, subject, course, date_insert, status) "
                        + "VALUES(?,?,?,?,?)";

                prepare = connect.prepareStatement(insertData);
                prepare.setString(1, addSubject_code.getText());
                prepare.setString(2, addSubject_subject.getText());
                prepare.setString(3, addSubject_course.getSelectionModel().getSelectedItem());
                prepare.setString(4, String.valueOf(sqlDate));
                prepare.setString(5, addSubject_status.getSelectionModel().getSelectedItem());

                prepare.executeUpdate();

                addSubjectDisplayData();

                alert.successMessage("¡Añadido correctamente!");

                addSubjectClear();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Error al añadir la asignatura: " + e.getMessage());
            }
        }
    }

    /*
    addSubjectUpdateBtn
    Botón Modificar
    Permite modificar asignaturas en la base de datos
    */
    
    public void addSubjectUpdateBtn() {
    if (addSubject_code.getText().isEmpty()
            || addSubject_subject.getText().isEmpty()
            || addSubject_course.getSelectionModel().isEmpty()
            || addSubject_status.getSelectionModel().isEmpty()) {
        alert.errorMessage("Por favor, completa todos los campos en blanco");
    } else {
        if (alert.confirmMessage("¿Seguro que quieres modificar la asignatura con código: "
                + addSubject_code.getText() + "?")) {
            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            String updateData = "UPDATE subject SET subject_code = ?, subject = ?, course = ?, date_update = ?, status = ? WHERE id = ?";

            connect = Database.connectDB();

            try {
                prepare = connect.prepareStatement(updateData);
                prepare.setString(1, addSubject_code.getText());
                prepare.setString(2, addSubject_subject.getText());
                prepare.setString(3, addSubject_course.getSelectionModel().getSelectedItem());
                prepare.setDate(4, sqlDate);
                prepare.setString(5, addSubject_status.getSelectionModel().getSelectedItem());
                prepare.setInt(6, subjectID);

                prepare.executeUpdate();

                addSubjectDisplayData();

                alert.successMessage("¡Modificado correctamente!");

                addSubjectClear();
            } catch (SQLException e) {
                e.printStackTrace();
                alert.errorMessage("Error al modificar la asignatura: " + e.getMessage());
            }
        } else {
            alert.errorMessage("Cancelado");
            }
          }
    }

    /*
    addSubjectDeleteBtn
    Botón eliminar
    Elimina la asignatura de la aplicación
    En BD, modifica su estado a No Disponible y su fecha de eliminación
    */
    
    public void addSubjectDeleteBtn() {
    if (subjectID == 0) {
        alert.errorMessage("Por favor, primero selecciona un elemento");
    } else {
        if (alert.confirmMessage("¿Seguro que quieres eliminar la asignatura con código: "
                + addSubject_code.getText() + "?")) {
            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            String deleteData = "UPDATE subject SET date_delete = ?, status = ? WHERE id = ?";
            connect = Database.connectDB();

            try {
                prepare = connect.prepareStatement(deleteData);
                prepare.setDate(1, sqlDate);
                prepare.setString(2, "No disponible");
                prepare.setInt(3, subjectID);

                prepare.executeUpdate();

                addSubjectDisplayData();

                alert.successMessage("¡Eliminado correctamente!");

                addSubjectClear();
            } catch (SQLException e) {
                e.printStackTrace();
                alert.errorMessage("Error al eliminar la asignatura: " + e.getMessage());
                }
            }
        }
    }

    /*
    addSubjectClear
    Botón Limpiar
    Vacía los campos de la interfaz
    */
    public void addSubjectClear() {
        addSubject_code.clear();
        addSubject_subject.clear();
        addSubject_course.getSelectionModel().clearSelection();
        addSubject_status.getSelectionModel().clearSelection();
    }

    /*
    addSubjectCourseList
    Obtiene la lista de cursos disponibles desde la base de datos y la muestra en un ComboBox en la interfaz de usuario
    */
    
    public void addSubjectCourseList() {
    String sql = "SELECT * FROM course WHERE date_delete IS NULL";
    try (Connection connect = Database.connectDB();
         PreparedStatement prepare = connect.prepareStatement(sql);
         ResultSet result = prepare.executeQuery()) {

        ObservableList<String> listData = FXCollections.observableArrayList();

        while (result.next()) {
            listData.add(result.getString("course"));
        }

        addSubject_course.setItems(listData);

    } catch (SQLException e) {
        e.printStackTrace();
        alert.errorMessage("Error al obtener la lista de cursos: " + e.getMessage());
        }
    }

    /*
    addSubjectStatusList
    Muestra en un desplegable status si la asignatura está disponible o no
    */
    public void addSubjectStatusList() {

        List<String> listS = new ArrayList<>();

        for (String data : ListData.statusA) {
            listS.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listS);
        addSubject_status.setItems(listData);
    }
    
    
    /*
    asignaturaSearch
    Buscador de asingaturas, que muestra resultados en la tabla
    */
    public void asignaturaSearch() {


        FilteredList<SubjectData> filter = new FilteredList<>(addSubjectListData, e -> true);

        adminAsignaturas_search.textProperty().addListener((Observable, oldValue, newValue) -> {
            filter.setPredicate(predicateCourseData -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String searchKey = newValue.toLowerCase();

                if (predicateCourseData.getSubjectCode().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateCourseData.getSubject().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateCourseData.getCourse().toString().contains(searchKey)) {
                    return true;
                } else if (predicateCourseData.getDateInsert().toString().contains(searchKey)) {
                    return true;
                } else if (predicateCourseData.getStatus().toLowerCase().contains(searchKey)) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<SubjectData> sortList = new SortedList<>(filter);
        sortList.comparatorProperty().bind(addSubject_tableView.comparatorProperty());
        addSubject_tableView.setItems(sortList);
    }
    
    

    ///Pagos
    
    /*
    paymentGetData
    Recupera los datos de los estudiantes cuyo estado de pago es "Pendiente" y que aún no han sido eliminados de la base de datos. 
    Devuelve una lista observable de StudentData con estos datos
    */
    public ObservableList<StudentData> paymentGetData() {

        ObservableList<StudentData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM student WHERE status_payment = 'Pendiente' AND date_delete IS NULL";

        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            StudentData sData;

            while (result.next()) {
                sData = new StudentData(result.getInt("id"), result.getString("student_id"),
                        result.getString("full_name"), result.getString("year"),
                        result.getString("course"), result.getString("section"),
                        result.getString("semester"), result.getDouble("payment"),
                        result.getString("status_payment"), result.getString("image"),
                        result.getDate("date_update"), result.getString("status"));

                listData.add(sData);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<StudentData> paymentListData;

    /*
    paymentDisplayData
    Se encarga de obtener los datos de los estudiantes cuyo estado de pago es "Pendiente" desde la 
    base de datos y mostrarlos en una tabla en la interfaz de usuario
    */
    public void paymentDisplayData() {
        paymentListData = paymentGetData();

        payment_col_studentID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        payment_col_name.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        payment_col_year.setCellValueFactory(new PropertyValueFactory<>("year"));
        payment_col_section.setCellValueFactory(new PropertyValueFactory<>("section"));
        payment_col_semester.setCellValueFactory(new PropertyValueFactory<>("semester"));
        payment_col_payment.setCellValueFactory(new PropertyValueFactory<>("payment"));
        payment_col_statusPayment.setCellValueFactory(new PropertyValueFactory<>("statusPayment"));

        payment_tableView.setItems(paymentListData);
    }

    /*
    paymentSelectItem
    Recupera los datos del estudiante seleccionado en la tabla.
    Muestra los datos del estudiante en los campos correspondientes de la interfaz de usuario.
    */
    
    public void paymentSelectItem() {
    StudentData sData = payment_tableView.getSelectionModel().getSelectedItem();

    if (sData == null) {
        return;
    }

    payment_studentID.setText(sData.getStudentID());
    payment_name.setText(sData.getFullName());
    payment_year.setText(sData.getYear());
    payment_section.setText(sData.getSection());
    payment_semester.setText(sData.getSemester());
    payment_payment.setText(String.valueOf(sData.getPayment()));
    payment_status.getSelectionModel().select(sData.getStatusPayment());

    String imagePath = sData.getImage();
    if (imagePath != null && !imagePath.isEmpty()) {
        ListData.path = imagePath;
        image = new Image("File:" + ListData.path, 94, 96, false, true);
        payment_imageView.setImage(image);
    } else {
        payment_imageView.setImage(null);
    }
}

    /*
    paymentDisableFields
    Desactiva todos los campos mostrados en el pago salvo el de Pagado, para poder actualizar
    solo ese
    */
    public void paymentDisableFields() {
        payment_studentID.setDisable(true);
        payment_name.setDisable(true);
        payment_year.setDisable(true);
        payment_section.setDisable(true);
        payment_semester.setDisable(true);
        payment_payment.setDisable(true);
    }

    /*
    paymentPaymentStatusList
    Llena el ComboBox de estado de pago con los valores proporcionados en ListData.paymentStatus

    */
    public void paymentPaymentStatusList() {

        List<String> listPS = new ArrayList<>();

        for (String data : ListData.paymentStatus) {
            listPS.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listPS);
        payment_status.setItems(listData);

    }

    /*
    paymentUpdateBtn
    Actualiza el estado de pago del alumno en Base de Datos
    */
    public void paymentUpdateBtn() {
    if (payment_studentID.getText().isEmpty() || payment_payment.getText().isEmpty()) {
        alert.errorMessage("Por favor, primero selecciona un estudiante");
        return;
    }

    if (alert.confirmMessage("¿Estás seguro de actualizar el estado del pago?")) {
        String updateData = "UPDATE student SET status_payment = ?, date_update = CURRENT_TIMESTAMP WHERE student_id = ?";
        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(updateData);
            prepare.setString(1, payment_status.getSelectionModel().getSelectedItem());
            prepare.setString(2, payment_studentID.getText());
            prepare.executeUpdate();

            paymentDisplayData();

            alert.successMessage("Pago realizado correctamente");

            paymentClearBtn();

        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Error al actualizar el estado del pago: " + e.getMessage());
            }
        }
    }

    /*
    paymentClearBtn
    Permite limpiar los campos en el pago
    */
    public void paymentClearBtn() {

        payment_studentID.clear();
        payment_name.clear();
        payment_year.clear();
        payment_section.clear();
        payment_semester.clear();
        payment_payment.clear();

        ListData.path = "";

        payment_imageView.setImage(null);

        payment_status.getSelectionModel().clearSelection();
    }

    
    /*
    pagosSearch
    Buscador de pagos, que muestra resultados en la tabla
    */
    public void pagosSearch() {


        FilteredList<StudentData> filter = new FilteredList<>(paymentListData, e -> true);

        adminPagos_search.textProperty().addListener((Observable, oldValue, newValue) -> {
            filter.setPredicate(predicateCourseData -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String searchKey = newValue.toLowerCase();

                if (predicateCourseData.getStudentID().toString().contains(searchKey)) {
                    return true;
                } else if (predicateCourseData.getFullName().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateCourseData.getYear().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateCourseData.getSection().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateCourseData.getPayment().toString().contains(searchKey)) {
                    return true;
                } else if (predicateCourseData.getStatusPayment().toLowerCase().contains(searchKey)) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<StudentData> sortList = new SortedList<>(filter);
        sortList.comparatorProperty().bind(payment_tableView.comparatorProperty());
        payment_tableView.setItems(sortList);
    }
    
    /*
    imprimir()
    Imprime en un fichero .jasper los pagos de los estudiantes
    */
    public void imprimir(){
        
        connect = Database.connectDB();

        
        try{
            
            JasperDesign jDesign = JRXmlLoader.load("C:\\Users\\alex_\\OneDrive\\Documentos\\NetBeansProjects\\EduOrganizerHub\\src\\Informes\\informePago.jrxml");
            
            JasperReport jReport = JasperCompileManager.compileReport(jDesign);
            
            JasperPrint jPrint = JasperFillManager.fillReport(jReport, null, connect);
            
            JasperViewer jViewer = new JasperViewer(jPrint, false);
            
            jViewer.setTitle("Factura Pago de Tasas Estudiantes | EduOrganizer Hub");
            jViewer.show();

        }catch(Exception e){}
    }
    
    
   /*
    imprimirIndividual
    Imprime en un fichero .jasper el pago del estudiante seleccionado en la tabla
    */
    public void imprimirIndividual(){
        
        connect = Database.connectDB();
        alert.successMessage("¡Importante! Se va a imprimir la factura del último pago realizado");

        
        try{
            
            JasperDesign jDesign = JRXmlLoader.load("C:\\Users\\alex_\\OneDrive\\Documentos\\NetBeansProjects\\EduOrganizerHub\\src\\Informes\\pagoIndividual.jrxml");
            
            JasperReport jReport = JasperCompileManager.compileReport(jDesign);
            
            JasperPrint jPrint = JasperFillManager.fillReport(jReport, null, connect);
            
            JasperViewer jViewer = new JasperViewer(jPrint, false);
            
            jViewer.setTitle("Factura Pago de Tasas Estudiantes | EduOrganizer Hub");
            jViewer.show();

        }catch(Exception e){}
    }
    
   
    
    //Salarios
    
    /*
    salaryGetData
    Recupera los datos de los profesores cuyo estado de salario está Pendiente y están Activos
    */
    public ObservableList<TeacherData> salaryGetData() {

        ObservableList<TeacherData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM teacher WHERE salary_status = 'Pendiente' AND date_delete IS NULL AND status = 'Activo'";

        try {
            connect = Database.connectDB();
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            TeacherData tData;
            while (result.next()) {
                tData = new TeacherData(result.getInt("id"),
                        result.getString("teacher_id"), result.getString("full_name"),
                        result.getString("gender"), result.getDouble("salary"),
                        result.getString("salary_status"), result.getDate("date_insert"),
                        result.getDate("date_update"), result.getString("status"));

                listData.add(tData);

            }
        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Error al recuperar datos de salario: " + e.getMessage());
        }
        return listData;
    }

    private ObservableList<TeacherData> salaryListData;

    /*
    salaryDisplayData
    Muestra los datos de los profesores cuyo salario está pendiente de actualización
    en una tabla en la interfaz
    */
    public void salaryDisplayData() {
        salaryListData = salaryGetData();

        salary_col_teacherID.setCellValueFactory(new PropertyValueFactory<>("teacherID"));
        salary_col_fullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        salary_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        salary_col_salaryPerDay.setCellValueFactory(new PropertyValueFactory<>("salary"));
        salary_col_dateInsert.setCellValueFactory(new PropertyValueFactory<>("dateInsert"));
        salary_col_dateUpdate.setCellValueFactory(new PropertyValueFactory<>("dateUpdate"));
        salary_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        salary_tableView.setItems(salaryListData);
    }

    /*
    salarySelectItem
    Llena los campos de la interfaz de usuario con los datos del profesor seleccionado en la tabla de salarios
    */
    public void salarySelectItem() {

        TeacherData tData = salary_tableView.getSelectionModel().getSelectedItem();
        int num = salary_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        salary_teacherID.setText(tData.getTeacherID());
        salary_name.setText(tData.getFullName());
        salary_salaryPerDay.setText("" + tData.getSalary());
        salary_status.getSelectionModel().select(tData.getSalaryStatus());

    }

    /*
    salaryDisableFields
    Desactiva en la pantalla los campos ID, nombre y sueldo diario para que no se puedan modificar
    */
    public void salaryDisableFields() {
        salary_teacherID.setDisable(true);
        salary_name.setDisable(true);
        salary_salaryPerDay.setDisable(true);
    }

    /*
    salaryGetSalaryPerDay
    Obtiene el salario diario de un profesor específico basado en el ID del profesor
    */
    
    public double salaryGetSalaryPerDay() {
    double salaryPerDay = 0;

    String sql = "SELECT * FROM teacher WHERE teacher_id = ?";
    connect = Database.connectDB();

    try {
        prepare = connect.prepareStatement(sql);
        prepare.setString(1, salary_teacherID.getText());
        result = prepare.executeQuery();

        if (result.next()) {
            double monthlySalary = result.getDouble("salary");
            salaryPerDay = monthlySalary / 30;
        }

    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (result != null) {
                result.close();
            }
            if (prepare != null) {
                prepare.close();
            }
            if (connect != null) {
                connect.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    return salaryPerDay;
    }


    private long countDays;
    private double totalSalary;

    /*
    salaryCountDays
    Calcula el salario total de un profesor durante un período de tiempo específico, basado en su salario diario y el número de días trabajados
    */
    public void salaryCountDays() {
    LocalDate fromDate = salary_fromDate.getValue();
    LocalDate toDate = salary_toDate.getValue();

    if (fromDate.isAfter(toDate)) {
        alert.errorMessage("La fecha de inicio debe ser anterior a la fecha de fin.");
        salary_totalDays.setText("");
        salary_salary.setText("");
        return;
    }

    try {
        countDays = ChronoUnit.DAYS.between(fromDate, toDate);
        totalSalary = salaryGetSalaryPerDay() * countDays;

        salary_totalDays.setText(String.valueOf(countDays));
        salary_salary.setText(totalSalary + "€");

    } catch (Exception e) {
        e.printStackTrace();
        alert.errorMessage("Error al calcular el salario total.");
        }
    }


    /*
    salaryPayBtn
    Al pulsarlo con los campos completos, se actualiza el estatus del salario y la fecha de pago en BD
    */
    public void salaryPayBtn() {
    if (countDays == 0 || totalSalary == 0 || salary_teacherID.getText().isEmpty()) {
        alert.errorMessage("Por favor, completa todos los campos");
        return;
    }

    if (alert.confirmMessage("¿Realizar el pago?")) {
        String sql = "INSERT INTO salary "
                   + "(teacher_id, name, salary_per_day, total_days, salary_paid, date_paid)"
                   + " VALUES(?,?,?,?,?,?)";

        connect = Database.connectDB();

        try {
            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, salary_teacherID.getText());
            prepare.setString(2, salary_name.getText());
            prepare.setString(3, salary_salaryPerDay.getText());
            prepare.setString(4, "" + countDays);
            prepare.setString(5, "" + totalSalary);
            prepare.setString(6, "" + sqlDate);

            prepare.executeUpdate();

            String updateData = "UPDATE teacher SET salary_status = ?, date_update = CURRENT_TIMESTAMP WHERE teacher_id = ?";

            prepare = connect.prepareStatement(updateData);
            prepare.setString(1, salary_status.getSelectionModel().getSelectedItem());
            prepare.setString(2, salary_teacherID.getText());

            prepare.executeUpdate();

            salaryDisplayData();

            alert.successMessage("¡Nómina pagada correctamente!");

            salaryClear();
        } catch (Exception e) {
            e.printStackTrace();
            alert.errorMessage("Error al realizar el pago de la nómina.");
            }
        }
    }

    /*
    salaryClear
    Limpia los campos tras realizar el pago
    */
    public void salaryClear() {
        salary_fromDate.setValue(null);
        salary_toDate.setValue(null);
        salary_teacherID.clear();
        salary_name.clear();
        salary_salaryPerDay.clear();
        salary_totalDays.setText("");
        salary_salary.setText("");
        salary_status.getSelectionModel().clearSelection();
    }

    /*
    salaryStatusList
    Devuelve la lista en un combobox del estatus del salario: pagado o pendiente
    */
    public void salarySalaryStatusList() {

        List<String> listSS = new ArrayList<>();

        for (String data : ListData.paymentStatus) {
            listSS.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listSS);
        salary_status.setItems(listData);
    }

    /*
    salarySPGetdata
    Obtiene los datos de la tabla de salarios y creando objetos SalaryData a partir de ellos
    */
    public ObservableList<SalaryData> salarySPGetdata() {
        ObservableList<SalaryData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM salary";

        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            SalaryData sData;

            while (result.next()) {
                sData = new SalaryData(result.getInt("id"),
                        result.getString("teacher_id"),
                        result.getString("name"),
                        result.getDouble("salary_per_day"),
                        result.getInt("total_days"),
                        result.getDouble("salary_paid"),
                        result.getDate("date_paid"));

                listData.add(sData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<SalaryData> salarySPListData;

    /*
    salaryDisplayData
    Muestra en una tabla los salarios pagados de los profesores (nóminas)
    */
    public void salaryDisplaydata() {
        salarySPListData = salarySPGetdata();

        salary_SP_col_teacherID.setCellValueFactory(new PropertyValueFactory<>("teacherID"));
        salary_SP_col_salaryPaid.setCellValueFactory(new PropertyValueFactory<>("salaryPaid"));
        salary_SP_col_datePaid.setCellValueFactory(new PropertyValueFactory<>("datePaid"));

        salary_SP_tableView.setItems(salarySPListData);
    }

    
    /*
    imprimirSalarioIndividual
    Imprime en un fichero .jasper el pago del estudiante seleccionado en la tabla
    */

    public void imprimirSalarioIndividual(){
        
        connect = Database.connectDB();
        
       
        alert.successMessage("¡Importante! Se va a imprimir la factura de la última nómina pagada");
        
        
        
        try{
            
            JasperDesign jDesign = JRXmlLoader.load("C:\\Users\\alex_\\OneDrive\\Documentos\\NetBeansProjects\\EduOrganizerHub\\src\\Informes\\salarioProfesorIndividual.jrxml");
            
            JasperReport jReport = JasperCompileManager.compileReport(jDesign);
            
            JasperPrint jPrint = JasperFillManager.fillReport(jReport, null, connect);
            
            JasperViewer jViewer = new JasperViewer(jPrint, false);
            
            jViewer.setTitle("Factura Pago de Nóminas al Profesorado | EduOrganizer Hub");
            jViewer.show();

        }catch(Exception e){}
    }
    
    
    /*
    imprimirNominas()
    Imprime en un fichero .jasper todas las nóminas
    */
    public void imprimirNominas(){
        
        connect = Database.connectDB();
        
        try{
            
            JasperDesign jDesign = JRXmlLoader.load("C:\\Users\\alex_\\OneDrive\\Documentos\\NetBeansProjects\\EduOrganizerHub\\src\\Informes\\nominasProfesores.jrxml");
            
            JasperReport jReport = JasperCompileManager.compileReport(jDesign);
            
            JasperPrint jPrint = JasperFillManager.fillReport(jReport, null, connect);
            
            JasperViewer jViewer = new JasperViewer(jPrint, false);
            
            jViewer.setTitle("Factura Pago de Nóminas al Profesorado | EduOrganizer Hub");
            jViewer.show();

        }catch(Exception e){}
    }
    
    
    //Cambiar de ventana
    
    /*
    switchForm
    Controla qué formulario se muestra en función del botón que se presiona
    */
    public void switchForm(ActionEvent event) {

        if (event.getSource() == dashboard_btn) {

            dashboard_form.setVisible(true);
            addStudent_form.setVisible(false);
            addTeacher_form.setVisible(false);
            addCourse_form.setVisible(false);
            addSubject_form.setVisible(false);
            payment_form.setVisible(false);
            salary_form.setVisible(false);

            dashboardDisplayTS();
            dashboardDisplayTT();
            dashboardDisplaySRT();
            dashboardDisplayTI();
            dashboardDSChart();
            dashboardDTChart();
            dashboardDIChart();

        } else if (event.getSource() == addStudent_btn) {
            dashboard_form.setVisible(false);
            addStudent_form.setVisible(true);
            addTeacher_form.setVisible(false);
            addCourse_form.setVisible(false);
            addSubject_form.setVisible(false);
            payment_form.setVisible(false);
            salary_form.setVisible(false);
            
            
            addStudentDisplayData();
            
        } else if (event.getSource() == addTeacher_btn) {
            dashboard_form.setVisible(false);
            addStudent_form.setVisible(false);
            addTeacher_form.setVisible(true);
            addCourse_form.setVisible(false);
            addSubject_form.setVisible(false);
            payment_form.setVisible(false);
            salary_form.setVisible(false);

            addTeacherDisplayData();
            addTeacherGenderList();
            addTeacherSSList();
            addTeacherEYList();
            addTeacherDepartmentList();
            addTeacherStatusList();
            addTeacherDisplayTeacherID();
           
            
        } else if (event.getSource() == addCourse_btn) {
            dashboard_form.setVisible(false);
            addStudent_form.setVisible(false);
            addTeacher_form.setVisible(false);
            addCourse_form.setVisible(true);
            addSubject_form.setVisible(false);
            payment_form.setVisible(false);
            salary_form.setVisible(false);

            addCourseDisplayData();
            addCourseStatus();
            cursoSearch();
            
        } else if (event.getSource() == addSubject_btn) {
            dashboard_form.setVisible(false);
            addStudent_form.setVisible(false);
            addTeacher_form.setVisible(false);
            addCourse_form.setVisible(false);
            addSubject_form.setVisible(true);
            payment_form.setVisible(false);
            salary_form.setVisible(false);

            addSubjectDisplayData();
            addSubjectCourseList();
            addSubjectStatusList();
        } else if (event.getSource() == payment_btn) {
            dashboard_form.setVisible(false);
            addStudent_form.setVisible(false);
            addTeacher_form.setVisible(false);
            addCourse_form.setVisible(false);
            addSubject_form.setVisible(false);
            payment_form.setVisible(true);
            salary_form.setVisible(false);

            paymentDisplayData();
            paymentPaymentStatusList();
            paymentDisableFields();
        } else if (event.getSource() == salary_btn) {
            dashboard_form.setVisible(false);
            addStudent_form.setVisible(false);
            addTeacher_form.setVisible(false);
            addCourse_form.setVisible(false);
            addSubject_form.setVisible(false);
            payment_form.setVisible(false);
            salary_form.setVisible(true);

            salaryDisplayData();
            salaryDisableFields();
            salarySalaryStatusList();
            salaryDisplaydata();
        }

    }

    /*
    displayGreet
    Muestra un mensaje de bienvenida para el usuario de la interfaz
    */
    public void displayGreet() {
        String username = ListData.admin_username;
        username = username.substring(0, 1).toUpperCase() + username.substring(1);

        greet_username.setText("Bienvenido, " + username);
    }
    
    /*
    logoutBtn
    Permite cerrar la sesión y volver al login
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

        
        displayGreet();

        //Gráficos
        dashboardDisplayTS();
        dashboardDisplayTT();
        dashboardDisplaySRT();
        dashboardDisplayTI();
        dashboardDSChart();
        dashboardDTChart();
        dashboardDIChart();
  
    }
    
}