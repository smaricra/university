app.controller("dashboardCtrl", function($scope, $rootScope, restaurantService, productService) {
	
	var restaurants = [];
	var products = [];
	var top10Products = [];
	
	getRestaurants();
	getProducts();
	
	$scope.addToChart = function(productId, amount) {
		if($rootScope.productOrders ==null) $rootScope.productOrders = [];
		if(amount>='1') {
			for(var i=0; i<$rootScope.productOrders.length; i++) {
				if($rootScope.productOrders[i].productId == productId) {
					var amountInt = parseInt($rootScope.productOrders[i].amount) + parseInt(amount);
					$rootScope.productOrders[i].amount = amountInt.toString();
					localStorage.setItem("productOrders",  JSON.stringify($rootScope.productOrders));
					return;
				}
				
			}
			$rootScope.productOrders.push({productId: productId.toString(), amount: amount.toString()});
			localStorage.setItem("productOrders",  JSON.stringify($rootScope.productOrders));
		}
		
	}
	
	$scope.topTen = function(evt, animName) {
		$scope.categorySelected = false;
		$scope.restaurantSelected = false;
		$scope.top10Selected = true;
		$scope.best10Products = angular.copy(top10Products);
		var i, x, tablinks;
		tablinks = document.getElementsByClassName("tablink");
		for (i = 0; i < tablinks.length; i++) {
		   tablinks[i].className = tablinks[i].className.replace(" w3-red", "");
		}
		$scope.categorySelected = true;
		evt.currentTarget.className += " w3-red";
	}
	
	$scope.openLink = function(evt, animName) {
		$scope.categorySelected = true;
		$scope.restaurantSelected = false;
		$scope.top10Selected = false;
		$scope.selectedRestaurants = [];
		  for(var i=0; i<restaurants.length; i++) {
			  if(restaurants[i] && restaurants[i].category===animName) {
				  $scope.selectedRestaurants[i] = angular.copy(restaurants[i]);
			  }
		  }
		  var i, x, tablinks;
		  tablinks = document.getElementsByClassName("tablink");
		  for (i = 0; i < tablinks.length; i++) {
		     tablinks[i].className = tablinks[i].className.replace(" w3-red", "");
		  }
		  $scope.categorySelected = true;
		  evt.currentTarget.className += " w3-red";
	}
	
	$scope.onSelectRestaurant = function(id) {
		$scope.categorySelected = false;
		$scope.top10Selected = false;
		$scope.restaurantSelected = true;
		$scope.selectedProducts = [];
		$scope.selectedRestaurant = angular.copy(restaurants[id]);
		for(var i=0; i<products.length; i++) {
			if(products[i] && products[i].restaurantId == id) {
				$scope.selectedProducts[i] = angular.copy(products[i]);
			}
		}
	}
	
	$scope.onBackRestaurant = function() {
		$scope.categorySelected = true;
		$scope.restaurantSelected = false;
	}

	function getRestaurants() {
		restaurantService.getAll().then(function(response) {
			for(var i=0; i<response.data.length; i++) {
				restaurants[response.data[i].id] = response.data[i];
			}
			getTop10Products();
		}, function(response) {
		});
	}
	
	function getProducts() {
		productService.getAll().then(function(response) {
			for(var i=0; i<response.data.length; i++) {
				products[response.data[i].id] = response.data[i];
			}
		}, function(response) {
			
		});
	}
	
	function getTop10Products() {
		productService.getTop10().then(function(response) {
			for(var i=0; i<response.data.length; i++) {
				top10Products[response.data[i].id] = response.data[i];
				for(var j=0; j<restaurants.length; j++) {
					if(restaurants[j] && response.data[i].restaurantId == restaurants[j].id) {
						top10Products[response.data[i].id].restaurantName = restaurants[j].name;
					}
				}
			}
		}, function(response) {
			
		});
	}
});