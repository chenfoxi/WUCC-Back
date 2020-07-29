package org.wucc.backservice.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wucc.backservice.model.pojo.LocalAuth;
import org.wucc.backservice.model.pojo.User;
import org.wucc.backservice.repository.LocalAuthRepository;
import org.wucc.backservice.repository.UserRepository;

/**
 * Created by foxi.chen on 29/07/20.
 *
 * @author foxi.chen
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    final UserRepository userRepository;

    final LocalAuthRepository localAuthRepository;

    public UserDetailsServiceImpl(UserRepository userRepository, LocalAuthRepository localAuthRepository) {
        this.userRepository = userRepository;
        this.localAuthRepository = localAuthRepository;
    }

    // override using email as input
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("user not find"));
        LocalAuth localAuth = localAuthRepository.findByUser(user).orElseThrow(() -> new UsernameNotFoundException("user not find"));
        return UserDetailsImpl.build(user, localAuth);
    }
}
