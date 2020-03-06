package org.example.springMVC.article.controller;

import org.example.springMVC.article.service.ArticleService;
import org.example.springMVC.article.vo.ArticleVO;
import org.example.springMVC.commons.paging.Criteria;
import org.example.springMVC.commons.paging.PageMaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;

// 프로젝트 구조 변경
// 기본적인 CRUD + 페이징 처리 구현
@Controller
@RequestMapping("/article/paging")
public class ArticlePagingController {

    private static final Logger logger = LoggerFactory.getLogger(ArticlePagingController.class);

    private final ArticleService articleService;

    @Inject
    public ArticlePagingController(ArticleService articleService) {
        this.articleService = articleService;
    }

    // 등록
    // 등록 페이지 이동
    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public String writeGET() {
        logger.info("paging writeGET()...");

        return "article/paging/write";
    }

    // 등록
    // 등록 처리
    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public String writePOST(ArticleVO articleVO,
                            RedirectAttributes redirectAttributes) throws Exception {
        logger.info("paging writePOST()...");
        logger.info(articleVO.toString());

        articleService.create(articleVO);
        redirectAttributes.addFlashAttribute("msg", "regSuccess");

        return "redirect:/article/paging/list";
    }

    // 목록
    // 목록 페이지 이동
    // 페이징 처리
    // 페이지 번호 출력처리가 된 목록 페이지를 처리할 메서드
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model, Criteria criteria) throws Exception {
        logger.info("paging list()...");

        PageMaker pageMaker = new PageMaker();
        pageMaker.setCriteria(criteria);
        pageMaker.setTotalCount(articleService.countArticles(criteria));

        model.addAttribute("article", articleService.listCriteria(criteria));
        model.addAttribute("pageMaker", pageMaker);

        return "article/paging/list";
    }

    // 조회
    // 조회 페이지 이동
    // 조회(개선: 목록 페이지 정보 유지)
    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public String read(@RequestParam("articleNo") int articleNo,
                       @ModelAttribute("criteria") Criteria criteria,
                       Model model) throws Exception {
        logger.info("paging read()...");
        model.addAttribute("article", articleService.read(articleNo));

        return "article/paging/read";
    }

    // 수정
    // 수정 페이지 이동
    // 수정 페이지 이동(개선: 목록 페이지 정보 유지)
    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public String modifyGET(@RequestParam("articleNo") int articleNo,
                            @ModelAttribute("criteria") Criteria criteria,
                            Model model) throws Exception {

        logger.info("paging modifyGet()...");
        model.addAttribute("article", articleService.read(articleNo));

        return "article/paging/modify";
    }

    // 수정
    // 수정 처리
    // 수정 페이지 처리(개선: 목록 페이지 정보 유지)
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String modifyPOST(ArticleVO articleVO,
                             Criteria criteria,
                             RedirectAttributes redirectAttributes) throws Exception {
        logger.info("paging modifyPOST()...");

        articleService.update(articleVO);
        redirectAttributes.addAttribute("page", criteria.getPage());
        redirectAttributes.addAttribute("perPageNum", criteria.getPerPageNum());
        redirectAttributes.addFlashAttribute("msg", "modSuccess");

        return "redirect:/article/paging/list";
    }

    // 삭제
    // 삭제(개선: 목록 페이지 정보 유지)
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String remove(@RequestParam("articleNo") int articleNo,
                         Criteria criteria,
                         RedirectAttributes redirectAttributes) throws Exception {
        logger.info("paging remove()...");

        articleService.delete(articleNo);
        redirectAttributes.addAttribute("page", criteria.getPage());
        redirectAttributes.addAttribute("perPageNum", criteria.getPerPageNum());
        redirectAttributes.addFlashAttribute("msg", "delSuccess");

        return "redirect:/article/paging/list";
    }

}
