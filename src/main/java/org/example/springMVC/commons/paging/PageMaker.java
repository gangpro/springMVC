package org.example.springMVC.commons.paging;

public class PageMaker {

    // 외부에서 입력되는 데이터 : page, perPageNum
    // DB에서 계산되는 데이터 : totalCount
    // 계산식을 통해 만들어지는 데이터 : startPage, endPage, prev, next

    private int totalCount; // 전체 게시글의 갯수
    private int startPage;  // 시작 페이지 번호
    private int endPage;    // 끝 페이지 번호
    private boolean prev;   // 이전 링크
    private boolean next;   // 다음 링크

    // 하단의 페이지 번호의 갯수를 의미
    private int displayPageNum = 10;

    private Criteria criteria;

    public void setCriteria(Criteria criteria) {

        this.criteria = criteria;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        calcData();
    }

    // 게시글의 전체 갯수가 설정되는 시점에 calcData() 메서드를 호출하여 필요한 데이터들를 계산
    private void calcData() {

        endPage = (int)(Math.ceil(criteria.getPage() / (double) displayPageNum) * displayPageNum);
        startPage = (endPage - displayPageNum) + 1;

        int tempEndPage = (int)(Math.ceil(totalCount / (double) criteria.getPerPageNum()));

        if(endPage > tempEndPage) {
            endPage = tempEndPage;
        }

        prev = startPage == 1 ? false : true;
        next = endPage * criteria.getPerPageNum() >= totalCount ? false : true;
    }
}
