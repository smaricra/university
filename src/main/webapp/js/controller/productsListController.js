app.controller("productsListCtrl", function($scope, $http, productService, restaurantService, $window) {
	
	$scope.restaurantProducts = [];
	$scope.searchProductByName="";
	$scope.searchProductByType="";
	$scope.searchProductByMinPrice="";
	$scope.searchProductByMaxPrice="";
	$scope.searchProductByQuantity="";
	$scope.searchProductByUnit="";
	$scope.searchProductByRestaurant="";
	$scope.searchProductByDescription="";
	$scope.resetProductButton = {};
	
	$scope.restaurants = [];
	$scope.restaurantId = "";

	var products = [];
	var restaurantProductsBackup = [];
	
	$scope.selectedRestaurant = function() {
		productsSelection();
	}
	
	function productsSelection() {
		restaurantProductsBackup = [];
		$scope.restaurantProducts = [];
		for(var i=0; i<products.length; i++) {
			if(products[i] && $scope.restaurantId && products[i].restaurantId==$scope.restaurantId) {
				$scope.restaurantProducts[i] = angular.copy(products[i]);
				restaurantProductsBackup[i] = angular.copy(products[i]);
			}
		}
		$scope.searchProduct();

	}
	
	restaurantService.getAll().then(
			function mySucc(response){
				for(var i=0; i<response.data.length; i++) 
					$scope.restaurants[response.data[i].id] = response.data[i];
			}, function error(response) {
				console.log("Getting all the restaurants went wrong...")
			}
		);
	
	productService.getAll().then(function mySucc(response){
		for(var i=0; i<response.data.length; i++) {
			products[response.data[i].id] = response.data[i];
			productsSelection();
		}
	});
	
	$scope.onProductChange = function(index, product) {
		if(restaurantProductsBackup[product.id].name!==product.name ||
				restaurantProductsBackup[product.id].type!==product.type ||
				restaurantProductsBackup[product.id].price!=product.price ||
				restaurantProductsBackup[product.id].quantity!=product.quantity ||
				restaurantProductsBackup[product.id].unit!==product.unit ||
				restaurantProductsBackup[product.id].description!==product.description) $scope.resetProductButton[index]=true;
		else $scope.resetProductButton[index]=false;
	}
	
	$scope.resetProduct = function(index, id) {
		$scope.restaurantProducts[index] = angular.copy(restaurantProductsBackup[id]);
		$scope.resetProductButton[index] = false;
	}
	
	$scope.updateProduct = function(index, product) {
		productService.update(product).then(function mySucc(response){
			$scope.resetProductButton[index] = false;
			products[product.id] = product;
			productsSelection();
	  }, function mySucc(response) {
	  	console.log("Something went wrongly!");
	  });
	}
	
	$scope.searchProduct = function() {
		$scope.restaurantProducts = [];
		$scope.resetProductButton = {};
		var minPrice = $scope.searchProductByMinPrice !== "" ? angular.copy($scope.searchProductByMinPrice) : Number.MIN_SAFE_INTEGER;
		var maxPrice = $scope.searchProductByMaxPrice !== "" ? angular.copy($scope.searchProductByMaxPrice) : Number.MAX_SAFE_INTEGER;
		var description, quantity;
		
		var j=0;
		for(var i=0; i<restaurantProductsBackup.length; i++) {
			if(restaurantProductsBackup[i]) {
				description = restaurantProductsBackup[i].description != null ? restaurantProductsBackup[i].description : "";
				quantity = $scope.searchProductByQuantity !== "" ? angular.copy($scope.searchProductByQuantity) : restaurantProductsBackup[i].quantity;
				if(restaurantProductsBackup[i].name.toLowerCase().includes($scope.searchProductByName.toLowerCase()) &&
						restaurantProductsBackup[i].type.toLowerCase().includes($scope.searchProductByType.toLowerCase()) &&
						restaurantProductsBackup[i].price >= minPrice &&		
						restaurantProductsBackup[i].price <= maxPrice &&
						restaurantProductsBackup[i].quantity == quantity &&
						restaurantProductsBackup[i].unit.toLowerCase().includes($scope.searchProductByUnit.toLowerCase()) &&
						description.toLowerCase().includes($scope.searchProductByDescription.toLowerCase())) {
					$scope.restaurantProducts[restaurantProductsBackup[i].id] = angular.copy(restaurantProductsBackup[i]);
					$scope.resetProductButton[j]=false; j++;
				}	
			}
		}	
	}
	
	$scope.clearProductFilter = function() {
		$scope.searchProductByName="";
		$scope.searchProductByType="";
		$scope.searchProductByMinPrice="";
		$scope.searchProductByMaxPrice="";
		$scope.searchProductByQuantity="";
		$scope.searchProductByUnit="";
		$scope.searchProductByDescription="";
		$scope.searchProduct();
	}
	
	function clearNewProductFields() {
		$scope.newProduct = {};
		$scope.newProductButton = false;
		$scope.searchProduct();
	}
	
	$scope.onNewProductChange = function() {	
		if($scope.newProduct.name && $scope.newProduct.name.trim().length!=0 &&
				$scope.newProduct.type && $scope.newProduct.type.trim().length!=0 &&
				$scope.newProduct.price && $scope.newProduct.price.trim().length!=0 &&
				$scope.newProduct.quantity && $scope.newProduct.quantity.trim().length!=0 &&
				$scope.newProduct.unit && $scope.newProduct.unit.trim().length!=0)
			$scope.newProductButton = true;
		else 
			$scope.newProductButton = false;
	}  
	
	$scope.addProduct = function(product) {
		product.restaurantId = parseInt(angular.copy($scope.restaurantId));
		productService.add(product).then(function mySucc(response){
			product.id = response.data;
			products[product.id] = product;
			clearNewProductFields();
			productsSelection();
	  }, function mySucc(response) {
	  	console.log("Something went wrongly!");
	  });
	}
	
	$scope.deleteProduct = function(id) {
		productService.remove(id).then(function () {
			products.splice(id, 1); 
			productsSelection();
		}).then(function () {
			console.log("Something went wrongly!");
		});
	}
});