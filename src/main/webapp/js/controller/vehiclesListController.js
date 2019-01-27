app.controller("vehiclesListCtrl", function($scope, $http, vehicleService, $window) {
	
	$scope.vehicles = [];
	$scope.searchVehicleByBrand="";
	$scope.searchVehicleByModel="";
	$scope.searchVehicleByType="";
	$scope.searchVehicleByRegistration="";
	$scope.searchVehicleByYear="";
	$scope.searchVehicleByAvailable="";
	$scope.searchVehicleByNote="";
	$scope.resetVehicleButton = {};
	
	var vehiclesBackup;
	
	$http({
	  method: 'GET',
	  url: 'api/vehicle/all',
	}).then(function mySucc(response){
		for(var i=0; i<response.data.length; i++) {
			$scope.vehicles[response.data[i].id] = response.data[i];
			console.log(response.data[i].available);
			$scope.vehicles[response.data[i].id].available = response.data[i].available.toString();
			console.log(response.data[i].available);
		}
	  vehiclesBackup = angular.copy($scope.vehicles);
	});
	
	$scope.onVehicleChange = function(index, vehicle) {
		if(vehiclesBackup[vehicle.id].brand!==vehicle.brand ||
				vehiclesBackup[vehicle.id].model!==vehicle.model ||
				vehiclesBackup[vehicle.id].type!==vehicle.type ||
				vehiclesBackup[vehicle.id].regNumber!==vehicle.regNumber ||
				vehiclesBackup[vehicle.id].productionYear!==vehicle.productionYear ||
				vehiclesBackup[vehicle.id].available!==vehicle.available ||
				vehiclesBackup[vehicle.id].note!==vehicle.note) {
			$scope.resetVehicleButton[index]=true;
		} else {
			$scope.resetVehicleButton[index]=false;
		}
	}
	
	$scope.resetVehicle = function(index, id) {
		$scope.vehicles[index] = angular.copy(vehiclesBackup[id]);
		$scope.resetVehicleButton[index] = false;
	}
	
	$scope.updateVehicle = function(index, vehicle) {
		vehicle.available = Boolean(vehicle.available=="true");
		vehicleService.update(vehicle).then(function mySucc(response){
			$scope.resetVehicleButton[index] = false;
			vehiclesBackup[vehicle.id] = vehicle;
			clearNewVehicleFields();
	  }, function mySucc(response) {
	  	console.log("Something went wrongly!");
	  });
	}
	
	$scope.searchVehicle = function() {
		$scope.vehicles = [];
		$scope.resetVehicleButton = {};
		var j=0;
		for(var i=0; i<vehiclesBackup.length; i++) {
			if(vehiclesBackup[i] && vehiclesBackup[i].brand.toLowerCase().includes($scope.searchVehicleByBrand.toLowerCase()) &&
					vehiclesBackup[i].model.toLowerCase().includes($scope.searchVehicleByModel.toLowerCase()) &&
					vehiclesBackup[i].type.toLowerCase().includes($scope.searchVehicleByType.toLowerCase()) &&
					vehiclesBackup[i].regNumber.toLowerCase().includes($scope.searchVehicleByRegistration.toLowerCase()) &&
					vehiclesBackup[i].productionYear.toString().includes($scope.searchVehicleByYear.toLowerCase()) &&
					vehiclesBackup[i].available.toString().includes($scope.searchVehicleByAvailable.toLowerCase()) &&
					vehiclesBackup[i].note.toLowerCase().includes($scope.searchVehicleByNote.toLowerCase())) {
				$scope.vehicles[vehiclesBackup[i].id] = angular.copy(vehiclesBackup[i]);
				$scope.resetVehicleButton[j]=false; j++;
			}	
		}	
	}
	
	$scope.clearVehicleFilter = function() {
		$scope.searchVehicleByBrand="";
		$scope.searchVehicleByModel="";
		$scope.searchVehicleByType="";
		$scope.searchVehicleByRegistration="";
		$scope.searchVehicleByYear="";
		$scope.searchVehicleByAvailable="";
		$scope.searchVehicleByNote="";
		$scope.searchVehicle();
	}
	
	function clearNewVehicleFields() {
		$scope.newVehicle= {};
		$scope.newVehicleButton = false;
		$scope.searchVehicle();
	}
	
	$scope.onNewVehicleChange = function() {
		if($scope.newVehicle.brand && $scope.newVehicle.brand.trim().length!=0 &&
				$scope.newVehicle.model && $scope.newVehicle.model.trim().length!=0 &&
				$scope.newVehicle.type && $scope.newVehicle.type.trim().length!=0 &&
				$scope.newVehicle.regNumber && $scope.newVehicle.regNumber.trim().length!=0 &&
				$scope.newVehicle.productionYear &&
				$scope.newVehicle.available && $scope.newVehicle.available.trim().length!=0)
			$scope.newVehicleButton = true;
		else 
			$scope.newVehicleButton = false;
	}  
	
	$scope.addVehicle = function(vehicle) {
		vehicle.available = Boolean(vehicle.available=="true");
		vehicleService.add(vehicle).then(function mySucc(response){
			vehicle.id = response.data;
			vehiclesBackup[vehicle.id] = vehicle;
			clearNewVehicleFields();
	  }, function mySucc(response) {
	  	console.log("Something went wrongly!");
	  });
	}
	
	$scope.deleteVehicle = function(id) {
		$http({
	      method: 'DELETE',
	      url: 'api/vehicle/' + id
		}).then(function () {
			vehiclesBackup.splice(id, 1); 
			$scope.clearVehicleFilter();
		}).then(function () {
			console.log("Something went wrongly!");
		});
	}
});