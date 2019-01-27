app.service('authenticationService', function($http) {
    this.myFunc = function (x) {
        return x.toString(16);
    },
    this.login = function(data) {
		return $http({
	        method: 'POST',
	        url: 'api/user/login',
	        data: data
		});
    },
    this.register = function(data) {
		return $http({
	        method: 'POST',
	        url: 'api/user/register',
	        data: data
		});
    }
});