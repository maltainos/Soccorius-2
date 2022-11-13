-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: soccorius
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `internados`
--

DROP TABLE IF EXISTS `internados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `internados` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `data_alta` datetime(6) DEFAULT NULL,
  `data_internamento` datetime(6) NOT NULL,
  `internado_id` varchar(35) NOT NULL,
  `numero_andar` int(11) NOT NULL,
  `numero_cama` int(11) NOT NULL,
  `numero_sala` int(11) NOT NULL,
  `status` tinyint(1) DEFAULT '1',
  `user_id` int(11) DEFAULT NULL,
  `paciente_id` int(11) DEFAULT NULL,
  `triagem_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1xjut7uscvqnyfwcuucijqk4w` (`user_id`),
  KEY `FKdrxtv71iad56bfxhgrd174f6q` (`paciente_id`),
  KEY `FKbncxo55b16givxrk0h71qdf5i` (`triagem_id`),
  CONSTRAINT `FK1xjut7uscvqnyfwcuucijqk4w` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKbncxo55b16givxrk0h71qdf5i` FOREIGN KEY (`triagem_id`) REFERENCES `triagens` (`id`),
  CONSTRAINT `FKdrxtv71iad56bfxhgrd174f6q` FOREIGN KEY (`paciente_id`) REFERENCES `pacientes` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `internados`
--

