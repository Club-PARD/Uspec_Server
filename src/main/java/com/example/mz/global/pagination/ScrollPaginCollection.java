package com.example.mz.global.pagination;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ScrollPaginCollection <T>{
    private final List<T> currentCursorItemsWithNextCursor; //현재 스크롤 아이템들 + 다음 아이템 1개(다음 아이템이 있는지 확인위해)
    private final int countPerScroll; //scroll 한번 할 때 가져오는 item 갯수

    public static <T> ScrollPaginCollection<T> of(List<T> currentCursorItemsWithNextCursor,int countPerScroll){
        return new ScrollPaginCollection<>(currentCursorItemsWithNextCursor, countPerScroll);
    }

    public boolean isLastScroll(){
//        현재 페이지 아이템 갯수 + 다음거 1개 해서 10개보다 작으면 마지막 페이지라 판단
        return this.currentCursorItemsWithNextCursor.size() <= countPerScroll;
    }

    public List<T> getCurrentItemsList(){
        if(isLastScroll()){ //마지막이면 지금 페이지 아이템 reutrn
            return this.currentCursorItemsWithNextCursor;
        }
//        마지막이 아니면 마지막 아이템 제외하고 10개 return
        return this.currentCursorItemsWithNextCursor.subList(0, countPerScroll);
    }

//    현재 스크롤 중 마지막 아이템을 cursor로 사용
    public T getNextCursor(){
        return currentCursorItemsWithNextCursor.get(countPerScroll - 1);
    }
}
