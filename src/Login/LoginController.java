/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;


import BaseDatos.Database;
import BaseDatos.ListData;

/**
 *
 * @author Alejandro Sanz Mediavilla
 */
public class LoginController implements Initializable {

    @FXML
    private AnchorPane login_form;

    @FXML
    private TextField login_username;

    @FXML
    private PasswordField login_password;

    @FXML
    private Button login_btn;

    @FXML
    private ComboBox<String> login_role;

    @FXML
    private AnchorPane admin_form;

    @FXML
    private TextField admin_username;

    @FXML
    private PasswordField admin_password;

    @FXML
    private Button admin_signupBtn;

    @FXML
    private Hyperlink admin_signIn;

    @FXML
    private PasswordField admin_cPassword;

    @FXML
    private AnchorPane student_form;

    @FXML
    private TextField student_email;

    @FXML
    private TextField student_username;

    @FXML
    private PasswordField student_password;

    @FXML
    private Button student_signupBtn;

    @FXML
    private Hyperlink student_signIn;

    @FXML
    private PasswordField student_cPassword;

    @FXML
    private AnchorPane teacher_form;

    @FXML
    private TextField teacher_email;

    @FXML
    private TextField teacher_username;

    @FXML
    private PasswordField teacher_password;

    @FXML
    private Button teacher_signupBtn;

    @FXML
    private Hyperlink teacher_signIn;

    @FXML
    private PasswordField teacher_cPassword;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;

    private MensajeAlerta alert = new MensajeAlerta();

    
    
    /*
    Método para hacer LOGIN
    */
    
    public void loginAccount() {
     // Verifica si los campos de nombre de usuario y contraseña están vacíos
     if (login_username.getText().isEmpty() || login_password.getText().isEmpty()) {
         alert.errorMessage("Por favor, completa todos los campos en blanco");
         return; // Sale del método si hay campos vacíos
     }

     // Consulta SQL para buscar un usuario con el nombre de usuario y contraseña proporcionados
     String selectData = "SELECT * FROM users WHERE username = ? AND password = ?";

     // Establece la conexión a la base de datos
     try (Connection connect = Database.connectDB();
          PreparedStatement prepare = connect.prepareStatement(selectData)) {

         // Establece los parámetros de la consulta
         prepare.setString(1, login_username.getText());
         prepare.setString(2, login_password.getText());

         // Ejecuta la consulta
         try (ResultSet result = prepare.executeQuery()) {
             // Si no se encuentra un usuario con las credenciales proporcionadas
             if (!result.next()) {
                 alert.errorMessage("Nombre de usuario o contraseña incorrectos");
                 return; // Sale del método si las credenciales son incorrectas
             }

             // Obtiene el rol del usuario
             String role = result.getString("role");

             // Realiza acciones dependiendo del rol del usuario
             switch (role) {
                 case "Admin":
                     handleAdminLogin();
                     break;
                 case "Student":
                     handleStudentLogin(result);
                     break;
                 case "Teacher":
                     handleTeacherLogin(result);
                     break;
                 default:
                     alert.errorMessage("Rol de usuario no válido");
             }
         }
     } catch (SQLException e) {
         alert.errorMessage("Error al realizar el inicio de sesión: " + e.getMessage());
     }
 }

