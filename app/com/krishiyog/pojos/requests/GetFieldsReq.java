package com.krishiyog.pojos.requests;

import com.krishiyog.enums.Seasons;

public class GetFieldsReq {

    public Seasons season;
    public String  year;

    public GetFieldsReq() {
        super();
    }

    public GetFieldsReq(Seasons season, String year) {
        super();
        this.season = season;
        this.year = year;
    }

}
