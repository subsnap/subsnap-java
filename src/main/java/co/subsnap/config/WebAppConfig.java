package co.subsnap.config;

import static co.subsnap.constants.DatabaseConstants.ENTITYMANAGER_PACKAGES_TO_SCAN;
import static co.subsnap.constants.DatabaseConstants.PROPERTY_NAME_HIBERNATE_DEFAULT_SCHEMA;
import static co.subsnap.constants.DatabaseConstants.PROPERTY_NAME_HIBERNATE_DIALECT;
import static co.subsnap.constants.DatabaseConstants.PROPERTY_NAME_HIBERNATE_SET_BIG_STRING;
import static co.subsnap.constants.DatabaseConstants.PROPERTY_NAME_HIBERNATE_SHOW_SQL;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.mail.javamail.JavaMailSenderImpl;


@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("co.subsnap.")
@PropertySource(value = { "classpath:default.properties", "classpath:${spring.profiles.active:default}.properties" })
@EnableJpaRepositories("co.subsnap.service")
@ImportResource({ "classpath:spring-dispatcher-servlet.xml"})
public class WebAppConfig extends WebMvcConfigurationSupport {

    @Autowired
    private Environment environment;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setUrl(environment.getProperty("database.url"));
        dataSource.setUsername(environment.getProperty("database.username"));
        dataSource.setPassword(environment.getRequiredProperty("database.password"));

        return dataSource;
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPackagesToScan(ENTITYMANAGER_PACKAGES_TO_SCAN);
        entityManagerFactoryBean.setJpaProperties(hibProperties());
        return entityManagerFactoryBean;
    }

    private Properties hibProperties() {
        Properties properties = new Properties();
        properties.put(PROPERTY_NAME_HIBERNATE_DIALECT, environment.getProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
        properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, environment.getProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
        properties.put(PROPERTY_NAME_HIBERNATE_DEFAULT_SCHEMA,
                environment.getProperty(PROPERTY_NAME_HIBERNATE_DEFAULT_SCHEMA));
        properties.put(PROPERTY_NAME_HIBERNATE_SET_BIG_STRING,
                environment.getProperty(PROPERTY_NAME_HIBERNATE_SET_BIG_STRING));
        return properties;
    }

    @Bean(name = "transactionManager")
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
 
    @Bean(name = "propertySourcesPlaceholderConfigurer")
    public static PropertySourcesPlaceholderConfigurer getPropertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
        "classpath:/META-INF/resources/", "classpath:/resources/",
        "classpath:/static/", "classpath:/public/", "classpath:/webapp/resources/" };
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (!registry.hasMappingForPattern("/webjars/**")) {
            registry.addResourceHandler("/webjars/**").addResourceLocations(
                    "classpath:/META-INF/resources/webjars/");
        }
        if (!registry.hasMappingForPattern("/**")) {
            registry.addResourceHandler("/**").addResourceLocations(
                    CLASSPATH_RESOURCE_LOCATIONS);
        }
    }
    
    @Bean
    public JavaMailSenderImpl getMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setPort(587);
        mailSender.setHost("smtp.sendgrid.net");
        mailSender.setUsername("subsnap");
        mailSender.setPassword("5195Wint!");
        return mailSender;
    }

    
}
