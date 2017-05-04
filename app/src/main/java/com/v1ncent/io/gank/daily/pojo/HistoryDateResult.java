package com.v1ncent.io.gank.daily.pojo;

import java.util.List;

/**
 * Created by v1ncent on 2017/4/26.
 */

public class HistoryDateResult {

    /**
     * error : false
     * results : ["2017-04-25","2017-04-24","2017-04-21","2017-04-20"]
     */

    private boolean error;
    private List<String> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<String> getResults() {
        return results;
    }

    public void setResults(List<String> results) {
        this.results = results;
    }
}
