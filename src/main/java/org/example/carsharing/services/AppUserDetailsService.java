package org.example.carsharing.services;

import org.example.carsharing.repositories.CustomerRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public class AppUserDetailsService implements UserDetailsService {
    private CustomerRepository customerRepository;

    public AppUserDetailsService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String number) throws UsernameNotFoundException {
        System.out.println("load num: " + number);
        return customerRepository.findByNumber(number)
                .map(u -> new User(
                        u.getNumber(),
                        u.getPassword(),
                        u.isAdmin() ? List.of(new SimpleGrantedAuthority("ROLE_ADMIN")) : List.of(new SimpleGrantedAuthority("ROLE_USER"))
                )).orElseThrow(() -> new UsernameNotFoundException(number + " was not found!"));
    }
}