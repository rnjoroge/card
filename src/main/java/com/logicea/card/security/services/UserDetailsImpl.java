package com.logicea.card.security.services;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.logicea.card.entity.User;



public class UserDetailsImpl implements UserDetails {
  private static final long serialVersionUID = 1L;

  private int id;
  private String username;
  private String email;
  private String role;
  @JsonIgnore
  private String password;
  private Collection<? extends GrantedAuthority> authorities;

  public UserDetailsImpl(int id, String username, String email, String password ,String role) {
    this.id = id;
    this.username =username;
    this.email = email;
    this.password = password;
    this.role=role;
    this.authorities = this.userRoleAsCollection(role);
  }

  public static UserDetailsImpl build(User user) {
    /*List<GrantedAuthority> authorities = user.getRoles().stream()
        .map(role -> new SimpleGrantedAuthority(role.getName().name()))
        .collect(Collectors.toList());
   */

    return new UserDetailsImpl(
        user.getUserId(), 
        user.getUsername(), 
        user.getEmail(),
        user.getPassword(),
        user.getUserRole()
        );
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  public int getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  public String getRole() {
    return role;
  }

  public String getUserRole(){
    return role;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
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

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    UserDetailsImpl user = (UserDetailsImpl) o;
    return Objects.equals(id, user.id);
  }

  private Collection<GrantedAuthority> userRoleAsCollection(String Role) {
    List<GrantedAuthority> authorities = new ArrayList<>();
    SimpleGrantedAuthority authority = new SimpleGrantedAuthority(Role);
    authorities.add(authority);
    return authorities;
  }


}  