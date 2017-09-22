package controllers;

import models.Crop;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import com.krishiyog.managers.CropManager;
import com.krishiyog.pojos.requests.CreateCropReq;
import com.krishiyog.pojos.requests.EditCropReq;
import com.krishiyog.pojos.requests.GetCropsReq;
import com.krishiyog.pojos.responses.CreateCropRes;
import com.krishiyog.pojos.responses.EditCropRes;
import com.krishiyog.pojos.responses.GetCropsRes;

public class Crops extends Controller {

    public static Result createCrop() {

        Form<CreateCropReq> createCropForm = Form.form(CreateCropReq.class).bindFromRequest();
        if (createCropForm.hasErrors()) {
            return ok("missing parameters");
        }
        CreateCropReq createCropReq = createCropForm.get();
        CreateCropRes createCropRes = CropManager.createCrop(createCropReq);
        return ok(createCropRes.toString());
    }

    public static Result editCrop() {
        Form<EditCropReq> editCropForm = Form.form(EditCropReq.class).bindFromRequest();
        if (editCropForm.hasErrors()) {
            return ok("missing parameters");
        }
        EditCropReq editCropReq = editCropForm.get();
        EditCropRes editCropRes = CropManager.editCrop(editCropReq);
        return ok(editCropRes.toString());
    }

    public static Result deleteCrop(String id) {
        Crop.delete(id);
        return ok("Successfully deleted Crop");
    }

    public static Result getCrops() {
        Form<GetCropsReq> getCropsForm = Form.form(GetCropsReq.class).bindFromRequest();
        if (getCropsForm.hasErrors()) {
            return ok("missing parameters");
        }
        GetCropsReq getCropsReq = getCropsForm.get();
        GetCropsRes getCropsRes = CropManager.getCrops(getCropsReq);
        return ok(getCropsRes.toString());
    }
}
