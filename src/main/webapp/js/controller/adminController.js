app.controller("adminCtrl", function($scope, $http, $window) {
//
//	
//	$scope.profile = "ADMIN";
//	
//	$scope.users = [];
//	$scope.serachUserByUsername = "";
//	$scope.serachUserByEmail = "";
//	$scope.serachUserByName = "";
//	$scope.serachUserBySurname = "";
//	$scope.serachUserByRole = "";
//	$scope.resetUserButton = {};
//	
//	$scope.restaurants = [];
//	$scope.searchRestaurantByName="";
//	$scope.searchRestaurantByAddress="";
//	$scope.searchRestaurantByCategory="";
//	$scope.resetRestaurantButton = {};
//	
//	$scope.products = [];
//	$scope.searchProductByName="";
//	$scope.searchProductByType="";
//	$scope.searchProductByMinPrice="";
//	$scope.searchProductByMaxPrice="";
//	$scope.searchProductByQuantity="";
//	$scope.searchProductByUnit="";
//	$scope.searchProductByRestaurant="";
//	$scope.searchProductByDescription="";
//	$scope.resetProductButton = {};
//	
//	$scope.vehicles = [];
//	$scope.searchVehicleByBrand="";
//	$scope.searchVehicleByModel="";
//	$scope.searchVehicleByType="";
//	$scope.searchVehicleByRegistration="";
//	$scope.searchVehicleByYear="";
//	$scope.searchVehicleByAvailable="";
//	$scope.searchVehicleByNote="";
//	$scope.resetVehicleButton = {};
//	
//	var usersBackup, restaurantsBackup, productsBackup, vehiclesBackup;
//	
//	// USERS
//	
//	$http({
//        method: 'GET',
//        url: 'api/user/all',
//	}).then(function mySucc(response){
//		for(var i=0; i<response.data.length; i++) {
//			$scope.users[response.data[i].id] = response.data[i];
//		}
//        usersBackup = angular.copy($scope.users);
//    });
//	
//	$scope.onUserChange = function(index, user) {
//		if(usersBackup[user.id].role!==user.role) $scope.resetUserButton[index]=true;
//		else $scope.resetUserButton[index]=false;
//	}
//	
//	$scope.resetUser = function(index, id) {
//		$scope.users[index].role = angular.copy(usersBackup[id].role);
//		$scope.resetUserButton[index] = false;
//	}
//	
//	$scope.updateUser = function(index, user) {
//		$http({
//	        method: 'PUT',
//	        url: 'api/user/',
//	        data: user
//		}).then(function mySucc(response){
//			$scope.resetUserButton[index] = false;
//			usersBackup[user.id] = user;
//	    }, function mySucc(response) {
//	    	console.log("Something went wrongly!");
//	    });
//	}
//	
//	$scope.searchUser = function() {
//		var j=0;
//		$scope.users = [];
//		for(var i=0; i<usersBackup.length; i++) {
//			if(usersBackup[i] && usersBackup[i].username.toLowerCase().includes($scope.serachUserByUsername.toLowerCase()) &&
//					usersBackup[i].email.toLowerCase().includes($scope.serachUserByEmail.toLowerCase()) &&
//					usersBackup[i].name.toLowerCase().includes($scope.serachUserByName.toLowerCase()) &&
//					usersBackup[i].surname.toLowerCase().includes($scope.serachUserBySurname.toLowerCase()) &&
//					usersBackup[i].role.toLowerCase().includes($scope.serachUserByRole.toLowerCase())) {
//				$scope.users[usersBackup[i].id] =angular.copy(usersBackup[i]);
//				$scope.resetUserButton[j]=false; j++;
//			}
//		}	
//	}
//
//	$scope.clearUserFilter = function() {
//		$scope.serachUserByUsername = "";
//		$scope.serachUserByEmail = "";
//		$scope.serachUserByName = "";
//		$scope.serachUserBySurname = "";
//		$scope.serachUserByRole = "";
//		$scope.searchUser();
//	}
//	
//	// RESTAURANTS
//	
//	$http({
//        method: 'GET',
//        url: 'api/restaurant/all',
//	}).then(function mySucc(response){
//		for(var i=0; i<response.data.length; i++) {
//			$scope.restaurants[response.data[i].id] = response.data[i];
//		}
//        restaurantsBackup = angular.copy($scope.restaurants);
//    });
//	
//	$scope.onRestaurantChange = function(index, restaurant) {
//		if(restaurantsBackup[restaurant.id].name!==restaurant.name ||
//				restaurantsBackup[restaurant.id].address!==restaurant.address ||
//				restaurantsBackup[restaurant.id].category!==restaurant.category) $scope.resetRestaurantButton[index]=true;
//		else $scope.resetRestaurantButton[index]=false;
//	}
//	
//	$scope.resetRestaurant = function(index, id) {
//		$scope.restaurants[index] = angular.copy(restaurantsBackup[id]);
//		$scope.resetRestaurantButton[index] = false;
//	}
//	
//	$scope.updateRestaurant = function(index, restaurant) {
//		$http({
//	        method: 'PUT',
//	        url: 'api/restaurant/',
//	        data: restaurant
//		}).then(function mySucc(response){
//			$scope.resetRestaurantButton[index] = false;
//			restaurantsBackup[restaurant.id] = restaurant;
//	    }, function mySucc(response) {
//	    	console.log("Something went wrongly!");
//	    });
//	}
//	
//	$scope.searchRestaurant = function() {
//		$scope.restaurants = [];
//		$scope.resetRestaurantButton = {};
//		var j=0;
//		for(var i=0; i<restaurantsBackup.length; i++) {
//			if(restaurantsBackup[i] && restaurantsBackup[i].name.toLowerCase().includes($scope.searchRestaurantByName.toLowerCase()) &&
//					restaurantsBackup[i].address.toLowerCase().includes($scope.searchRestaurantByAddress.toLowerCase()) &&
//					restaurantsBackup[i].category.toLowerCase().includes($scope.searchRestaurantByCategory.toLowerCase())) {
//				$scope.restaurants[restaurantsBackup[i].id] = angular.copy(restaurantsBackup[i]);
//				$scope.resetRestaurantButton[j]=false; j++;
//			}	
//		}	
//	}
//	
//	$scope.clearRestaurantFilter = function() {
//		$scope.searchRestaurantByName="";
//		$scope.searchRestaurantByAddress="";
//		$scope.searchRestaurantByCategory="";
//		$scope.searchRestaurant();
//	}
//	
//	function clearNewRestaurantFields() {
//		$scope.newRestaurant = {};
//		$scope.newRestaurantButton = false;
//		$scope.searchRestaurant();
//	}
//	
//	$scope.onNewRestaurantChange = function() {
//		if($scope.newRestaurant.name && $scope.newRestaurant.name.trim().length!=0 &&
//				$scope.newRestaurant.address && $scope.newRestaurant.address.trim().length!=0 &&
//				$scope.newRestaurant.category && $scope.newRestaurant.category.trim().length!=0)
//			$scope.newRestaurantButton = true;
//		else 
//			$scope.newRestaurantButton = false;
//	}  
//	
//	$scope.addRestaurant = function(restaurant) {
//		$http({
//	        method: 'POST',
//	        url: 'api/restaurant/',
//	        data: restaurant
//		}).then(function mySucc(response){
//			restaurant.id = response.data;
//			restaurantsBackup[restaurant.id] = restaurant;
//			clearNewRestaurantFields();
//	    }, function mySucc(response) {
//	    	console.log("Something went wrongly!");
//	    });
//	}
//	
//	$scope.deleteRestaurant = function(id) {
//		$http({
//	        method: 'DELETE',
//	        url: 'api/restaurant/' + id
//		}).then(function () {
//			restaurantsBackup.splice(id, 1); 
//			$scope.clearRestaurantFilter();
//		}).then(function () {
//			console.log("Something went wrongly!");
//		});
//	}
//	
//	// PRODUCTS
//	
//	$http({
//        method: 'GET',
//        url: 'api/product/all',
//	}).then(function mySucc(response){
//		for(var i=0; i<response.data.length; i++) {
//			$scope.products[response.data[i].id] = response.data[i];
//			for(var j=0; j<restaurantsBackup.length; j++) {
//				if(restaurantsBackup[j] && response.data[i].restaurantId==restaurantsBackup[j].id) {
//					$scope.products[response.data[i].id].restaurant = restaurantsBackup[j].name;
//					console.log($scope.products[response.data[i].id].restaurant);
//				}
//			}
//		}
//		productsBackup = angular.copy($scope.products);
//		if(!$scope.newProduct) $scope.newProduct = {};
//		$scope.newProduct.restaurants = angular.copy(restaurantsBackup);
//    });
//	
//	$scope.onProductChange = function(index, product) {
//		if(productsBackup[product.id].name!==product.name ||
//				productsBackup[product.id].type!==product.type ||
//				productsBackup[product.id].price!==product.price ||
//				productsBackup[product.id].quantity!==product.quantity ||
//				productsBackup[product.id].unit!==product.unit ||
//				productsBackup[product.id].restaurantId!==product.restaurantId ||
//				productsBackup[product.id].description!==product.description) $scope.resetProductButton[index]=true;
//		else $scope.resetProductButton[index]=false;
//	}
//	
//	$scope.resetProduct = function(index, id) {
//		$scope.products[index] = angular.copy(productsBackup[id]);
//		$scope.resetProductButton[index] = false;
//	}
//	
//	$scope.updateProduct = function(index, product) {
//		$http({
//	        method: 'PUT',
//	        url: 'api/product/',
//	        data: product
//		}).then(function mySucc(response){
//			$scope.resetProductButton[index] = false;
//			productsBackup[product.id] = product;
//	    }, function mySucc(response) {
//	    	console.log("Something went wrongly!");
//	    });
//	}
//	
//	$scope.searchProduct = function() {
//		$scope.products = [];
//		$scope.resetProductButton = {};
//		var minPrice = $scope.searchProductByMinPrice !== "" ? angular.copy($scope.searchProductByMinPrice) : Number.MIN_SAFE_INTEGER;
//		var maxPrice = $scope.searchProductByMaxPrice !== "" ? angular.copy($scope.searchProductByMaxPrice) : Number.MAX_SAFE_INTEGER;
//		var description, quantity;
//		
//		var j=0;
//		for(var i=0; i<productsBackup.length; i++) {
//			if(productsBackup[i]) {
//				description = productsBackup[i].description != null ? productsBackup[i].description : "";
//				quantity = $scope.searchProductByQuantity !== "" ? angular.copy($scope.searchProductByQuantity) : productsBackup[i].quantity;
//				if(productsBackup[i] && productsBackup[i].name.toLowerCase().includes($scope.searchProductByName.toLowerCase()) &&
//						productsBackup[i].type.toLowerCase().includes($scope.searchProductByType.toLowerCase()) &&
//						productsBackup[i].price >= minPrice &&		
//						productsBackup[i].price <= maxPrice &&
//						productsBackup[i].quantity == quantity &&
//						productsBackup[i].unit.toLowerCase().includes($scope.searchProductByUnit.toLowerCase()) &&
//						productsBackup[i].restaurant.toLowerCase().includes($scope.searchProductByRestaurant) &&
//						description.toLowerCase().includes($scope.searchProductByDescription.toLowerCase())) {
//					$scope.products[productsBackup[i].id] = angular.copy(productsBackup[i]);
//					$scope.resetProductButton[j]=false; j++;
//				}	
//			}
//		}	
//	}
//	
//	$scope.clearProductFilter = function() {
//		$scope.searchProductByName="";
//		$scope.searchProductByType="";
//		$scope.searchProductByMinPrice="";
//		$scope.searchProductByMaxPrice="";
//		$scope.searchProductByQuantity="";
//		$scope.searchProductByUnit="";
//		$scope.searchProductByRestaurant="";
//		$scope.searchProductByDescription="";
//		$scope.searchProduct();
//	}
//	
//	function clearNewProductFields() {
//		$scope.newProduct = {};
//		$scope.newProductButton = false;
//		$scope.searchProduct();
//		$scope.newProduct.restaurants = angular.copy(restaurantsBackup);
//	}
//	
//	$scope.onNewProductChange = function() {
//		var validRestaurant = false;
//		for (var rest of restaurantsBackup) {
//			if(rest && rest.name===$scope.newProduct.restaurant) {
//				validRestaurant = true;
//				$scope.newProduct.restaurantId = rest.id;
//				break;
//			}
//		}		
//		if(validRestaurant && $scope.newProduct.name && $scope.newProduct.name.trim().length!=0 &&
//				$scope.newProduct.type && $scope.newProduct.type.trim().length!=0 &&
//				$scope.newProduct.price && $scope.newProduct.price.trim().length!=0 &&
//				$scope.newProduct.quantity && $scope.newProduct.quantity.trim().length!=0 &&
//				$scope.newProduct.unit && $scope.newProduct.unit.trim().length!=0 &&
//				$scope.newProduct.restaurant && $scope.newProduct.restaurant.trim().length!=0)
//			$scope.newProductButton = true;
//		else 
//			$scope.newProductButton = false;
//	}  
//	
//	$scope.addProduct = function(product) {
//		$http({
//	        method: 'POST',
//	        url: 'api/product/',
//	        data: product
//		}).then(function mySucc(response){
//			product.id = response.data;
//			delete(product.restaurants);
//			productsBackup[product.id] = product;
//			clearNewProductFields();
//	    }, function mySucc(response) {
//	    	console.log("Something went wrongly!");
//	    });
//	}
//	
//	$scope.deleteProduct = function(id) {
//		$http({
//	        method: 'DELETE',
//	        url: 'api/product/' + id
//		}).then(function () {
//			productsBackup.splice(id, 1); 
//			$scope.clearProductFilter();
//		}).then(function () {
//			console.log("Something went wrongly!");
//		});
//	}
//	
//	// VEHICLES
//	
//	$http({
//        method: 'GET',
//        url: 'api/vehicle/all',
//	}).then(function mySucc(response){
//		for(var i=0; i<response.data.length; i++) {
//			$scope.vehicles[response.data[i].id] = response.data[i];
//		}
//        vehiclesBackup = angular.copy($scope.vehicles);
//    });
//	
//	$scope.onVehicleChange = function(index, vehicle) {
//		if(vehiclesBackup[vehicle.id].brand!==vehicle.brand ||
//				vehiclesBackup[vehicle.id].model!==vehicle.model ||
//				vehiclesBackup[vehicle.id].type!==vehicle.type ||
//				vehiclesBackup[vehicle.id].regNumber!==vehicle.regNumber ||
//				vehiclesBackup[vehicle.id].productionYear!==vehicle.productionYear ||
//				vehiclesBackup[vehicle.id].available!==vehicle.available ||
//				vehiclesBackup[vehicle.id].note!==vehicle.note) {
//			$scope.resetVehicleButton[index]=true;
//		} else {
//			$scope.resetVehicleButton[index]=false;
//		}
//	}
//	
//	$scope.resetVehicle = function(index, id) {
//		$scope.vehicles[index] = angular.copy(vehiclesBackup[id]);
//		$scope.resetVehicleButton[index] = false;
//	}
//	
//	$scope.updateVehicle = function(index, vehicle) {
//		$http({
//	        method: 'PUT',
//	        url: 'api/vehicle/',
//	        data: vehicle
//		}).then(function mySucc(response){
//			$scope.resetVehicleButton[index] = false;
//			vehiclesBackup[vehicle.id] = vehicle;
//	    }, function mySucc(response) {
//	    	console.log("Something went wrongly!");
//	    });
//	}
//	
//	$scope.searchVehicle = function() {
//		$scope.vehicles = [];
//		$scope.resetVehicleButton = {};
//		var j=0;
//		for(var i=0; i<vehiclesBackup.length; i++) {
//			if(vehiclesBackup[i] && vehiclesBackup[i].brand.toLowerCase().includes($scope.searchVehicleByBrand.toLowerCase()) &&
//					vehiclesBackup[i].model.toLowerCase().includes($scope.searchVehicleByModel.toLowerCase()) &&
//					vehiclesBackup[i].type.toLowerCase().includes($scope.searchVehicleByType.toLowerCase()) &&
//					vehiclesBackup[i].regNumber.toLowerCase().includes($scope.searchVehicleByRegistration.toLowerCase()) &&
//					vehiclesBackup[i].productionYear.toString().includes($scope.searchVehicleByYear.toLowerCase()) &&
//					vehiclesBackup[i].available.toString().includes($scope.searchVehicleByAvailable.toLowerCase()) &&
//					vehiclesBackup[i].note.toLowerCase().includes($scope.searchVehicleByNote.toLowerCase())) {
//				$scope.vehicles[vehiclesBackup[i].id] = angular.copy(vehiclesBackup[i]);
//				$scope.resetVehicleButton[j]=false; j++;
//			}	
//		}	
//	}
//	
//	$scope.clearVehicleFilter = function() {
//		$scope.searchVehicleByBrand="";
//		$scope.searchVehicleByModel="";
//		$scope.searchVehicleByType="";
//		$scope.searchVehicleByRegistration="";
//		$scope.searchVehicleByYear="";
//		$scope.searchVehicleByAvailable="";
//		$scope.searchVehicleByNote="";
//		$scope.searchVehicle();
//	}
//	
//	function clearNewVehicleFields() {
//		$scope.newVehicle= {};
//		$scope.newVehicleButton = false;
//		$scope.searchVehicle();
//	}
//	
//	$scope.onNewVehicleChange = function() {
//		if($scope.newVehicle.brand && $scope.newVehicle.brand.trim().length!=0 &&
//				$scope.newVehicle.model && $scope.newVehicle.model.trim().length!=0 &&
//				$scope.newVehicle.type && $scope.newVehicle.type.trim().length!=0 &&
//				$scope.newVehicle.regNumber && $scope.newVehicle.regNumber.trim().length!=0 &&
//				$scope.newVehicle.productionYear &&
//				$scope.newVehicle.available && $scope.newVehicle.available.trim().length!=0)
//			$scope.newVehicleButton = true;
//		else 
//			$scope.newVehicleButton = false;
//	}  
//	
//	$scope.addVehicle = function(vehicle) {
//		$http({
//	        method: 'POST',
//	        url: 'api/vehicle/',
//	        data: vehicle
//		}).then(function mySucc(response){
//			vehicle.id = response.data;
//			vehiclesBackup[vehicle.id] = vehicle;
//			clearNewVehicleFields();
//	    }, function mySucc(response) {
//	    	console.log("Something went wrongly!");
//	    });
//	}
//	
//	$scope.deleteVehicle = function(id) {
//		$http({
//	        method: 'DELETE',
//	        url: 'api/vehicle/' + id
//		}).then(function () {
//			vehiclesBackup.splice(id, 1); 
//			$scope.clearVehicleFilter();
//		}).then(function () {
//			console.log("Something went wrongly!");
//		});
//	}

});