package it.mauxilium.springarduino.annotation;

import org.springframework.stereotype.Controller;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.CLASS)
@Documented
@Controller
public @interface ToArduinoAction {
}
