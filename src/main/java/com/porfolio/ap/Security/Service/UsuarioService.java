package com.porfolio.ap.Security.Service;

import com.porfolio.ap.Security.Entity.Usuario;
import com.porfolio.ap.Security.Repository.UsuarioRepository;
import javax.transaction.Transactional;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Optional<Usuario> getByUserName(String userName){
        return usuarioRepository.findByUserName(userName);

    }
    public boolean existsByUserName(String userName){

        return usuarioRepository.existsByUserName(userName);
    }
    public boolean existsByEmail(String email){

        return usuarioRepository.existsByEmail(email);
    }
    public void save(Usuario usuario){
        usuarioRepository.save(usuario);
    }
}
