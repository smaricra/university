app.controller("customerCtrl", function($scope, restaurantService, productService, orderService) {
	$scope.orderJson = [];
	$scope.orderJson.order = {};
	$scope.orderJson.order.customerId = localStorage.getItem("userId");
	$scope.orderJson.productOrders = [];
	$scope.orderJson.productOrders = angular.copy(JSON.parse(localStorage.getItem("productOrders")));
	
	

	var json = {'order': $scope.orderJson.order, 'orderProduct': $scope.orderJson.productOrders};
	orderService.add(json).then(function mySucc(response){
		console.log("Bravo");
    }, function mySucc(response) {
    	console.log("Drugi put");
    });
	
});