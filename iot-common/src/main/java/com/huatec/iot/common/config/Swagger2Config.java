package com.huatec.iot.common.config;

import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 *swagger 配置
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    private static final String  AUTHORIZATION= "Authorization";
	@Value("${swagger.enable}")
	private boolean enableSwagger;

    /*引入Knife4j提供的扩展类*/
    private final OpenApiExtensionResolver openApiExtensionResolver;
    //
    // @Override
    // public void addResourceHandlers(ResourceHandlerRegistry registry) {
    //     // 配置 knife4j 文档资源的访问路径
    //     registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
    //     registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    //     registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    // }

    public Swagger2Config(OpenApiExtensionResolver openApiExtensionResolver) {
        this.openApiExtensionResolver = openApiExtensionResolver;
    }


    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("全部模块")
                .apiInfo(apiInfo())
                .enable(enableSwagger)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.huatec.iot"))
                .paths(PathSelectors.any())
                .build()
                // .securitySchemes(securitySchemes())
                .securityContexts(securityContexts())
                //赋予插件体系
                .extensions(openApiExtensionResolver.buildExtensions("3.X版本"));
    }

    // @Bean
    // public Docket createRestApiMarket() {
    //     return new Docket(DocumentationType.OAS_30)
    //             .groupName("市场营销模块")
    //             .apiInfo(apiInfo())
    //             .enable(enableSwagger)
    //             .select()
    //             .apis(RequestHandlerSelectors.basePackage("com.huatec.cockpit.market.controller"))
    //             .paths(PathSelectors.any())
    //             .build()
    //             // .securitySchemes(securitySchemes())
    //             .securityContexts(securityContexts())
    //             ;
    // }

    private static ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("华晟IOT接口文档")
                .description("欢迎使用华晟IOT接口文档")
                .contact(new Contact("软件研发部java组", "http://development.huatec.com", "huatec.development@huatec.com"))
                .version("1.0")
                .build();
    }
    private static List<ApiKey> securitySchemes() {
    	List<ApiKey> apiKeys = new ArrayList<>();
        apiKeys.add(new ApiKey(AUTHORIZATION, AUTHORIZATION, "header"));
        return apiKeys;
    }

    private List<SecurityContext> securityContexts() {
    	List<SecurityContext> securityContexts = new ArrayList<>();
    	SecurityContext build = SecurityContext.builder()
        .securityReferences(defaultAuth())
                //过滤url 带auth不添加header参数
        .forPaths(PathSelectors.regex("^(?!"+AUTHORIZATION+").*$"))
        .build();
        securityContexts.add(build);
        return securityContexts;
    }

    List<SecurityReference> defaultAuth() {
    	List<SecurityReference> securityReferences = new ArrayList<>();
    	AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        securityReferences.add(new SecurityReference(AUTHORIZATION, authorizationScopes));
        return securityReferences;
    }
}
