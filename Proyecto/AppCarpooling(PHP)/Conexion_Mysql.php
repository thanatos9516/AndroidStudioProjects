<?php
class Conexion_Mysql extends mysqli {
	private $host = "localhost";
	private $usuario = "root";
	private $clave = "root";
	private $db = "appcarpooling";

	public function __construct() {
		parent::__construct($this -> host, $this -> usuario, $this -> clave, $this -> db);
		$this -> set_charset('utf-8');
		$this -> connect_errno ? die('Error en la conexion' . mysqli_connect_errno()) : $m = 'Conectado';
		//echo $m;
	}

}
?>