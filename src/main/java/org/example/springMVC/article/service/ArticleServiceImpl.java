package org.example.springMVC.article.service;

import org.example.springMVC.article.dao.ArticleDAO;
import org.example.springMVC.article.vo.ArticleVO;
import org.example.springMVC.commons.paging.Criteria;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

// 클래스 생성하고, 메서드를 오버라이딩
@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleDAO articleDAO;

    @Inject
    public ArticleServiceImpl(ArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }

    @Override
    public void create(ArticleVO articleVO) throws Exception {
        articleDAO.create(articleVO);
    }

    @Override
    public ArticleVO read(Integer articleNo) throws Exception {
        return articleDAO.read(articleNo);
    }

    @Override
    public void update(ArticleVO articleVO) throws Exception {
        articleDAO.update(articleVO);
    }

    @Override
    public void delete(Integer articleNo) throws Exception {
        articleDAO.delete(articleNo);
    }

    @Override
    public List<ArticleVO> listAll() throws Exception {
        return articleDAO.listAll();
    }

    // 페이징 목록 메서드 구현
    @Override
    public List<ArticleVO> listCriteria(Criteria criteria) throws Exception {
        return articleDAO.listCriteria(criteria);
    }
    // 페이징 전체 게시글 갯수 처리
    @Override
    public int countArticles(Criteria criteria) throws Exception {
        return articleDAO.countArticles(criteria);
    }
}
