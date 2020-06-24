package com.home.js_gg.config.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

/**
 * 타임리프 관련 자바 설정파일
 */
@Configuration
public class ThymeleafConfiguration {

    /**
     * Template resolver spring resource template resolver.
     *
     * @return the spring resource template resolver
     */
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver  templateResolver = new SpringResourceTemplateResolver ();
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCacheable(false);       // for development mode.

        return templateResolver;
    }

    /**
     * Template engine spring template engine.
     *
     * @param messageSource the message source
     * @return the spring template engine
     */
    @Bean
    public SpringTemplateEngine templateEngine(MessageSource messageSource) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setTemplateEngineMessageSource(messageSource);
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    /**
     * View resolver view resolver.
     *
     * @param messageSource the message source
     * @return the view resolver
     */
    @Bean
    @Autowired
    public ViewResolver viewResolver(MessageSource messageSource) {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine(messageSource));
        viewResolver.setCharacterEncoding("UTF-8");
        viewResolver.setOrder(Ordered.HIGHEST_PRECEDENCE);
        viewResolver.setViewNames(new String[] {".html", ".xhtml"});
        return viewResolver;
    }
}
