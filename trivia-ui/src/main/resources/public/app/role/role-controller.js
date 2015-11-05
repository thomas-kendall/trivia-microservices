(function() {
	var RoleController = function($scope, $uibModal, roleService) {

		// private functions
		var onGetRolesComplete = function(data){
			$scope.roles = data;
		};
		
		var showRoleEditModal = function(){
			var modalInstance = $uibModal.open({
				templateUrl: "roleEditModal.html",
				controller: "RoleEditModalInstanceController",
				resolve: {
					selectedRole: function(){
						return angular.copy($scope.selectedRole);
					}
				}
			});
			
			modalInstance.result.then(function(selectedRole){
				// Role submitted successfully
				refreshRoles();
			}, function(){
				// Role cancelled out				
			});
		};

		var refreshRoles = function(){
			roleService.getRoles().then(onGetRolesComplete);
		}
		
		$scope.roles = [];
		$scope.selectedRole = {};		
		
		$scope.editRole = function(role){
			$scope.selectedRole = role;
			showRoleEditModal();
		};
		
		$scope.editRoleSubmit = function(){
			alert($scope.selectedRole.name);
		};
		
		$scope.editNewRole = function(){
			var role = {};
			$scope.editRole(role);
		};
		
		$scope.deleteRole = function(role){
			roleService.deleteRole(role);
			refreshRoles();
		};
		
		// Initialize
		refreshRoles();
	};
	
	angular.module("triviaApp").controller("RoleController", RoleController); 
	                                                          
}());


(function() {
	var RoleEditModalInstanceController = function($scope, $uibModalInstance, roleService, selectedRole) {

		$scope.selectedRole = selectedRole;
		
		$scope.editRoleSubmit = function(){
			roleService.saveRole($scope.selectedRole);			
			$uibModalInstance.close($scope.selectedRole);
		};
		
		$scope.cancel = function(){
			$uibModalInstance.dismiss("cancel");
		}
	};
	
	angular.module("triviaApp").controller("RoleEditModalInstanceController", RoleEditModalInstanceController); 
	                                                          
}());