package grassrootunitedapi.grassrootunitedapi.config;

import grassrootunitedapi.grassrootunitedapi.resolver.TokenPayloadResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class AppConfiguration implements WebMvcConfigurer {
    @Autowired
    TokenPayloadResolver tokenPayloadResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        WebMvcConfigurer.super.addArgumentResolvers(resolvers);
        resolvers.add(tokenPayloadResolver);
    }
}
