/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estudiante;

import BaseDatos.*;
import Login.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.*;
import java.sql.*;
import java.text.*;
import java.time.*;
import java.util.*;
import java.util.Date;
import javafx.collections.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 *
 * @author Alejandro Sanz Mediavilla
 */
public class AltaEstudianteController implements Initializable {

    @FXML
    private AnchorPane main_form;

    @FXML
    private TextField student_number;

    @FXML
    private TextField student_name;

    @FXML
    private DatePicker student_birthDate;

    @FXML
    private ComboBox<String> student_year;

    @FXML
    private ComboBox<String> student_course;

    @FXML
    private ComboBox<String> student_section;

    @FXML
    private TextField student_pay;

    @FXML
    private ComboBox<String> student_payment;

    @FXML
    private ImageView student_imageView;

    @FXML
    private Button student_importBtn;

    @FXML
    private Button student_addBtn;

    @FXML
    private Button student_updateBtn;

    @FXML
    private ComboBox<String> student_status;

    @FXML
    private Label student_price;

    @FXML
    private ComboBox<String> student_semester;

    @FXML
    private ComboBox<String> student_gender;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private MensajeAlerta alert = new MensajeAlerta();

    private Image image;
    
    /*
    METODO addBtn:
    Controlador de evento para el botón de "Agregar" en la interfaz de usuario. 
    Su función es verificar si todos los campos necesarios están completos y luego agregar un nuevo registro de estudiante a la base de datos.
    */
    
   


