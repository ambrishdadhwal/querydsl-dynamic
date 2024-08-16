package com.ammycodes.querydsldynamicquery.serviceimpl;

import com.ammycodes.querydsldynamicquery.domain.UsersDTO;
import com.ammycodes.querydsldynamicquery.entity.Users;
import com.ammycodes.querydsldynamicquery.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    @Autowired
    UsersRepository usersRepository;

    public UsersDTO addUser(UsersDTO user)
    {
        Users newUser = Users.builder()
                .fullName(user.getFullName())
                .userName(user.getUserName())
                .email(user.getEmail())
                .isActive(true)
                .build();
        newUser = usersRepository.save(newUser);
        return user;
    }
}
