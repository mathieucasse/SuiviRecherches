package ch.matfly.suivirecherches.util;

import ch.matfly.suivirecherches.model.Role;

import java.util.ArrayList;
import java.util.List;

public class StaticLists {

    private StaticLists(){}
    private static List<String> approcheMedia  = new ArrayList<>();
    private static List<String> assignationOrp = new ArrayList<>();
    private static List<String> rechercheStatut = new ArrayList<>();
    private static List<String> tauxActivite = new ArrayList<>();
    private static List<Role> roles = new ArrayList<>();

    public static List<String> getAssignationOrp() {
        return assignationOrp;
    }

    public static List<String> getRechercheStatut() {
        return rechercheStatut;
    }

    public static List<String> getTauxActivite() {
        return tauxActivite;
    }

    public static List<Role> getRoles() {
        return roles;
    }

    public static List<String> getApprocheMedia() {
        return approcheMedia;
    }

    public static void setRoles(List<Role> roles) {
        StaticLists.roles = roles;
    }

    public static void setApprocheMedia(List<String> approcheMedia) {
        StaticLists.approcheMedia = approcheMedia;
    }

    public static void setAssignationOrp(List<String> assignationOrp) {
        StaticLists.assignationOrp = assignationOrp;
    }

    public static void setRechercheStatut(List<String> rechercheStatut) {
        StaticLists.rechercheStatut = rechercheStatut;
    }

    public static void setTauxActivite(List<String> tauxActivite) {
        StaticLists.tauxActivite = tauxActivite;
    }
}
