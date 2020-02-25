package org.example.springMVC.article;

import org.example.springMVC.article.dao.ArticleDAO;
import org.example.springMVC.article.vo.ArticleVO;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import java.io.File;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring-config/applicationContext.xml"})
public class ArticleDAOTest {

    private static final Logger logger = LoggerFactory.getLogger(ArticleDAOTest.class);

    @Inject
    private ArticleDAO articleDAO;

    @Test
    public void testCreate() throws Exception {
        ArticleVO articleVO = new ArticleVO();
        articleVO.setTitle("제목 신규 테스트");
        articleVO.setContent("내용 신규 테스트");
        articleVO.setWriter("작성자 신규 테스트");
        articleDAO.create(articleVO);
    }

    @Test
    public void testRead() throws Exception {
        logger.info(articleDAO.read(1).toString());
    }

    @Test
    public void testUpdate() throws Exception {
        ArticleVO articleVO = new ArticleVO();
        articleVO.setArticleNo(1);
        articleVO.setTitle("제목 수정 테스트");
        articleVO.setContent("내용 수정 테스트");
        articleDAO.update(articleVO);
    }

    @Test
    public void testDelete() throws Exception {
        articleDAO.delete(1);
    }

}
