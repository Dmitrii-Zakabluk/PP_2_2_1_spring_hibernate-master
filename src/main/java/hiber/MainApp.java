package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("Ivan", "Ivanov", "i.ivanov@mail.ru");
        User user2 = new User("Petr", "Sidorov", "p.sidorov@gmail.com");
        User user3 = new User("Alexandr", "Andreev", "a.andreev@inbox.ru");
        User user4 = new User("Victor", "Petrov", "petrovv@rambler.com");

        Car car1 = new Car("Volkswagen Passat", 8);
        Car car2 = new Car("Audi", 6);
        Car car3 = new Car("BMW", 525);
        Car car4 = new Car("Mazda", 6);

        userService.add(user1.setCar(car1).setUser(user1));
        userService.add(user2.setCar(car2).setUser(user2));
        userService.add(user3.setCar(car3).setUser(user3));
        userService.add(user4.setCar(car4).setUser(user4));

        for (User user : userService.listUsers()) {
            System.out.println(user + " " + user.getCar());
        }

        System.out.println(userService.getUserByCar("BMW", 525));

        try {
            System.out.println(userService.getUserByCar("VAZ", 2114));
        } catch (NoResultException e) {
            System.out.println("User not found");
        }

        context.close();
    }
}
