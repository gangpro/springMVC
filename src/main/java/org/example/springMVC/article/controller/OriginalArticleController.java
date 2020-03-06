//package org.example.springMVC.article.controller;
//
//import org.example.springMVC.article.service.ArticleService;
//import org.example.springMVC.article.vo.ArticleVO;
//import org.example.springMVC.commons.paging.Criteria;
//import org.example.springMVC.commons.paging.PageMaker;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import javax.inject.Inject;
//
//// 프로젝트 구조 변경 전 파일
//@Controller
//@RequestMapping("/article")
//public class OriginalArticleController {
//
//    private static final Logger logger = LoggerFactory.getLogger(OriginalArticleController.class);
//
//    private final ArticleService articleService;
//
//    @Inject
//    public OriginalArticleController(ArticleService articleService) {
//        this.articleService = articleService;
//    }
//
//    // 등록
//    // 등록 페이지 이동
//    @RequestMapping(value = "/write", method = RequestMethod.GET)
//    public String writeGET() {
//        logger.info("write GET...");
//
//        return "article/normal/write";
//    }
//
//    // 등록
//    // 등록 처리
//    @RequestMapping(value = "/write", method = RequestMethod.POST)
//    public String writePOST(ArticleVO articleVO, RedirectAttributes redirectAttributes) throws Exception {
//        logger.info("write POST...");
//        logger.info(articleVO.toString());
//
//        articleService.create(articleVO);
//        redirectAttributes.addFlashAttribute("msg", "regSuccess");
//
//        return "article/normal/list";
//    }
//
//    // 목록
//    // 목록 페이지 이동
//    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    public String list(Model model) throws Exception {
//        logger.info("list...");
//        model.addAttribute("article", articleService.listAll());
//
//        return "article/normal/list";
//    }
//
//    // 조회
//    // 조회 페이지 이동
//    @RequestMapping(value = "/read", method = RequestMethod.GET)
//    public String read(@RequestParam("articleNo") int articleNo, Model model) throws Exception {
//        logger.info("read...");
//        model.addAttribute("article", articleService.read(articleNo));
//
//        return "article/normal/read";
//    }
//
//    // 수정
//    // 수정 페이지 이동
//    @RequestMapping(value = "/modify", method = RequestMethod.GET)
//    public String modifyGET(@RequestParam("articleNo") int articleNo, Model model) throws Exception {
//
//        logger.info("normal modifyGet() called ...");
//        model.addAttribute("article", articleService.read(articleNo));
//
//        return "article/normal/modify";
//    }
//
//    // 수정
//    // 수정 처리
//    @RequestMapping(value = "/modify", method = RequestMethod.POST)
//    public String modifyPOST(ArticleVO articleVO, RedirectAttributes redirectAttributes) throws Exception {
//        logger.info("modifyPOST...");
//        articleService.update(articleVO);
//        redirectAttributes.addFlashAttribute("msg", "modSuccess");
//
//        return "article/normal/list";
//    }
//
//    // 삭제
//    @RequestMapping(value = "/remove", method = RequestMethod.POST)
//    public String remove(@RequestParam("articleNo") int articleNo, RedirectAttributes redirectAttributes) throws Exception {
//        logger.info("remove...");
//        articleService.delete(articleNo);
//        redirectAttributes.addFlashAttribute("msg", "delSuccess");
//
//        return "article/normal/list";
//    }
//
//    // 페이징 처리
//    @RequestMapping(value = "/listCriteria", method = RequestMethod.GET)
//    public String listCriteria(Model model, Criteria criteria) throws Exception {
//        logger.info("listCriteria... ");
//        model.addAttribute("articles", articleService.listCriteria(criteria));
//
//        return "article/normal/list_criteria";
//    }
//
//    // 페이지 번호 출력처리가 된 목록 페이지를 처리할 메서드
//    @RequestMapping(value = "/listPaging", method = RequestMethod.GET)
//    public String listPaging(Model model, Criteria criteria) throws Exception {
//        // Criteria, Model 타입의 변수 criteria 와 model 을 파라미터로 사용
//        // Model 객체를 사용하여 PageMaker 에서 계산한 결과 값을 저장
//        logger.info("listPaging...");
//
//        PageMaker pageMaker = new PageMaker();
//        pageMaker.setCriteria(criteria);
////        pageMaker.setTotalCount(1000);  // 아직 영속계층에서 전체 게시글의 갯수를 구하는 로직을 구현하지 않았기 때문에 setTotalCount 의 매개변수는 1000을 임의로 넣었다.
//        pageMaker.setTotalCount(articleService.countArticles(criteria));
//
//        model.addAttribute("articles", articleService.listCriteria(criteria));
//        model.addAttribute("pageMaker", pageMaker);
//
//        return "/article/list_paging";
//    }
//
//    // 조회(개선: 목록 페이지 정보 유지)
//    @RequestMapping(value = "/readPaging", method = RequestMethod.GET)
//    public String readPaging(@RequestParam("articleNo") int articleNo,
//                             @ModelAttribute("criteria") Criteria criteria,
//                             Model model) throws Exception {
//        logger.info("readPaging...");
//        model.addAttribute("article", articleService.read(articleNo));
//
//        return "/article/read_paging";
//    }
//
//    // 수정 페이지 이동(개선: 목록 페이지 정보 유지)
//    @RequestMapping(value = "/modifyPaging", method = RequestMethod.GET)
//    public String modifyGETPaging(@RequestParam("articleNo") int articleNo,
//                                  @ModelAttribute("criteria") Criteria criteria,
//                                  Model model) throws Exception {
//
//        logger.info("normal modifyGetPaging...");
//        model.addAttribute("article", articleService.read(articleNo));
//
//        return "article/modify_paging";
//    }
//
//    // 수정 페이지 처리(개선: 목록 페이지 정보 유지)
//    @RequestMapping(value = "/modifyPaging", method = RequestMethod.POST)
//    public String modifyPOSTPaging(ArticleVO articleVO,
//                                   Criteria criteria,
//                                   RedirectAttributes redirectAttributes) throws Exception {
//        logger.info("modifyPOSTPaging...");
//        articleService.update(articleVO);
//        redirectAttributes.addFlashAttribute("page", criteria.getPage());
//        redirectAttributes.addFlashAttribute("perPageNum",criteria.getPerPageNum());
//        redirectAttributes.addFlashAttribute("msg", "modSuccess");
//
//        return "redirect:/article/listPaging";
//    }
//
//    // 삭제(개선: 목록 페이지 정보 유지)
//    @RequestMapping(value = "/removePaging", method = RequestMethod.POST)
//    public String removePaging(@RequestParam("articleNo") int articleNo,
//                               Criteria criteria,
//                               RedirectAttributes redirectAttributes) throws Exception {
//        logger.info("remove...");
//        articleService.delete(articleNo);
//        redirectAttributes.addFlashAttribute("page", criteria.getPage());
//        redirectAttributes.addFlashAttribute("perPageNum", criteria.getPerPageNum());
//        redirectAttributes.addFlashAttribute("msg", "delSuccess");
//
//        return "redirect:/article/listPaging";
//    }
//
//}