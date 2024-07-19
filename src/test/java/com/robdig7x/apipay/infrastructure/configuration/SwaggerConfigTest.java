package com.robdig7x.apipay.infrastructure.configuration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;

import java.util.Properties;

import static org.mockito.Mockito.*;

public class SwaggerConfigTest {

    @Mock
    private ApplicationPreparedEvent event;

    @Mock
    private ConfigurableApplicationContext applicationContext;

    @Mock
    private ConfigurableEnvironment env;

    @Mock
    private MutablePropertySources propertySources;

    @InjectMocks
    private SwaggerConfig swaggerConfig;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testOnApplicationEvent() {
        when(event.getApplicationContext()).thenReturn(applicationContext);
        when(event.getApplicationContext().getEnvironment()).thenReturn(env);
        when(env.getPropertySources()).thenReturn(propertySources);

        // Arrange
        Properties expectedProps = new Properties();
        expectedProps.put("springdoc.swagger-ui.path", "swagger");
        expectedProps.put("springdoc.swagger-ui.path", "/v3/api-docs");
        PropertiesPropertySource expectedPropertySource = new PropertiesPropertySource("programmatically", expectedProps);

        // Act
        swaggerConfig.onApplicationEvent(event);

        // Assert
        verify(propertySources, times(1)).addFirst(expectedPropertySource);
    }
}
