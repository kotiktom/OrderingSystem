package com.example.OrderProject.domain;

import java.util.List;


import org.springframework.data.repository.CrudRepository;

public interface LoginRepository extends CrudRepository<Login, Long> {
	Login findByUsername(String username);

	List<Login> findByRole(String string);
}
