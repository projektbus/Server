//package ProjektBus.Server.config;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.Jws;
//import io.jsonwebtoken.Jwts;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Collections;
//import java.util.Set;
//
//public class JwtFilter extends BasicAuthenticationFilter {
//
//    public JwtFilter(AuthenticationManager authenticationManager) {
//        super(authenticationManager);
//    }
//
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
//        String authorizationHeader = request.getHeader("Authorization");
//        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
//            throw new ServletException("Authorization header missing or wrong type");
//        }
//        UsernamePasswordAuthenticationToken authResult = getAuthenticationByToken(authorizationHeader);
//        SecurityContextHolder.getContext().setAuthentication(authResult);
//        chain.doFilter(request, response);
//    }
//
//    private UsernamePasswordAuthenticationToken getAuthenticationByToken(String header) throws ServletException {
//        Jws<Claims> claimsJws = null;
//        try {
//            claimsJws = Jwts.parser().setSigningKey("TbUL55^O|T<;UyT".getBytes())
//                    .parseClaimsJws(header.replace("Bearer ", ""));
//        } catch (ExpiredJwtException ex) {
//            throw new ServletException("Token expired");
//        } catch (Exception ex) {
//            throw new ServletException("Token wrong formatted");
//        }
//        String login = claimsJws.getBody().get("login").toString();
//        Set<SimpleGrantedAuthority> simpleGrantedAuthorities = Collections.singleton(new SimpleGrantedAuthority("user"));
//
//        return new UsernamePasswordAuthenticationToken(login, "", simpleGrantedAuthorities);
//    }
//}
