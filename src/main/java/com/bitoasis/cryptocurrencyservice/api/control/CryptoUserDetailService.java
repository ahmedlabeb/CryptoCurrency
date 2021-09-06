package com.bitoasis.cryptocurrencyservice.api.control;

import com.bitoasis.cryptocurrencyservice.api.entity.User;
import com.bitoasis.cryptocurrencyservice.api.control.configuration.MyUserDetails;
import com.bitoasis.cryptocurrencyservice.api.entity.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CryptoUserDetailService implements UserDetailsService {

    private UserRepository userRepository;

    public CryptoUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByName(username);
        if (user.isEmpty())
            throw new UsernameNotFoundException("No user found with username: " + username);
        return user.map(MyUserDetails::new).get();
    }
}
