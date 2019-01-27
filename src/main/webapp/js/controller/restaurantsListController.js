app.controller("restaurantsListCtrl", function($scope, $http, restaurantService, $window) {
	
	$scope.restaurants = [];
	$scope.searchRestaurantByName="";
	$scope.searchRestaurantByAddress="";
	$scope.searchRestaurantByCategory="";
	$scope.resetRestaurantButton = {};
	
	var restaurantsBackup;
	
	restaurantService.getAll().then(
		function mySucc(response){
			for(var i=0; i<response.data.length; i++) 
				$scope.restaurants[response.data[i].id] = response.data[i];
			restaurantsBackup = angular.copy($scope.restaurants);
		}, function error(response) {
			console.log("Getting all the restaurants went wrong...")
		}
	);
	
	$scope.onRestaurantChange = function(index, restaurant) {
		if(restaurantsBackup[restaurant.id].name!==restaurant.name ||
				restaurantsBackup[restaurant.id].address!==restaurant.address ||
				restaurantsBackup[restaurant.id].category!==restaurant.category) $scope.resetRestaurantButton[index]=true;
		else $scope.resetRestaurantButton[index]=false;
	}
	
	$scope.resetRestaurant = function(index, id) {
		$scope.restaurants[index] = angular.copy(restaurantsBackup[id]);
		$scope.resetRestaurantButton[index] = false;
	}
	
	$scope.updateRestaurant = function(index, restaurant) {
		restaurantService.update(restaurant).then(function mySucc(response){
			$scope.resetRestaurantButton[index] = false;
			restaurantsBackup[restaurant.id] = restaurant;
	    }, function mySucc(response) {
	    	console.log("Restaurant couldn't be updated...");
	    });
	}
	
	$scope.searchRestaurant = function() {
		$scope.restaurants = [];
		$scope.resetRestaurantButton = {};
		var j=0;
		for(var i=0; i<restaurantsBackup.length; i++) {
			if(restaurantsBackup[i] && restaurantsBackup[i].name.toLowerCase().includes($scope.searchRestaurantByName.toLowerCase()) &&
					restaurantsBackup[i].address.toLowerCase().includes($scope.searchRestaurantByAddress.toLowerCase()) &&
					restaurantsBackup[i].category.toLowerCase().includes($scope.searchRestaurantByCategory.toLowerCase())) {
				$scope.restaurants[restaurantsBackup[i].id] = angular.copy(restaurantsBackup[i]);
				$scope.resetRestaurantButton[j]=false; j++;
			}	
		}	
	}
	
	$scope.clearRestaurantFilter = function() {
		$scope.searchRestaurantByName="";
		$scope.searchRestaurantByAddress="";
		$scope.searchRestaurantByCategory="";
		$scope.searchRestaurant();
	}
	
	function clearNewRestaurantFields() {
		$scope.newRestaurant = {};
		$scope.newRestaurantButton = false;
		$scope.searchRestaurant();
	}
	
	$scope.onNewRestaurantChange = function() {
		if($scope.newRestaurant.name && $scope.newRestaurant.name.trim().length!=0 &&
				$scope.newRestaurant.address && $scope.newRestaurant.address.trim().length!=0 &&
				$scope.newRestaurant.category && $scope.newRestaurant.category.trim().length!=0)
			$scope.newRestaurantButton = true;
		else 
			$scope.newRestaurantButton = false;
	}  
	
	$scope.addRestaurant = function(restaurant) {
		restaurantService.add(restaurant).then(
			function mySucc(response){
				restaurant.id = response.data;
				restaurantsBackup[restaurant.id] = restaurant;
				$scope.restaurants[restaurant.id] = angular.copy(restaurantsBackup[restaurant.id]);
				clearNewRestaurantFields();
				$scope.clearRestaurantFilter();
				$scope.searchRestaurant();
			}, function mySucc(response) {
				console.log("Restaurant couldn't be added...");
			}
		);
	}
	
	$scope.deleteRestaurant = function(id) {
		restaurantService.remove(id).then(
			function() {
				restaurantsBackup.splice(id, 1); 
				$scope.clearRestaurantFilter();
			}, function() {
				console.log("Restaurant couldn't be deleted...");
			}
		);
	}
});