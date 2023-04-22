package com.porfolio.ap.Security.Repository;

import com.porfolio.ap.Security.Entity.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario , Long> {
       Optional<Usuario> findByUserName(String userName);
       boolean existsByUserName(String userName);
       boolean existsByEmail(String email);

}
