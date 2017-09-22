package com.krishiyog.managers;

import java.util.ArrayList;
import java.util.List;

import models.Crop;
import models.Field;

import com.krishiyog.pojos.requests.CreateFieldReq;
import com.krishiyog.pojos.requests.EditFieldReq;
import com.krishiyog.pojos.requests.GetCropsReq;
import com.krishiyog.pojos.requests.GetFieldsReq;
import com.krishiyog.pojos.responses.CreateFieldRes;
import com.krishiyog.pojos.responses.EditFieldRes;
import com.krishiyog.pojos.responses.GetCropsRes;
import com.krishiyog.pojos.responses.GetFieldsRes;

public class FieldManager {

    public static CreateFieldRes createField(CreateFieldReq createFieldReq) {
        CreateFieldRes response = new CreateFieldRes();
        Field field = Field.create(createFieldReq.farmerId, createFieldReq.farmId,
                createFieldReq.boundaryPoints);
        response.field = field;
        return response;
    }

    public static EditFieldRes editField(EditFieldReq editFieldReq) {
        EditFieldRes response = new EditFieldRes();
        Field field = Field.getFieldById(editFieldReq.id);
        field.boundaryPoints = field.boundaryPoints;
        Field.getField().save(field);
        response.field = field;
        return response;
    }

    public static GetFieldsRes getFields(GetFieldsReq getFieldsReq) {
        GetFieldsRes response = new GetFieldsRes();
        GetCropsReq getCropsReq = new GetCropsReq(getFieldsReq.season, getFieldsReq.year);
        GetCropsRes getCropsRes = CropManager.getCrops(getCropsReq);
        List<Crop> crops = getCropsRes.crops;
        List<Field> fields = new ArrayList<Field>();
        for (Crop crop : crops) {
            Field field = Field.getFieldById(crop.fieldId);
            fields.add(field);
        }
        response.fields = fields;
        return response;
    }

}
