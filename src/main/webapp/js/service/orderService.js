app.service('orderService', function($http) {
    this.getAll = function() {
		return $http({
	        method: 'GET',
	        url: 'api/order/all'
		});
    },
    this.add = function(json) {
    	return $http({
	        method: 'POST',
	        url: 'api/order/',
	        data: json
    	});
    }
});
