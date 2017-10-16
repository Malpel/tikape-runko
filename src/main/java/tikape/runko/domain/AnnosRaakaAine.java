package tikape.runko.domain;

public class AnnosRaakaAine {

    private Integer raakaAineId;
    private Integer AnnosId;
    private Integer jarjestys;
    private Integer maara;
    private String ohje;

    public AnnosRaakaAine(Integer AnnosId, Integer raakaAineId, Integer jarjestys, Integer maara, String ohje) {
        this.raakaAineId = raakaAineId;
        this.AnnosId = AnnosId;
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

    public Integer getJarjestys() {
        return jarjestys;
    }

    public void setJarjestys(Integer jarjestys) {
        this.jarjestys = jarjestys;
    }

    public Integer getMaara() {
        return maara;
    }

    public void setMaara(Integer maara) {
        this.maara = maara;
    }

    public String getOhje() {
        return ohje;
    }

    public void setOhje(String ohje) {
        this.ohje = ohje;
    }

}
