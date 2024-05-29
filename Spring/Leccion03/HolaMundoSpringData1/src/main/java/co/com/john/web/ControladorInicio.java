package co.com.john;

import java.util.ArrayList;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
@Slf4j
public class ControladorInicio {
    
    
    @Value("${index.saludo}")
    private String saludo;
    
    @GetMapping("/")
    public String inicio(Model model){
        var mensaje = "Hola Mundo con Thymeleaf";
        
        var persona = new Persona();
        persona.setNombre("John");
        persona.setApellido("Casallas");
        persona.setEmail("prueba@email.com");
        persona.setTelefono("55443322");
        
        var persona2 = new Persona();
        persona2.setNombre("Juan");
        persona2.setApellido("Casallas");
        persona2.setEmail("juan@email.com");
        persona2.setTelefono("33229900");
        
//        var personas = new ArrayList();
//        personas.add(persona);
//        personas.add(persona2);

//        var personas = Arrays.asList(persona, persona2);
        var personas = new ArrayList();

        log.info("Ejecutando el controlador Spring MVC");        
        model.addAttribute("mensaje", mensaje);
        model.addAttribute("saludo", saludo);
        model.addAttribute("personas", personas);
        return "index";
    }
    
}

