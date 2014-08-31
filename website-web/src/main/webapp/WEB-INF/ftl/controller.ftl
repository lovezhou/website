package ${basePath}.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ${basePath}.domain.${className};
import ${basePath}.service.${className}Service;
<#assign  lowclassName="${className[0]?lower_case+className[1..]}">
<#assign  lowServiceName="${lowclassName}Service">

@Controller
@RequestMapping(value = "/${lowclassName}")
public class ${className}Controller {

    @Autowired
    private ${className}Service ${lowServiceName};

    @RequestMapping(value = "/toListView.do", method = RequestMethod.GET)
    public ModelAndView to${className}Page(HttpServletRequest req)  throws Exception {
        ModelAndView mav = new ModelAndView("/${lowclassName}_list.jsp");
        return mav;
    }
 

}
