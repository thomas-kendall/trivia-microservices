(function() {
	var UserController = function($scope, $uibModal, userService, roleService) {

		// private functions
		var onGetUsersComplete = function(data){
			$scope.users = data;
		};
		
		var onGetRolesComplete = function(data){
			$scope.roles = data;
		};
		
		var showUserEditModal = function(){
			var modalInstance = $uibModal.open({
				templateUrl: "userEditModal.html",
				controller: "UserEditModalInstanceController",
				resolve: {
					selectedUser: function(){
						return angular.copy($scope.selectedUser);
					},
					allRoles: function(){
						return $scope.roles;
					}
				}
			});
			
			modalInstance.result.then(function(selectedUser){
				// User submitted successfully
				refreshUsers();
			}, function(){
				// User cancelled out				
			});
		};

		var refreshUsers = function(){
			userService.getUsers().then(onGetUsersComplete);
		}
		
		var refreshRoles = function(){
			roleService.getRoles().then(onGetRolesComplete);
		}
		
		$scope.users = [];
		$scope.roles = [];
		$scope.selectedUser = {};		
		
		$scope.editUser = function(user){
			$scope.selectedUser = user;
			showUserEditModal();
		};
		
		$scope.editUserSubmit = function(){
			alert($scope.selectedUser.email);
		};
		
		$scope.editNewUser = function(){
			var user = {
				email: "",
				roleIds: []
			};
			$scope.editUser(user);
		};
		
		$scope.deleteUser = function(user){
			userService.deleteUser(user);
			refreshUsers();
		};
		
		// Initialize
		refreshUsers();
		refreshRoles();
	};
	
	angular.module("triviaApp").controller("UserController", UserController); 
	                                                          
}());


(function() {
	var UserEditModalInstanceController = function($scope, $uibModalInstance, userService, selectedUser, allRoles) {

		$scope.selectedUser = selectedUser;		
		$scope.allRoles = allRoles;		
				
		$scope.isRoleSelected = function(role){
			return $scope.selectedUser.roleIds.indexOf(role.id) > -1;
		};
		
		$scope.onRoleChecked = function($event, role){
			var checkbox = $event.target;
			if(checkbox.checked){
				$scope.selectedUser.roleIds.push(role.id);
			}else{
				var index = $scope.selectedUser.roleIds.indexOf(role.id);
				$scope.selectedUser.roleIds.splice(index, 1);
			}
		}
		
		$scope.editUserSubmit = function(){
			userService.saveUser($scope.selectedUser);			
			$uibModalInstance.close($scope.selectedUser);
		};
		
		$scope.cancel = function(){
			$uibModalInstance.dismiss("cancel");
		}
	};
	
	angular.module("triviaApp").controller("UserEditModalInstanceController", UserEditModalInstanceController); 
	                                                          
}());