LOCK TABLES `internados` WRITE;
/*!40000 ALTER TABLE `internados` DISABLE KEYS */;
INSERT INTO `internados` VALUES (1,NULL,'2022-08-22 15:29:08.842589','NZD746',1,1,1,1,2,1,9),(2,NULL,'2022-08-22 15:32:16.508562','RFZ451',1,20,2,1,2,1,9),(3,NULL,'2022-09-19 06:41:35.282191','QHC447',2,60,4,1,2,1,10),(4,NULL,'2022-09-19 06:53:01.464953','ZIP415',3,1,1,1,2,4,11),(5,NULL,'2022-09-19 06:55:45.495749','YVA713',4,239,40,1,2,4,11),(6,NULL,'2022-09-19 06:58:30.256018','IAR832',2,192,47,1,2,4,11),(7,NULL,'2022-09-19 07:02:36.203226','FZY050',5,780,100,1,2,5,12),(8,NULL,'2022-09-19 07:10:19.952464','YGW357',1,1,1,1,2,4,11),(9,NULL,'2022-10-19 10:36:36.873651','BQY570',1,1,1,1,1,1,15);
/*!40000 ALTER TABLE `internados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `laboratorios`
--

DROP TABLE IF EXISTS `laboratorios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `laboratorios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hora_entrada` datetime(6) NOT NULL,
  `hora_saida` datetime(6) DEFAULT NULL,
  `resultado_amostra` varchar(255) DEFAULT NULL,
  `serie_amostra` varchar(10) NOT NULL,
  `status` tinyint(1) DEFAULT '0',
  `tipo_exame` varchar(255) NOT NULL,
  `laboratorista_id` int(11) DEFAULT NULL,
  `medico_id` int(11) DEFAULT NULL,
  `paciente_id` int(11) DEFAULT NULL,
  `triagem_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ej0nc8efs6c0v9yb1hs38vxy8` (`serie_amostra`),
  KEY `FKkunl89i7cglsrdvki6m4lj3m8` (`laboratorista_id`),
  KEY `FKnvabwde6745n8rxbfnibjogu9` (`medico_id`),
  KEY `FKachjr0uss47ritvce9qrexf38` (`paciente_id`),
  KEY `FKk4c684bkx5irmkwmb76j0nf75` (`triagem_id`),
  CONSTRAINT `FKachjr0uss47ritvce9qrexf38` FOREIGN KEY (`paciente_id`) REFERENCES `pacientes` (`id`),
  CONSTRAINT `FKk4c684bkx5irmkwmb76j0nf75` FOREIGN KEY (`triagem_id`) REFERENCES `triagens` (`id`),
  CONSTRAINT `FKkunl89i7cglsrdvki6m4lj3m8` FOREIGN KEY (`laboratorista_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKnvabwde6745n8rxbfnibjogu9` FOREIGN KEY (`medico_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `laboratorios`
--

LOCK TABLES `laboratorios` WRITE;
/*!40000 ALTER TABLE `laboratorios` DISABLE KEYS */;
INSERT INTO `laboratorios` VALUES (1,'2022-09-20 09:36:28.000000',NULL,NULL,'2009223',1,'tac1,tac2,tac3',NULL,2,1,NULL),(2,'2022-09-20 09:39:17.000000',NULL,NULL,'2009225',0,'tac2,tac3',NULL,2,2,17),(3,'2022-09-21 10:16:52.000000',NULL,NULL,'2109226',0,'tac1,tac2,tac3',NULL,2,3,18),(4,'2022-10-19 10:32:21.000000',NULL,NULL,'1910221',0,'tac1,tac3',NULL,1,6,13);
/*!40000 ALTER TABLE `laboratorios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pacientes`
--

DROP TABLE IF EXISTS `pacientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pacientes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `fathers_name` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `mothers_name` varchar(255) DEFAULT NULL,
  `paciente_code` varchar(255) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `created_on` date DEFAULT NULL,
  `place_of_birth` varchar(255) DEFAULT NULL,
  `updated_on` date DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `years_old` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pacientes`
--

LOCK TABLES `pacientes` WRITE;
/*!40000 ALTER TABLE `pacientes` DISABLE KEYS */;
INSERT INTO `pacientes` VALUES (1,'nelsonz@codejava.rvw','Zaona Joao','Nelson Zaona','Albino','Suhura Suale','LRRY007150','2022-05-30','Sofala, Beira','2022-06-12','Angoche',NULL,'+258849240496',20),(2,'rony@codejava.rvw','Zaona Joao','Rony Zaona Joao','Albino','Suhura Suale','BUPQ106004','2008-03-08','Muahiver-Expansao, Nampula','2022-06-13','Angoche',NULL,'+258849240496',20),(3,'inovancia@codejava.rvw','Zaona Joao','Inocencia Zaona','Joao','Suhura Suale','EHCN282250','2020-11-30','Muahiver-Expansao, Nampula','2022-06-13','Nampula',NULL,'+258849240496',5),(4,'edilson@codejava.rvw','Massingue Edilson','Edilson Massingue','Edilson','Fatima Jose','QUUS727703','2022-06-08','Sofala, Beira','2022-06-13','Beira',NULL,'+258849240496',20),(5,'edsonmeque@codejava.net','Meque Dores','Edson Meque','Dores','Dores Tatenta','BOUP085544','2022-08-17','Macurungo, Rua 1 Casa nr 20','2022-08-08','Nampula',NULL,'849000000',22),(6,'paciente@codejava.rvw','Zaona Joao','Paciente','Unico','Suhura Suale','ABBA342011','2022-08-18','Sofala, Beira','2022-08-12','Beira',NULL,'849000000',12);
/*!40000 ALTER TABLE `pacientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pacientes_triagens`
--

DROP TABLE IF EXISTS `pacientes_triagens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pacientes_triagens` (
  `paciente_id` int(11) NOT NULL,
  `triagens_id` int(11) NOT NULL,
  PRIMARY KEY (`paciente_id`,`triagens_id`),
  UNIQUE KEY `UK_mtup0toyejq70k4yubw5hwel4` (`triagens_id`),
  CONSTRAINT `FK2tyxur3isi5pno9v5plnvdyy2` FOREIGN KEY (`paciente_id`) REFERENCES `pacientes` (`id`),
  CONSTRAINT `FKjjig735q7fn0ved6dyewqndif` FOREIGN KEY (`triagens_id`) REFERENCES `triagens` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pacientes_triagens`
--

LOCK TABLES `pacientes_triagens` WRITE;
/*!40000 ALTER TABLE `pacientes_triagens` DISABLE KEYS */;
/*!40000 ALTER TABLE `pacientes_triagens` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_descriptions` text,
  `role_name` varchar(128) NOT NULL,
  `role_description` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_716hgxp60ym1lifrdgp67xt5k` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,NULL,'Admin','This User Manager Everthing In System'),(2,NULL,'Assistente','This See Everthing about patients, clinical history, income and outcome'),(3,NULL,'Enfermeiro','This Manager Some Data about patients, clinical history, income and outcome'),(4,NULL,'Recepcionista','This See Everthing about patients, income and outcome'),(5,NULL,'Medical','This Manager Everthing about patients, clinical history, laboratory results, income and outcome');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transferencias`
--

DROP TABLE IF EXISTS `transferencias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transferencias` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `data_transferencia` datetime(6) DEFAULT NULL,
  `descricao` text,
  `transferencia_id` varchar(35) NOT NULL,
  `tranfereido_para` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `paciente_id` int(11) DEFAULT NULL,
  `triagem_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_t6oyoskeexfdt0hpc74yrs1f1` (`transferencia_id`),
  KEY `FKc6mxwagc55r884s6yhukbvh4b` (`user_id`),
  KEY `FKj04le4loim3c5gwr2f4gw5p40` (`paciente_id`),
  KEY `FKk8rxpncncriwlsildqrd9rrwu` (`triagem_id`),
  CONSTRAINT `FKc6mxwagc55r884s6yhukbvh4b` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKj04le4loim3c5gwr2f4gw5p40` FOREIGN KEY (`paciente_id`) REFERENCES `pacientes` (`id`),
  CONSTRAINT `FKk8rxpncncriwlsildqrd9rrwu` FOREIGN KEY (`triagem_id`) REFERENCES `triagens` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transferencias`
--

LOCK TABLES `transferencias` WRITE;
/*!40000 ALTER TABLE `transferencias` DISABLE KEYS */;
INSERT INTO `transferencias` VALUES (1,'2022-08-22 09:34:23.138174','<div><div><strong><span lang=\"PT\">Assunto: Pedido de Estágio</span></strong><b><span lang=\"PT\"><o:p></o:p></span></b></div>\r\n\r\n<div><b><span lang=\"PT\" style=\"font-size:\r\n12.0pt;line-height:150%;font-family:&quot;Times New Roman&quot;,serif\">Emanuel Adriano\r\nFrancisco Meque Taçoça</span></b><span lang=\"PT\" style=\"font-size:12.0pt;\r\nline-height:150%;font-family:&quot;Times New Roman&quot;,serif\">, </span><span lang=\"PT-BR\" style=\"font-size:12.0pt;line-height:150%;font-family:&quot;Times New Roman&quot;,serif;\r\nmso-ansi-language:PT-BR\">solteiro,</span><span lang=\"PT\" style=\"font-size:12.0pt;\r\nline-height:150%;font-family:&quot;Times New Roman&quot;,serif\"> nascid</span><span lang=\"PT-BR\" style=\"font-size:12.0pt;line-height:150%;font-family:&quot;Times New Roman&quot;,serif;\r\nmso-ansi-language:PT-BR\">o </span><span lang=\"PT\" style=\"font-size:12.0pt;\r\nline-height:150%;font-family:&quot;Times New Roman&quot;,serif\">aos 06 de Maio de 1995,\r\nfilho de Adriano Francisco Meque Taçoça e de Maria Helena Tete Taçoça, natural\r\nde Beira, residente no 2º Bairro-Palmeiras I, titular do BI nº 070104849392M,\r\nemitido aos 24 de Janeiro de 2020, pelo arquivo de Identificação Civil da\r\nBeira, licenciado no curso de Ciências Actuariais<b>,</b> vem mui respeitosamente requerer a V. Excia que se digne\r\nautorizar a admissão para estágio</span><span lang=\"PT-BR\" style=\"font-size:12.0pt;\r\nline-height:150%;font-family:&quot;Times New Roman&quot;,serif;mso-ansi-language:PT-BR\">,\r\n</span><span lang=\"PT\" style=\"font-size:12.0pt;line-height:150%;font-family:&quot;Times New Roman&quot;,serif\">pelo\r\nque,<o:p></o:p></span></div></div>','ZCKQKGLA03573828',NULL,1,1,5),(2,'2022-08-22 09:38:54.330392','<div><strong><span lang=\"PT\"><h1>Assunto: Transferencia</h1></span></strong><b><span lang=\"PT\"><o:p></o:p></span></b></div>\r\n\r\n<h3>Emanuel Adriano Francisco Meque Taçoça, <span lang=\"PT-BR\" style=\"font-size: 12pt; font-weight: var(--bs-body-font-weight); text-align: var(--bs-body-text-align); line-height: 150%; font-family: &quot;Times New Roman&quot;, serif;\">solteiro,</span><span lang=\"PT\" style=\"font-size: 12pt; font-weight: var(--bs-body-font-weight); text-align: var(--bs-body-text-align); line-height: 150%; font-family: &quot;Times New Roman&quot;, serif;\"> nascid</span><span lang=\"PT-BR\" style=\"font-size: 12pt; font-weight: var(--bs-body-font-weight); text-align: var(--bs-body-text-align); line-height: 150%; font-family: &quot;Times New Roman&quot;, serif;\">o </span><span lang=\"PT\" style=\"font-size: 12pt; font-weight: var(--bs-body-font-weight); text-align: var(--bs-body-text-align); line-height: 150%; font-family: &quot;Times New Roman&quot;, serif;\">aos 06 de Maio de 1995,\r\nfilho de Adriano Francisco Meque Taçoça e de Maria Helena Tete Taçoça, natural\r\nde Beira, residente no 2º Bairro-Palmeiras I, titular do BI nº </span><span lang=\"PT\" style=\"font-size: 12pt; text-align: var(--bs-body-text-align); line-height: 150%; font-family: &quot;Times New Roman&quot;, serif;\"><b>070104849392M</b></span><span lang=\"PT\" style=\"font-size: 12pt; font-weight: var(--bs-body-font-weight); text-align: var(--bs-body-text-align); line-height: 150%; font-family: &quot;Times New Roman&quot;, serif;\">,\r\nemitido aos 24 de Janeiro de 2020, pelo arquivo de Identificação Civil da\r\nBeira, licenciado no curso de Ciências Actuariais<b>,</b> vem mui respeitosamente requerer a V. Excia que se digne\r\nautorizar a admissão para estágio</span><span lang=\"PT-BR\" style=\"font-size: 12pt; font-weight: var(--bs-body-font-weight); text-align: var(--bs-body-text-align); line-height: 150%; font-family: &quot;Times New Roman&quot;, serif;\">,\r\n</span><span lang=\"PT\" style=\"font-size: 12pt; font-weight: var(--bs-body-font-weight); text-align: var(--bs-body-text-align); line-height: 150%; font-family: &quot;Times New Roman&quot;, serif;\">pelo\r\nque,</span></h3>','UUYUGHOJ26562270','Hospital Central da Beira (HCB)',1,6,3),(3,'2022-08-22 09:59:20.193528','<div><div><strong><span lang=\"PT\">Assunto: Pedido de Estágio</span></strong><b><span lang=\"PT\"><o:p></o:p></span></b></div>\r\n\r\n<div><b><span lang=\"PT\" style=\"font-size:\r\n12.0pt;line-height:150%;font-family:&quot;Times New Roman&quot;,serif\">Emanuel Adriano\r\nFrancisco Meque Taçoça</span></b><span lang=\"PT\" style=\"font-size:12.0pt;\r\nline-height:150%;font-family:&quot;Times New Roman&quot;,serif\">, </span><span lang=\"PT-BR\" style=\"font-size:12.0pt;line-height:150%;font-family:&quot;Times New Roman&quot;,serif;\r\nmso-ansi-language:PT-BR\">solteiro,</span><span lang=\"PT\" style=\"font-size:12.0pt;\r\nline-height:150%;font-family:&quot;Times New Roman&quot;,serif\"> nascid</span><span lang=\"PT-BR\" style=\"font-size:12.0pt;line-height:150%;font-family:&quot;Times New Roman&quot;,serif;\r\nmso-ansi-language:PT-BR\">o </span><span lang=\"PT\" style=\"font-size:12.0pt;\r\nline-height:150%;font-family:&quot;Times New Roman&quot;,serif\">aos 06 de Maio de 1995,\r\nfilho de Adriano Francisco Meque Taçoça e de Maria Helena Tete Taçoça, natural\r\nde Beira, residente no 2º Bairro-Palmeiras I, titular do BI nº 070104849392M,\r\nemitido aos 24 de Janeiro de 2020, pelo arquivo de Identificação Civil da\r\nBeira, licenciado no curso de Ciências Actuariais<b>,</b> vem mui respeitosamente requerer a V. Excia que se digne\r\nautorizar a admissão para estágio</span><span lang=\"PT-BR\" style=\"font-size:12.0pt;\r\nline-height:150%;font-family:&quot;Times New Roman&quot;,serif;mso-ansi-language:PT-BR\">,\r\n</span><span lang=\"PT\" style=\"font-size:12.0pt;line-height:150%;font-family:&quot;Times New Roman&quot;,serif\">pelo\r\nque,<o:p></o:p></span></div></div>','IFINTPMS20628232','Hospital Central de Nampula (HCB)',1,1,5),(4,'2022-08-22 10:05:20.430855','HIV/SIDA no estagio avancado','LCEGTPTD63051650','Hospital Central da Beira (HCB)',1,4,6),(5,'2022-08-22 10:18:16.889046','<div>Morreu</div>','QPIPTPNO88513882','Cova',1,6,7),(6,'2022-08-22 10:33:20.344886','<div>Cova</div>','JONGEYDB48712208','Cova',1,5,8),(7,'2022-10-19 10:35:06.613764','<div>Detalhes</div>','YPWTFDME60830132','Hospital Central de Nampula (HCB)',1,3,18),(8,'2022-10-19 10:35:48.446324','<div>transferir</div>','MMBWPAJR35712727','',1,2,17);
/*!40000 ALTER TABLE `transferencias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `triagens`
--

DROP TABLE IF EXISTS `triagens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `triagens` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_at` date DEFAULT NULL,
  `sintomas` varchar(256) NOT NULL,
  `triagem_number` int(11) NOT NULL,
  `update_at` date DEFAULT NULL,
  `users_id` int(11) DEFAULT NULL,
  `paciente_id` int(11) DEFAULT NULL,
  `triagem_status` bit(1) DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `FKbybml7ry42vltjovcr3xmbmgf` (`users_id`),
  KEY `FKahf7u52sy0jxjekkttg7biy4w` (`paciente_id`),
  CONSTRAINT `FKahf7u52sy0jxjekkttg7biy4w` FOREIGN KEY (`paciente_id`) REFERENCES `pacientes` (`id`),
  CONSTRAINT `FKbybml7ry42vltjovcr3xmbmgf` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `triagens`
--

LOCK TABLES `triagens` WRITE;
/*!40000 ALTER TABLE `triagens` DISABLE KEYS */;
INSERT INTO `triagens` VALUES (1,'2022-08-14','Bla bla bla',1,NULL,NULL,1,_binary ''),(2,'2022-08-15','Triagem 2',2,NULL,1,1,_binary ''),(3,'2022-08-14','Segunda Triagem',2,NULL,1,6,_binary ''),(4,'2022-08-14','Terceira Triagem',3,NULL,1,1,_binary ''),(5,'2022-08-14','4 Pessoa de Hoje',4,NULL,1,1,_binary ''),(6,'2022-08-22','HIV/SIDA',1,NULL,1,4,_binary ''),(7,'2022-08-22','Diareia',2,NULL,1,6,_binary ''),(8,'2022-08-22','Acidente de Viacao',3,NULL,1,5,_binary ''),(9,'2022-08-22','Sem sintomas',4,NULL,2,1,_binary ''),(10,'2022-09-19','Doce',1,NULL,2,1,_binary ''),(11,'2022-09-19','Febre alta, acompanhada por doce seca',2,NULL,2,4,_binary ''),(12,'2022-09-19','Diareias',3,NULL,2,5,_binary ''),(13,'2022-09-20','Doce',1,NULL,2,6,_binary '\0'),(14,'2022-09-20','Febre Amarela',2,NULL,2,5,_binary '\0'),(15,'2022-09-20','Diareias, doce',3,NULL,2,1,_binary ''),(16,'2022-09-20','Desconhecidos',4,NULL,2,1,_binary '\0'),(17,'2022-09-20','Sindrome de Estocolmo',5,NULL,2,2,_binary ''),(18,'2022-09-20','Sindrome de MEAL',6,NULL,2,3,_binary ''),(19,'2022-10-19','ffffff',1,NULL,1,1,_binary '\0'),(20,'2022-10-19','nfhhfh',2,NULL,1,1,_binary '\0');
/*!40000 ALTER TABLE `triagens` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_on` date NOT NULL,
  `email` varchar(128) NOT NULL,
  `email_verification_status` tinyint(1) NOT NULL DEFAULT '0',
  `first_name` varchar(64) NOT NULL,
  `image` varchar(128) NOT NULL,
  `last_name` varchar(128) NOT NULL,
  `updated_on` date DEFAULT NULL,
  `encrypt_password` varchar(64) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'2022-06-07','rony@codejava.rvw',1,'Rony Zaona Joao','','Albino','2022-06-08','$2a$10$94P.4xbbJ27F4DqDL3YzdOqVW0Udodq2TQ4ZgF2pBeJT.Jlrtsd8q'),(2,'2022-06-07','zaonanelson@codejava.rvw',1,'Zaona Jaoa','','Albino','2022-06-08','$2a$10$94P.4xbbJ27F4DqDL3YzdOqVW0Udodq2TQ4ZgF2pBeJT.Jlrtsd8q'),(3,'2022-06-07','inovancia@codejava.rvw',1,'Inovancia Zaona','','Joao',NULL,'$2a$10$94P.4xbbJ27F4DqDL3YzdOqVW0Udodq2TQ4ZgF2pBeJT.Jlrtsd8q'),(4,'2022-06-07','inocencia@codejava.rvw',1,'Inocencia Zaona','','Joao',NULL,'$2a$10$94P.4xbbJ27F4DqDL3YzdOqVW0Udodq2TQ4ZgF2pBeJT.Jlrtsd8q'),(6,'2022-06-11','atija@codejava.rvw',1,'Atija Zaona','','Joao',NULL,'atija2022'),(7,'2022-06-11','anamaria@codejava.rvw',1,'Ana Maria','','Zaona',NULL,'anamaria2022'),(8,'2022-08-08','alfredo.dias@codejava.rvw',1,'Alfredo','','Dias',NULL,'dias2022'),(14,'2022-08-09','nelsonzja@codejava.rvw',0,'Nelson','','Albino',NULL,'nelson2022');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKj6m8fwv7oqv74fcehir1a9ffy` (`role_id`),
  CONSTRAINT `FK2o0jvgh89lemvvo17cbqvdxaa` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKj6m8fwv7oqv74fcehir1a9ffy` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
INSERT INTO `users_roles` VALUES (1,1),(14,2),(14,4),(3,5),(4,5);
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'soccorius'
--

--
-- Dumping routines for database 'soccorius'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-13 15:19:56
