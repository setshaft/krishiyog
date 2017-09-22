package controllers;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import com.krishiyog.managers.FarmerManager;
import com.krishiyog.pojos.requests.FarmersCreateReq;
import com.krishiyog.pojos.requests.GerFarmersReq;
import com.krishiyog.pojos.responses.FarmersCreateRes;
import com.krishiyog.pojos.responses.GetFarmersRes;

public class Farmers extends Controller {

    public static Result index() {
        return ok("krishiyog is ready.");
    }

    public static Result createFarmer() {

        Form<FarmersCreateReq> createFarmersForm = Form.form(FarmersCreateReq.class)
                .bindFromRequest();
        if (createFarmersForm.hasErrors()) {
            return ok("missing parameters");
        }
        FarmersCreateReq createFarmersReq = createFarmersForm.get();
        FarmersCreateRes createFarmersRes = FarmerManager.createFarmer(createFarmersReq);
        return ok(createFarmersRes.toString());
    }

    public static Result getFarmers() {

        Form<GerFarmersReq> getFarmersForm = Form.form(GerFarmersReq.class).bindFromRequest();
        if (getFarmersForm.hasErrors()) {
            return ok("missing parameters");
        }
        GerFarmersReq getFarmersReq = getFarmersForm.get();
        GetFarmersRes getFarmersRes = FarmerManager.getFarmers(getFarmersReq);
        return ok(getFarmersRes.toString());
    }
}
