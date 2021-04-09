package com.library.models;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class enums {
    public enum AccountType{
        PATRON, LIBRARIAN, ADMIN;

        public SimpleGrantedAuthority toAuth(){
            if (this == ADMIN)
                return new SimpleGrantedAuthority("ADMIN");
            else if(this == PATRON)
                return new SimpleGrantedAuthority("PATRON");
            else if (this == LIBRARIAN)
                return new SimpleGrantedAuthority("LIBRARIAN");
            else return null;
        }

    }

    public enum FeeType{
        LATE, DAMAGE, MISC
    }

    public enum FeeStatus{
        UNPAID, PAID, FORGIVEN
    }

    public enum CheckoutStatus{
        DUE, OVERDUE, RETURNED
    }

    public enum Condition{
        WILLIAM, GOOD, FAIR, POOR, TRASHED
    }

    public enum BookStatus{
        AVAILABLE, CHECKED_OUT, RETURNED
    }
}
