package it.mauxilium.springarduino.processor;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;
import java.util.Set;

@Configuration
public class ArduinoControllerProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.out.println("ECCOMI!!!!");
        return false;
    }
}
