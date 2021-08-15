package io.demo.repository;

import io.demo.model.FieldOfActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldOfActivityRepository extends JpaRepository<FieldOfActivity, Long>, JpaSpecificationExecutor<FieldOfActivity> {

}
