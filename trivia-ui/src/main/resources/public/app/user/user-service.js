(function(){
	var UserService = function($http){
		
		var getUsers = function(){
			return $http.get("/api/users", {headers: {"Content-Type": "application/json"} })
						.then(function(response){
							return response.data;
						});
		};
		
		var createUser = function(user){
			return $http.post("/api/users/", user, {headers: {"Content-Type": "application/json"} })
						.then(function(response){
							return response.data;
						});
		};
		
		var updateUser = function(user){
			return $http.put("/api/users/" + user.id, user, {headers: {"Content-Type": "application/json"} })
						.then(function(response){
							// There is no data for this
							console.log("The user has been saved.");
						});
		};
		
		var saveUser = function(user){
			if(user.id){
				updateUser(user);
			} else {
				createUser(user);
			}
		};
		
		var deleteUser = function(user){
			return $http.delete("/api/users/" + user.id, {headers: {"Content-Type": "application/json"} })
			.then(function(response){
				// There is no data for this
			});			
		};
		
		return{
			getUsers : getUsers,
			saveUser : saveUser,
			deleteUser : deleteUser
		};
	};
	
	angular.module("triviaApp").factory("userService", UserService);
}());