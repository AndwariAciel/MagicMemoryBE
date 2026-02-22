package de.andwari.memory.backend.config;

import de.andwari.memory.backend.interceptor.EndpointInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class MagicMemoryConfig implements WebMvcConfigurer {

    private final EndpointInterceptor endpointInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(endpointInterceptor);
    }
}
