package org.example.springMVC.reply.service;

import org.example.springMVC.article.dao.ArticleDAO;
import org.example.springMVC.commons.paging.Criteria;
import org.example.springMVC.reply.dao.ReplyDAO;
import org.example.springMVC.reply.vo.ReplyVO;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService {

    private final ReplyDAO replyDAO;

    private final ArticleDAO articleDAO;

    @Inject
    public ReplyServiceImpl(ReplyDAO replyDAO, ArticleDAO articleDAO) {
        this.replyDAO = replyDAO;
        this.articleDAO = articleDAO;
    }

    // 댓글 목록
    @Override
    public List<ReplyVO> getReplies(Integer articleNo) throws Exception {
        return replyDAO.list(articleNo);
    }

    // 댓글 등록
    @Override
    public void addReply(ReplyVO replyVO) throws Exception {
        replyDAO.create(replyVO);
    }

    // 댓글 수정
    @Override
    public void modifyReply(ReplyVO replyVO) throws Exception {
        replyDAO.update(replyVO);
    }

    // 댓글 삭제
    @Override
    public void removeReply(Integer replyNo) throws Exception {
        replyDAO.delete(replyNo);
    }

    // 댓글 페이징 목록
    @Override
    public List<ReplyVO> getRepliesPaging(Integer articleNo, Criteria criteria) throws Exception {
        return replyDAO.listPaging(articleNo, criteria);
    }

    // 댓글 페이징 카운팅
    @Override
    public int countReplies(Integer articleNo) throws Exception {
        return replyDAO.countReplies(articleNo);
    }
}
