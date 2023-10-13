package com.example.retrofit.data;

public class Company {
    private String name;
    private String catchPhase;
    private String bs;
    private Geo geo;

    public Company(String name, String catchPhase, String bs, Geo geo) {
        this.name = name;
        this.catchPhase = catchPhase;
        this.bs = bs;
        this.geo = geo;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatchPhase() {
        return catchPhase;
    }

    public void setCatchPhase(String catchPhase) {
        this.catchPhase = catchPhase;
    }

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }

    public Geo getGeo() {
        return geo;
    }

    public void setGeo(Geo geo) {
        this.geo = geo;
    }


}
