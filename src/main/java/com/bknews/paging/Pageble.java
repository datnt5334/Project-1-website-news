package com.bknews.paging;

import com.bknews.sort.Sorter;

public interface Pageble {
    Integer getPage();
    Integer getOffSet();
    Integer getLimit();
    Sorter getSorter();
}
