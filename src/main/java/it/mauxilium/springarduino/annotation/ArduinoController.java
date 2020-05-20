package it.mauxilium.springarduino.annotation;

import org.springframework.stereotype.Controller;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Controller
public @interface ArduinoController {
    String cardName() default "";
    String port() default "";
    String baudRate() default "";
    String[] baudRates() default {};
}
