package ro.scoalainformala.hello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ro.scoalainformala.hello.model.User;
import ro.scoalainformala.hello.persistence.UserRepository;
import ro.scoalainformala.hello.service.dto.UserDTO;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder pwdEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException(username);
//        System.out.println("Hashed password is:" + pwdEncoder.encode("1234"));
//
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("USER"));
        grantedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }

    public void addUserToDatabase(UserDTO userDTO) {
        if (userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            userDTO.setPassword(pwdEncoder.encode(userDTO.getPassword()));
            if (userRepository.findByUsername(userDTO.getUsername()) == null) {
                User user = buildUser(userDTO);
                this.userRepository.save(user);
            } else {
                throw new IllegalArgumentException("User already exists!!!");
            }
        } else {
            throw new IllegalArgumentException("Mismatch password!!!");
        }
    }
    public User buildUser(UserDTO userDTO) {
        return new User(userDTO.getId(), userDTO.getName(), userDTO.getUsername(), userDTO.getPassword(),
                userDTO.getFirstName(),userDTO.getLastName(),userDTO.getCity(),userDTO.getAdress(),userDTO.getPhoneNumber());
    }
    public void editUserInfo(UserDTO userDTO, String username) {
        User user = userRepository.findByUsername(username);

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setCity(userDTO.getCity());
        user.setAdress(userDTO.getAdress());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        this.userRepository.save(user);
    }

    public UserDTO getUser(String username) {
        User user = userRepository.findByUsername(username);
        return from(user);
    }

    public static UserDTO from(final User user) {
        return new UserDTO(user.getId(), user.getUsername(),user.getFirstName(),user.getLastName(),user.getCity(),user.getAdress(),user.getPhoneNumber());
    }
}
