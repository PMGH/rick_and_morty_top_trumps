package com.example.peter.toptrumps;

/**
 * Created by Peter on 10/11/2017.
 */

public class Card {

    private String name;
    private Integer intellect;
    private Integer lethality;
    private Integer morality;
    private Integer howSchwifty;
    private String catchphrase;
    private Integer imageSource;

    Card(String name, Integer intellect, Integer lethality, Integer morality, Integer howSchwifty, String catchphrase, Integer imageSource) {
        this.name = name;
        this.intellect = intellect;
        this.lethality = lethality;
        this.morality = morality;
        this.howSchwifty = howSchwifty;
        this.catchphrase = catchphrase;
        this.imageSource = imageSource;
    }

    public String getName() {
        return name;
    }

    public Integer getIntellect() {
        return intellect;
    }

    public Integer getLethality() {
        return lethality;
    }

    public Integer getMorality() {
        return morality;
    }

    public Integer getHowSchwifty() {
        return howSchwifty;
    }

    public String getCatchphrase() {
        return catchphrase;
    }

    public Integer getImageSource() {
        return imageSource;
    }

}
