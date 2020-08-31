package ru.mycity.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.PathProvider;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletContext;

@Configuration
@EnableSwagger2
public class SpringFoxConfig{
    private final static String BASE_PATH = "/myapi";

//    @Bean
//    @Primary
//    public PathProvider customPathProvider() {
//        return new PathProvider() {
//            @Override
//            public String getApplicationBasePath() {
//                return BASE_PATH;
//            }
//
//            @Override
//            public String getOperationPath(String operationPath) {
//                return operationPath.replace(BASE_PATH, "");
//            }
//            @Override
//            public String getResourceListingPath(String groupName, String apiDeclaration) {
//                return BASE_PATH;
//            }
//        };
//    }
@Bean
public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
//                .pathProvider(customPathProvider())
            .select()
            .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
            .paths(PathSelectors.any())
            .build();
}


}