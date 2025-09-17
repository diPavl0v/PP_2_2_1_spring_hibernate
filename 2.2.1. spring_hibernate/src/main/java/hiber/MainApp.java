package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(AppConfig.class)) {

            UserService userService = context.getBean(UserService.class);

            User u1 = new User("User1", "Lastname1", "user1@mail.ru"); u1.setCar(new Car("BMW", 3));
            User u2 = new User("User2", "Lastname2", "user2@mail.ru"); u2.setCar(new Car("Audi", 5));
            User u3 = new User("User3", "Lastname3", "user3@mail.ru"); u3.setCar(new Car("Tesla", 3));
            User u4 = new User("User4", "Lastname4", "user4@mail.ru"); u4.setCar(new Car("BMW", 7));

            userService.add(u1);
            userService.add(u2);
            userService.add(u3);
            userService.add(u4);

            User owner = userService.findUserByCar("Audi", 5);
            if (owner != null) {
                System.out.println("Owner of Audi 5: " + owner.getFirstName() + " " + owner.getLastName());
            } else {
                System.out.println("Owner of Audi 5: not found");
            }

            List<User> users = userService.listUsers();
            for (User user : users) {
                System.out.println("Id=" + user.getId()
                        + ", FirstName=" + user.getFirstName()
                        + ", LastName=" + user.getLastName()
                        + ", Email=" + user.getEmail()
                        + ", Car=" + user.getCar().getModel() + " " + user.getCar().getSeries());
            }
        }
    }
}
