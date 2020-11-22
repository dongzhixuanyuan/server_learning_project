package webmvc;


import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.loader.ServletLoader;
import com.mitchellbosecke.pebble.spring.servlet.PebbleViewResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import webmvc.websocket.ChatHandler;
import webmvc.websocket.ChatHandshakeInterceptor;

import javax.servlet.ServletContext;
import java.util.Arrays;
import java.util.Locale;
import java.util.TimeZone;
import java.util.function.Consumer;

@Configuration
@ComponentScan
@EnableWebMvc
@EnableTransactionManagement
@PropertySource("classpath:spring_mvc_jdbc.properties")
public class Appconfig {

    @Bean
    WebMvcConfigurer createWebMvcConfigurer(@Autowired HandlerInterceptor[] interceptors) {
        return new WebMvcConfigurer() {
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/static/**").addResourceLocations("/static/");
            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
            Arrays.stream(interceptors).forEach(new Consumer<HandlerInterceptor>() {
                @Override
                public void accept(HandlerInterceptor handlerInterceptor) {
                    registry.addInterceptor(handlerInterceptor);
                }
            });
            }

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/").allowedOrigins("http://localhost:8080")
                        .allowedMethods("GET","POST").maxAge(360);
            }
        };
    }

    @Bean
    ViewResolver createViewResolver(@Autowired ServletContext servletContext) {
        PebbleEngine engine = new PebbleEngine.Builder().autoEscaping(true)
                .cacheActive(false)
                .loader(new ServletLoader(servletContext))
//                .extension(new SpringExtension())
                .build();
        PebbleViewResolver viewResolver = new PebbleViewResolver();
        viewResolver.setPrefix("/WEB-INF/templates/");
        viewResolver.setSuffix("");
        viewResolver.setPebbleEngine(engine);
        return viewResolver;
    }

    @Bean
    LocaleResolver createLocalResolver(){
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setDefaultLocale(Locale.ENGLISH);
        cookieLocaleResolver.setDefaultTimeZone(TimeZone.getDefault());
        return cookieLocaleResolver;
    }

    @Bean("i18n")
    MessageSource createMsgSrc(){
        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
        resourceBundleMessageSource.setDefaultEncoding("UTF-8");
        resourceBundleMessageSource.setBasename("string");
        return resourceBundleMessageSource;
    }


    @Bean
    WebSocketConfigurer createWebsocketConfigurer(@Autowired ChatHandler chatHandler, @Autowired ChatHandshakeInterceptor interceptor){
        return new WebSocketConfigurer() {
            @Override
            public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
                registry.addHandler(chatHandler,"/chat").addInterceptors(interceptor);
            }
        };
    }
}
