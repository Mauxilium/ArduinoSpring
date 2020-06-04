package it.mauxilium.arduinospring.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({ArduinoControllerConfiguration.class})
@Documented
public @interface EnableArduino {
}
