
package name.valch.controllers;



import name.valch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/login")
public class LoginController {
    private static UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        LoginController.userService = userService;
    }

//    public static User getCurrentUser(Principal principal) {
//       // Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String username = null;
//        if (principal instanceof UserDetails) {
//            username = ((UserDetails) principal).getUsername();
//        }
//            else {
//                username = principal.toString();
//            }
//        User loginUser = userService.findUserByUsername(username);
//        return new SecurityUser(loginUser);
//
//       // return null;\
//    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
      //
         return "redirect:/login";
    }

}

