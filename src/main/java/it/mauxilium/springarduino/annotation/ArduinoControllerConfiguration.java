package it.mauxilium.springarduino.annotation;

import it.mauxilium.springarduino.processor.InternalArduinoAnnotationProcessor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

@Configuration
public class ArduinoControllerConfiguration {
    public ArduinoControllerConfiguration() {
    }

    @Bean(name = {"it.mauxilium.springarduino.processor.internalArduinoAnnotationProcessor"})
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public InternalArduinoAnnotationProcessor arduinoAnnotationProcessor() {
        return new InternalArduinoAnnotationProcessor();
    }
}
