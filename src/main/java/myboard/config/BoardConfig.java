package myboard.config;

import myboard.controller.BoardController;
import myboard.interceptor.BoardInterceptor;
import myboard.repository.BoardDbRepository;
import myboard.repository.BoardMemoryRepository;
import myboard.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: ojh
 * Date: 13. 6. 5
 * Time: 오후 5:10
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "myboard")
public class BoardConfig extends WebMvcConfigurerAdapter{
    @Autowired
    BoardRepository boardRepository;

    @Bean
    public BoardRepository boardRepository() {
        return new BoardDbRepository(psqlDataSource());
    }

    @Bean
    public BoardInterceptor boardInterceptor() {
        return new BoardInterceptor();
    }

    @Bean
    public BoardController boardController() {
        return new BoardController();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(boardInterceptor()).addPathPatterns("/board/**");
    }

    @Bean
    public InternalResourceViewResolver configureInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/board/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("validation");
        return messageSource;
    }

/*    @Bean
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver(){
        SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
        Properties props = new Properties();
        props.setProperty("java.lang.Exception", "/error/error");
        props.setProperty("java.lang.Throwable", "/error/error");
        exceptionResolver.setExceptionMappings(props);
        return exceptionResolver;

    }*/

    @Bean
    public DataSource psqlDataSource (){
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource( );
        dataSource.setDriverClass(org.postgresql.Driver.class);
        dataSource.setUrl("jdbc:postgresql://localhost:5432/BOARD");
        dataSource.setUsername("board");
        dataSource.setPassword("board");

        return dataSource;
    }

/*
    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:validation");
        return messageSource;
    }
*/

}
