package org.example.springMVC.article.dao;

import org.apache.ibatis.session.SqlSession;
import org.example.springMVC.article.vo.ArticleVO;
import org.example.springMVC.commons.paging.Criteria;
import org.example.springMVC.commons.paging.SearchCriteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 클래스 생성하고, 메서드를 오버라이딩
@Repository
public class ArticleDAOImpl implements ArticleDAO {

    // 변수 NAMESPACE는 articleMapper.xml의 <mapper> 태그의 namespace 속성과 일치해야 한다.
    private static final String NAMESPACE = "org.example.springMVC.mappers.article.ArticleMapper";

    private final SqlSession sqlSession;

    private final ArticleDAO articleDAO;       // 게시글 비즈니스 계층의 수정 및 트랜잭션 적용


    @Inject
    public ArticleDAOImpl(SqlSession sqlSession, ArticleDAO articleDAO) {
        this.sqlSession = sqlSession;
        this.articleDAO = articleDAO;   // 게시글 비즈니스 계층의 수정 및 트랜잭션 적용
    }

    @Override
    public void create(ArticleVO articleVO) throws Exception {
        sqlSession.insert(NAMESPACE + ".create", articleVO);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)   // 게시글 비즈니스 계층의 수정 및 트랜잭션 적용
    @Override
    public ArticleVO read(Integer articleNo) throws Exception {
        articleDAO.updateViewCnt(articleNo);
        return articleDAO.read(articleNo);
    }

    @Override
    public void update(ArticleVO articleVO) throws Exception {
        sqlSession.update(NAMESPACE + ".update", articleVO);
    }

    @Override
    public void delete(Integer articleNo) throws Exception {
        sqlSession.delete(NAMESPACE + ".delete", articleNo);
    }

    @Override
    public List<ArticleVO> listAll() throws Exception {
        return sqlSession.selectList(NAMESPACE + ".listAll");
    }

    // 페이징 처리 구현 클래스
    @Override
    public List<ArticleVO> listPaging(int page) throws Exception {

        if (page <= 0) {
            page = 1;
        }

        page = (page - 1) * 10;

        return sqlSession.selectList(NAMESPACE + ".listPaging", page);
    }

    // 페이징 처리 + Criteria 타입의 변수 구현 클래스
    @Override
    public List<ArticleVO> listCriteria(Criteria criteria) throws Exception {
        return sqlSession.selectList(NAMESPACE + ".listCriteria", criteria);
    }

    // 페이징 전체 게시글 갯수 처리
    @Override
    public int countArticles(Criteria criteria) throws Exception {
        return sqlSession.selectOne(NAMESPACE + ".countArticles", criteria);
    }

    // 검색된 목록 추상 메서드
    @Override
    public List<ArticleVO> listSearch(SearchCriteria searchCriteria) throws Exception {
        return sqlSession.selectList(NAMESPACE + ".listSearch", searchCriteria);
    }

    // 검색된 게시글의 갯수를 리턴하는 추상 메서드 선언
    @Override
    public int countSearchedArticles(SearchCriteria searchCriteria) throws Exception {
        return sqlSession.selectOne(NAMESPACE + ".countSearchedArticles", searchCriteria);
    }

    // 게시글의 댓글에 따른 트랜잭션 처리
    @Override
    public void updateReplyCnt(Integer articleNo, int amount) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("articleNo", articleNo);
        paramMap.put("amount", amount);

        sqlSession.update(NAMESPACE + ".updateReplyCnt", paramMap);
    }

    // 게시글의 조회에 따른 트랜재션 처리
    @Override
    public void updateViewCnt(Integer articleNo) throws Exception {
        sqlSession.update(NAMESPACE + ".updateViewCnt", articleNo);
    }


}
