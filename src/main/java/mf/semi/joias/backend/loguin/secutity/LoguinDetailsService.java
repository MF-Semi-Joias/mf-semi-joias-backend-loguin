package mf.semi.joias.backend.loguin.secutity;

        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.security.core.userdetails.*;
        import org.springframework.stereotype.Service;

        @Service
        public class LoguinDetailsService implements UserDetailsService {

            private static final Logger logger = LoggerFactory.getLogger(LoguinDetailsService.class);

            private final ClienteFeignClient clienteFeignClient;

            public LoguinDetailsService(ClienteFeignClient clienteFeignClient) {
                this.clienteFeignClient = clienteFeignClient;
            }

            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                logger.info("Buscando usuário: {}", username);
                ClienteDTO cliente = clienteFeignClient.buscarPorUsuario(username);
                if (cliente == null) {
                    logger.warn("Usuário não encontrado: {}", username);
                    throw new UsernameNotFoundException("Usuário não encontrado");
                }
                logger.info("Usuário encontrado: {}", username);
                return User.builder()
                        .username(cliente.getUsuario())
                        .password(cliente.getSenha())
                        .roles("USER")
                        .build();
            }
        }