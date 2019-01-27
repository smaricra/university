app.service('restaurantService', function($http) {
    this.getAll = function() {
		return $http({
	        method: 'GET',
	        url: 'api/restaurant/all'
		});
    },
    this.add = function(restaurant) {
    	return $http({
	        method: 'POST',
	        url: 'api/restaurant/',
	        data: restaurant
    	});
    },
    this.update = function(restaurant) {
    	return $http({
	        method: 'PUT',
	        url: 'api/restaurant/',
	        data: restaurant
		});
    },
    this.remove = function(id) {
    	return $http({
	        method: 'DELETE',
	        url: 'api/restaurant/' + id
		})
    }
});
