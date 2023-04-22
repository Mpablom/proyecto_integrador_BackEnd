package com.porfolio.ap.Security.Controller;

import com.porfolio.ap.Security.Entity.Rol;
import com.porfolio.ap.Security.Entity.Usuario;
import com.porfolio.ap.Security.Enums.RolName;
import com.porfolio.ap.Security.Service.RolService;
import com.porfolio.ap.Security.Service.UsuarioService;
import com.porfolio.ap.Security.dto.JwtDto;
import com.porfolio.ap.Security.dto.LoginUsuario;
import com.porfolio.ap.Security.dto.NuevoUsuario;
import com.porfolio.ap.Security.jwt.JwtProvider;
import java.util.HashSet;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    RolService rolService;
    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Campos mal puestos o email invalido"),HttpStatus.BAD_REQUEST);

        if(usuarioService.existsByUserName(nuevoUsuario.getUserName()))
            return new ResponseEntity (new Mensaje("Ese nombre ya existe"),HttpStatus.BAD_REQUEST);

        if(usuarioService.existsByEmail(nuevoUsuario.getEmail()))
            return new ResponseEntity (new Mensaje("Ese email ya existe"),HttpStatus.BAD_REQUEST);

        Usuario usuario = new Usuario(nuevoUsuario.getName(), nuevoUsuario.getUserName(), nuevoUsuario.getEmail(), passwordEncoder.encode(nuevoUsuario.getPassword()));

        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolName(RolName.ROLE_USER).get());

        if(nuevoUsuario.getRoles().contains("admin")) roles.add(rolService.getByRolName(RolName.ROLE_ADMIN).get());
        usuario.setRoles(roles);
        usuarioService.save(usuario);

        return new ResponseEntity( new Mensaje ("Usuario guardado"),HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("campos mal puestos"), HttpStatus.BAD_REQUEST);
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getUserName(), loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }
}