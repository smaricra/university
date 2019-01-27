app.service('productService', function($http) {
    this.getAll = function() {
		return $http({
	        method: 'GET',
	        url: 'api/product/all'
		});
    },
    this.getTop10 = function() {
		return $http({
	        method: 'GET',
	        url: 'api/product/bestTen'
		});
    },
    this.add = function(product) {
    	return $http({
	        method: 'POST',
	        url: 'api/product/',
	        data: product
    	});
    },
    this.update = function(product) {
    	return $http({
	        method: 'PUT',
	        url: 'api/product/',
	        data: product
		});
    },
    this.remove = function(id) {
    	return $http({
	        method: 'DELETE',
	        url: 'api/product/' + id
		})
    }
});