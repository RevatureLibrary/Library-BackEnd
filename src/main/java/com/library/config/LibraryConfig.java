package com.library.config;

import com.library.controller.CheckoutController;
import com.library.models.*;
import com.library.models.dto.BookDTO;
import com.library.models.dto.CheckoutDTO;
import com.library.repo.FeeRepo;
import com.library.services.*;

import com.library.models.Department;
import com.library.models.Library;
import com.library.models.User;
import com.library.models.enums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import javax.annotation.PostConstruct;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;

@Configuration
public class LibraryConfig {

    final private LibraryService libraryService;
    final  private UserService userService;
    final  private DepartmentService departmentService;
    final private BookService bookService;
    final private CheckoutService checkoutService;

    @Autowired
    FeeRepo feeRepo;


    @Autowired
    public LibraryConfig(LibraryService libraryService, @Lazy UserService userService, DepartmentService departmentService, BookService bookService, CheckoutService checkoutService) {

        this.libraryService = libraryService;
        this.userService = userService;
        this.departmentService = departmentService;
        this.bookService = bookService;
        this.checkoutService = checkoutService;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedDepartmentTable();
        seedLibraryTable();
        seedUsersTable();
        seedFeeTable();
        seedBooksTable();
    }

    private void seedDepartmentTable() {
        departmentService.createDepartment( new Department(0, "Unsorted", null));
        departmentService.createDepartment( new Department(0, "Horror", null));
        departmentService.createDepartment( new Department(0, "Romance", null));
        departmentService.createDepartment( new Department(0, "Sci-Fi", null));
        departmentService.createDepartment( new Department(0, "Fantasy", null));
        departmentService.createDepartment( new Department(0, "Self-Help", null));
        departmentService.createDepartment( new Department(0, "Non-Fiction", null));

    }

    public void seedLibraryTable(){
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
            user = new User();
            user.setFirstName("Patrick");
            user.setLastName("Gonzalez");
            user.setUsername("pgonzalez");
            user.setEmail("patrick.gonzalez@revature.net");
            user.setPassword("password");
            user.setAccountType(enums.AccountType.PATRON);
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
        feeRepo.save(f);
    }

    private void seedBooksTable(){
        if(bookService.getAll() != null){
            BookDTO bookDTO = new BookDTO(0, 42,
                    "The Hitchhiker's Guide to the Galaxy",
                    "Douglas Adams", "Pan Books",
                    enums.Condition.GOOD, enums.BookStatus.AVAILABLE,
                    new String[]{"Sci-Fi"});

            bookService.addBook(bookDTO);
            bloodHeartSpaceQuest();
            WEncyclopedia();
        }
    }
    private void seedCheckoutTable(){
        Checkout patsLate = new Checkout();
        patsLate.setBook(bookService.getByBookId(50));
        patsLate.setUser(userService.readByUsername("pgonzalez"));
        patsLate.setCheckoutDate(new Timestamp(System.currentTimeMillis() - (31556952 *1000)));
        patsLate.setCheckoutStatus(enums.CheckoutStatus.DUE);
        patsLate.setReturnDueDate(
                Timestamp.valueOf(patsLate.getCheckoutDate().toLocalDateTime().plusDays(7)));
        System.out.println("==========================================");
        System.out.println("==========================================");
        System.out.println("==========================================");
        System.out.println("==========================================");
        System.out.println("==========================================");
        System.out.println("==========================================");
        System.out.println("==========================================");
        System.out.println("==========================================");
        System.out.println("==========================================");
        System.out.println(checkoutService.checkoutBook(patsLate));


    }

    private void bloodHeartSpaceQuest() {
        System.out.println("==========================================");
        System.out.println("==========================================");
        System.out.println("==========================================");
        System.out.println("==========================================");
        System.out.println("==========================================");
        System.out.println("==========================================");
        System.out.println("==========================================");
        System.out.println("==========================================");
        System.out.println("==========================================");
            BookDTO bhsq= new BookDTO(0, 8675309,
                    "Blood Heart Space Quest",
                    "Sudo Nym", "Low Standards Publishing House",
                    enums.Condition.GOOD, enums.BookStatus.AVAILABLE,
                    new String[]{"Sci-Fi","Horror", "Romance","Fantasy"});

            for(int i = 0; i<50;i++){
                bhsq.setCondition(bookService.cycleCondition(bhsq.getCondition()));
                bhsq.setId(0);
                bookService.addBook(bhsq);
            }

            seedCheckoutTable();

    }

    private void WEncyclopedia(){

        char volume= 'A';
        BookDTO w= new BookDTO(0, 987654433,
                "William's World Wide Wesource Wolume: " + volume,
                "William Wallace William", "WPH",
                enums.Condition.WILLIAM, enums.BookStatus.AVAILABLE,
                new String[]{"Non-Fiction"});

        for(int i = 0; i<26;i++){
            //w.setCondition(bookService.cycleCondition(w.getCondition()));
            w.setId(0);
            w.setTitle("William's World Wide Wesource Wolume: " + volume);
            bookService.addBook(w);
            volume++;
        }
    }



}
