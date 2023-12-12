package br.com.api.parking.services;

import br.com.api.parking.dtos.UserDTO;
import br.com.api.parking.enums.UserRole;
import br.com.api.parking.exceptions.UserAlreadyCreatedException;
import br.com.api.parking.models.User;
import br.com.api.parking.repositories.UserRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

  private final UserRepository repository;

  public UserService(UserRepository repository) {
    this.repository = repository;
  }

  @Transactional
  public User save(UserDTO userDTO) {
    //TODO utilizar a lib MapStruct
    User user = new User();
    BeanUtils.copyProperties(userDTO, user);
    this.validateEmail(user.getEmail());
    this.encodeAndSetPassword(user);
    user.setRole(UserRole.valueOf(userDTO.getRole()));
    return this.repository.save(user);
  }

  public Page<User> findAll(Pageable pageable) {
    return this.repository.findAll(pageable);
  }

  public User findById(UUID id) {
    return this.find(id);
  }

  @Transactional
  public User update(UserDTO userDTO, UUID id) {
    User user = new User();
    BeanUtils.copyProperties(userDTO, user);
    this.find(id);
    user.setId(id);
    this.encodeAndSetPassword(user);
    user.setRole(UserRole.valueOf(userDTO.getRole()));
    return this.repository.save(user);
  }

  @Transactional
  public void delete(UUID id) {
    this.find(id);
    this.repository.deleteById(id);
  }

  private void validateEmail(String email) {
    if (this.repository.findByEmail(email).isPresent())
      throw new UserAlreadyCreatedException("Este email já pertence a um usuário!");
  }

  private void encodeAndSetPassword(User user) {
    if (StringUtils.isBlank(user.getPassword())) {
      throw new IllegalArgumentException("A senha não pode ser vazia!");
    }
    user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
  }

  private User find(UUID id) {
    return this.repository.findById(id)
            .orElseThrow(
                    () -> new UsernameNotFoundException("Usuário não encontrado!")
            );
  }
}
