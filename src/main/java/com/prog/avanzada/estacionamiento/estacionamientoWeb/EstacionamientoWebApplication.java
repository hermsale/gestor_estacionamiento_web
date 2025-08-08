package com.prog.avanzada.estacionamiento.estacionamientoWeb;

import com.prog.avanzada.estacionamiento.estacionamientoWeb.Model.Usuario;
import com.prog.avanzada.estacionamiento.estacionamientoWeb.Repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class EstacionamientoWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(EstacionamientoWebApplication.class, args);
	}


//	Verifica si ya existe un usuario "admin".
//
//	Si no existe, lo crea con contraseña "admin123" encriptada con BCrypt.
//
//	Si ya existe, lo deja
	@Bean
	CommandLineRunner init(UsuarioRepository repo, BCryptPasswordEncoder encoder) {
		return args -> {
			if (repo.findByUsername("admin").isEmpty()) {
				Usuario user = new Usuario();
				user.setNombre("Admin");
				user.setApellido("Principal");
				user.setUsername("admin");
				user.setPassword(encoder.encode("admin123"));
				user.setRol("ADMIN");
				repo.save(user);
				System.out.println("🟢 Usuario admin creado correctamente.");
			} else {
				System.out.println("ℹ️ Usuario admin ya existe.");
			}
		};
	}
}
