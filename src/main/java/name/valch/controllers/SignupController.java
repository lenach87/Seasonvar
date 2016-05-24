package name.valch.controllers;



import name.valch.entity.User;
import name.valch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/signup")
public class SignupController {

    private final UserService userService;

    @Autowired
    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String signupForm(Model model) {

        User userForm = new User();
        model.addAttribute ("userForm", userForm);
        return "signup";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView doSignup(@ModelAttribute("userForm") @Valid User user, BindingResult result, ModelMap model, RedirectAttributes redirect) {

        if(result.hasErrors()) {
            return new ModelAndView("signup");
        }

        model.addAttribute("email",user.getEmail());
        model.addAttribute("login",user.getLogin());
        model.addAttribute("password",user.getPassword());
        model.addAttribute("role",user.getRole());

        user = this.userService.save(user);
        return new ModelAndView("redirect:list");
    }
}