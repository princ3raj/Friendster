<?php

// Add route callbacks
$app->post('/app/login', function ( $request) {

		include __DIR__ .'/../bootstrap/dbconnect.php';

 		$uid = $request->getParsedBody()['uid'];
		 $name = $request->getParsedBody()['name'];
		 $email = $request->getParsedBody()['email'];
		 $profileUrl = $request->getParsedBody()['profileUrl'];
		 $coverUrl = $request->getParsedBody()['coverUrl'];
		 $userToken = $request->getParsedBody()['userToken'];


		 $checkStmt =  $pdo->prepare("SELECT `uid` from `users` WHERE `uid` = :uid LIMIT 1");
		 $checkStmt->bindParam(':uid', $uid, PDO::PARAM_STR);
		 $checkStmt->execute();			 
		 $count = $checkStmt->rowCount();

		 if($count==1){
		 
		 		// user has already signed up just update the token 

 			$stmt = $pdo->prepare("UPDATE  `users` SET `userToken` = :userToken WHERE `uid` = :uid; ");
		 	$stmt->bindParam(':userToken', $userToken, PDO::PARAM_STR);
			$stmt->bindParam(':uid', $uid, PDO::PARAM_STR);
			$stmt= $stmt->execute();
		
		 	
		  }else{

		  			// User has just signed up... insert all the user data from here
		 $stmt = $pdo->prepare("INSERT INTO `users` (`uid`, `name`, `email`, `profileUrl`, `coverUrl`, `userToken`) VALUES (:uid, :name, :email, :profileUrl, :coverUrl, :userToken); ");

		 $stmt->bindParam(':uid', $uid, PDO::PARAM_STR);
		 $stmt->bindParam(':name', $name, PDO::PARAM_STR);
		 $stmt->bindParam(':email', $email, PDO::PARAM_STR);
		 $stmt->bindParam(':profileUrl', $profileUrl, PDO::PARAM_STR);
		 $stmt->bindParam(':coverUrl', $coverUrl, PDO::PARAM_STR);
		 $stmt->bindParam(':userToken', $userToken, PDO::PARAM_STR);
		 $stmt= $stmt->execute();
	
		
			}	

		 if($stmt){
				echo true;
			}else{
				echo false;
			}

});


?>