    // Método para manejar el inicio de sesión de un administrador
    private void handleAdminLogin() {
        ListData.admin_username = login_username.getText();
        // ENLACE FORM PRINCIPAL PARA ADMIN
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Vistas/AdminMainForm.fxml"));
            Stage stage = new Stage();
            stage.setTitle("EduOrganizer Hub | Portal Admin");
            stage.setScene(new Scene(root));
            stage.show();
            // Esconder el formulario de login
            login_btn.getScene().getWindow().hide();
        } catch (IOException e) {
            alert.errorMessage("Error al cargar el formulario principal del administrador: " + e.getMessage());
        }
    }

    // Método para manejar el inicio de sesión de un estudiante
    private void handleStudentLogin(ResultSet result) throws SQLException {
        String tempStudentID = result.getString("student_id");
        String checkData = "SELECT * FROM student WHERE student_id = ?";
        try (Connection connect = Database.connectDB();
             PreparedStatement statement = connect.prepareStatement(checkData)) {
            statement.setString(1, tempStudentID);
            try (ResultSet studentResult = statement.executeQuery()) {
                if (studentResult.next()) {
                    if (studentResult.getString("status").equals("Revisando")) {
                        alert.errorMessage("¡Necesitas la aprobación de un Administrador!");
                    } else {
                        ListData.student_username = login_username.getText();
                        loadMainForm("/Vistas/EstudianteMainForm.fxml", "EduOrganizer Hub | Portal Estudiante");
                    }
                }
            }
        }
    }

    // Método para manejar el inicio de sesión de un profesor
    private void handleTeacherLogin(ResultSet result) throws SQLException {
        String tempTeacherID = result.getString("teacher_id");
        String checkData = "SELECT * FROM teacher WHERE teacher_id = ?";
        try (Connection connect = Database.connectDB();
             PreparedStatement statement = connect.prepareStatement(checkData)) {
            statement.setString(1, tempTeacherID);
            try (ResultSet teacherResult = statement.executeQuery()) {
                if (teacherResult.next()) {
                    if (teacherResult.getString("status").equals("Revisando")) {
                        alert.errorMessage("¡Necesitas la aprobación de un administrador!");
                    } else {
                        ListData.teacher_username = login_username.getText();
                        loadMainForm("/Vistas/ProfesorMainForm.fxml", "EduOrganizer Hub | Portal Profesorado");
                    }
                }
            }
        }
    }

    // Método para cargar el formulario principal
    private void loadMainForm(String resource, String title) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(resource));
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();
            // Esconder el formulario de login
            login_btn.getScene().getWindow().hide();
        } catch (IOException e) {
            alert.errorMessage("Error al cargar el formulario principal: " + e.getMessage());
        }
    }
    
    /*
    Método para Registrar al Administrador
    */
    public void registerAdmin() {
    if (admin_username.getText().isEmpty() || admin_password.getText().isEmpty()
            || admin_cPassword.getText().isEmpty()) {
        alert.errorMessage("Por favor, completa todos los campos en blanco");
        return;
    }
    
    String username = admin_username.getText();
    String password = admin_password.getText();
    String confirmPassword = admin_cPassword.getText();
    
    // Validar si las contraseñas coinciden
    if (!password.equals(confirmPassword)) {
        alert.errorMessage("Las contraseñas no coinciden");
        return;
    }

    // Validar la longitud mínima de la contraseña
    if (password.length() < 8) {
        alert.errorMessage("Contraseña inválida. Debe tener al menos 8 caracteres.");
        return;
    }

    try (Connection connection = Database.connectDB()) {
        // Verificar si el nombre de usuario ya existe
        String selectQuery = "SELECT * FROM users WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setString(1, username);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    alert.errorMessage(username + " ya existe");
                    return;
                }
            }
        }

        // Insertar el nuevo usuario en la base de datos
        String insertQuery = "INSERT INTO users (username, password, role, date) VALUES (?, ?, 'Admin', ?)";
        Date currentDate = new Date();
        java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
        try (PreparedStatement prepare = connection.prepareStatement(insertQuery)) {
            prepare.setString(1, username);
            prepare.setString(2, password);
            prepare.setDate(3, sqlDate);

            prepare.executeUpdate();
        }

        alert.successMessage("Usuario registrado correctamente!");
        login_form.setVisible(true);
        admin_form.setVisible(false);
    } catch (SQLException e) {
        e.printStackTrace();
        alert.errorMessage("Error al registrar el usuario: " + e.getMessage());
        }
    }

    /*
    Método para Registrar un Estudiante
    */
    public void registerStudent() {

        if (student_email.getText().isEmpty() || student_username.getText().isEmpty()
                || student_password.getText().isEmpty()
                || student_cPassword.getText().isEmpty()) {
            alert.errorMessage("Por favor, completa todos los campos en blanco");
            
        } else {
            connect = Database.connectDB();

            String selectData = "SELECT * FROM users WHERE username = '"
                    + student_username.getText() + "'";

            try {
                statement = connect.createStatement();
                result = statement.executeQuery(selectData);

                if (result.next()) {
                    alert.errorMessage("El estudiante " +student_username.getText() + " ya existe");
                } else {
                    if (!student_password.getText().equals(student_cPassword.getText())) {
                        alert.errorMessage("Las contraseñas no coinciden");
                    } else if (student_password.getText().length() < 8) {
                        alert.errorMessage("Contraseña inválida, mínimo 8 caracteres");
                    } else {
                        String insertData = "INSERT INTO users (email, username, password, role, student_id, date) "
                                + "VALUES(?,?,?,?,?,?)";

                        Date date = new Date();
                        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                        SimpleDateFormat format = new SimpleDateFormat("yyyy");
                        String getYear = format.format(date);
                        String studentNum = getYear + "0000";
                        int sNum = Integer.parseInt(studentNum) + studentIDGenerator();

                        prepare = connect.prepareStatement(insertData);
                        prepare.setString(1, student_email.getText());
                        prepare.setString(2, student_username.getText());
                        prepare.setString(3, student_password.getText());
                        prepare.setString(4, "Student");
                        prepare.setString(5, String.valueOf(sNum));
                        prepare.setString(6, String.valueOf(sqlDate));

                        prepare.executeUpdate();

                        String insertStudent = "INSERT INTO student (student_id, date_insert, status) "
                                + "VALUES(?,?,?)";

                        prepare = connect.prepareStatement(insertStudent);
                        prepare.setString(1, String.valueOf(sNum));
                        prepare.setString(2, String.valueOf(sqlDate));
                        prepare.setString(3, "Revisando");

                        prepare.executeUpdate();

                        alert.successMessage("Usuario registrado correctamente!");

                        login_form.setVisible(true);
                        student_form.setVisible(false);
                    }
                }

                connect.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
 

    private int studentID;
    
    /*
    studentIDGenerator
    Metodo para dar de alta el id de un estudiante
    */
    public int studentIDGenerator() {
        String selectData = "SELECT MAX(id) FROM student";

        connect = Database.connectDB();

        int temp_studentID = 0;

        try {
            statement = connect.createStatement();
            result = statement.executeQuery(selectData);

            if (result.next()) {
                temp_studentID = result.getInt("MAX(id)");
            }

            if (temp_studentID == 0) {
                studentID = 1;
            } else {
                studentID = temp_studentID + 1;
                System.out.println(studentID);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return studentID;
    }



 
    /*
    Método para registrar al profesor
    */
    public void registerTeacher() {

        if (teacher_email.getText().isEmpty() || teacher_username.getText().isEmpty()
                || teacher_password.getText().isEmpty()
                || teacher_cPassword.getText().isEmpty()) {
            alert.errorMessage("Por favor, completa todos los campos en blanco");
        } else {
            connect = Database.connectDB();

            String selectData = "SELECT * FROM users WHERE username = '"
                    + teacher_username.getText() + "'";

            try {
                statement = connect.createStatement();
                result = statement.executeQuery(selectData);

                if (result.next()) {
                    alert.errorMessage(teacher_username.getText() + " ya existe");
                } else if (!teacher_password.getText().equals(teacher_cPassword.getText())) {
                    alert.errorMessage("Las contraseñas no coinciden");
                } else if (teacher_password.getText().length() < 8) {
                    alert.errorMessage("Contraseña inválida, mínimo 8 caracteres");
                } else {

                    String temp_teacherID = "TID-" + String.valueOf(teacherIDGenerator());

                    String insertData = "INSERT INTO users "
                            + "(email, username, password, role, teacher_id, date) "
                            + "VALUES(?,?,?,?,?,?)";

                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, teacher_email.getText());
                    prepare.setString(2, teacher_username.getText());
                    prepare.setString(3, teacher_password.getText());
                    prepare.setString(4, "Teacher");
                    prepare.setString(5, temp_teacherID);
                    prepare.setString(6, String.valueOf(sqlDate));

                    prepare.executeUpdate();

                    String insertStudent = "INSERT INTO teacher "
                            + "(teacher_id, date_insert, status) "
                            + "VALUES(?,?,?)";

                    prepare = connect.prepareStatement(insertStudent);
                    prepare.setString(1, temp_teacherID);
                    prepare.setString(2, String.valueOf(sqlDate));
                    prepare.setString(3, "Revisando");

                    prepare.executeUpdate();

                    alert.successMessage("Usuario registrado correctamente");

                    login_form.setVisible(true);
                    teacher_form.setVisible(false);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private int teacherID = 0;

    /*
    Método para generar el ID asociado al profesor
    */
    public int teacherIDGenerator() {

        String sql = "SELECT MAX(id) FROM teacher";

        connect = Database.connectDB();
        int temp_teacherID = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                temp_teacherID = result.getInt("MAX(id)");
            }

            if (temp_teacherID == 0) {
                teacherID = 1;
            } else {
                teacherID = temp_teacherID + 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return teacherID;
    }

    public void roleList() {

        List<String> listR = new ArrayList<>();

        for (String data : ListData.role) {
            listR.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listR);
        login_role.setItems(listData);

    }

    public void signInForm() {
        login_form.setVisible(true);
        admin_form.setVisible(false);
        student_form.setVisible(false);
        teacher_form.setVisible(false);
    }

    /*
    switchForm
    Método para mostrar las diferentes ventanas según la acción realizada
    */
    public void switchForm(ActionEvent event) {

        switch (login_role.getSelectionModel().getSelectedItem()) {
            case "Administrador":
                login_form.setVisible(false);
                admin_form.setVisible(true);
                student_form.setVisible(false);
                teacher_form.setVisible(false);
                break;
            case "Profesor":
                login_form.setVisible(false);
                admin_form.setVisible(false);
                student_form.setVisible(false);
                teacher_form.setVisible(true);
                break;
            case "Estudiante":
                login_form.setVisible(false);
                admin_form.setVisible(false);
                student_form.setVisible(true);
                teacher_form.setVisible(false);
                break;
            default:
                break;
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        roleList();
    }

}