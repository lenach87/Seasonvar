
package name.valch.controllers;

import name.valch.SeasonvarApplication;
import name.valch.entity.SerialWithDates;
import name.valch.entity.User;
import name.valch.security.CurrentUser;
import name.valch.service.SerialWithDatesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class SerialRestController {

    private SerialWithDatesService serialWithDatesService;
    private static final Logger log = LoggerFactory.getLogger(SeasonvarApplication.class);

    @Autowired
    public void setSerialWithDatesService(SerialWithDatesService serialWithDatesService) {
        this.serialWithDatesService = serialWithDatesService;
    }

    @RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
    public ModelAndView getSerialsList(@CurrentUser User user) {
        Iterable<SerialWithDates> list = serialWithDatesService.findAllForUser(user);
        return new ModelAndView("list", "list", list);
    }

//    @RequestMapping(method = RequestMethod.GET)
//    public ModelAndView getAll() {
//        List<SerialWithDates> list = serialWithDatesService.findAll();
//        return new ModelAndView("list", "list", list);
//    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @Transactional
    public ModelAndView delete (@RequestParam(value = "toDelete[]", required = false) Long[] toDelete,
                                       @CurrentUser User user, RedirectAttributes redirect) {

        if (toDelete != null) {
            serialWithDatesService.deleteMultiple(toDelete, user);
        }
        return new ModelAndView("redirect:/list");
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addForm(Model model) { //@ModelAttribute MessageForm messageForm) {
        SerialWithDates addForm = new SerialWithDates();
        model.addAttribute("addForm", addForm);
        return new ModelAndView ("add");
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView add(@ModelAttribute("addForm") @Valid SerialWithDates addForm,
                            @CurrentUser User currentUser, BindingResult result, ModelMap model, RedirectAttributes redirect) {

        model.addAttribute("name", addForm.getName());

        if (addForm.getName()!=null) {
            SerialWithDates serial = serialWithDatesService.add(addForm, currentUser);
            redirect.addFlashAttribute("globalMessage", "Message added successfully");
            return new ModelAndView("redirect:/list");
        }
        else {
            result.rejectValue("name", "name", "Already added");
            return new ModelAndView("add");
        }
    }

}