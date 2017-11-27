package com.example.peter.toptrumps.Objects;

import com.example.peter.toptrumps.R;

/**
 * Created by Peter on 10/11/2017.
 */

public enum Character {

    RICK("Rick Sanchez", 95, 80, 40, 9, "WUBBA LUBBA DUB DUB", R.drawable.rick_sanchez),
    MORTY("Morty Smith", 45, 60, 78, 8, "Ohh, man! Ohh, ohhh geez!!", R.drawable.morty_smith),
    BETH("Beth Smith", 62, 40, 60, 4, "I WILL REACH INTO HEAVEN AND YANK YOUR SCREAMING DEER SOUL BACK!", R.drawable.beth_smith),
    JERRY("Jerry Smith", 34, 46, 65, 3, "[crying] I'm a parasite! ", R.drawable.jerry_smith),
    SUMMER("Summer Smith", 47, 20, 63, 4, "Boo-ya", R.drawable.summer_smith),
    BIRDPERSON("Birdperson", 71, 50, 80, 7, "In bird culture, this is considered a d*ck move.", R.drawable.birdperson),
    SCREAMINGSUN("Screaming Sun", 1, 15, 50, 4, "AaaOOaaOOOOAAaaaaaaHHHH", R.drawable.screaming_sun),
    KROMBOPULOUSMICHAEL("Krombopulous Michael", 58, 92, 10, 8, "Here I go, killing again!", R.drawable.krombopulous_michael),
    SCARYTERRY("Scary Terry", 30, 80, 28, 5, "You can run, but you can't hide, B#TCH!", R.drawable.scary_terry),
    UNITY("Unity", 84, 70, 55, 7, "In the news today, this looks a lot worse than it is. We’re… really just having a good time.", R.drawable.unity),
    ABRADOLFLINCLER("Abradolf Lincler", 71, 62, 50, 5, "Prepare to be emancipated from your own inferior genes!", R.drawable.abradolf_lincler),
    DRXENONBLOOM("Dr. Xenon Bloom", 87, 5 ,41, 2, "The digestive tract is the evacuation route, get it?", R.drawable.dr_xenon_bloom),
    ZEEP("Zeep Xanflorp", 90, 61, 40, 3, "I dropped out of school, its not a place for smart people.", R.drawable.zeep_xanflorp),
    GOLDENCROMULON("Golden Cromulon", 75, 78, 15, 10, "SHOW ME WHAT YOU GOT!", R.drawable.golden_cromulon),
    MRMEESEEKS("Mr. Meeseeks", 40, 53, 36, 7, "I'm Mr. Meeseeks look at me!", R.drawable.mr_meeseeks),
    EVILMORTY("Evil Morty", 65, 80, 18, 8, "This seems like a good time for a drink, and a cold calculated speech with sinister overtones. A speech about politics, about order, brotherhood, power. But speeches are for campaigning. Now is the time for action.", R.drawable.evil_morty);

    String name;
    int intellect;
    int lethality;
    int morality;
    int howSchwifty;
    String catchphrase;
    int imageSource;

    Character(String name, int intellect, int lethality, int morality, int howSchwifty, String catchphrase, int imageSource) {
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

    public int getIntellect() {
        return intellect;
    }

    public int getLethality() {
        return lethality;
    }

    public int getMorality() {
        return morality;
    }

    public int getHowSchwifty() {
        return howSchwifty;
    }

    public String getCatchphrase() {
        return catchphrase;
    }

    public int getImageSource() {
        return imageSource;
    }
}
