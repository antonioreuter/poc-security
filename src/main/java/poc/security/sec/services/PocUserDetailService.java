package poc.security.sec.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import poc.security.sec.PocUserPrincipal;
import poc.security.sec.models.User;
import poc.security.sec.repositories.UserRepository;

/**
 * Created by 310280812 on 3/28/2017.
 */
@Service
public class PocUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new PocUserPrincipal(user);
    }
}