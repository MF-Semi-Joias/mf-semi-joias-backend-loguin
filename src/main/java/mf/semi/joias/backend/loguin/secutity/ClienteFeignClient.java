// src/main/java/mf/semi/joias/backend/loguin/secutity/ClienteFeignClient.java
    package mf.semi.joias.backend.loguin.secutity;

    import org.springframework.cloud.openfeign.FeignClient;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.PathVariable;

    @FeignClient(name = "cliente-service", url = "http://localhost:8081")
    public interface ClienteFeignClient {
        @GetMapping("/clientes/usuario/{usuario}")
        ClienteDTO buscarPorUsuario(@PathVariable("usuario") String usuario);
    }