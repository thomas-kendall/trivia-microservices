(function() {
	var triviaApp = angular.module("triviaApp", [ "ngRoute", "ui.bootstrap" ]);

	triviaApp.config(function($routeProvider) {
		$routeProvider
			.when("/users", {
				templateUrl : "/app/user/users.html",
				controller : "UserController"
			})
			.when("/roles", {
				templateUrl : "/app/role/roles.html",
				controller : "RoleController"
			})
			.otherwise({
				redirectTo : "/users"
			});
	});

}());