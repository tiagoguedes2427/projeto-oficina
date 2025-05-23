package com.tiagogguedes.oficina.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        
        // Permitir origens específicas
        config.addAllowedOrigin("http://localhost:5173" ); // Frontend React
        
        // Se necessário, adicione outras origens
        // config.addAllowedOrigin("http://localhost:3000" );
        
        // Configurações importantes
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.setAllowCredentials(false); // Alterado para false para evitar problemas com CORS
        
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
