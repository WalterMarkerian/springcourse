package com.springcourse;

import com.springcourse.models.Libro;
import com.springcourse.models.Producto;
import com.springcourse.models.UserData;
import com.springcourse.myBeans.MiBean;
import com.springcourse.myBeans.MiComponente;
import com.springcourse.services.IOrderService;
import com.springcourse.services.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class Rutas {

    private OrderService orderService;
    private MiBean miBean;
//    @Autowired
    //Inyección por Atributo es más sencilla, pero puede dificultar las pruebas y la gestión de dependencias.
    //Es menos recomendable en aplicaciones complejas donde el control de las dependencias y las pruebas unitarias son importantes.
    private MiComponente miComponente;

    public Rutas(OrderService orderService, MiBean miBean, MiComponente miComponente){
        //Inyección por Constructor es generalmente preferida porque hace que las dependencias sean explícitas,
        //facilita la escritura de pruebas, y promueve inmutabilidad y claridad en el diseño de las clases.
        this.orderService = orderService;
        this.miBean = miBean;
        this.miComponente = miComponente;
    }
    private final Logger logger = LoggerFactory.getLogger(Rutas.class);
    @GetMapping("/hola")
    String miPriemraRuta(){
        return "Hola mundo desde Spring Controller.";
    }

    @GetMapping("/libro/{id}/editorial/{editorial}")
    String leerLibro(@PathVariable int id, @PathVariable String editorial){
        return "Leyendo el libro id: " + id + ", editorial: " + editorial;
    }

    @GetMapping("/libro2/{id}")
    String leerLibro2(@PathVariable int id, @RequestParam String gato, @PathVariable String editorial){
        return "Leyendo el libro id: " + id + ", params: " + gato +", editorial: " + editorial;
    }

    @PostMapping("/libro")
    String guardarLibro(@RequestBody Libro libro){
            logger.debug("libro {} editorial {}", libro.nombre, libro.editorial);
        return "libro guardado";
    }

    @GetMapping("/saludar")
    @ResponseStatus(value = HttpStatus.MOVED_PERMANENTLY, reason = "Fue movido.")
    String miSegundaRutaConStatus(){
        return "Aprendiendo statuses http en Spring Boot";
    }

    @GetMapping("/animales/{lugar}")
    public ResponseEntity<String> getAnimales(@PathVariable String lugar){
        if(lugar.equals("granja")){
            return ResponseEntity.status(HttpStatus.OK).body("Caballo, vaca, oveja, gallina");
        } else if (lugar.equals("selva")){
            return ResponseEntity.status(HttpStatus.OK).body("Mono, Gorila, Puma");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("LUGAR INEXISTENTE");
        }
    }

    @GetMapping("/calcular/{numero}")
    public int getCalculo(@PathVariable int numero){
        throw new NullPointerException("La clave del usuario es password123, y no deberia leerse en POSTMAN");
    }

    @GetMapping("/userData")
    public ResponseEntity<String> getUserData(){
        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body("{\"name\": \"mary\"}");
    }
    @GetMapping("/userDataV2")
    public Map<String, Map<String, Object>> getUserDataV2(){
        return Map.of("user", Map.of("user", "mary", "age", 31));
    }

    @GetMapping("/userDataV3")
    public UserData getUserDataV3(){
        return new UserData("mary", 31, "direccion falssa");
    }

    @PostMapping("/order")
    public String crearOrden(@RequestBody List<Producto> products){
        orderService.saveOrder(products);
        return "Productos guardados";
    }

    @GetMapping("/mibean")
    public String saludarDesdeBean(){
        miBean.saludar();
        return "Completado";
    }
    @GetMapping("/micomponente")
    public String saludarDesdeMiComponente(){
        miComponente.saludarDesdeComponente();
        return "Completado";
    }

}
