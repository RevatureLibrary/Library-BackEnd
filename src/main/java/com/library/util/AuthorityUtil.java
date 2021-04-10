package com.library.util;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthorityUtil {
    public static boolean isPatron(SimpleGrantedAuthority auth){ return auth.getAuthority().equals("PATRON");}

    public static boolean isAdmin(SimpleGrantedAuthority auth){ return auth.getAuthority().equals("ADMIN");}

    public static boolean isLibrarian(SimpleGrantedAuthority auth){ return auth.getAuthority().equals("LIBRARIAN");}

    public static boolean isAnon(SimpleGrantedAuthority auth){ return auth.getAuthority().equals("ROLE_ANONYMOUS");}

    public static boolean isEmployee(SimpleGrantedAuthority auth){ return (isAdmin(auth)||isLibrarian(auth));}

    public static SecurityContext getSecurityContext(){ return SecurityContextHolder.getContext();}

    public static SimpleGrantedAuthority getAuth(){
        return (SimpleGrantedAuthority) getSecurityContext().getAuthentication().getAuthorities().stream().findFirst().get();
    }
}
