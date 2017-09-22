package com.krishiyog.managers;

import java.util.List;

import models.Crop;

import com.krishiyog.pojos.requests.CreateCropReq;
import com.krishiyog.pojos.requests.EditCropReq;
import com.krishiyog.pojos.requests.GetCropsReq;
import com.krishiyog.pojos.responses.CreateCropRes;
import com.krishiyog.pojos.responses.EditCropRes;
import com.krishiyog.pojos.responses.GetCropsRes;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class CropManager {

    public static CreateCropRes createCrop(CreateCropReq createCropReq) {
        CreateCropRes response = new CreateCropRes();
        Crop crop = Crop.create(createCropReq.season, createCropReq.cropName, createCropReq.year,
                createCropReq.fieldId);
        response.crop = crop;
        return response;
    }

    public static EditCropRes editCrop(EditCropReq editCropReq) {
        EditCropRes response = new EditCropRes();
        Crop crop = Crop.getCropById(editCropReq.id);
        crop.season = editCropReq.season;
        crop.cropName = editCropReq.cropName;
        crop.year = editCropReq.year;
        crop.fieldId = editCropReq.fieldId;
        Crop.getCrop().save(crop);
        response.crop = crop;
        return response;
    }

    public static GetCropsRes getCrops(GetCropsReq getCropsReq) {
        GetCropsRes response = new GetCropsRes();
        DBObject query = new BasicDBObject();
        if (getCropsReq.season != null) {
            query.put("season", getCropsReq.season);
        }
        if (getCropsReq.year != null) {
            query.put("year", getCropsReq.year);
        }
        List<Crop> crops = Crop.getCrop().find(query).toArray();
        response.crops = crops;
        return response;
    }
}
