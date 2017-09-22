package com.krishiyog.pojos.responses;

import java.util.List;

import models.Farmer;

public class GetFarmersRes {
    public List<Farmer> farmers;

    @Override
    public String toString() {
        return "GerFarmersRes [farmers=" + farmers + "]";
    }
}
