package ru.education;

import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.Bindable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@SpringBootApplication
@EnableJpaAuditing
public class Application implements RepositoryRestConfigurer {
    private final EntityManager entityManager;

    public Application(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        Class<?>[] entities = (Class<?>[]) entityManager.getMetamodel().getEntities().stream().map(Bindable::getBindableJavaType).toArray(Class[]::new);

        config.exposeIdsFor(entities);
    }
}
