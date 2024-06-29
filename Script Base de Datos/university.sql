-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 26-05-2024 a las 00:17:12
-- Versión del servidor: 10.1.38-MariaDB
-- Versión de PHP: 5.6.40

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `university`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `course`
--

CREATE TABLE `course` (
  `id` int(11) NOT NULL,
  `course` varchar(255) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `date_insert` date DEFAULT NULL,
  `date_update` date DEFAULT NULL,
  `date_delete` date DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `course`
--

INSERT INTO `course` (`id`, `course`, `department`, `price`, `date_insert`, `date_update`, `date_delete`, `status`) VALUES
(9, 'Desarrollo de Aplicaciones Multiplataforma', 'Informática', 1400, '2024-05-13', '2024-05-13', NULL, 'Disponible'),
(11, 'Administración Sistemas Informáticos en Red', 'Informática', 1500, '2024-05-13', '2024-05-13', '2024-05-13', 'No disponible'),
(12, 'Higiene Bucodental', 'Salud', 1400, '2024-05-13', '2024-05-25', NULL, 'Disponible');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `salary`
--

CREATE TABLE `salary` (
  `id` int(11) NOT NULL,
  `teacher_id` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `salary_per_day` double DEFAULT NULL,
  `total_days` int(11) DEFAULT NULL,
  `salary_paid` double DEFAULT NULL,
  `date_paid` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `salary`
--

INSERT INTO `salary` (`id`, `teacher_id`, `name`, `salary_per_day`, `total_days`, `salary_paid`, `date_paid`) VALUES
(21, 'TID-1', 'Ramiro González Ruiz', 1350, 1, 45, '2024-05-13'),
(22, 'TID-33', 'Jesús Gil Peña', 2000, 3, 200, '2024-05-25'),
(23, 'TID-34', 'Jorge Javier Amadeo Abúndez', 1245, 24, 996, '2024-05-25');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `student`
--

CREATE TABLE `student` (
  `id` int(11) NOT NULL,
  `student_id` bigint(20) DEFAULT NULL,
  `full_name` varchar(250) DEFAULT NULL,
  `gender` varchar(250) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `year` varchar(250) DEFAULT NULL,
  `course` varchar(250) DEFAULT NULL,
  `section` varchar(250) DEFAULT NULL,
  `semester` varchar(250) DEFAULT NULL,
  `payment` double DEFAULT NULL,
  `status_payment` varchar(250) DEFAULT NULL,
  `image` varchar(500) DEFAULT NULL,
  `date_insert` date DEFAULT NULL,
  `date_update` date DEFAULT NULL,
  `date_delete` date DEFAULT NULL,
  `status` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `student`
--

INSERT INTO `student` (`id`, `student_id`, `full_name`, `gender`, `birth_date`, `age`, `year`, `course`, `section`, `semester`, `payment`, `status_payment`, `image`, `date_insert`, `date_update`, `date_delete`, `status`) VALUES
(40, 20240001, 'Julia Rojas Pérez', 'Femenino', '1999-11-12', 0, '3er Año', 'Higiene Bucodental', 'A', '2º Semestre', 1500, 'Pagado', 'C:\\\\\\\\\\\\\\\\Users\\\\\\\\\\\\\\\\alex_\\\\\\\\\\\\\\\\Downloads\\\\\\\\\\\\\\\\julia.JPG', '2024-05-13', '2024-05-13', NULL, 'Activo'),
(41, 20240041, 'Rodrigo Suárez Rojas', 'Masculino', '2005-05-14', 18, '1er Año', 'Desarrollo de Aplicaciones Multiplataforma', 'A', '1er Semestre', 1400, 'Pagado', 'C:\\\\Users\\\\alex_\\\\Downloads\\\\rodrigosuarez.JPG', '2024-05-13', '2024-05-18', NULL, 'Activo'),
(42, 20240042, 'Pedro Mendez Sousa', 'Masculino', '2004-05-08', 20, '2º Año', 'Desarrollo de Aplicaciones Multiplataforma', 'A', '1er Semestre', 1400, 'Pagado', 'C:\\\\Users\\\\alex_\\\\Downloads\\\\pedro.JPG', '2024-05-18', '2024-05-25', NULL, 'Activo'),
(44, 20240043, 'Jose Miguel Antúnez Gil', 'Masculino', '2024-05-08', 0, '1er Año', 'Desarrollo de Aplicaciones Multiplataforma', 'A', '1er Semestre', 1400, 'Pendiente', 'C:\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\Users\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\alex_\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\Downloads\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\ramiro.JPG', '2024-05-18', '2024-05-25', NULL, 'Activo'),
(45, 20240045, 'Bruno Pascual Gómez', 'Femenino', '2004-05-08', 0, '1er Año', 'Desarrollo de Aplicaciones Multiplataforma', 'A', '1er Semestre', 1400, 'Pagado', 'C:\\\\\\\\\\\\\\\\Users\\\\\\\\\\\\\\\\alex_\\\\\\\\\\\\\\\\Downloads\\\\\\\\\\\\\\\\jorgejavier.JPG', '2024-05-18', '2024-05-25', NULL, 'Activo'),
(46, 20240046, 'Belinda Séneca Parrique', 'Femenino', '2001-05-01', 0, '3er Año', 'Desarrollo de Aplicaciones Multiplataforma', 'B', '1er Semestre', 1400, 'Pendiente', 'C:\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\Users\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\alex_\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\Downloads\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\belinda.JPG', '2024-05-25', '2024-05-26', NULL, 'Activo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `subject`
--

CREATE TABLE `subject` (
  `id` int(11) NOT NULL,
  `subject_code` varchar(255) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `course` varchar(255) DEFAULT NULL,
  `date_insert` date DEFAULT NULL,
  `date_update` date DEFAULT NULL,
  `date_delete` date DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `subject`
--

INSERT INTO `subject` (`id`, `subject_code`, `subject`, `course`, `date_insert`, `date_update`, `date_delete`, `status`) VALUES
(10, 'ADD', 'Acceso a Datos', 'Desarrollo de Aplicaciones Multiplataforma', '2024-05-13', NULL, NULL, 'Disponible'),
(11, 'DWB', 'Despliegue de Aplicaciones Web', 'Desarrollo de Aplicaciones Web', '2024-05-13', '2024-05-13', '2024-05-13', 'No disponible'),
(12, 'FG', 'Fisiopatología General', 'Higiene Bucodental', '2024-05-13', '2024-05-13', NULL, 'Disponible');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `teacher`
--

CREATE TABLE `teacher` (
  `id` int(11) NOT NULL,
  `teacher_id` varchar(250) DEFAULT NULL,
  `full_name` varchar(250) DEFAULT NULL,
  `gender` varchar(250) DEFAULT NULL,
  `year_experience` varchar(250) DEFAULT NULL,
  `experience` varchar(250) DEFAULT NULL,
  `department` varchar(250) DEFAULT NULL,
  `salary` double DEFAULT NULL,
  `salary_status` varchar(255) DEFAULT NULL,
  `image` varchar(500) DEFAULT NULL,
  `date_insert` date DEFAULT NULL,
  `date_update` date DEFAULT NULL,
  `date_delete` date DEFAULT NULL,
  `status` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `teacher`
--

INSERT INTO `teacher` (`id`, `teacher_id`, `full_name`, `gender`, `year_experience`, `experience`, `department`, `salary`, `salary_status`, `image`, `date_insert`, `date_update`, `date_delete`, `status`) VALUES
(32, 'TID-1', 'Ramiro González Ruiz', 'No binario', '4 años o más', 'Varios centros asociados', 'Informática', 1350, 'Pagado', 'C:\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\Users\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\alex_\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\Downloads\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\ramiro.JPG', '2024-05-13', '2024-05-13', NULL, 'Activo'),
(33, 'TID-33', 'Jesús Gil Peña', 'Masculino', '2 años', 'Institutos públicos', 'Informática', 2000, 'Pagado', 'C:\\\\Users\\\\alex_\\\\Downloads\\\\jesusgil.JPG', '2024-05-13', '2024-05-25', NULL, 'Activo'),
(34, 'TID-34', 'Jorge Javier Amadeo Abúndez', 'Masculino', '1 año', 'Institutos de FP', 'Salud', 1245, 'Pagado', 'C:\\\\Users\\\\alex_\\\\Downloads\\\\jorgejavier.JPG', '2024-05-18', '2024-05-25', NULL, 'Activo'),
(35, 'TID-35', 'Carlos Ruiz Sanz', 'Masculino', '1 año', 'Variada en varios centros', 'Informática', 1400, 'Pendiente', 'C:\\\\\\\\Users\\\\\\\\alex_\\\\\\\\Downloads\\\\\\\\jesusgil.JPG', '2024-05-18', '2024-05-18', NULL, 'Activo'),
(36, 'TID-36', 'Belén Martorell López', 'Femenino', '1 año', 'FP a distancia', 'Salud', 1345, 'Pendiente', 'C:\\\\Users\\\\alex_\\\\Downloads\\\\belen.JPG', '2024-05-25', NULL, NULL, 'Activo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `teacher_handle`
--

CREATE TABLE `teacher_handle` (
  `id` int(11) NOT NULL,
  `teacher_id` varchar(255) DEFAULT NULL,
  `subject_code` varchar(255) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `teacher_handle`
--

INSERT INTO `teacher_handle` (`id`, `teacher_id`, `subject_code`, `subject`, `date`, `status`) VALUES
(31, 'TID-33', 'FG', 'Fisiopatología General', '2024-05-15', 'Activo'),
(33, 'TID-33', 'ADD', 'Acceso a Datos', '2024-05-15', 'Activo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `teacher_student`
--

CREATE TABLE `teacher_student` (
  `id` int(11) NOT NULL,
  `teacher_id` varchar(255) DEFAULT NULL,
  `stud_studentID` varchar(255) DEFAULT NULL,
  `stud_name` varchar(255) DEFAULT NULL,
  `stud_gender` varchar(255) DEFAULT NULL,
  `stud_year` varchar(255) DEFAULT NULL,
  `stud_course` varchar(255) DEFAULT NULL,
  `stud_semester` varchar(255) DEFAULT NULL,
  `primer_trim` double DEFAULT NULL,
  `segundo_trim` double DEFAULT NULL,
  `nota_final` double DEFAULT NULL,
  `date_insert` date DEFAULT NULL,
  `date_delete` date DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `teacher_student`
--

INSERT INTO `teacher_student` (`id`, `teacher_id`, `stud_studentID`, `stud_name`, `stud_gender`, `stud_year`, `stud_course`, `stud_semester`, `primer_trim`, `segundo_trim`, `nota_final`, `date_insert`, `date_delete`, `status`) VALUES
(21, 'TID-33', '20240001', 'Julia Rojas Pérez', 'Femenino', '3er Año', 'Higiene Bucodental', '2º Semestre', 8.4, 0, 0, '2024-05-13', NULL, 'Activo'),
(22, 'TID-33', '20240041', 'Rodrigo Suárez Rojas', 'Masculino', '1er Año', 'Desarrollo de Aplicaciones Multiplataforma', '1er Semestre', 3, 1, 2, '2024-05-13', NULL, 'Activo'),
(23, 'TID-33', '20240046', 'Belinda Séneca Parriques', 'Femenino', '3er Año', 'Desarrollo de Aplicaciones Multiplataforma', '1er Semestre', 9, 7, 8, '2024-05-25', NULL, 'Activo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `role` varchar(100) DEFAULT NULL,
  `student_id` varchar(250) DEFAULT NULL,
  `teacher_id` varchar(250) DEFAULT NULL,
  `date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`id`, `email`, `username`, `password`, `role`, `student_id`, `teacher_id`, `date`) VALUES
(49, NULL, 'admin1', '12345678', 'Admin', NULL, NULL, '2024-05-11'),
(50, 'jesusgil@gmail.com', 'jesusgil1', '12345678', 'Teacher', NULL, 'TID-33', '2024-05-13'),
(51, 'rodrigosuarez@gmail.com', 'rodrigosuarez', '12345678', 'Student', '20240041', NULL, '2024-05-13'),
(52, 'estudiante1@gmail.com', 'estudiante1', '12345678', 'Student', '20240042', NULL, '2024-05-18'),
(53, 'profesor1@gmail.com', 'profesor1', '12345678', 'Teacher', NULL, 'TID-34', '2024-05-18'),
(54, NULL, 'administrador2', '12345678', 'Admin', NULL, NULL, '2024-05-25');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `salary`
--
ALTER TABLE `salary`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `subject`
--
ALTER TABLE `subject`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `teacher`
--
ALTER TABLE `teacher`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `teacher_handle`
--
ALTER TABLE `teacher_handle`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `teacher_student`
--
ALTER TABLE `teacher_student`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `course`
--
ALTER TABLE `course`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `salary`
--
ALTER TABLE `salary`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT de la tabla `student`
--
ALTER TABLE `student`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- AUTO_INCREMENT de la tabla `subject`
--
ALTER TABLE `subject`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `teacher`
--
ALTER TABLE `teacher`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT de la tabla `teacher_handle`
--
ALTER TABLE `teacher_handle`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT de la tabla `teacher_student`
--
ALTER TABLE `teacher_student`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT de la tabla `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=55;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
