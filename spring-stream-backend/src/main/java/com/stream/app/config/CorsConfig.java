package com.stream.app.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.cors.CorsConfiguration;
// import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
// import org.springframework.web.filter.CorsFilter;

// @Configuration
public class CorsConfig {

//     @Bean
//     public CorsFilter corsFilter() {
//         UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//         CorsConfiguration config = new CorsConfiguration();
        
//         // Allow credentials
//         config.setAllowCredentials(true);
        
//         // Allow requests from React dev server
//         config.addAllowedOriginPattern("http://localhost:*");
//         config.addAllowedOriginPattern("http://127.0.0.1:*");
        
//         // Allow all headers
//         config.addAllowedHeader("*");
        
//         // Allow all HTTP methods
//         config.addAllowedMethod("*");
        
//         // How long the response from a pre-flight request can be cached
//         config.setMaxAge(3600L);
        
//         source.registerCorsConfiguration("/**", config);
//         return new CorsFilter(source);
//     }
// }package com.streaming.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.cors.CorsConfiguration;
// import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
// import org.springframework.web.filter.CorsFilter;

// @Configuration
// public class CorsConfig {

//     @Bean
//     public CorsFilter corsFilter() {
//         UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//         CorsConfiguration config = new CorsConfiguration();
        
//         // Allow credentials
//         config.setAllowCredentials(true);
        
//         // Allow requests from React dev server
//         config.addAllowedOriginPattern("http://localhost:*");
//         config.addAllowedOriginPattern("http://127.0.0.1:*");
        
//         // Allow all headers
//         config.addAllowedHeader("*");
        
//         // Allow all HTTP methods
//         config.addAllowedMethod("*");
        
//         // How long the response from a pre-flight request can be cached
//         config.setMaxAge(3600L);
        
//         source.registerCorsConfiguration("/**", config);
//         return new CorsFilter(source);
//     }
}