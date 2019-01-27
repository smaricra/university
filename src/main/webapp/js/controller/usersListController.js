app.controller("usersListCtrl", function($scope, $http, userService, $window) {
	
	$scope.users = [];
	$scope.serachUserByUsername = "";
	$scope.serachUserByEmail = "";
	$scope.serachUserByName = "";
	$scope.serachUserBySurname = "";
	$scope.serachUserByRole = "";
	$scope.resetUserButton = {};
	
	var usersBackup;
	
	userService.getAll().then(function mySucc(response){
		for(var i=0; i<response.data.length; i++) {
			$scope.users[response.data[i].id] = response.data[i];
		}
        usersBackup = angular.copy($scope.users);
    });
	
	$scope.onUserChange = function(index, user) {
		if(usersBackup[user.id].role!==user.role) $scope.resetUserButton[index]=true;
		else $scope.resetUserButton[index]=false;
	}
	
	$scope.resetUser = function(index, id) {
		$scope.users[index].role = angular.copy(usersBackup[id].role);
		$scope.resetUserButton[index] = false;
	}
	
	$scope.updateUser = function(index, user) {
		userService.update(user).then(function mySucc(response){
			$scope.resetUserButton[index] = false;
			usersBackup[user.id] = angular.copy(user);
	    }, function mySucc(response) {
	    	console.log("Something went wrongly!");
	    });
	}
	
	$scope.searchUser = function() {
		var j=0;
		$scope.users = [];
		for(var i=0; i<usersBackup.length; i++) {
			if(usersBackup[i] && usersBackup[i].username.toLowerCase().includes($scope.serachUserByUsername.toLowerCase()) &&
					usersBackup[i].email.toLowerCase().includes($scope.serachUserByEmail.toLowerCase()) &&
					usersBackup[i].name.toLowerCase().includes($scope.serachUserByName.toLowerCase()) &&
					usersBackup[i].surname.toLowerCase().includes($scope.serachUserBySurname.toLowerCase()) &&
					usersBackup[i].role.toLowerCase().includes($scope.serachUserByRole.toLowerCase())) {
				$scope.users[usersBackup[i].id] =angular.copy(usersBackup[i]);
				$scope.resetUserButton[j]=false; j++;
			}
		}	
	}

	$scope.clearUserFilter = function() {
		$scope.serachUserByUsername = "";
		$scope.serachUserByEmail = "";
		$scope.serachUserByName = "";
		$scope.serachUserBySurname = "";
		$scope.serachUserByRole = "";
		$scope.searchUser();
	}
});