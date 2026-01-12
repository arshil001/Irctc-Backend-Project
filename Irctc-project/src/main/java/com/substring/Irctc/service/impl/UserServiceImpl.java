package com.substring.Irctc.service.impl;

import com.substring.Irctc.dto.UserDto;
import com.substring.Irctc.entity.Role;
import com.substring.Irctc.entity.User;
import com.substring.Irctc.exception.ResourceNotFoundException;
import com.substring.Irctc.repository.RoleRepo;
import com.substring.Irctc.repository.UserRepo;
import com.substring.Irctc.service.UserService;
import org.modelmapper.ModelMapper;

public class UserServiceImpl implements UserService {

    private UserRepo userRepo;
    private RoleRepo roleRepo;
    private ModelMapper modelMapper;

    public UserServiceImpl(UserRepo userRepo, RoleRepo roleRepo, ModelMapper modelMapper) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto registerUser(UserDto userDto) {

        User user = modelMapper.map(userDto, User.class);

        Role roleNormal = roleRepo.findByName("ROLE_NORMAL").orElseThrow(() -> new ResourceNotFoundException("server is not configured properly contact support"));
        user.getRoles().add(roleNormal);
        User save = userRepo.save(user);

        return modelMapper.map(save,UserDto.class);
    }
}
