package myboard.config;

import myboard.repository.BoardDbRepository;
import myboard.repository.BoardMemoryRepository;
import myboard.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

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
        return new BoardMemoryRepository();
    }

    @Bean
    public InternalResourceViewResolver configureInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/board/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
}