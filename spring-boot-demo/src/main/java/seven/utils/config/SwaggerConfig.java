package seven.utils.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Profile({"dev","sit","uat"})
@EnableSwagger2
@SpringBootConfiguration
public class SwaggerConfig {
    /**
     * 服务对应联系人信息
     */
    @Value("${seven.springboot.demo.swagger.contact.principalName}")
    private String pricipalName;

    /**
     * 服务主页url
     */
    @Value("${seven.springboot.demo.swagger.contact.url}")
    private String url;
    /**
     * 联系人email
     */
    @Value("${seven.springboot.demo.swagger.contact.email}")
    private String email;
    /**
     * 服务描述
     */
    @Value("${seven.springboot.demo.swagger.description}")
    private String description;
    /**
     * 服务分组
     */
    @Value("${seven.springboot.demo.swagger.groupName}")
    private String groupName;
    /**
     * 服务名称
     */
    @Value("${spring.application.name}")
    private String appName;


    @Bean
    public Docket createApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .groupName(groupName)
                .select()
                .apis(RequestHandlerSelectors.basePackage("seven.utils"))
                .paths(PathSelectors.any())
                .build();
    }


    /**
     * 定义服务的基本信息，包括服务对应的联系人信息，服务主页，email，服务名称以及描述
     * 请根据实际情况替换
     * @return
     */
    private ApiInfo getApiInfo(){
        Contact contactInfo = new Contact(pricipalName, url, email);
        return new ApiInfoBuilder().title(appName).description(description).contact(contactInfo).build();
    }


}
