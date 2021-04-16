package com.library.util;

import com.library.models.request.JWTUserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthorityUtil {
        public static boolean isPatron(){ return getAuth().getAuthority().equals("PATRON");}

        public static boolean isAdmin(){ return getAuth().getAuthority().equals("ADMIN");}

        public static boolean isLibrarian(){ return getAuth().getAuthority().equals("LIBRARIAN");}

        public static boolean isAnon(){ return getAuth().getAuthority().equals("ROLE_ANONYMOUS");}

        public static boolean isEmployee(){ return (isAdmin()||isLibrarian());}

        public static SecurityContext getSecurityContext(){ return SecurityContextHolder.getContext();}

        public static String getRequesterUsername(){ return ((JWTUserDetails) getSecurityContext()).getUsername();}

        public static SimpleGrantedAuthority getAuth(){
         return (SimpleGrantedAuthority) getSecurityContext()
                 .getAuthentication().getAuthorities().stream().findFirst().get();
    }
}
