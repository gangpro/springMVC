package org.example.springMVC.article.dao;

import org.apache.ibatis.session.SqlSession;
import org.example.springMVC.article.vo.ArticleVO;
import org.example.springMVC.commons.paging.Criteria;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

// 클래스 생성하고, 메서드를 오버라이딩
@Repository
public class ArticleDAOImpl implements ArticleDAO {

    // 변수 NAMESPACE는 articleMapper.xml의 <mapper> 태그의 namespace 속성과 일치해야 한다.
    private static final String NAMESPACE = "org.example.springMVC.mappers.article.ArticleMapper";

    private final SqlSession sqlSession;

    @Inject
    public ArticleDAOImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public void create(ArticleVO articleVO) throws Exception {
        sqlSession.insert(NAMESPACE + ".create", articleVO);
    }

    @Override
    public ArticleVO read(Integer articleNo) throws Exception {
        return sqlSession.selectOne(NAMESPACE + ".read", articleNo);
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


}
