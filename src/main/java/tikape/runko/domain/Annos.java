package tikape.runko.domain;

public class Annos implements Comparable<Annos> {

    private Integer id;
    private String nimi;

    public Annos(Integer id, String nimi) {
        this.id = id;
        this.nimi = nimi;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    @Override
    public int compareTo(Annos t) {
        return this.nimi.compareTo(t.getNimi());
    }

}
