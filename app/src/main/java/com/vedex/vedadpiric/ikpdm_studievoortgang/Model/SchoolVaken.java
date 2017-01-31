package com.vedex.vedadpiric.ikpdm_studievoortgang.Model;

/**
 * Created by vedadpiric on 04-01-17.
 */

public class SchoolVaken {
    private String moduleNaam;

    private int ecs;
    private double cijfer;

    public SchoolVaken(String moduleNaam, int ecs, double cijfer){

        this.moduleNaam = moduleNaam;
        this.ecs = ecs;
        this.cijfer = cijfer;

    }


    public double getCijfer() {
        return cijfer;
    }

    public void setCijfer(double cijfer) {
        this.cijfer = cijfer;
    }

    public String getModuleNaam() {
        return moduleNaam;
    }

    public void setModuleNaam(String moduleNaam) {
        this.moduleNaam = moduleNaam;
    }

    public int getEcs() {
        return ecs;
    }

    public void setEcs(int ecs) {
        this.ecs = ecs;
    }





}
