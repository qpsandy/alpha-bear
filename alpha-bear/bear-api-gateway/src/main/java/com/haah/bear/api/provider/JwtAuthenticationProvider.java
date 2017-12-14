package com.haah.bear.api.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.haah.bear.api.client.UserServiceClient;
import com.haah.bear.api.enums.LoginStatusEnum;
import com.haah.bear.api.token.JwtAuthenticationToken;
import com.haah.bear.api.utils.JwtTokenUtils;
import com.haah.bear.core.constants.BizCode;
import com.haah.bear.core.pojo.UserPojo;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationProvider.class);

    @Autowired
    private UserServiceClient userServiceClient;

//    @Autowired
//    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Override
    @SuppressWarnings("unchecked")
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = (String)authentication.getCredentials();
        if (!StringUtils.isEmpty(token) && JwtTokenUtils.isTokenExpired(token)){
            logger.info("token is expired，If the user is logined in, then quit.");
//            UserPojo user = userServiceClient.retrieveUserByUsername(JwtTokenUtils.getUsernameFromExpiredToken(token));
//            if (user.getUserStatus().equals(LoginStatusEnum.LOGIN.getCode().toString())) {
//                userServiceClient.changeUserStatus(user.getUsername(), LoginStatusEnum.LOGOUT.getCode().toString());
//                user.setUserStatus(LoginStatusEnum.LOGOUT.getCode().toString());
//            }else{
//                logger.info(String.format("user: %s already log out", user.getUsername()));
//            }
        }
        String username = JwtTokenUtils.getUsernameFromToken(token);
        UserPojo user = userServiceClient.retrieveUserByUsername(username);
        if (LoginStatusEnum.LOGOUT.getCode().toString().equals(user.getUserStatus())){
            logger.info(String.format("user: %s already log out,Please login！", user.getUsername()));
            throw new BadCredentialsException(BizCode.USER_ALREADY_LOGOUT.getMessage());
        }
        Jws<Claims> claimsJws = JwtTokenUtils.validateToken(token);
        String subject = claimsJws.getBody().getSubject();
        List<String> scopes = claimsJws.getBody().get("scopes", List.class);
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String s : scopes) {
            authorities.add(new SimpleGrantedAuthority(s));
        }

        authentication = new UsernamePasswordAuthenticationToken(subject, null,authorities);
        if (JwtTokenUtils.canTokenBeRefreshed(token)){
            logger.info("Refresh and return the new token in response header");
            response.setHeader("Authorization", String.format("%s %s", JwtTokenUtils.TOKEN_PREFIX ,JwtTokenUtils.refreshToken(token)));
        }
        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
