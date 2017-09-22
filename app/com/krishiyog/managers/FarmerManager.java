package com.krishiyog.managers;

import java.util.List;

import models.Farmer;

import com.krishiyog.pojos.requests.FarmersCreateReq;
import com.krishiyog.pojos.requests.GerFarmersReq;
import com.krishiyog.pojos.responses.FarmersCreateRes;
import com.krishiyog.pojos.responses.GetFarmersRes;

public class FarmerManager {

    public static FarmersCreateRes createFarmer(FarmersCreateReq createFarmerRequest) {
        Farmer farmer = new Farmer(createFarmerRequest.name);
        Farmer.create(farmer);
        FarmersCreateRes response = new FarmersCreateRes();
        response.farmer = farmer;
        return response;
    }

    public static GetFarmersRes getFarmers(GerFarmersReq getFarmersReq) {
        GetFarmersRes response = new GetFarmersRes();
        List<Farmer> farmers = Farmer.all();
        response.farmers = farmers;
        return response;
    }

}
