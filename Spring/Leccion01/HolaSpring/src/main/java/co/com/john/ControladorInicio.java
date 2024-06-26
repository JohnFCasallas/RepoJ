package co.com.john;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class ControladorInicio {
    
    @GetMapping("/")
    public String inicio(){
        log.info("Ejecutando el controlador rest");
        log.debug("Mas detalle del controlador");
        return "HolaMundo con Spring 2.0";
    }
    
}
