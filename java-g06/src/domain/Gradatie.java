/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Steve
 */
public enum Gradatie {
// Kyu
    RokkuKyu("Rokku Kyu",1),
    GoKyu("Go Kyu",2),
    YonKyu("Yon Kyu",3),
    SanKyu("San Kyu",4),
    NiKyu("Ni Kyu",5),
    IchiKyu("Ichi Kyu",6),

//Dan
    ShoDan("Sho Dan",7),
    NiDan("Ni Dan",8),
    SanDan("San Dan",9),
    YonDan("Yon Dan",10),
    GoDan("Go Dan",11),
    RokkuDan("Rokku Dan",12),
    ShichiDan("Shichi Dan",13),
    HachiDan("Hachi Dan",14),
    KuDan("Ku Dan",15),
    JuDan("Ju Dan",16),
    JuichiDan("Juichi Dan",17),
    JuniDan("Juni Dan",18);
    
    private String naam;
    private int value;

    private Gradatie(String naam, int value) {
        this.naam = naam;
        this.value = value;
    }

    public String getNaam() {
        return naam;
    }

    public int getValue() {
        return value;
    }
    
    

    
}
