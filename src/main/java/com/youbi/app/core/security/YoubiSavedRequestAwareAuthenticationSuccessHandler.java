package com.youbi.app.core.security;

import com.youbi.app.core.admin.controller.BaseController;
import com.youbi.app.core.utils.EncryptDecryptUtils;
import org.apache.commons.io.IOExceptionWithCause;
import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hubin1 on 2017/3/28.
 */
public class YoubiSavedRequestAwareAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private static Logger logger = Logger.getLogger(YoubiSavedRequestAwareAuthenticationSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        Object obj = authentication.getPrincipal();
        if (obj instanceof Account) {
            Account currentUser = (Account) obj;
            String userName = currentUser.getUsername();
            try {
                EncryptDecryptUtils encryptDecryptUtils = new EncryptDecryptUtils();
                userName = encryptDecryptUtils.encrypt(userName);
            } catch (Exception e) {
                logger.error("加密用户名出错", e);
            }
            Cookie cookie = new Cookie(BaseController.CURRENT_USER_SESSION_KEY, userName);
            cookie.setMaxAge(-1);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
