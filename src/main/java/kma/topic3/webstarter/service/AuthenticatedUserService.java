package kma.topic3.webstarter.service;

import java.util.List;
import java.util.stream.Collectors;

import kma.topic3.webstarter.database.UserRepository;
import kma.topic3.webstarter.model.entities.PermissionEntity;
import kma.topic3.webstarter.model.entities.UserEntity;
import kma.topic3.webstarter.model.security.AuthenticatedUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthenticatedUserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final UserEntity user = userRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("No user with login: " + username));

        return new AuthenticatedUser(
                username,
                user.getPassword(),
                mapAuthorities(user.getPermissions())
        );
    }

    private static List<GrantedAuthority> mapAuthorities(final List<PermissionEntity> permissions) {
        return permissions.stream()
                .map(PermissionEntity::getPermission)
                .map(Enum::name)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toUnmodifiableList());
    }
}
