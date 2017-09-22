package com.krishiyog.pojos.requests;

import com.krishiyog.enums.Seasons;

public class GetCropsReq {

    public Seasons season;
    public String  year;

    public GetCropsReq() {
        super();
    }

    public GetCropsReq(Seasons season, String year) {
        super();
        this.season = season;
        this.year = year;
    }

}
