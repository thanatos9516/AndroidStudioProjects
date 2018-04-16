<?php 
require_once 'Conexion_Mysql.php';

//Recibiendo parámetros
$usu = $_REQUEST['usu'];
$clave = $_REQUEST['clave'];

$conect = new Conexion_Mysql();
$consulta = "select * from tblogin where Correo = '".$usu."' and Clave = '".$clave."' ";

$result = $conect -> query($consulta);
//Arreglo para enviarlo en JSON
$datos = array();
//Llena el arreglo
foreach ($result as $row) {
	$datos[] = $row;
}
//Si existe usuario, envía por JSON
echo json_encode($datos);

?>