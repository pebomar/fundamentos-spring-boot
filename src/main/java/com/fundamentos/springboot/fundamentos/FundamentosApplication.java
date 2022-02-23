package com.fundamentos.springboot.fundamentos;

import com.fundamentos.springboot.fundamentos.bean.*;
import com.fundamentos.springboot.fundamentos.component.ComponentDependency;
import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.pojo.UserPojo;
import com.fundamentos.springboot.fundamentos.repository.UserRepository;
import com.fundamentos.springboot.fundamentos.service.UserService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	Log log = LogFactory.getLog(FundamentosApplication.class);

	//Implementación curso
	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;

	@Autowired
	private MyBeanWithProperties myBeanWithProperties;

	@Autowired
	private UserPojo userPojo;

	@Autowired
	private AnalizaCadena analizaCadena;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency, MyBean myBean, MyBeanWithDependency myBeanWithDependency) {
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
	}

	//Implementación  alternativa
	/*@Autowired
	@Qualifier("componentTwoImplement")
	private ComponentDependency componentDependency;*/



	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args)  {
		//saveUsersInDatabase();
		//getInformationJpqlFromUser();

		saveWhitErrorTransactional();
	}

	private void saveWhitErrorTransactional(){
		User test1 = new User("TestTransactional1", "TestTransactional1@mail.com", LocalDate.now());
		User test2 = new User("TestTransactional2", "TestTransactional2@mail.com", LocalDate.now());
		User test3 = new User("TestTransactional3", "TestTransactional3@mail.com", LocalDate.now());
		User test4 = new User("TestTransactional4", "TestTransactional4@mail.com", LocalDate.now());

		List<User> users = Arrays.asList(test1, test2, test3, test4);

		userService.saveTransactional(users);

		userService.getAllUsers().forEach(user -> log.info("Encontré el usuario "+user));
	}

	private void getInformationJpqlFromUser(){
		/*log.info("El usuario con el metodo findByUserEmail ["+
				userRepository.findByUserEmail("jhon@mail.com")
						.orElseThrow(() -> new RuntimeException("No se encontro el usuario"))+"]");

		userRepository.findAndSort("Usuario", Sort.by("id").descending())
				.stream()
				.forEach(user -> log.info("Usuario con metodo sort"+user));

		userRepository.findByName("Jhon")
				.forEach(user -> log.info("Usuario con query method " + user));

		log.info("Usuario con query method findByEmailAndName"+userRepository.findByEmailAndName("usuario2@mail.com", "Usuario2")
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado")));

		userRepository.findByNameLike("%suario%")
				.forEach(user -> log.info("Usuario con query method findByNameLike ["+user+"]"));

		userRepository.findByNameOrEmail(null, "usuario2@mail.com")
				.forEach(user -> log.info("Usuario con query method findByNameOrEmail ["+user+"]"));


		userRepository.findByBirthDateBetween(LocalDate.of(2020, 01, 01),
				LocalDate.of(2021, 12, 31))
				.forEach(user -> log.info("Usuario con query method findByBirthDateBetween ["+user+"]"));
		*/

		userRepository.findByNameLikeOrderByIdDesc("%suario%")
				.forEach(user -> log.info("Consulta con query method findByNameLikeOrderByIdDesc "+user));

		log.info("El usuario es: "+
				userRepository.getAllByBirthDateAndEmail(LocalDate.of(2021, 3, 20),
								"jhon@mail.com")
				.orElseThrow(() -> new RuntimeException("El registro no fue encontrado a partir" +
						"de la fecha de nacimiento y correo")));

	}

	private void saveUsersInDatabase(){
		User user1 = new User("Jhon", "jhon@mail.com", LocalDate.of(2021, 3, 20));
		User user2 = new User("Usuario2", "usuario2@mail.com", LocalDate.of(2020, 11, 10));
		User user3 = new User("Usuario3", "usuario3@mail.com", LocalDate.of(2021, 10, 1));
		User user4 = new User("Usuario4", "usuario4@mail.com", LocalDate.of(2020, 4, 2));
		User user5 = new User("Usuario5", "usuario5@mail.com", LocalDate.of(2021, 5, 3));
		User user6 = new User("Usuario6", "usuario6@mail.com", LocalDate.of(2020, 6, 4));
		User user7 = new User("Usuario7", "usuario7@mail.com", LocalDate.of(2021, 7, 5));
		User user8 = new User("Usuario8", "usuario8@mail.com", LocalDate.of(2020, 8, 6));
		User user9 = new User("Usuario9", "suario9@mail.com", LocalDate.of(2021, 9, 7));
		User user10 = new User("Usuario10", "usuario10@mail.com", LocalDate.of(2020, 10, 8));
		User user11 = new User("Usuario11", "usuario11@mail.com", LocalDate.of(2020, 11, 9));
		User user12 = new User("Usuario12", "usuario12@mail.com", LocalDate.of(2020, 12, 10));

		List<User> list = Arrays.asList(user1,user2,user3,user4, user5, user6, user7, user8, user9, user10, user11, user12);
		list.stream().forEach(userRepository::save);
	}

	private void ejemplosAnteriores(){
		componentDependency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();

		System.out.println(myBeanWithProperties.function());

		System.out.println(userPojo.getEmail() + "-" + userPojo.getPassword() + "-" + userPojo.getAge());

		try{
			int value = 10/0;
			log.debug("Mi valor "+value);
		}catch(Exception e){
			log.error("Esto es un error al dividir por cero " + e.getMessage());
		}
	}
}
