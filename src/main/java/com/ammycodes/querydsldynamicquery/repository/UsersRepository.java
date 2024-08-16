package com.ammycodes.querydsldynamicquery.repository;

import com.ammycodes.querydsldynamicquery.entity.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<Users, Integer> {
}
