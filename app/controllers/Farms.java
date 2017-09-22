package controllers;

import models.Farm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import com.krishiyog.managers.FarmManager;
import com.krishiyog.pojos.requests.CreateFarmReq;
import com.krishiyog.pojos.requests.EditFarmReq;
import com.krishiyog.pojos.requests.GetFarmsReq;
import com.krishiyog.pojos.responses.CreateFarmRes;
import com.krishiyog.pojos.responses.EditFarmRes;
import com.krishiyog.pojos.responses.GetFarmsRes;

public class Farms extends Controller {

    public static Result createFarm() {

        Form<CreateFarmReq> createFarmForm = Form.form(CreateFarmReq.class).bindFromRequest();
        if (createFarmForm.hasErrors()) {
            return ok("missing parameters");
        }
        CreateFarmReq createFarmReq = createFarmForm.get();
        CreateFarmRes createFarmRes = FarmManager.createFarm(createFarmReq);
        return ok(createFarmRes.toString());
    }

    public static Result editFarm() {
        Form<EditFarmReq> editFarmForm = Form.form(EditFarmReq.class).bindFromRequest();
        if (editFarmForm.hasErrors()) {
            return ok("missing parameters");
        }
        EditFarmReq editFarmReq = editFarmForm.get();
        EditFarmRes editFarmRes = FarmManager.editFarm(editFarmReq);
        return ok(editFarmRes.toString());
    }

    public static Result getFarms() {
        Form<GetFarmsReq> getFarmsForm = Form.form(GetFarmsReq.class).bindFromRequest();
        if (getFarmsForm.hasErrors()) {
            return ok("missing parameters");
        }
        GetFarmsReq getFarmsReq = getFarmsForm.get();
        GetFarmsRes getFarmsRes = FarmManager.getFarms(getFarmsReq);
        return ok(getFarmsRes.toString());
    }

    public static Result deleteFarm(String id) {
        Farm.delete(id);
        return ok("Successfully deleted Farm");
    }
}
