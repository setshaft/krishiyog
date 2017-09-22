package com.krishiyog.managers;

import java.util.ArrayList;
import java.util.List;

import models.Farm;
import models.Field;

import com.krishiyog.pojos.requests.CreateFarmReq;
import com.krishiyog.pojos.requests.EditFarmReq;
import com.krishiyog.pojos.requests.GetFarmsReq;
import com.krishiyog.pojos.requests.GetFieldsReq;
import com.krishiyog.pojos.responses.CreateFarmRes;
import com.krishiyog.pojos.responses.EditFarmRes;
import com.krishiyog.pojos.responses.GetFarmsRes;
import com.krishiyog.pojos.responses.GetFieldsRes;

public class FarmManager {

    public static CreateFarmRes createFarm(CreateFarmReq createFarmReq) {
        CreateFarmRes response = new CreateFarmRes();
        Farm farm = Farm.create(createFarmReq.farmerId, createFarmReq.boundaryPoints);
        response.farm = farm;
        return response;
    }

    public static EditFarmRes editFarm(EditFarmReq editFarmReq) {
        EditFarmRes response = new EditFarmRes();
        Farm farm = Farm.getFarmById(editFarmReq.id);
        farm.boundaryPoints = editFarmReq.boundaryPoints;
        Farm.getFarm().save(farm);
        response.farm = farm;
        return response;
    }

    public static GetFarmsRes getFarms(GetFarmsReq getFarmsReq) {
        GetFarmsRes response = new GetFarmsRes();

        GetFieldsReq getFieldsReq = new GetFieldsReq(getFarmsReq.season, getFarmsReq.year);
        GetFieldsRes getFieldsRes = FieldManager.getFields(getFieldsReq);
        List<Field> fields = getFieldsRes.fields;
        List<Farm> farms = new ArrayList<Farm>();
        for (Field field : fields) {
            Farm farm = Farm.getFarmById(field.farmId);
            farms.add(farm);
        }
        response.farms = farms;
        return response;
    }
}
