package hiber;

import hiber.config.AppConfig;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;
import hiber.model.Car;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

       User u1 = new User("Ivan","Ivanov","i@ex.com");
       u1.setCar(new Car("BMW", 3));

       User u2 = new User("Petr","Petrov","p@ex.com");
       u2.setCar(new Car("Audi", 5));

       User u3 = new User("Anna","Sidorova","a@ex.com");
       u3.setCar(new Car("Tesla", 3));

       User u4 = new User("Oleg","Orlov","o@ex.com");
       u4.setCar(new Car("BMW", 5));

       userService.add(u1);
       userService.add(u2);
       userService.add(u3);
       userService.add(u4);

       User owner = userService.findUserByCar("Audi", 5);
       System.out.println("Owner of Audi 5: " + owner.getFirstName() + " " + owner.getLastName());


       userService.listUsers().forEach(System.out::println);


       List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      context.close();
   }
}
