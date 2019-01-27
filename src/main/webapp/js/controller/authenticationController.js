app.controller("authenticationCtrl", function($scope, $http, authenticationService, $window) {
	
	$scope.messageLogin="";
	$scope.messageRegistration="";
	
	$scope.loginButton = false;
	$scope.login = function() {
		login($scope.usernameLogin, $scope.passwordLogin);
	}
	
	function login(username, password) {
		$scope.messageLogin="";
		var data = {"username":username, "password":password};
		console.log(authenticationService.myFunc(255));
		authenticationService.login(data).then(function(response) {
			localStorage.setItem("username", response.data.username);
			localStorage.setItem("userId", response.data.id);
			localStorage.setItem("role", response.data.role);
			$window.location.reload();
			console.log("OOOKKK");
		}, function(response) {
			console.log(response.data);
			$scope.messageLogin= response.data;
			console.log("WROOOONG");
		});
	}
	
	$scope.loginChange = function() {
		if($scope.usernameLogin && $scope.usernameLogin.trim().length!=0 && $scope.passwordLogin && $scope.passwordLogin.trim().length!=0 ) {
			$scope.loginButton = true;
		} else {
			$scope.loginButton = false;
		}
	}
	
	
	
	
	
	
	$scope.registerButton = false;
	$scope.register = function() {
		$scope.messageRegistration="";
		console.log($scope.nameRegistration);
		console.log($scope.surnameRegistration);
		var data = {"name":$scope.nameRegistration, "surname":$scope.surnameRegistration,
				"email":$scope.emailRegistration, "phone":$scope.phoneRegistration,
				"username":$scope.usernameRegistration, "password":$scope.passwordRegistration,
				"password2":$scope.password2Registration};
		authenticationService.register(data).then(function(response) {
			console.log(response.data);
			login($scope.usernameRegistration, $scope.passwordRegistration);
		}, function(response) {
			console.log(response.data);
			$scope.messageRegistration= response.data;
		});
	}
	
	$scope.registrationChange = function() {
		if($scope.nameRegistration && $scope.nameRegistration.trim().length!=0 && 
				$scope.surnameRegistration && $scope.surnameRegistration.trim().length!=0 &&
				$scope.emailRegistration && $scope.emailRegistration.trim().length!=0 && 
				$scope.phoneRegistration && $scope.phoneRegistration.trim().length!=0 &&
				$scope.usernameRegistration && $scope.usernameRegistration.trim().length!=0 &&
				$scope.passwordRegistration && $scope.passwordRegistration.trim().length!=0 &&
				$scope.password2Registration && $scope.password2Registration.trim().length!=0) {
			$scope.registerButton = true;
		} else {
			$scope.registerButton = false;
		}
	}
});