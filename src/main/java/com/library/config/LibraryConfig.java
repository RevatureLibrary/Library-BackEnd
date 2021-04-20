package com.library.config;

import com.library.models.*;
import com.library.repo.FeeDao;
import com.library.services.DepartmentService;
import com.library.services.LibraryService;
import com.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import javax.annotation.PostConstruct;
import java.sql.Time;

@Configuration
public class LibraryConfig {

    final private LibraryService libraryService;
    final  private UserService userService;
    final  private DepartmentService departmentService;
    @Autowired
    FeeDao feeDao;

    @Autowired
    public LibraryConfig(LibraryService libraryService, @Lazy UserService userService, DepartmentService departmentService) {
        this.libraryService = libraryService;
        this.userService = userService;
        this.departmentService = departmentService;
    }
    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedDepartmentTable();
        seedLibraryTable();
        seedUsersTable();
        seedFeeTable();

    }

    private void seedDepartmentTable() {
        departmentService.createDepartment( new Department(0, "Unsorted", null));
        departmentService.createDepartment( new Department(0, "Horror", null));
        departmentService.createDepartment( new Department(0, "Romance", null));
        departmentService.createDepartment( new Department(0, "Comedy", null));
        departmentService.createDepartment( new Department(0, "Sci-Fi", null));
        departmentService.createDepartment( new Department(0, "Fantasy", null));
        departmentService.createDepartment( new Department(0, "Self-Help", null));
        departmentService.createDepartment( new Department(0, "Non-Fiction", null));

    }

    public void seedLibraryTable() {
        String libraryName = "William Memorial Library";

        if (libraryService.getLibrary() != null) {
            Time openingTime = Time.valueOf("00:00:00");
            Time closingTime = Time.valueOf("23:59:59");
            boolean isOpen = true;
            Library library = new Library(1, libraryName, openingTime, closingTime, isOpen);
            libraryService.update(library);
        }
    }


    private void seedUsersTable() {
        String sql = "SELECT username, email FROM users U WHERE U.username = \"admin\" OR U.email = \"test@test.com\" LIMIT 1";
        User u = userService.readByUsername("admin");
        //List<User> u = (List<User>) entityManagerFactory().getObject().getCurrentSession().createQuery(sql,User.class).uniqueResult();
        if(u == null ) {
            User user = new User();
            user.setFirstName("Test");
            user.setLastName("Admin");
            user.setUsername("admin");
            user.setEmail("test@test.com");
            user.setPassword("pass");
            user.setAccountType(enums.AccountType.ADMIN);
//                user.setConfirmEmail(true);
            userService.save(user);
//                logger.info("Users Seeded");
        } else {

            System.out.println("Admin exists");
//                logger.info("Users Seeding Not Required");
        }
    }

    private void seedFeeTable(){
        Fee f = new Fee();
        f.setFeeStatus(enums.FeeStatus.UNPAID);
        f.setAmount(10.00);
        f.setUser(userService.readByUsername("admin"));
        f.setFeeType(enums.FeeType.LATE);
        feeDao.save(f);
    }
}
