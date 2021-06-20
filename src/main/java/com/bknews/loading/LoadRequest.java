package com.bknews.loading;

import com.bknews.sort.Sorter;

public class LoadRequest implements Loadable{

    private Integer offset;
    private Integer limit;
    private Sorter sorter;

    public LoadRequest(Integer offset, Integer limit, Sorter sorter) {
        this.offset = offset;
        this.limit = limit;
        this.sorter = sorter;
    }

    @Override
    public Integer getOffset() {
        return this.offset;
    }

    @Override
    public Integer getLimit() {
        return this.limit;
    }

    @Override
    public Sorter getSorter() {
        if (this.sorter != null) {
            return this.sorter;
        }
        return null;
    }
}
