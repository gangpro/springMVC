package org.example.springMVC.article.service;

import org.example.springMVC.article.vo.ArticleVO;

import java.util.List;
// 인터페이스 생성 후 메서드 정의
public interface ArticleService {

    void create(ArticleVO articleVO) throws Exception;

    ArticleVO read(Integer articleNo) throws Exception;

    void update(ArticleVO articleVO) throws Exception;

    void delete(Integer articleNo) throws Exception;

    List<ArticleVO> listAll() throws Exception;

}
