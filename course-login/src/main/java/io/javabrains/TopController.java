package io.javabrains;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TopController {

	public static final String PAGE = "/";
    private static final String HTML = "top";

    @RequestMapping(value = TopController.PAGE, method = RequestMethod.GET)
    public String top(Model model)
    {
        return TopController.HTML;
    }
}
