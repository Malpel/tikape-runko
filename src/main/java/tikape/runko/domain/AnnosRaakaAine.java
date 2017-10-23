package tikape.runko.domain;

public class AnnosRaakaAine {
    
    private Integer AnnosId;
    private Integer raakaAineId;
    private String jarjestys;
    private String maara;
    private String ohje;

    public AnnosRaakaAine(Integer AnnosId, Integer raakaAineId, String jarjestys, String maara, String ohje) {
        this.AnnosId = AnnosId;
        this.raakaAineId = raakaAineId;      
        this.jarjestys = jarjestys;
        this.maara = maara;
        this.ohje = ohje;
    }

    public Integer getRaakaAineId() {
        return raakaAineId;
    }

    public void setRaakaAineId(Integer raakaAineId) {
        this.raakaAineId = raakaAineId;
    }

    public Integer getAnnosId() {
        return AnnosId;
    }

    public void setAnnosId(Integer AnnosId) {
        this.AnnosId = AnnosId;
    }

    public String getJarjestys() {
        return jarjestys;
    }

    public void setJarjestys(String jarjestys) {
        this.jarjestys = jarjestys;
    }

    public String getMaara() {
        return maara;
    }

    public void setMaara(String maara) {
        this.maara = maara;
    }

    public String getOhje() {
        return ohje;
    }

    public void setOhje(String ohje) {
        this.ohje = ohje;
    }

}
