package com.gym.workout.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gym.workout.model.UserGymWorkout;
import com.gym.workout.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserGymWorkout userGymWorkout = repository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Login não encontrado."));

        return User
                .builder()
                .username(userGymWorkout.getUsername()+" "+userGymWorkout.getId_user())
                .password(userGymWorkout.getPassword())
                .roles(userGymWorkout.getTypeUser().toString())
                .build();
    }
}
