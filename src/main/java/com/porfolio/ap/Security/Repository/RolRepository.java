package com.porfolio.ap.Security.Repository;

import com.porfolio.ap.Security.Entity.Rol;
import com.porfolio.ap.Security.Enums.RolName;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

    Optional<Rol> findByRolName(RolName rolName);

}