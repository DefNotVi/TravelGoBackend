package com.example.Travelgo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.Travelgo.model.Cliente;
import com.example.Travelgo.model.Itinerario;
import com.example.Travelgo.model.PaqueteTuristico;
import com.example.Travelgo.model.Role;
import com.example.Travelgo.repository.ClienteRepository;
import com.example.Travelgo.repository.ItinerarioRepository;
import com.example.Travelgo.repository.PaqueteTuristicoRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private final PaqueteTuristicoRepository paqueteRepo;
    private final ClienteRepository clienteRepo;
    private final ItinerarioRepository itinerarioRepo;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(
            PaqueteTuristicoRepository paqueteRepo,
            ClienteRepository clienteRepo,
            ItinerarioRepository itinerarioRepo,
            PasswordEncoder passwordEncoder) {
        this.paqueteRepo = paqueteRepo;
        this.clienteRepo = clienteRepo;
        this.itinerarioRepo = itinerarioRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        // ---  Carga de Paquetes Turísticos ---
        if (paqueteRepo.count() == 0) {
            // Guardamos los paquetes y capturamos las instancias para usarlas con Itinerario
            PaqueteTuristico valparaiso = paqueteRepo.save(new PaqueteTuristico(null, "Valparaíso", "Tour por la ciudad y cerros", 699990.0,"1 a 2 horas", "https://www.mhnv.gob.cl/sites/www.mhnv.gob.cl/files/2021-04/valparaiso-1-mpo2z5tez0o9jr4dke1y6aygrvyefe346e9gfoo48g.png"));
            PaqueteTuristico concon = paqueteRepo.save(new PaqueteTuristico(null, "Concón", "Dunas y playa", 659990.0,"1 a 2 horas","https://upload.wikimedia.org/wikipedia/commons/1/14/Dunas_De_Conc%C3%B3n_%2840046667682%29.jpg"));
            paqueteRepo.save(new PaqueteTuristico(null, "Rancagua", "Recorrido histórico", 399990.0,"1 a 2 horas","https://images.visitchile.com/destinos/574_Rancagua.jpg?w=960&h=448&fit=crop&q=auto&auto=format"));
            paqueteRepo.save(new PaqueteTuristico(null, "Talca", "Descubre Talca", 59990.0,"3 a 8 horas","https://blog.uber-cdn.com/cdn-cgi/image/width=2160,quality=80,onerror=redirect,format=auto/wp-content/uploads/2018/05/Descubre-todo-lo-que-puedes-hacer-en-Talca-en-so%CC%81lo-dos-di%CC%81as.jpg"));
            paqueteRepo.save(new PaqueteTuristico(null, "Concepción", "Ciudad universitaria", 79990.0,"3 a 8 horas","https://images.visitchile.com/destinos/4296_Concepcion.jpg?w=960&h=448&fit=crop&q=auto&auto=format"));
            paqueteRepo.save(new PaqueteTuristico(null, "Puerto Montt", "Puerto y naturaleza", 49990.0,"13 a 24 horas","https://dynamic-media-cdn.tripadvisor.com/media/photo-o/2b/41/18/29/caption.jpg?w=1200&h=-1&s=1"));
            paqueteRepo.save(new PaqueteTuristico(null, "Chiloé", "Isla mágica", 899990.0,"13 a 24 horas","https://www.skorpios.cl/wp-content/uploads/Isla-de-Chilo%C3%A9.jpg"));
            paqueteRepo.save(new PaqueteTuristico(null, "Arica", "Sol y playa", 129990.0,"mas de 24 horas","https://blog.uber-cdn.com/cdn-cgi/image/width=2160,quality=80,onerror=redirect,format=auto/wp-content/uploads/2018/04/5-panoramas-en-Arica-que-no-te-puedes-perder-1024x512.png"));
            
            // --- Carga de Itinerarios (Datos de prueba) ---
            if (itinerarioRepo.count() == 0) {
                // Itinerarios para Valparaíso (ID 1)
                Itinerario it1Dia1 = new Itinerario(null, 1, "Paseo por el Muelle Prat", valparaiso);
                Itinerario it2Dia1 = new Itinerario(null, 1, "Ascenso por el Ascensor Reina Victoria", valparaiso);
                Itinerario it3Dia2 = new Itinerario(null, 2, "Visita a la Casa Museo La Sebastiana", valparaiso);
                
                // Itinerarios para Concón (ID 2)
                Itinerario it4Dia1 = new Itinerario(null, 1, "Clase de surf en la Playa Reñaca", concon);
                Itinerario it5Dia1 = new Itinerario(null, 1, "Recorrido y fotos en las Dunas de Concón", concon);
                Itinerario it6Dia2 = new Itinerario(null, 2, "Cena en el restaurant de mariscos La Picá de Nicanor", concon);

                // Opción 1 (List.of)
                 itinerarioRepo.saveAll(List.of(it1Dia1, it2Dia1, it3Dia2, it4Dia1, it5Dia1, it6Dia2));
                
                // Opción 2 (new ArrayList): Más compatible y robusto
                /*List<Itinerario> itinerarios = new ArrayList<>();
                itinerarios.add(it1Dia1);
                itinerarios.add(it2Dia1);
                itinerarios.add(it3Dia2);
                itinerarios.add(it4Dia1);
                itinerarios.add(it5Dia1);
                itinerarios.add(it6Dia2);
                
                itinerarioRepo.saveAll(itinerarios);*/
            }
        }
        
        // ---  Carga de Usuarios Iniciales ---
        if (clienteRepo.count() == 0) {
            // Usuario 1: ADMIN (password: admin123456)
            Cliente admin = Cliente.builder()
                .name("Admin Travelgo")
                .email("admin@travel.com")
                .password(passwordEncoder.encode("admin123456"))
                .role(Role.ROLE_ADMIN)
                .build();
            clienteRepo.save(admin);

            // Usuario 2: USER (password: user123456)
            Cliente user = Cliente.builder()
                .name("User Normal")
                .email("user@travel.com")
                .password(passwordEncoder.encode("user123456"))
                .role(Role.ROLE_USER)
                .build();
            clienteRepo.save(user);
        }
    }
}