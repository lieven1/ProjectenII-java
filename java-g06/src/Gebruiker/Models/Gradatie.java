package Gebruiker.Models;

public enum Gradatie {
    // Kyu
    RokkuKyu(1),
    GoKyu(3),
    YonKyu(3),
    SanKyu(4),
    NiKyu(5),
    IchiKyu(6),
    // Dan
    ShoDan(7),
    NiDan(8),
    SanDan(9),
    YonDan(10),
    GoDan(11),
    RokkuDan(12),
    ShichiDan(13),
    HachiDan(14),
    KuDan(15),
    JuDan(16),
    JuichiDan(17),
    JuniDan(18);

    private final int val;

    private Gradatie(int val) {
        this.val = val;
    }
}
