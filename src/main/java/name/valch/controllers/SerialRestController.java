
package name.valch.controllers;

import name.valch.entity.Serial;
import name.valch.service.SerialService;
import name.valch.service.SerialWithDatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/")
public class SerialRestController {

    private SerialService serialService;
    private SerialWithDatesService serialWithDatesService;

    @Autowired
    public void setSerialService(SerialService serialService) {
        this.serialService = serialService;
    }

    @Autowired
    public void setSerialWithDatesService(SerialWithDatesService serialWithDatesService) {
        this.serialWithDatesService = serialWithDatesService;
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView getSerialsList() {
        List<Serial> list = serialService.findAll();
        return new ModelAndView("list", "list", list);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getAll() {
        List<Serial> list = serialService.findAll();
        return new ModelAndView("list", "list", list);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @Transactional
    public ModelAndView deleteRemoved (@RequestParam(value = "toDelete[]", required = false) Long[] toDelete,
                                       RedirectAttributes redirect) {
        if (toDelete != null) {
            serialService.deleteMultiple(toDelete);
        }
        return new ModelAndView("redirect:/list");
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public ModelAndView addForm(Model model) { //@ModelAttribute MessageForm messageForm) {
        Serial addForm = new Serial();
        model.addAttribute("addForm", addForm);
        return new ModelAndView ("add");
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ModelAndView add(@ModelAttribute("addForm") @Valid Serial addForm,
                                BindingResult result, ModelMap model, RedirectAttributes redirect) {

        model.addAttribute("name", addForm.getName());

        if (addForm.getName()!=null) {
            Serial serial = serialService.add(addForm);
            serialService.save(serial);
            redirect.addFlashAttribute("globalMessage", "Message added successfully");
            return new ModelAndView("redirect:/list");
        }
        else {
            result.rejectValue("name", "name", "Already added");
            return new ModelAndView("add");
        }
    }

}