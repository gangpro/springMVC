package org.example.springMVC.article.dao;

import org.example.springMVC.article.vo.ArticleVO;

import java.util.List;
// 인터페이스 생성 후 메서드 정의
public interface ArticleDAO {

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

    // 페이징 처리
    List<ArticleVO> listPaging(int page) throws Exception;
}
