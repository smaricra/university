var app = angular.module("foodWebApp", ["ngRoute"]); 
app.controller("myCtrl", function($scope) {
    $scope.products = ["Milk", "Bread", "Cheese"];
});
app.controller("profileCtrl", function($scope, $http) {
	$scope.profile = "Sava Maric";
	
	$http({
        method: 'GET',
        url: 'api/user/1',
	}).then(function mySucc(response){
        $scope.profile = response.data.email;
    });
});

app.config(function($routeProvider, $locationProvider) {
    $routeProvider
    .when("/", {
        templateUrl : "html/admin.html",
        controller: "adminCtrl",
        resolve:{
            loggedIn:onlyLoggedIn
        }
    })
    .when("/profile", {
        templateUrl : "html/profile.html",
        controller: "profileCtrl"
    })
    .when("/customer", {
        templateUrl : "html/customer.html",
        controller: "customerCtrl"
    })
    .when("/dashboard", {
    	templateUrl: "html/dashboard.html",
    	controller: "dashboardCtrl"
    })
    .when("/admin", {
        templateUrl : "html/admin.html",
        controller: "adminCtrl",
        resolve:{
            loggedIn:onlyLoggedIn
        }
    })
    .when("/login", {
    	templateUrl : "html/login.html",
    	controller: "authenticationCtrl",
        resolve:{
            loggedIn:notLoggedIn
        }
    });
});

var notLoggedIn = function ($location, $q) {
	var deferred = $q.defer();
	if(!localStorage.getItem('username')) {
		deferred.resolve();
	} else {
		deferred.reject();
	    $location.url('/admin');
	}
   
    return deferred.promise;
}

var onlyLoggedIn = function ($location,$q) {
	var deferred = $q.defer();
	if(localStorage.getItem('username')) {
		deferred.resolve();
	} else {
		deferred.reject();
	    $location.url('/login');
	}
   
    return deferred.promise;
};