   public void addBtn() {
    // Lista de los campos que deseas validar
    List<TextField> fieldsToValidate = Arrays.asList(student_number, student_name, student_pay);
    List<ComboBox<String>> comboBoxesToValidate = Arrays.asList(student_year, student_course, student_section, 
                                               student_payment, student_status, student_semester, 
                                               student_gender);
    
    // Validación de campos
    for (TextField field : fieldsToValidate) {
        if (field.getText().isEmpty()) {
            alert.errorMessage("Por favor complete todos los campos.");
            return;
        }
    }
    
    for (ComboBox<String> comboBox : comboBoxesToValidate) {
        if (comboBox.getSelectionModel().isEmpty()) {
            alert.errorMessage("Por favor complete todos los campos.");
            return;
        }
    }
    
    // Validación de fecha de nacimiento
    if (student_birthDate.getValue() == null) {
        alert.errorMessage("Por favor seleccione la fecha de nacimiento.");
        return;
    }

    try (Connection connect = Database.connectDB()) {
        // Verificar si el número de estudiante ya existe
        String checkStudentNum = "SELECT * FROM student WHERE student_id = ?";
        try (PreparedStatement checkStudentNumStmt = connect.prepareStatement(checkStudentNum)) {
            checkStudentNumStmt.setString(1, student_number.getText());
            try (ResultSet result = checkStudentNumStmt.executeQuery()) {
                if (result.next()) {
                    alert.errorMessage("El número de estudiante: " + student_number.getText() + " ya existe. Modifiquelo por uno distinto si quieres pulsar Añadir. Si no, pulsa Editar.");
                    return;
                }
            }
        }

        // Insertar datos del estudiante
        String insertData = "INSERT INTO student "
                + "(student_id, full_name, gender, birth_date, year, age, course, section, semester, payment"
                + ", status_payment, image, date_insert, status) "
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement prepare = connect.prepareStatement(insertData)) {
            prepare.setString(1, student_number.getText());
            prepare.setString(2, student_name.getText());
            prepare.setString(3, student_gender.getSelectionModel().getSelectedItem());
            prepare.setString(4, String.valueOf(student_birthDate.getValue()));
            prepare.setString(5, student_year.getSelectionModel().getSelectedItem());
            prepare.setString(6, String.valueOf(getAge));
            prepare.setString(7, student_course.getSelectionModel().getSelectedItem());
            prepare.setString(8, student_section.getSelectionModel().getSelectedItem());
            prepare.setString(9, student_semester.getSelectionModel().getSelectedItem());
            prepare.setString(10, String.valueOf(price));
            prepare.setString(11, student_payment.getSelectionModel().getSelectedItem());

            String path = ListData.path;
            path = path.replace("\\", "\\\\");

            prepare.setString(12, path);

            java.sql.Date sqlDate = java.sql.Date.valueOf(LocalDate.now());
            prepare.setDate(13, sqlDate);

            prepare.setString(14, student_status.getSelectionModel().getSelectedItem());

            prepare.executeUpdate();

            // Copiar la imagen del estudiante
            Path transfer = Paths.get(path);
            Path copy = Paths.get("C:\\Users\\alex_\\OneDrive\\Documentos\\NetBeansProjects\\EduOrganizerHub\\src\\Almacen_Estudiante\\nenerandom.JPG"
                    + student_number.getText() + ".jpg");
            Files.copy(transfer, copy, StandardCopyOption.REPLACE_EXISTING);

            alert.successMessage("¡Estudiante añadido correctamente!");

            clearFields();
            
            
            // Cerrar la ventana
            Stage stage = (Stage) student_number.getScene().getWindow();
            stage.close();
        }
    } catch (SQLException | IOException e) {
        e.printStackTrace();
        alert.errorMessage("Error al añadir el estudiante. Por favor, inténtelo de nuevo.");
        }
    }
    
   

   /*
   updateBtn: método para modificar el estudiante
   */
   public void updateBtn() {
    if (student_number.getText().isEmpty()
            || student_name.getText().isEmpty()
            || student_year.getSelectionModel().isEmpty()
            || student_course.getSelectionModel().isEmpty()
            || student_section.getSelectionModel().isEmpty()
            || student_pay.getText().isEmpty()
            || student_payment.getSelectionModel().isEmpty()
            || student_status.getSelectionModel().isEmpty()
            || ListData.path == null || "".equals(ListData.path)
            || student_birthDate.getValue() == null
            || student_semester.getSelectionModel().isEmpty()
            || student_gender.getSelectionModel().isEmpty()) {
        alert.errorMessage("Por favor, completa todos los campos en blanco");
        return;
    }

    try (Connection connect = Database.connectDB()) {
        
        // Verificar si el número de estudiante existe
        String checkStudentNum = "SELECT * FROM student WHERE student_id = ?";
        try (PreparedStatement checkStudentNumStmt = connect.prepareStatement(checkStudentNum)) {
            checkStudentNumStmt.setString(1, student_number.getText());
            try (ResultSet result = checkStudentNumStmt.executeQuery()) {
                if (!result.next()) {
                    // Si el número de estudiante no existe, mostrar un mensaje de error
                    alert.errorMessage("El número de estudiante: " + student_number.getText() + " no existe. No lo puedes editar. Pulsa en Añadir si quieres hacer el alta.");
                    return;
                }
            }
        }
     
        
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        String path = ListData.path;
        path = path.replace("\\", "\\\\");
        priceList();
        if (alert.confirmMessage("¿Seguro que quieres editar el estudiante con ID: "
                + student_number.getText())) {
            String updateData = "UPDATE student SET "
                    + "full_name = ?, birth_date = ?, age = ?, year = ?, gender = ?, course = ?, "
                    + "semester = ?, section = ?, payment = ?, status_payment = ?, image = ?, "
                    + "date_update = ?, status = ? WHERE student_id = ?";

            try (PreparedStatement prepare = connect.prepareStatement(updateData)) {
                prepare.setString(1, student_name.getText());
                prepare.setString(2, String.valueOf(student_birthDate.getValue()));
                prepare.setString(3, String.valueOf(getAge));
                prepare.setString(4, student_year.getSelectionModel().getSelectedItem());
                prepare.setString(5, student_gender.getSelectionModel().getSelectedItem());
                prepare.setString(6, student_course.getSelectionModel().getSelectedItem());
                prepare.setString(7, student_semester.getSelectionModel().getSelectedItem());
                prepare.setString(8, student_section.getSelectionModel().getSelectedItem());
                prepare.setString(9, String.valueOf(price));
                prepare.setString(10, student_payment.getSelectionModel().getSelectedItem());
                prepare.setString(11, path);
                prepare.setString(12, String.valueOf(sqlDate));
                prepare.setString(13, student_status.getSelectionModel().getSelectedItem());
                prepare.setString(14, student_number.getText());

                prepare.executeUpdate();

                Path transfer = Paths.get(path);
                Path copy = Paths.get("C:\\Users\\alex_\\OneDrive\\Documentos\\NetBeansProjects\\EduOrganizerHub\\src\\Almacen_Estudiante\\"
                        + student_number.getText() + ".jpg");

                Files.copy(transfer, copy, StandardCopyOption.REPLACE_EXISTING);

                alert.successMessage("¡Estudiante modificado correctamente!");

                clearFields();

                // Cerrar la ventana
                Stage stage = (Stage) student_number.getScene().getWindow();
                stage.close();

            } catch (IOException e) {
                e.printStackTrace();
                alert.errorMessage("Error al copiar la imagen. Por favor, inténtelo de nuevo.");
            }
        } else {
            alert.errorMessage("Cancelado");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        alert.errorMessage("Error al conectar con la base de datos. Por favor, inténtelo de nuevo.");
    }
}


   /*
   Método para limpiar los campos (dejarlos vacíos)
   */
    public void clearFields() {
        displayStudentNumber();

        student_name.clear();
        student_birthDate.setValue(null);
        student_year.getSelectionModel().clearSelection();
        student_course.getSelectionModel().clearSelection();
        student_gender.getSelectionModel().clearSelection();
        student_section.getSelectionModel().clearSelection();
        student_semester.getSelectionModel().clearSelection();
        student_payment.getSelectionModel().clearSelection();
        student_status.getSelectionModel().clearSelection();
        student_pay.clear();

        ListData.path = "";

        student_imageView.setImage(null);

    }

    private int getAge = 0;

    /*
    Método para contar la edad
    */
    public void countAge() {
        if (student_birthDate.getValue() != null) {
            LocalDate birthDate = student_birthDate.getValue();
            int age = Period.between(birthDate, LocalDate.now()).getYears();

            getAge = age;

            System.out.println(getAge);
        }
    }

    /*
    Método para importar la imágen
    */
    public void importBtn() {

        FileChooser open = new FileChooser();
        open.getExtensionFilters().add(new ExtensionFilter("Open Image", "*.jpg", "*.jpeg", "*.png"));

        File file = open.showOpenDialog(student_importBtn.getScene().getWindow());

        if (file != null) {
            ListData.path = file.getAbsolutePath();

            image = new Image(file.toURI().toString(), 100, 113, false, true);
            student_imageView.setImage(image);
        }
    }

    /*
    Método para mostrar el número del estudiante
    */
    public void displayStudentNumber() {
        LoginController control = new LoginController();

        int getnumber = control.studentIDGenerator();

        Date date = new Date();

        SimpleDateFormat format = new SimpleDateFormat("yyyy");

        String getyear = format.format(date) + "0000";

        int getStudentNum = Integer.parseInt(getyear) + getnumber;

        student_number.setText(String.valueOf(getStudentNum));

    }


    /*
    Método para obtener la lista de años
    */
    public void yearList() {
        List<String> listY = new ArrayList<>();

        for (String data : ListData.year) {
            listY.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listY);
        student_year.setItems(listData);
    }

    /*
    Método para seleccionar la lista de cursos:
    */
    public void courseList() {
    String sql = "SELECT * FROM course WHERE date_delete IS NULL and status = 'Disponible'";

    try (Connection connect = Database.connectDB();
         PreparedStatement prepare = connect.prepareStatement(sql);
         ResultSet result = prepare.executeQuery()) {

        ObservableList<String> listData = FXCollections.observableArrayList();

        while (result.next()) {
            listData.add(result.getString("course"));
        }
        student_course.setItems(listData);

    } catch (SQLException e) {
        e.printStackTrace();
        alert.errorMessage("Error al cargar la lista de cursos. Por favor, inténtelo de nuevo.");
    }

    priceList();
}

    private double price = 0;

    /*
    Obtiene el precio del curso seleccionado por el estudiante y muestra su precio en student_pay
    */
    public void priceList() {
    String selectedCourse = student_course.getSelectionModel().getSelectedItem();
    if (selectedCourse == null) {
        return;
    }

    String selectData = "SELECT * FROM course WHERE course = ? AND date_delete IS NULL";

    try (Connection connect = Database.connectDB();
         PreparedStatement prepare = connect.prepareStatement(selectData)) {

        prepare.setString(1, selectedCourse);

        try (ResultSet result = prepare.executeQuery()) {
            if (result.next()) {
                price = result.getDouble("price");
                System.out.println(price);
                student_pay.setText("$" + String.valueOf(price));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        alert.errorMessage("Error al obtener el precio del curso. Por favor, inténtelo de nuevo.");
        }
    }

    /*
    Establece los valores de los campos en la interfaz de usuario con los datos del estudiante seleccionado.
    */
    
    public void setFields() {
    if (ListData.temp_studentNumber == null) {
        return;
    }

    try (Connection connect = Database.connectDB()) {
        String sql = "SELECT * FROM student WHERE student_id = ?";
        prepare = connect.prepareStatement(sql);
        prepare.setString(1, ListData.temp_studentNumber);
        result = prepare.executeQuery();

        if (result.next()) {
            student_number.setText(ListData.temp_studentNumber);
            student_name.setText(result.getString("full_name"));
            student_birthDate.setValue(LocalDate.parse(result.getString("birth_date")));
            student_year.getSelectionModel().select(result.getString("year"));
            student_course.getSelectionModel().select(result.getString("course"));
            student_section.getSelectionModel().select(result.getString("section"));
            student_gender.getSelectionModel().select(result.getString("gender"));
            student_semester.getSelectionModel().select(result.getString("semester"));
            student_payment.getSelectionModel().select(result.getString("status_payment"));
            student_status.getSelectionModel().select(result.getString("status"));
            student_pay.setText(result.getString("payment"));

            ListData.path = result.getString("image");
            image = new Image("File:" + ListData.path, 100, 113, false, true);
            student_imageView.setImage(image);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        alert.errorMessage("Error al obtener los datos del estudiante. Por favor, inténtelo de nuevo.");
    } catch (Exception e) {
        e.printStackTrace();
        }
    }

    /*
    Carga la lista de secciones en un ComboBox en la interfaz de usuario.
    */
    public void sectionList() {
        List<String> listS = new ArrayList<>();

        for (String data : ListData.section) {
            listS.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listS);
        student_section.setItems(listData);
    }

    /*
    Carga la lista de estados de pago en un ComboBox en la interfaz de usuario.
    */
    public void statusPaymentList() {
        List<String> listSP = new ArrayList<>();

        for (String data : ListData.paymentStatus) {
            listSP.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listSP);
        student_payment.setItems(listData);
    }

    /*
    Carga la lista de estados en un combobox en la interfaz de usuario
    */
    public void statusList() {
        List<String> listS = new ArrayList<>();

        for (String data : ListData.status) {
            listS.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listS);
        student_status.setItems(listData);
    }

    /*
    Carga la lista de semestres en un combobox
    */
    public void semesterList() {
        List<String> listS = new ArrayList<>();

        for (String data : ListData.semester) {
            listS.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listS);
        student_semester.setItems(listData);
    }

    /*
    Carga la lista de géneros en un combobox
    */
    public void genderList() {
        List<String> listG = new ArrayList<>();

        for (String data : ListData.gender) {
            listG.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listG);
        student_gender.setItems(listData);
    }
    
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        yearList();
        courseList();
        sectionList();
        statusPaymentList();
        statusList();
        semesterList();
        genderList();

        displayStudentNumber();

        setFields();
    }

}