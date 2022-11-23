package com.accountingservices.lisa.repository;

import com.accountingservices.lisa.models.UserRequest;
import org.springframework.data.repository.CrudRepository;

public interface UserRequestRepository extends CrudRepository<UserRequest, Long> {
}
