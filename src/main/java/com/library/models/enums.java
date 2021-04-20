package com.library.models;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class enums {
    public enum AccountType{
        PATRON, LIBRARIAN, ADMIN;

        public Set<SimpleGrantedAuthority> toAuth(){
            return Collections.singleton(new SimpleGrantedAuthority(this.toString()));
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
        AVAILABLE, CHECKED_OUT, OFF_SHELF
    }
}
