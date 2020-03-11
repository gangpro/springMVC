package org.example.springMVC.reply.dao;

import org.example.springMVC.commons.paging.Criteria;
import org.example.springMVC.reply.vo.ReplyVO;

import java.util.List;

public interface ReplyDAO {

    // 댓글 처리

    List<ReplyVO> list(Integer articleNo) throws Exception;
    void create(ReplyVO replyVO) throws Exception;
    void update(ReplyVO replyVO) throws Exception;
    void delete(Integer replyNo) throws Exception;

    // 댓글 페이징 처리

    List<ReplyVO> listPaging(Integer articleNo, Criteria criteria) throws Exception;
    int countReplies(Integer articleNo) throws Exception;
    int getArticleNo(Integer replyNo) throws Exception;

}
