app.service('vehicleService', function($http) {
    this.getAll = function() {
		return $http({
	        method: 'GET',
	        url: 'api/vehicle/all'
		});
    },
    this.add = function(vehicle) {
    	return $http({
	        method: 'POST',
	        url: 'api/vehicle/',
	        data: vehicle
    	});
    },
    this.update = function(vehicle) {
    	return $http({
	        method: 'PUT',
	        url: 'api/vehicle/',
	        data: vehicle
		});
    },
    this.remove = function(id) {
    	return $http({
	        method: 'DELETE',
	        url: 'api/vehicle/' + id
		})
    }
});