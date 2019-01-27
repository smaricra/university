app.service('userService', function($http) {
    this.getAll = function() {
		return $http({
	        method: 'GET',
	        url: 'api/user/all'
		});
    },
    this.update = function(user) {
    	return $http({
	        method: 'PUT',
	        url: 'api/user/',
	        data: user
		})
    }
});
