package com.mg.HomeBudgetApp.controller.mvc;

import static com.mg.HomeBudgetApp.commons.ControllerConstants.*;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MenuController {

    @RequestMapping(path = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public String getIndex() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if ((authentication instanceof AnonymousAuthenticationToken)) {
            return INDEX;
        } else {
            return INDEX;
        }
    }
}
