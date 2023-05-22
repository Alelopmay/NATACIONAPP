package org.example.Domain;

public class TrialSwimmer extends Trial {
    private pool_type Pool_Type;
    private org.example.Domain.Meters Meters;
    private style Style;

    public TrialSwimmer(int id, SEX sex, category category, pool_type pool_Type, org.example.Domain.Meters meters, style style) {
        super(id, sex, category);
        Pool_Type = pool_Type;
        Meters = meters;
        Style = style;
    }

    public TrialSwimmer() {

    }

    public pool_type getPool_Type() {
        return Pool_Type;
    }

    public void setPool_Type(pool_type pool_Type) {
        Pool_Type = pool_Type;
    }

    public org.example.Domain.Meters getMeters() {
        return Meters;
    }

    public void setMeters(org.example.Domain.Meters meters) {
        Meters = meters;
    }

    public style getStyle() {
        return Style;
    }

    public void setStyle(style style) {
        Style = style;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Pool_Type=" + Pool_Type +
                ", Meters=" + Meters +
                ", Style=" + Style +
                '}';
    }
}
