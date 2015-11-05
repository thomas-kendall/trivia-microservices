(function(){
	var RoleService = function($http){
		
		var getRoles = function(){
			return $http.get("/api/roles", {headers: {'Content-Type': 'application/json'} })
						.then(function(response){
							return response.data;
						});
		};
		
		var createRole = function(role){
			return $http.post("/api/roles/", role, {headers: {'Content-Type': 'application/json'} })
						.then(function(response){
							return response.data;
						});
		};
		
		var updateRole = function(role){
			return $http.put("/api/roles/" + role.id, role, {headers: {'Content-Type': 'application/json'} })
						.then(function(response){
							// There is no data
						});
		};
		
		var saveRole = function(role){
			if(role.id){
				updateRole(role);
			} else {
				createRole(role);
			}
		};
		
		var deleteRole = function(role){
			return $http.delete("/api/roles/" + role.id, {headers: {'Content-Type': 'application/json'} })
			.then(function(response){
				// There is no data
			});			
		};
		
		return{
			getRoles : getRoles,
			saveRole : saveRole,
			deleteRole : deleteRole
		};
	};
	
	angular.module("triviaApp").factory("roleService", RoleService);
}());