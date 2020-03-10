package org.example.springMVC.reply.service;

import org.example.springMVC.reply.vo.ReplyVO;

import java.util.List;

public interface ReplyService {

    List<ReplyVO> getReplies(Integer articleNo) throws Exception;

    void addReply(ReplyVO replyVO) throws Exception;

    void modifyReply(ReplyVO replyVO) throws Exception;

    void removeReply(Integer replyNo) throws Exception;

}
