package com.example.Travelgo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.Travelgo.model.PaqueteTuristico;
import com.example.Travelgo.repository.PaqueteTuristicoRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private final PaqueteTuristicoRepository repo;

    public DataLoader(PaqueteTuristicoRepository repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args) {
        if (repo.count() == 0) {
            repo.save(new PaqueteTuristico(null, "Valparaíso", "Tour por la ciudad y cerros", 699990.0,"1 a 2 horas", "https://www.mhnv.gob.cl/sites/www.mhnv.gob.cl/files/2021-04/valparaiso-1-mpo2z5tez0o9jr4dke1y6aygrvyefe346e9gfoo48g.png"));
            repo.save(new PaqueteTuristico(null, "Concón", "Dunas y playa", 659990.0,"1 a 2 horas","https://upload.wikimedia.org/wikipedia/commons/1/14/Dunas_De_Conc%C3%B3n_%2840046667682%29.jpg"));
            repo.save(new PaqueteTuristico(null, "Rancagua", "Recorrido histórico", 399990.0,"1 a 2 horas","https://images.visitchile.com/destinos/574_Rancagua.jpg?w=960&h=448&fit=crop&q=auto&auto=format"));
            repo.save(new PaqueteTuristico(null, "Talca", "Descubre Talca", 59990.0,"3 a 8 horas","https://blog.uber-cdn.com/cdn-cgi/image/width=2160,quality=80,onerror=redirect,format=auto/wp-content/uploads/2018/05/Descubre-todo-lo-que-puedes-hacer-en-Talca-en-so%CC%81lo-dos-di%CC%81as.jpg"));
            repo.save(new PaqueteTuristico(null, "Concepción", "Ciudad universitaria", 79990.0,"3 a 8 horas","https://images.visitchile.com/destinos/4296_Concepcion.jpg?w=960&h=448&fit=crop&q=auto&auto=format"));
            repo.save(new PaqueteTuristico(null, "Puerto Montt", "Puerto y naturaleza", 49990.0,"13 a 24 horas","https://dynamic-media-cdn.tripadvisor.com/media/photo-o/2b/41/18/29/caption.jpg?w=1200&h=-1&s=1"));
            repo.save(new PaqueteTuristico(null, "Chiloé", "Isla mágica", 899990.0,"13 a 24 horas","https://www.skorpios.cl/wp-content/uploads/Isla-de-Chilo%C3%A9.jpg"));
            repo.save(new PaqueteTuristico(null, "Arica", "Sol y playa", 129990.0,"mas de 24 horas","https://blog.uber-cdn.com/cdn-cgi/image/width=2160,quality=80,onerror=redirect,format=auto/wp-content/uploads/2018/04/5-panoramas-en-Arica-que-no-te-puedes-perder-1024x512.png"));
        }
    }
}