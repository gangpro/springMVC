package org.example.springMVC.article.service;

import org.example.springMVC.article.vo.ArticleVO;
import org.example.springMVC.commons.paging.Criteria;

import java.util.List;
// 인터페이스 생성 후 메서드 정의
public interface ArticleService {

    // 생성
    void create(ArticleVO articleVO) throws Exception;

    // 읽기
    ArticleVO read(Integer articleNo) throws Exception;

    // 수정
    void update(ArticleVO articleVO) throws Exception;

    // 삭제
    void delete(Integer articleNo) throws Exception;

    // 조회
    List<ArticleVO> listAll() throws Exception;

    // 페이징 목록 메서드 추가
    List<ArticleVO> listCriteria(Criteria criteria) throws Exception;

    // 페이징 전체 게시글 갯수
    int countArticles(Criteria criteria) throws Exception;

}
