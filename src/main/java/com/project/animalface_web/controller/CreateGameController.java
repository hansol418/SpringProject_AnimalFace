package com.project.animalface_web.controller;


import com.project.animalface_web.dto.CreateGameDTO;
import com.project.animalface_web.service.CreateGameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/createGame")
public class CreateGameController {
    private final CreateGameService createGameService;

    @GetMapping("/create")
    public String create(Model model) {
        return "createGame/create2";
    }

    @PostMapping("/create")
    public String createRegister(@Valid @ModelAttribute CreateGameDTO createGameDTO,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            log.info("register 중 오류 발생" + bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/createGame/create";
        }
        log.info("화면에서 입력 받은 내용 확인 : " + createGameDTO);

        Long createGameNo = createGameService.registerCreateGame(createGameDTO);
        redirectAttributes.addFlashAttribute("result", createGameNo);
        redirectAttributes.addFlashAttribute("resultType", "register");

        return "redirect:/main";
    }


    @GetMapping("/read")
    public void read(@AuthenticationPrincipal UserDetails user, Long createGameNo, CreateGameDTO createGameDTO, Model model) {
        log.info("CreateGameController : /read 확인 중");

        CreateGameDTO createGameDTO1 = createGameService.readCreateGame(createGameNo);

        log.info("CreateGameController 확인 중, createGameDTO1 : " + createGameDTO1);
        model.addAttribute("createGameDTO", createGameDTO1);
        model.addAttribute("user", user);
    }

}
