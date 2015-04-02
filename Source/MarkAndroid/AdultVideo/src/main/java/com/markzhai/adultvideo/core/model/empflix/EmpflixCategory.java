package com.markzhai.adultvideo.core.model.empflix;

import com.markzhai.adultvideo.R;

/**
 * Created by marktlzhai on 2015/4/1.
 */
public enum EmpflixCategory {
    Amateur("/categories/new-amateur-{0}.html", R.string.c_Amateur),
    Anal("/categories/new-anal-{0}.html", R.string.c_Anal),
    ArabSex("/categories/new-arab-{0}.html", R.string.c_Arab),
    Asian("/categories/new-asian-{0}.html", R.string.c_Asian),
    Ass("/categories/new-ass-{0}.html", R.string.c_Ass),
    Babes("/categories/new-babe-{0}.html", R.string.c_Babes),
    BBW("/categories/new-bbw-{0}.html", R.string.c_BBW),
    BigBooks("/categories/new-bigboobs-{0}.html", R.string.c_Boobs),
    BigCock("/categories/new-bigcock-{0}.html", R.string.c_Cock),
    Bizarre("/categories/new-bizarre-{0}.html", R.string.c_Bizarre),
    Blonde("/categories/new-blonde-{0}.html", R.string.c_Blonde),
    Blowjobs_OralSex("/categories/new-blowjobs-{0}.html", R.string.c_Blowjobs),
    Brunette("/categories/new-brunette-{0}.html", R.string.c_Brunette),
    CartoonSex("/categories/new-cartoon-{0}.html", R.string.c_Cartoon),
    Celebrity("/categories/new-celebrity-{0}.html", R.string.c_Celebrity),
    Classic("/categories/new-classic-{0}.html", R.string.c_Classic),
    ClubChix("/categories/new-clubchix-{0}.html", R.string.c_Club),
    Creampie("/categories/new-creampie-{0}.html", R.string.c_Creampie),
    Cumshots("/categories/new-cumshots-{0}.html", R.string.c_Cumshots),
    DoublePenetration("/categories/new-dp-{0}.html", R.string.c_Double),
    Ebony("/categories/new-ebony-{0}.html", R.string.c_Ebony),
    EuroPorn("/categories/new-euro-{0}.html", R.string.c_Euro),
    Extreme("/categories/new-extreme-{0}.html", R.string.c_Extreme),
    FacialCumShots("/categories/new-facial-{0}.html", R.string.c_Facial),
    Fat("/categories/new-fat-{0}.html", R.string.c_Fat),
    FetishSex("/categories/new-fetish-{0}.html", R.string.c_Fetish),
    Fisting("/categories/new-fisting-{0}.html", R.string.c_Fisting),
    Flashing("/categories/new-flashing-{0}.html", R.string.c_Flashing),
    FootFetish("/categories/new-foot-{0}.html", R.string.c_Foot),
    Funny("/categories/new-funny-{0}.html", R.string.c_Funny),
    GangBang("/categories/new-gangbang-{0}.html", R.string.c_Gang),
    Gay_BiMale("/categories/new-gay-{0}.html", R.string.c_Gay),
    GermanPorn("/categories/new-german-{0}.html", R.string.c_German),
    Gothic("/categories/new-gothic-{0}.html", R.string.c_Gothic),
    Granny("/categories/new-granny-{0}.html", R.string.c_Granny),
    GroupSex("/categories/new-groupsex-{0}.html", R.string.c_Group),
    Hairy("/categories/new-hairy-{0}.html", R.string.c_Hairy),
    HardcorePorn("/categories/new-hardcore-{0}.html", R.string.c_Hardcore),
    Hentai("/categories/new-hentai-{0}.html", R.string.c_Hentai),
    HomeMade("/categories/new-homemade-{0}.html", R.string.c_Home),
    Indian("/categories/new-indian-{0}.html", R.string.c_Indian),
    Insertions("/categories/new-insertions-{0}.html", R.string.c_Insertions),
    Interracial("/categories/new-interracial-{0}.html", R.string.c_Interracial),
    JapanesePorn("/categories/new-japanese-{0}.html", R.string.c_Japanese),
    Latinas("/categories/new-latinas-{0}.html", R.string.c_Latinas),
    Lesbian("/categories/new-lesbian-{0}.html", R.string.c_Lesbian),
    Mature("/categories/new-mature-{0}.html", R.string.c_Mature),
    Milf("/categories/new-milf-{0}.html", R.string.c_MILF),
    Petite("/categories/new-petite-{0}.html", R.string.c_Petite),
    Piss("/categories/new-piss-{0}.html", R.string.c_Piss),
    PornStars("/categories/new-pornstars-{0}.html", R.string.c_Stars),
    POV("/categories/new-pov-{0}.html", R.string.c_POV),
    Pregnant("/categories/new-pregnant-{0}.html", R.string.c_Pregnant),
    Public_OutDoor("/categories/new-outdoor-{0}.html", R.string.c_Door),
    Redhead("/categories/new-redhead-{0}.html", R.string.c_Redhead),
    SexToys("/categories/new-toys-{0}.html", R.string.c_Toys),
    Shemale_Trans("/categories/new-shemale-{0}.html", R.string.c_Shemale),
    Softcore("/categories/new-softcore-{0}.html", R.string.c_Softcore),
    Solo("/categories/new-solo-{0}.html", R.string.c_Solo),
    Spanking("/categories/new-spanking-{0}.html", R.string.c_Spanking),
    Squirting("/categories/new-squirting-{0}.html", R.string.c_Squirting),
    Storyline("/categories/new-storyline-{0}.html", R.string.c_Storyline),
    Swingers("/categories/new-swingers-{0}.html", R.string.c_Swingers),
    Teens18Plus("/categories/new-teens-{0}.html", R.string.c_Teens),
    UKDogging("/categories/new-dogging-{0}.html", R.string.c_Dogging),
    UpskirtDownBlouse("/categories/new-upskirt-{0}.html", R.string.c_Upskirt),
    Voyeurism("/categories/new-voyeur-{0}.html", R.string.c_Voyeurism);

    private String url;

    private int nameRes;

    private EmpflixCategory(String url, int nameRes) {
        this.url = url;
        this.nameRes = nameRes;
    }

    public String getUrl() {
        return url;
    }

    public int getNameRes() {
        return nameRes;
    }
}
