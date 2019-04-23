package fr.emse.ai.csp.core;

/**
 * Cette classe permet de créer un objet qui contient à la fois la valeur et les disponibilités assiociées
 */
public class ValueDisponibility {

    public double disponibility;
    public Object value;

    public ValueDisponibility(double disponibility, Object value) {
        this.disponibility = disponibility;
        this.value = value;
    }

    public double getDisponibility() {
        return disponibility;
    }

    public Object getValue() {
        return value;
    }

    public void setDisponibility(double disponibility) {
        this.disponibility = disponibility;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
