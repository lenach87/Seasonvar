package name.valch.configs;

import name.valch.SeasonvarApplication;
import name.valch.mail.MailManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.Ordered;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Properties;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan({"name.valch"})
@PropertySource("classpath:application.properties")
@EnableJpaRepositories(basePackages = {"name.valch.repository"})
@EnableScheduling

public class AppConfig extends WebMvcConfigurerAdapter {

    // inject properties
    @Value("${jdbc.driver}")
    private String jdbcDriver;
    @Value("${jdbc.url}")
    private String jdbcURL;
    @Value("${jdbc.username}")
    private String jdbcUsername;
    @Value("${jdbc.password}")
    private String jdbcPassword;
    @Value("${hibernate.dialect}")
    private String sqlDialect;
    @Value("${smtp.auth}")
    private String smtpAuth;
    @Value("${smtp.starttls.enable}")
    private String smtpStarttls;
    @Value("${smtp.host}")
    private String smtpHost;
    @Value("${smtp.port}")
    private String smtpPort;
    @Value("${mail.username}")
    private String mailUsername;
    @Value("${mail.password}")
    private String mailPassword;
    @Value("${mail.from.email}")
    private String mailFrom;
    @Value("${hbm2ddl.auto}")
    private String hbm2dllAuto;
    @Value("${connection.charset}")
    private String connectionCharset;
    @Value("${connection.release}")
    private String connectionRelease;
    @Value("${validation.mode}")
    private String validationMode;

    private static final Logger log = LoggerFactory.getLogger(SeasonvarApplication.class);

    @Bean(name = "myDataSource")
    public DriverManagerDataSource myDataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(jdbcDriver);
        driverManagerDataSource.setUrl(jdbcURL);
        driverManagerDataSource.setUsername(jdbcUsername);
        driverManagerDataSource.setPassword(jdbcPassword);
        return driverManagerDataSource; //test commits
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(myDataSource());
        factoryBean.setPersistenceUnitName("SeasonvarJPA"); //change from data if necessary
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(true);
        adapter.setGenerateDdl(false);
        adapter.setDatabasePlatform(sqlDialect);
        factoryBean.setJpaVendorAdapter(adapter);
        factoryBean.setPackagesToScan("name.valch");
        Properties jpaProp = new Properties();
        jpaProp.put("hibernate.dialect", sqlDialect);
        jpaProp.put("hibernate.hbm2ddl.auto", hbm2dllAuto);
        jpaProp.put("hibernate.show_sql", "true");
        jpaProp.put("hibernate.connection.charset", connectionCharset);
        jpaProp.put("hibernate.connection.release_mode", connectionRelease);
        jpaProp.put("javax.persistence.validation.mode", validationMode);
        factoryBean.setJpaProperties(jpaProp);
        factoryBean.afterPropertiesSet();
        factoryBean.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
        return factoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return txManager;
    }

    @Bean
    public ViewResolver getViewResolver() {

    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    resolver.setPrefix("/WEB-INF/jsp/");
    resolver.setSuffix(".jsp");
  //  log.info("Final view resolver" + resolver.getApplicationContext());
        return resolver;
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Controller
    static class FaviconController {
        @RequestMapping("favicon.ico")
        String favicon() {
            return "forward:/resources/favicon.ico";
        }
    }
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    @Bean
    public MailManager mailManager() {
        MailManager manager = new MailManager();
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        Properties props = new Properties();
        props.put("mail.smtp.auth", smtpAuth);
        props.put("mail.smtp.starttls.enable", smtpStarttls);
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", smtpPort);
        props.put("mail.from.email", mailFrom);

        sender.setJavaMailProperties(props);
        sender.setUsername(mailUsername);
        sender.setPassword(mailPassword);
        manager.setSender(sender);
        return manager;
    }

}
