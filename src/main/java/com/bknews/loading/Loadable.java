package com.bknews.loading;

import com.bknews.sort.Sorter;

public interface Loadable {
    Integer getOffset();
    Integer getLimit();
    Sorter getSorter();
}
