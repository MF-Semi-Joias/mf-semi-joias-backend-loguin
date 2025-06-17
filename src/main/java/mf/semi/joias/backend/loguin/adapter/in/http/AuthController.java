package mf.semi.joias.backend.loguin.adapter.in.http;

    import mf.semi.joias.backend.loguin.secutity.JwtUtil;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    import org.springframework.security.authentication.AuthenticationManager;
    import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
    import org.springframework.security.core.Authentication;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("/auth")
    public class AuthController {

        private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

        private final AuthenticationManager authenticationManager;
        private final JwtUtil jwtUtil;

        public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
            this.authenticationManager = authenticationManager;
            this.jwtUtil = jwtUtil;
        }

        @PostMapping("/login")
        public String login(@RequestParam String username, @RequestParam String password) {
            logger.info("Tentativa de login para o usuário: {}", username);
            try {
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(username, password)
                );
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                String token = jwtUtil.generateToken(userDetails.getUsername());
                logger.info("Login bem-sucedido para o usuário: {}", username);
                return token;
            } catch (Exception e) {
                logger.error("Falha no login para o usuário: {} - Erro: {}", username, e.getMessage());
                throw e;
            }
        }
    }