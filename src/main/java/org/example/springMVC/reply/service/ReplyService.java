package org.example.springMVC.reply.service;

import org.example.springMVC.commons.paging.Criteria;
import org.example.springMVC.reply.vo.ReplyVO;

import java.util.List;

public interface ReplyService {

    // 댓글 처리
    List<ReplyVO> getReplies(Integer articleNo) throws Exception;
    void addReply(ReplyVO replyVO) throws Exception;
    void modifyReply(ReplyVO replyVO) throws Exception;
    void removeReply(Integer replyNo) throws Exception;

    // 댓글 페이징 처리
    List<ReplyVO> getRepliesPaging(Integer articleNo, Criteria criteria) throws Exception;
    int countReplies(Integer articleNo) throws Exception;
}
