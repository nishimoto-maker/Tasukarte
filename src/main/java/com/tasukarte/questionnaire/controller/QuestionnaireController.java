package com.tasukarte.questionnaire.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tasukarte.questionnaire.dto.QuestionnaireForm;
import com.tasukarte.questionnaire.service.QuestionnaireService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * 問診票コントローラー
 *
 * 問診票の登録・検索・詳細表示を行う。
 */
@Controller
@RequestMapping("/questionnaire")
@RequiredArgsConstructor
public class QuestionnaireController {

    private final QuestionnaireService questionnaireService;

    /* 問診票登録画面表示 */
    @GetMapping("/register")
    public String showRegistration(
            Model model) {

        model.addAttribute(
                "form",
                new QuestionnaireForm());

        return "QuestionnaireRegistration";
    }

    /* 問診票登録内容確認画面表示 */
    @PostMapping("/confirm")
    public String showConfirm(
            @Valid QuestionnaireForm form,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            return "QuestionnaireRegistration";
        }

        model.addAttribute(
                "form",
                form);

        return "QuestionnaireRegistrationCheck";
    }

    /* 問診票登録処理 */
    @PostMapping("/create")
    public String create(
            QuestionnaireForm form) {

        questionnaireService.register(
                form);

        return "redirect:/questionnaire/done";
    }

    /* 問診票登録完了画面表示 */
    @GetMapping("/done")
    public String showDone() {

        return "QuestionnaireRegistrationDone";
    }

    /* 問診票検索画面表示 */
    @GetMapping("/search")
    public String showSearch() {

        return "QuestionnaireSearch";
    }

    /* 問診票詳細画面表示 */
    @GetMapping("/detail/{questionnaireId}")
    public String showDetail(
            @PathVariable Integer questionnaireId,
            Model model) {

        model.addAttribute(
                "questionnaireId",
                questionnaireId);

        return "QuestionnaireDetail";
    }

}