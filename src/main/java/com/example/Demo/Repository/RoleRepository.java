package com.example.Demo.Repository;
import com.example.Demo.Model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role , Long>  {
     List<Role> findAllByAvailabilityIsTrue();
     Role getByUsertype(String uesrtype);
}
