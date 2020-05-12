<?php

	$dbh = 'mysql:host=localhost; dbname=Friendster';
	$username = 'root';
	$password = '';

	try {
		$pdo = new PDO($dbh,$username,$password);
	} catch (Exception $e) {
		echo "Connection Error";
		die();
	}
?>