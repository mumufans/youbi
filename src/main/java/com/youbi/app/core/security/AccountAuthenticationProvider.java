package com.youbi.app.core.security;

//import com.youbi.app.core.controller.BaseController;
import com.youbi.app.core.admin.controller.BaseController;
import com.youbi.app.core.utils.AuthenticationUtils;
import com.youbi.app.model.User;
import com.youbi.app.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by hubin1 on 2016/12/6.
 */

@Service
public class AccountAuthenticationProvider implements AuthenticationProvider {

    private static Logger logger = Logger.getLogger(AccountAuthenticationProvider.class);

    @Autowired
    private final HttpServletRequest request = null;

    @Autowired
    UserService userService;

    @Override
    @Transactional(noRollbackFor = BadCredentialsException.class)
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        logger.trace("user authentication");
        String username = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());
        User user = userService.findByUserName(username);
        authenticateAccount(user, username, password);
        Account account = new Account();
        account.setUser(user);
        request.getSession().setAttribute(BaseController.CURRENT_USER_SESSION_KEY, account);
        return new UsernamePasswordAuthenticationToken(account, null, account.getAuthorities());
    }

    private void authenticateAccount(User user, String username, String password){
        String errorMsg = null;
        if(user == null) {
            errorMsg = "用户名不存在";
        }else if (!AuthenticationUtils.generatePassword(password + user.getSalt()).equals(user.getHash())){
            errorMsg = "密码错误";
        }
        if (!StringUtils.isEmpty(errorMsg)){
            request.getSession().setAttribute("user_login_name", username);
            throw new BadCredentialsException(errorMsg);
        }
    }

    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
