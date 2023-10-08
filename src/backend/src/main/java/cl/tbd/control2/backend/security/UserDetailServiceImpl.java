package cl.tbd.control2.backend.security;

import cl.tbd.control2.backend.entities.UsuarioEntity;
import cl.tbd.control2.backend.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        UsuarioEntity usuario = usuarioRepository.findOneByUsername(username);
        if(usuario == null){
            throw new UsernameNotFoundException("El usuario con username " + username +" no existe");
        }
        return new UserDetailsImpl(usuario);
    }

}
