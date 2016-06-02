package trivia.usermanagement.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import trivia.usermanagement.dto.RoleDTO;
import trivia.usermanagement.dto.UserDTO;
import trivia.usermanagement.model.Role;
import trivia.usermanagement.model.User;
import trivia.usermanagement.repository.RoleRepository;
import trivia.usermanagement.repository.UserRepository;

@Service
@Transactional
public class UserManagementService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	public UserDTO authenticateUser(String email, String password) {
		UserDTO userDTO = null;
		User user = userRepository.findByEmail(email);
		if (user != null) {
			if (user.getPasswordHash() == password.hashCode()) {
				userDTO = convertUserToUserDTO(user);
			}
		}
		return userDTO;
	}

	private Collection<RoleDTO> convertRolesToRoleDTOs(Collection<Role> roles) {
		List<RoleDTO> roleDTOs = new ArrayList<RoleDTO>();
		for (Role role : roles) {
			roleDTOs.add(convertRoleToRoleDTO(role));
		}
		return roleDTOs;
	}

	private Collection<Integer> convertRolesToRoleIds(Collection<Role> roles) {
		List<Integer> roleIds = new ArrayList<>();
		for (Role userRole : roles) {
			roleIds.add(userRole.getId());
		}
		return roleIds;
	}

	private RoleDTO convertRoleToRoleDTO(Role role) {
		return new RoleDTO(role.getId(), role.getName());
	}

	private List<UserDTO> convertUsersToUserDTOs(Collection<User> users) {
		List<UserDTO> userDTOs = new ArrayList<UserDTO>();
		for (User user : users) {
			userDTOs.add(convertUserToUserDTO(user));
		}
		return userDTOs;
	}

	private UserDTO convertUserToUserDTO(User user) {
		Collection<Integer> roleIds = convertRolesToRoleIds(user.getRoles());
		return new UserDTO(user.getId(), user.getEmail(), roleIds);
	}

	public RoleDTO createRole(RoleDTO roleDTO) {
		Role role = new Role(roleDTO.name);
		role = roleRepository.save(role);
		return convertRoleToRoleDTO(role);
	}

	public UserDTO createUser(UserDTO userDTO) {
		Set<Role> roles = new HashSet<>(roleRepository.findAll(userDTO.roleIds));
		User user = new User(userDTO.email, userDTO.password, roles);
		user = userRepository.save(user);
		userDTO = convertUserToUserDTO(user);
		return userDTO;
	}

	public void deleteRole(int id) {
		Role role = roleRepository.findOne(id);
		if (role != null) {
			roleRepository.delete(role);
		}
	}

	public void deleteUser(int id) {
		User user = userRepository.findOne(id);
		if (user != null) {
			userRepository.delete(user);
		}
	}

	public Collection<RoleDTO> findAllRoles() {
		List<Role> roles = roleRepository.findAll();
		Collection<RoleDTO> roleDTOs = convertRolesToRoleDTOs(roles);
		return roleDTOs;
	}

	public Collection<RoleDTO> findAllRolesForUser(int id) {
		Collection<Role> roles = new HashSet<>();
		User user = userRepository.findOne(id);
		if (user != null) {
			roles = user.getRoles();
		}
		Collection<RoleDTO> roleDTOs = convertRolesToRoleDTOs(roles);
		return roleDTOs;
	}

	public List<UserDTO> findAllUsers() {
		List<User> users = userRepository.findAll();
		List<UserDTO> userDTOs = convertUsersToUserDTOs(users);
		return userDTOs;
	}

	public RoleDTO findRoleById(int id) {
		return convertRoleToRoleDTO(roleRepository.findOne(id));
	}

	public UserDTO findUserById(int id) {
		return convertUserToUserDTO(userRepository.findOne(id));
	}

	public RoleDTO updateRole(int id, RoleDTO roleDTO) {
		Role role = roleRepository.findOne(id);
		// TODO: make a converter for RoleDTO to Role
		role.setName(roleDTO.name);
		return convertRoleToRoleDTO(roleRepository.save(role));
	}

	public UserDTO updateUser(int id, UserDTO userDTO) {
		User user = userRepository.findOne(id);
		// TODO: make a converter for UserDTO to User
		Set<Role> roles = new HashSet<>(roleRepository.findAll(userDTO.roleIds));
		user.setEmail(userDTO.email);
		// Only set the password if it was passed in
		if (userDTO.password != null) {
			user.setPassword(userDTO.password);
		}
		user.setRoles(roles);
		user = userRepository.save(user);
		return convertUserToUserDTO(user);
	}
}
