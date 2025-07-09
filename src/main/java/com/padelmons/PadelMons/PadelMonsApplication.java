package com.padelmons.PadelMons;

import com.padelmons.PadelMons.entities.*;
import com.padelmons.PadelMons.repositories.PlayerRepository;
import com.padelmons.PadelMons.repositories.RoleRepository;
import com.padelmons.PadelMons.repositories.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class PadelMonsApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PadelMonsApplication.class, args);
	}

	@Bean
	public ApplicationRunner configure(PlayerRepository playerRepository, RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder encoder) {
		return env -> {


			Player player0 = new Player("Carlos", "Palomares", 52, "Masculino", "", new DataContact("carlos@hotmail.com","123456789"),new Team(""));
			Player player1 = new Player("Jasmany", "Zambrano", 37, "Masculino", "", new DataContact("carlos@hotmail.com","123456789"),new Team(""));
			Player player2 = new Player("Oscar", "Colmenero", 38, "Masculino", "", new DataContact("carlos@hotmail.com","123456789"),new Team(""));
			Player player3 = new Player("Alberto", "Bueno", 52, "Masculino", "", new DataContact("carlos@hotmail.com","123456789"),new Team(""));
			Player player4 = new Player("Luis", "Trejo", 52, "Masculino", "", new DataContact("carlos@hotmail.com","123456789"),new Team(""));
			Player player5 = new Player("Joseba", "Sanchez", 25, "Masculino", "", new DataContact("carlos@hotmail.com","123456789"),new Team(""));
			Player player6 = new Player("Mikel", "Virceda", 38, "Masculino", "", new DataContact("carlos@hotmail.com","123456789"),new Team(""));
			Player player7 = new Player("Sofia", "Araujo", 24, "Femenino", "", new DataContact("carlos@hotmail.com","123456789"),new Team(""));
			Player player8 = new Player("Antonia", "Tapia", 29, "Femenino", "", new DataContact("carlos@hotmail.com","123456789"),new Team(""));
			Player player9 = new Player("Ohiana", "Gordobil", 24, "Femenino", "", new DataContact("carlos@hotmail.com","123456789"),new Team(""));
			Player player10 = new Player("Maria", "Arrieta", 31, "Femenino", "", new DataContact("carlos@hotmail.com","123456789"),new Team(""));
			Player player11 = new Player("Pedro", "Caryon", 32, "Masculino", "", new DataContact("carlos@hotmail.com","123456789"),new Team(""));
			Player player12 = new Player("Jon", "Baryolo", 27, "Masculino", "", new DataContact("carlos@hotmail.com","123456789"),new Team(""));
			Player player13 = new Player("Adrian", "Cohello", 20, "Masculino", "", new DataContact("carlos@hotmail.com","123456789"),new Team(""));


			playerRepository.save(player0);
			playerRepository.save(player1);
			playerRepository.save(player2);
			playerRepository.save(player3);
			playerRepository.save(player4);
			playerRepository.save(player5);
			playerRepository.save(player6);
			playerRepository.save(player7);
			playerRepository.save(player8);
			playerRepository.save(player9);
			playerRepository.save(player10);
			playerRepository.save(player11);
			playerRepository.save(player12);
			playerRepository.save(player13);







			Role r = new Role(ERole.ROLE_USER);
			Role r1 = new Role(ERole.ROLE_MODERATOR);
			Role r2 = new Role(ERole.ROLE_ADMIN);

			roleRepository.save(r);
			roleRepository.save(r1);
			roleRepository.save(r2);

			Set<Role> roles = new HashSet<>();
			roles.add(r);
			roles.add(r1);
			roles.add(r2);

			String pass = "jasio";
			User admin = new User("admin", "age002@gmail.com", encoder.encode(pass), roles);
			userRepository.save(admin);
		};
	}
	@Override
	public void run(String... args) throws Exception {

	}
}
