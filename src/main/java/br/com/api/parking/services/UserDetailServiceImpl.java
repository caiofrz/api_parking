package br.com.api.parking.services;

import br.com.api.parking.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

  private final UserRepository repository;

  public UserDetailServiceImpl(UserRepository repository) {
    this.repository = repository;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    var user = this.repository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado!"));
    return new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            true,
            true,
            true,
            true,
            user.getAuthorities());
  }
}
