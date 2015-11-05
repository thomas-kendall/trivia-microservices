package trivia.usermanagement.service;

import java.util.ArrayList;
import java.util.List;

import trivia.usermanagement.dto.RoleDTO;
import trivia.usermanagement.dto.UserDTO;
import trivia.usermanagement.model.Role;
import trivia.usermanagement.model.User;
import trivia.usermanagement.model.UserRole;
import trivia.usermanagement.repository.RoleRepository;
import trivia.usermanagement.repository.UserRepository;
import trivia.usermanagement.repository.UserRoleRepository;

public class UserManagementService {
	private RoleRepository roleRepository = new RoleRepository();
	private UserRepository userRepository = new UserRepository();
	private UserRoleRepository userRoleRepository = new UserRoleRepository();

	private List<RoleDTO> convertRolesToRoleDTOs(List<Role> roles) {
		List<RoleDTO> roleDTOs = new ArrayList<RoleDTO>();
		for (Role role : roles) {
			roleDTOs.add(convertRoleToRoleDTO(role));
		}
		return roleDTOs;
	}

	private RoleDTO convertRoleToRoleDTO(Role role) {
		return new RoleDTO(role.getId(), role.getName());
	}

	private List<String> convertUserRolesToRoleIds(List<UserRole> userRoles) {
		List<String> roleIds = new ArrayList<String>();
		for (UserRole userRole : userRoles) {
			roleIds.add(userRole.getRole().getId());
		}
		return roleIds;
	}

	private List<UserDTO> convertUsersToUserDTOs(List<User> users) {
		List<UserDTO> userDTOs = new ArrayList<UserDTO>();
		for (User user : users) {
			userDTOs.add(convertUserToUserDTO(user));
		}
		return userDTOs;
	}

	private UserDTO convertUserToUserDTO(User user) {
		List<String> roleIds = convertUserRolesToRoleIds(userRoleRepository.findAllForUser(user.getId()));
		return new UserDTO(user.getId(), user.getEmail(), roleIds);
	}

	public RoleDTO createRole(RoleDTO roleDTO) {
		Role role = new Role(roleDTO.name);
		role = roleRepository.create(role);
		return convertRoleToRoleDTO(role);
	}

	public UserDTO createUser(UserDTO userDTO) {
		User user = new User(userDTO.email);
		user = userRepository.create(user);
		updateUserRoles(user, userDTO.roleIds);
		userDTO = convertUserToUserDTO(user);
		return userDTO;
	}

	public void deleteRole(String id) {
		userRoleRepository.deleteForRole(id);
		roleRepository.delete(id);
	}

	public void deleteUser(String id) {
		userRoleRepository.deleteForUser(id);
		userRepository.delete(id);
	}

	public List<RoleDTO> findAllRoles() {
		List<Role> roles = roleRepository.findAll();
		List<RoleDTO> roleDTOs = convertRolesToRoleDTOs(roles);
		return roleDTOs;
	}

	public List<RoleDTO> findAllRolesForUser(String id) {
		List<Role> roles = userRoleRepository.findAllRolesForUser(id);
		List<RoleDTO> roleDTOs = convertRolesToRoleDTOs(roles);
		return roleDTOs;
	}

	public List<UserDTO> findAllUsers() {
		List<User> users = userRepository.findAll();
		List<UserDTO> userDTOs = convertUsersToUserDTOs(users);
		return userDTOs;
	}

	public RoleDTO findRoleById(String id) {
		return convertRoleToRoleDTO(roleRepository.findById(id));
	}

	public UserDTO findUserById(String id) {
		return convertUserToUserDTO(userRepository.findById(id));
	}

	public RoleDTO updateRole(String id, RoleDTO roleDTO) {
		Role role = roleRepository.findById(id);
		// TODO: make a converter for RoleDTO to Role
		role.setName(roleDTO.name);
		return convertRoleToRoleDTO(roleRepository.update(id, role));
	}

	public UserDTO updateUser(String id, UserDTO userDTO) {
		User user = userRepository.findById(id);
		// TODO: make a converter for UserDTO to User
		user.setEmail(userDTO.email);
		user = userRepository.update(id, user);
		updateUserRoles(user, userDTO.roleIds);
		return convertUserToUserDTO(user);
	}

	private void updateUserRoles(User user, List<String> roleIds) {
		List<UserRole> currentUserRoles = userRoleRepository.findAllForUser(user.getId());
		List<String> currentRoleIds = convertUserRolesToRoleIds(currentUserRoles);

		// Find all UserRoles to delete (in userRoles but not in roleIds)
		List<String> userRoleIdsToDelete = new ArrayList<String>();
		for (UserRole userRole : currentUserRoles) {
			if (!roleIds.contains(userRole.getRole().getId())) {
				userRoleIdsToDelete.add(userRole.getId());
			}
		}

		// Find all Roles to create (in roleIds but not in userRoles)
		List<String> roleIdsToCreate = new ArrayList<String>();
		for (String roleId : roleIds) {
			if (!currentRoleIds.contains(roleId)) {
				roleIdsToCreate.add(roleId);
			}
		}

		// Delete UserRoles
		for (String userRoleId : userRoleIdsToDelete) {
			userRoleRepository.delete(userRoleId);
		}

		// Create UserRoles
		for (String roleId : roleIdsToCreate) {
			Role role = roleRepository.findById(roleId);
			if (role != null) {
				UserRole userRole = new UserRole(user, role);
				userRole = userRoleRepository.create(userRole);
			}
		}
	}

}
