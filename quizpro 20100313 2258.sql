-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.44-community


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema quizpro
--

CREATE DATABASE IF NOT EXISTS quizpro;
USE quizpro;

--
-- Definition of table `convocatoria`
--

DROP TABLE IF EXISTS `convocatoria`;
CREATE TABLE `convocatoria` (
  `con_id` bigint(20) NOT NULL,
  `con_fecha_fin` varchar(255) DEFAULT NULL,
  `con_descripcion` varchar(2048) DEFAULT NULL,
  `con_nombre` varchar(255) DEFAULT NULL,
  `con_vacantes` int(11) DEFAULT NULL,
  `con_fecha_inicio` varchar(255) DEFAULT NULL,
  `exa_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`con_id`),
  KEY `FK_convocatoria_exa_id` (`exa_id`),
  CONSTRAINT `FK_convocatoria_exa_id` FOREIGN KEY (`exa_id`) REFERENCES `examen` (`exa_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `convocatoria`
--

/*!40000 ALTER TABLE `convocatoria` DISABLE KEYS */;
INSERT INTO `convocatoria` (`con_id`,`con_fecha_fin`,`con_descripcion`,`con_nombre`,`con_vacantes`,`con_fecha_inicio`,`exa_id`) VALUES 
 (2,'2010-03-24 22:11','<p>Esta es la descripci&oacute;n de la <em>convocatoria de ejemplo</em>.</p>\r\n<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod  tempor incididunt ut labore et dolore magna aliqua. <strong>Ut enim ad minim  veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea  commodo consequat.</strong> Duis aute irure dolor in reprehenderit in voluptate  velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint  occaecat cupidatat non proident, sunt in culpa qui officia deserunt  mollit anim id est laborum.</p>\r\n<p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem  accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab  illo inventore veritatis et quasi architecto beatae vitae dicta sunt  explicabo. Nemo enim ipsam voluptatem quia <strong><span style=\"font-size: larger;\">voluptas sit aspernatur</span></strong> aut  odit aut fugit, sed quia consequuntur magni dolores eos qui ratione  voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum  quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam  eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat  voluptatem.</p>\r\n<ul>\r\n    <li>&Iacute;tem 1.</li>\r\n    <li>&Iacute;tem 2.</li>\r\n    <li>&Iacute;tem 3.</li>\r\n</ul>\r\n<p><span style=\"color: rgb(255, 0, 0);\">At vero eos et accusamus et iusto odio dignissimos ducimus qui  blanditiis praesentium voluptatum deleniti atque corrupti quos dolores  et quas molestias excepturi sint occaecati cupiditate non provident,  similique sunt in culpa qui officia deserunt mollitia animi, id est  laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita  distinctio.</span></p>','Ejemplo 1',5,'2010-03-12 05:11',351);
/*!40000 ALTER TABLE `convocatoria` ENABLE KEYS */;


--
-- Definition of table `convocatoria_postulante`
--

DROP TABLE IF EXISTS `convocatoria_postulante`;
CREATE TABLE `convocatoria_postulante` (
  `con_id` bigint(20) NOT NULL,
  `pos_id` bigint(20) NOT NULL,
  PRIMARY KEY (`con_id`,`pos_id`),
  KEY `FK_convocatoria_postulante_pos_id` (`pos_id`),
  CONSTRAINT `FK_convocatoria_postulante_con_id` FOREIGN KEY (`con_id`) REFERENCES `convocatoria` (`con_id`),
  CONSTRAINT `FK_convocatoria_postulante_pos_id` FOREIGN KEY (`pos_id`) REFERENCES `postulante` (`pos_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `convocatoria_postulante`
--

/*!40000 ALTER TABLE `convocatoria_postulante` DISABLE KEYS */;
INSERT INTO `convocatoria_postulante` (`con_id`,`pos_id`) VALUES 
 (2,251),
 (2,252),
 (2,253);
/*!40000 ALTER TABLE `convocatoria_postulante` ENABLE KEYS */;


--
-- Definition of table `decision`
--

DROP TABLE IF EXISTS `decision`;
CREATE TABLE `decision` (
  `dec_id` bigint(20) NOT NULL,
  `pre_id` bigint(20) DEFAULT NULL,
  `pos_id` bigint(20) DEFAULT NULL,
  `res_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`dec_id`),
  KEY `FK_decision_res_id` (`res_id`),
  KEY `FK_decision_pre_id` (`pre_id`),
  KEY `FK_decision_pos_id` (`pos_id`),
  CONSTRAINT `FK_decision_pos_id` FOREIGN KEY (`pos_id`) REFERENCES `postulante` (`pos_id`),
  CONSTRAINT `FK_decision_pre_id` FOREIGN KEY (`pre_id`) REFERENCES `pregunta` (`pre_id`),
  CONSTRAINT `FK_decision_res_id` FOREIGN KEY (`res_id`) REFERENCES `respuesta` (`res_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `decision`
--

/*!40000 ALTER TABLE `decision` DISABLE KEYS */;
/*!40000 ALTER TABLE `decision` ENABLE KEYS */;


--
-- Definition of table `examen`
--

DROP TABLE IF EXISTS `examen`;
CREATE TABLE `examen` (
  `exa_id` bigint(20) NOT NULL,
  `exa_publico` tinyint(1) DEFAULT '0',
  `exa_descripcion` varchar(255) DEFAULT NULL,
  `exa_nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`exa_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `examen`
--

/*!40000 ALTER TABLE `examen` DISABLE KEYS */;
INSERT INTO `examen` (`exa_id`,`exa_publico`,`exa_descripcion`,`exa_nombre`) VALUES 
 (351,1,'Un sencillo examen de ejemplo.','Exámen de ejemplo 1');
/*!40000 ALTER TABLE `examen` ENABLE KEYS */;


--
-- Definition of table `examen_ip_autorizada`
--

DROP TABLE IF EXISTS `examen_ip_autorizada`;
CREATE TABLE `examen_ip_autorizada` (
  `exa_id` bigint(20) NOT NULL,
  `ipa_id` bigint(20) NOT NULL,
  PRIMARY KEY (`exa_id`,`ipa_id`),
  KEY `FK_examen_ip_autorizada_ipa_id` (`ipa_id`),
  CONSTRAINT `FK_examen_ip_autorizada_ipa_id` FOREIGN KEY (`ipa_id`) REFERENCES `ip_autorizada` (`ipa_id`),
  CONSTRAINT `FK_examen_ip_autorizada_exa_id` FOREIGN KEY (`exa_id`) REFERENCES `examen` (`exa_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `examen_ip_autorizada`
--

/*!40000 ALTER TABLE `examen_ip_autorizada` DISABLE KEYS */;
/*!40000 ALTER TABLE `examen_ip_autorizada` ENABLE KEYS */;


--
-- Definition of table `grupo`
--

DROP TABLE IF EXISTS `grupo`;
CREATE TABLE `grupo` (
  `gru_id` bigint(20) NOT NULL,
  `gru_nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`gru_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `grupo`
--

/*!40000 ALTER TABLE `grupo` DISABLE KEYS */;
INSERT INTO `grupo` (`gru_id`,`gru_nombre`) VALUES 
 (100,'Administradores del sistema'),
 (101,'Usuarios autenticados'),
 (200,'Recursos humanos'),
 (201,'Comité de pruebas'),
 (202,'Aplicación');
/*!40000 ALTER TABLE `grupo` ENABLE KEYS */;


--
-- Definition of table `grupo_servicio`
--

DROP TABLE IF EXISTS `grupo_servicio`;
CREATE TABLE `grupo_servicio` (
  `gru_id` bigint(20) NOT NULL,
  `ser_id` bigint(20) NOT NULL,
  PRIMARY KEY (`gru_id`,`ser_id`),
  KEY `FK_grupo_servicio_ser_id` (`ser_id`),
  CONSTRAINT `FK_grupo_servicio_ser_id` FOREIGN KEY (`ser_id`) REFERENCES `servicio` (`ser_id`),
  CONSTRAINT `FK_grupo_servicio_gru_id` FOREIGN KEY (`gru_id`) REFERENCES `grupo` (`gru_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `grupo_servicio`
--

/*!40000 ALTER TABLE `grupo_servicio` DISABLE KEYS */;
INSERT INTO `grupo_servicio` (`gru_id`,`ser_id`) VALUES 
 (201,52),
 (200,102),
 (200,103),
 (200,104),
 (202,105),
 (201,106),
 (201,107),
 (200,108),
 (100,300),
 (100,301),
 (100,302),
 (100,303),
 (100,304),
 (101,401),
 (101,402),
 (200,500),
 (202,500);
/*!40000 ALTER TABLE `grupo_servicio` ENABLE KEYS */;


--
-- Definition of table `ip_autorizada`
--

DROP TABLE IF EXISTS `ip_autorizada`;
CREATE TABLE `ip_autorizada` (
  `ipa_id` bigint(20) NOT NULL,
  `ipa_ip` varchar(255) DEFAULT NULL,
  `ipa_alias` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ipa_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ip_autorizada`
--

/*!40000 ALTER TABLE `ip_autorizada` DISABLE KEYS */;
INSERT INTO `ip_autorizada` (`ipa_id`,`ipa_ip`,`ipa_alias`) VALUES 
 (451,'127.0.0.1','localhost');
/*!40000 ALTER TABLE `ip_autorizada` ENABLE KEYS */;


--
-- Definition of table `menu`
--

DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `men_id` bigint(20) NOT NULL,
  `men_nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`men_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `menu`
--

/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` (`men_id`,`men_nombre`) VALUES 
 (1,'Servicios'),
 (2,'Reportes'),
 (3,'Sistema'),
 (4,'Sesión');
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;


--
-- Definition of table `postulante`
--

DROP TABLE IF EXISTS `postulante`;
CREATE TABLE `postulante` (
  `pos_id` bigint(20) NOT NULL,
  `pos_apellido` varchar(255) DEFAULT NULL,
  `pos_email` varchar(255) DEFAULT NULL,
  `pos_documento` varchar(255) DEFAULT NULL,
  `pos_telefono` varchar(255) DEFAULT NULL,
  `pos_password` varchar(255) DEFAULT NULL,
  `pos_nombre` varchar(255) DEFAULT NULL,
  `pos_clasificado` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`pos_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `postulante`
--

/*!40000 ALTER TABLE `postulante` DISABLE KEYS */;
INSERT INTO `postulante` (`pos_id`,`pos_apellido`,`pos_email`,`pos_documento`,`pos_telefono`,`pos_password`,`pos_nombre`,`pos_clasificado`) VALUES 
 (251,'Pérez','pepito@test.test','111','111','testtest','Pepito',NULL),
 (252,'Banana','juanita@test.test','222','222','testtest','Juaina',NULL),
 (253,'Gómez','juan@test.test','333','333','testtest','Juan',NULL);
/*!40000 ALTER TABLE `postulante` ENABLE KEYS */;


--
-- Definition of table `pregunta`
--

DROP TABLE IF EXISTS `pregunta`;
CREATE TABLE `pregunta` (
  `pre_id` bigint(20) NOT NULL,
  `pre_texto` varchar(2048) DEFAULT NULL,
  `pre_ponderacion` int(11) DEFAULT NULL,
  `res_id` bigint(20) DEFAULT NULL,
  `exa_id` bigint(20) DEFAULT NULL,
  `tem_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`pre_id`),
  KEY `FK_pregunta_tem_id` (`tem_id`),
  KEY `FK_pregunta_exa_id` (`exa_id`),
  KEY `FK_pregunta_res_id` (`res_id`),
  CONSTRAINT `FK_pregunta_exa_id` FOREIGN KEY (`exa_id`) REFERENCES `examen` (`exa_id`),
  CONSTRAINT `FK_pregunta_res_id` FOREIGN KEY (`res_id`) REFERENCES `respuesta` (`res_id`),
  CONSTRAINT `FK_pregunta_tem_id` FOREIGN KEY (`tem_id`) REFERENCES `tema` (`tem_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pregunta`
--

/*!40000 ALTER TABLE `pregunta` DISABLE KEYS */;
INSERT INTO `pregunta` (`pre_id`,`pre_texto`,`pre_ponderacion`,`res_id`,`exa_id`,`tem_id`) VALUES 
 (352,'<p>2 + 1 = ?</p>',5,355,351,301),
 (358,'<p>Si tengo 4 manzanas y le regalo una al profesor, &iquest;cu&aacute;ntas manzanas me quedan?</p>',8,359,351,301),
 (401,'<p>Colombia es:</p>',9,402,351,302),
 (407,'<p>Colombia queda en:</p>',10,411,351,302),
 (414,'<p><span style=\"font-size: large;\"><strong><span style=\"color: rgb(51, 102, 255);\">Esta frase est&aacute; en color:</span></strong></span></p>',3,417,351,413),
 (426,'<p>La bandera de <strong>Colombia </strong>tiene los colores:</p>',4,427,351,302),
 (432,'<p>Entre estas <em>opciones</em>:</p>\r\n<ul>\r\n    <li><span style=\"font-size: xx-large;\">Opci&oacute;n 1</span></li>\r\n    <li><span style=\"font-size: x-large;\">Opci&oacute;n 2</span></li>\r\n</ul>\r\n<p>&iquest;Cu&aacute;l est&aacute; escrita con texto m&aacute;s <strong>grande</strong>?</p>',7,434,351,413);
/*!40000 ALTER TABLE `pregunta` ENABLE KEYS */;


--
-- Definition of table `pregunta_respuesta`
--

DROP TABLE IF EXISTS `pregunta_respuesta`;
CREATE TABLE `pregunta_respuesta` (
  `pre_id` bigint(20) NOT NULL,
  `res_id` bigint(20) NOT NULL,
  PRIMARY KEY (`pre_id`,`res_id`),
  KEY `FK_pregunta_respuesta_res_id` (`res_id`),
  CONSTRAINT `FK_pregunta_respuesta_pre_id` FOREIGN KEY (`pre_id`) REFERENCES `pregunta` (`pre_id`),
  CONSTRAINT `FK_pregunta_respuesta_res_id` FOREIGN KEY (`res_id`) REFERENCES `respuesta` (`res_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pregunta_respuesta`
--

/*!40000 ALTER TABLE `pregunta_respuesta` DISABLE KEYS */;
INSERT INTO `pregunta_respuesta` (`pre_id`,`res_id`) VALUES 
 (352,353),
 (352,354),
 (352,356),
 (352,357),
 (358,360),
 (358,361),
 (358,362),
 (358,363),
 (401,403),
 (401,404),
 (401,405),
 (401,406),
 (407,408),
 (407,409),
 (407,410),
 (407,412),
 (414,415),
 (414,416),
 (414,418),
 (414,419),
 (426,428),
 (426,429),
 (426,430),
 (426,431),
 (432,433),
 (432,435),
 (432,436),
 (432,437);
/*!40000 ALTER TABLE `pregunta_respuesta` ENABLE KEYS */;


--
-- Definition of table `reporte`
--

DROP TABLE IF EXISTS `reporte`;
CREATE TABLE `reporte` (
  `rep_id` bigint(20) NOT NULL,
  `rep_nombre` varchar(255) DEFAULT NULL,
  `rep_consulta` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`rep_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reporte`
--

/*!40000 ALTER TABLE `reporte` DISABLE KEYS */;
INSERT INTO `reporte` (`rep_id`,`rep_nombre`,`rep_consulta`) VALUES 
 (1,'Lista de inscritos','SELECT\r\n  pos.pos_documento Documento,\r\n  pos.pos_apellido Apellidos,\r\n  pos.pos_nombre Nombres\r\nFROM\r\n  postulante pos\r\n  NATURAL JOIN convocatoria_postulante cp\r\nWHERE\r\n  cp.con_id = :con_id');
/*!40000 ALTER TABLE `reporte` ENABLE KEYS */;


--
-- Definition of table `respuesta`
--

DROP TABLE IF EXISTS `respuesta`;
CREATE TABLE `respuesta` (
  `res_id` bigint(20) NOT NULL,
  `res_texto` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`res_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `respuesta`
--

/*!40000 ALTER TABLE `respuesta` DISABLE KEYS */;
INSERT INTO `respuesta` (`res_id`,`res_texto`) VALUES 
 (353,'1'),
 (354,'21'),
 (355,'3'),
 (356,'2'),
 (357,'4'),
 (359,'3'),
 (360,'0'),
 (361,'4'),
 (362,'100'),
 (363,'5'),
 (402,'Un pa&iacute;s.'),
 (403,'Un mar.'),
 (404,'Un departamento.'),
 (405,'Un planeta'),
 (406,'Un continente.'),
 (408,'Jap&oacute;n.'),
 (409,'Una galaxia cercana a la V&iacute;a Lactea.'),
 (410,'Marte.'),
 (411,'America.'),
 (412,'Las profundidades del Oc&eacute;ano Atl&aacute;ntico.'),
 (415,'<strong><span style=\"color: rgb(0, 0, 255);\">Verde.</span></strong>'),
 (416,'<strong><span style=\"color: rgb(255, 0, 0);\">Rojo.</span></strong>'),
 (417,'<strong><span style=\"color: rgb(51, 153, 51);\">Azul.</span></strong>'),
 (418,'<strong><span style=\"color: rgb(255, 0, 0);\">Amarillo.</span></strong>'),
 (419,'<strong><span style=\"color: rgb(51, 102, 255);\">Negro.</span></strong>'),
 (427,'<span style=\"background-color: rgb(255, 255, 0);\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></p>\r\n<p><span style=\"background-color: rgb(0, 0, 255);\"><span style=\"color: rgb(0, 0, 255);\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span></p>\r\n<p><span style=\"background-color: rgb(255, 0, 0);\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>'),
 (428,'<span style=\"background-color: rgb(255, 255, 0);\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></p>\r\n<p><span style=\"background-color: rgb(51, 51, 51);\"><span style=\"color: rgb(0, 0, 255);\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span></p>\r\n<p><span style=\"background-color: rgb(255, 0, 0);\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>'),
 (429,'<span style=\"background-color: rgb(255, 255, 0);\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></p>\r\n<p><span style=\"background-color: rgb(153, 204, 0);\"><span style=\"color: rgb(0, 0, 255);\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span></p>\r\n<p><span style=\"background-color: rgb(255, 0, 0);\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>'),
 (430,'<span style=\"background-color: rgb(255, 255, 0);\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></p>\r\n<p><span style=\"background-color: rgb(0, 0, 255);\"><span style=\"color: rgb(0, 0, 255);\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span></p>\r\n<p><span style=\"background-color: rgb(255, 0, 255);\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>'),
 (431,'<span style=\"background-color: rgb(0, 255, 0);\"><span style=\"color: rgb(0, 255, 0);\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span></p>\r\n<p><span style=\"background-color: rgb(0, 0, 255);\"><span style=\"color: rgb(0, 0, 255);\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span></p>\r\n<p><span style=\"background-color: rgb(255, 0, 0);\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>'),
 (433,'No es posible determinar la respuesta.'),
 (434,'La opci&oacute;n 1.'),
 (435,'Las dos opciones presentan el mismo tama&ntilde;o.'),
 (436,'Ninguna es m&aacute;s grande.'),
 (437,'La opci&oacute;n 2.');
/*!40000 ALTER TABLE `respuesta` ENABLE KEYS */;


--
-- Definition of table `resultado`
--

DROP TABLE IF EXISTS `resultado`;
CREATE TABLE `resultado` (
  `res_id` bigint(20) NOT NULL,
  `res_puntaje` double DEFAULT NULL,
  `exa_id` bigint(20) DEFAULT NULL,
  `pos_id` bigint(20) DEFAULT NULL,
  `tem_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`res_id`),
  KEY `FK_resultado_pos_id` (`pos_id`),
  KEY `FK_resultado_exa_id` (`exa_id`),
  KEY `FK_resultado_tem_id` (`tem_id`),
  CONSTRAINT `FK_resultado_tem_id` FOREIGN KEY (`tem_id`) REFERENCES `tema` (`tem_id`),
  CONSTRAINT `FK_resultado_exa_id` FOREIGN KEY (`exa_id`) REFERENCES `examen` (`exa_id`),
  CONSTRAINT `FK_resultado_pos_id` FOREIGN KEY (`pos_id`) REFERENCES `postulante` (`pos_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `resultado`
--

/*!40000 ALTER TABLE `resultado` DISABLE KEYS */;
/*!40000 ALTER TABLE `resultado` ENABLE KEYS */;


--
-- Definition of table `sequence`
--

DROP TABLE IF EXISTS `sequence`;
CREATE TABLE `sequence` (
  `SEQ_NAME` varchar(50) NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL,
  PRIMARY KEY (`SEQ_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sequence`
--

/*!40000 ALTER TABLE `sequence` DISABLE KEYS */;
INSERT INTO `sequence` (`SEQ_NAME`,`SEQ_COUNT`) VALUES 
 ('SEQ_GEN','500');
/*!40000 ALTER TABLE `sequence` ENABLE KEYS */;


--
-- Definition of table `servicio`
--

DROP TABLE IF EXISTS `servicio`;
CREATE TABLE `servicio` (
  `ser_id` bigint(20) NOT NULL,
  `ser_url` varchar(255) DEFAULT NULL,
  `ser_publico` tinyint(1) DEFAULT '0',
  `ser_nombre` varchar(255) DEFAULT NULL,
  `ser_requiere_convocatoria` tinyint(1) DEFAULT '0',
  `men_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ser_id`),
  KEY `FK_servicio_men_id` (`men_id`),
  CONSTRAINT `FK_servicio_men_id` FOREIGN KEY (`men_id`) REFERENCES `menu` (`men_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `servicio`
--

/*!40000 ALTER TABLE `servicio` DISABLE KEYS */;
INSERT INTO `servicio` (`ser_id`,`ser_url`,`ser_publico`,`ser_nombre`,`ser_requiere_convocatoria`,`men_id`) VALUES 
 (52,'/servicios/administrarPreguntas/',0,'Administrar preguntas',1,1),
 (100,'/servicios/seleccionarConvocatoria/',1,'Seleccionar convocatoria',0,1),
 (101,'/servicios/inscripcion/',1,'Inscripción',1,1),
 (102,'/servicios/publicarResultados/',0,'Publicar resultados',1,1),
 (103,'/servicios/administrarConvocatorias/',0,'Administrar convocatorias',0,1),
 (104,'/servicios/administrarPostulantes/',0,'Administrar postulantes',0,1),
 (105,'/servicios/administrarIps/',0,'Administrar IPs',0,1),
 (106,'/servicios/administrarTemas/',0,'Administrar temas',0,1),
 (107,'/servicios/administrarExamenes/',0,'Administrar exámenes',0,1),
 (108,'/servicios/asignarExamenAConvocatoria/',0,'Asignar examen a convocatoria',0,1),
 (109,'/servicios/presentarExamen/',1,'Presentar examen',1,1),
 (201,'/servicios/consultarResultados/',1,'Consultar resultados',1,1),
 (300,'/servicios/administrarUsuarios/',0,'Administrar usuarios',0,3),
 (301,'/servicios/administrarGrupos/',0,'Administrar grupos',0,3),
 (302,'/servicios/administrarServicios/',0,'Administrar servicios',0,3),
 (303,'/servicios/administrarMenus/',0,'Administrar menús',0,3),
 (304,'/servicios/administrarReportes/',0,'Administrar reportes',0,3),
 (400,'/servicios/autenticacion/',1,'Autenticarse',0,4),
 (401,'/servicios/cerrarSesion/',0,'Cerrar sesión',0,4),
 (402,'/servicios/actualizarDatos/',0,'Actualizar datos',0,4),
 (500,'/reportes/reporte.action?id=1',0,'Lista de inscritos',1,2),
 (1000,'/',1,'Inicio',0,NULL),
 (1010,'/comun/mensaje.jsp',1,'Mensaje',0,NULL),
 (1020,'/comun/error.jsp',1,'Error',0,NULL);
/*!40000 ALTER TABLE `servicio` ENABLE KEYS */;


--
-- Definition of table `tema`
--

DROP TABLE IF EXISTS `tema`;
CREATE TABLE `tema` (
  `tem_id` bigint(20) NOT NULL,
  `tem_nombre` varchar(255) DEFAULT NULL,
  `tem_descripcion` varchar(255) DEFAULT NULL,
  `tem_ponderacion` int(11) DEFAULT NULL,
  PRIMARY KEY (`tem_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tema`
--

/*!40000 ALTER TABLE `tema` DISABLE KEYS */;
INSERT INTO `tema` (`tem_id`,`tem_nombre`,`tem_descripcion`,`tem_ponderacion`) VALUES 
 (301,'Matemáticas','Pregúntas de razonamiento matemático general.',2),
 (302,'Historia de Colombia','Preguntas relacionadas con la historia de Colombia.',8),
 (413,'Cultura general','Preguntas de cultura general en varios tópicos.',8);
/*!40000 ALTER TABLE `tema` ENABLE KEYS */;


--
-- Definition of table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
  `usu_id` bigint(20) NOT NULL,
  `usu_nombre` varchar(255) DEFAULT NULL,
  `usu_login` varchar(255) DEFAULT NULL,
  `usu_documento` varchar(255) DEFAULT NULL,
  `usu_password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`usu_id`),
  UNIQUE KEY `UNQ_usuario_0` (`usu_documento`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usuario`
--

/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`usu_id`,`usu_nombre`,`usu_login`,`usu_documento`,`usu_password`) VALUES 
 (100,'Administrador','admin','---','admin');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;


--
-- Definition of table `usuario_grupo`
--

DROP TABLE IF EXISTS `usuario_grupo`;
CREATE TABLE `usuario_grupo` (
  `usu_id` bigint(20) NOT NULL,
  `gru_id` bigint(20) NOT NULL,
  PRIMARY KEY (`usu_id`,`gru_id`),
  KEY `FK_usuario_grupo_gru_id` (`gru_id`),
  CONSTRAINT `FK_usuario_grupo_gru_id` FOREIGN KEY (`gru_id`) REFERENCES `grupo` (`gru_id`),
  CONSTRAINT `FK_usuario_grupo_usu_id` FOREIGN KEY (`usu_id`) REFERENCES `usuario` (`usu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usuario_grupo`
--

/*!40000 ALTER TABLE `usuario_grupo` DISABLE KEYS */;
INSERT INTO `usuario_grupo` (`usu_id`,`gru_id`) VALUES 
 (100,100),
 (100,101),
 (100,200),
 (100,201),
 (100,202);
/*!40000 ALTER TABLE `usuario_grupo` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
