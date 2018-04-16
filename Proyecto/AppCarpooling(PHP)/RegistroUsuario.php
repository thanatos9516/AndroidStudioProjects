<?php 
require_once 'Conexion_Mysql.php';

//Recibiendo parámetros
$cedula = $_REQUEST['cedula'];
$nombre = $_REQUEST['nombre'];
$apellido1 = $_REQUEST['apellido1'];
$apellido2 = $_REQUEST['apellido2'];
$nacionalidad = $_REQUEST['nacionalidad'];
$usu = $_REQUEST['usu'];
$clave = $_REQUEST['clave'];

$conect = new Conexion_Mysql();
$consulta = "insert into tblogin (Correo,Clave) values '".$usu."', '".$clave."'; 
	insert into tbpersona (Cedula,Nombre,Apellido1,Apellido2,Nacionalidad,Correo,Clave) values '".$cedula."','".$nombre."','".$apellido1."','".$apellido2."','".$nacionalidad."''".$usu."', '".$clave."' ;";

 /* insert into tblogin (Correo,Clave)
values('anyu@gmail.com','1234');
insert into tbpersona (Cedula,Nombre,Apellido1,Apellido2,Nacionalidad,Correo,Clave)
values('116000070','Villalobos','Peralta','Costarricense','anyu@gmail.com','1234'); 

http://192.168.241.2:8080/AppCarpooling/RegistroUsuario.php?cedula=116000070&nombre=yu&apellido1=villa&apellido2=pera&nacionalidad=costarricense&usu=yu@hotmail.com&clave=1234
*/

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