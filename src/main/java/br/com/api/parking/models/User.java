package br.com.api.parking.models;

import br.com.api.parking.enums.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Schema(description = "User Model")
@Entity
@Table(name = "tb_users")
@Data
public class User implements UserDetails {

  @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "User Id", type = "UUID")
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Schema(description = "Nome do usuário", example = "Caio Ferraz")
  @Column(nullable = false)
  private String name;
  @Schema(description = "Email do usuário", example = "caio@teste.com")
  @Column(nullable = false, unique = true)
  private String email;
  @Schema(description = "Senha do usuário", example = "12345678")
  private String password;
  @Schema(description = "Nível de acesso do usuário", example = "ADMIN ou USER")
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private UserRole role;

  @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Data de registro do usuário")
  @Column(nullable = false)
  @CreationTimestamp
  private Timestamp createdAt;


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    if (this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),
            new SimpleGrantedAuthority("ROLE_USER"));
    return List.of(new SimpleGrantedAuthority("ROLE_USER"));
  }

  @Override
  public String getUsername() {
    return this.email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
