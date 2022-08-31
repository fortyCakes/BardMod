package bardmod;

import bardmod.bard.BardColor;
import bardmod.bard.BardEnum;
import bardmod.bard.ChordHelper;
import bardmod.bard.ScaleHelper;
import bardmod.bard.cards.Strike_BARD;
import bardmod.bard.characters.TheBard;
import bardmod.bard.relics.SevenStringLute;
import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.abstracts.CustomRelic;
import basemod.interfaces.*;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

@SpireInitializer
public class BardMod implements EditCharactersSubscriber, EditCardsSubscriber, EditStringsSubscriber, EditRelicsSubscriber, EditKeywordsSubscriber, OnCardUseSubscriber {
    private static final String MOD_ASSETS_FOLDER = "img";
    private static final String MOD_ID = "BardClassModId";

    public BardMod()    {
        BaseMod.subscribe(this);

        BaseMod.addColor(
                BardColor.BARD_ORANGE,
                BardColor.ORANGE_COLOR,
                makePath("cardui/512/bard_attack"),
                makePath("cardui/512/bard_skill"),
                makePath("cardui/512/bard_power"),
                makePath("cardui/512/card_bard_orb"),
                makePath("cardui/1024/bard_attack"),
                makePath("cardui/1024/bard_skill"),
                makePath("cardui/1024/bard_power"),
                makePath("cardui/1024/card_bard_orb"),
                makePath("cardui/512/card_bard_small_orb")
        );
    }

    public static void initialize(){

        @SuppressWarnings("unused")
        BardMod modInitializer = new BardMod();

    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(
                new TheBard("the Bard", BardEnum.BARD_CLASS),
                makePath("ui/bardButton"),
                makePath("ui/bardPortrait"),
                BardEnum.BARD_CLASS);
    }

    @Override
    public void receiveEditCards() {
        new AutoAdd(MOD_ID)
                .packageFilter(Strike_BARD.class)
                .setDefaultSeen(true)
                .cards();
    }

    @Override
    public void receiveEditStrings() {
        // RelicStrings
        BaseMod.loadCustomStringsFile(RelicStrings.class, "localization/Bard-RelicStrings.json");
        // CardStrings
        BaseMod.loadCustomStringsFile(CardStrings.class, "localization/Bard-CardStrings.json");

    }

    @Override
    public void receiveEditRelics() {
        new AutoAdd(MOD_ID)
                .packageFilter(SevenStringLute.class)
                .any(CustomRelic.class, (info, relic) -> {
                    BaseMod.addRelicToCustomPool(relic, BardColor.BARD_ORANGE);
                    if (info.seen) {
                        UnlockTracker.markRelicAsSeen(relic.relicId);
                    }
                });
    }

    @Override
    public void receiveEditKeywords(){
        BaseMod.addKeyword(BardMod.MOD_ID, "Chord", new String[]{"Chord", "Note A", "Note B", "Note C"}, "When cards with Note A, B and C have all been played, they are all played again.");
        BaseMod.addKeyword(BardMod.MOD_ID, "Happy", new String[]{"Happy"}, "Happy increases your damage, and decreases each turn.");
        BaseMod.addKeyword(BardMod.MOD_ID, "Sadness", new String[]{"Sadness"}, "Sadness decreases the damage enemies deal, and decreases each turn.");
        BaseMod.addKeyword(BardMod.MOD_ID, "Scale", new String[] {"Scale"}, "Determines the power of Scale cards. Gain Scale when you play a card that costs 1 more or less than your last card; otherwise, your Scale resets to 1.");
    }

    @Override
    public void receiveCardUsed(AbstractCard abstractCard) {
        ChordHelper.receiveCardUsed(abstractCard);
        ScaleHelper.receiveCardUsed(abstractCard);
    }

    public static final String makeCardImagePath(String cardName) {
        return makePath("cards/" + cardName);
    }

    public static final String makeRelicImagePath(String power) {
        return makePath("relics/" + power);
    }

    public static final String makePowerImagePath(String power) {
        return makePath("powers/" + power);
    }

    public static final String makePath(String resource) {
        if (resource.startsWith("/"))
        {
            resource = resource.substring(1); // If the resource starts with '/', strip it off.
        }

        String result = MOD_ASSETS_FOLDER + "/bard/" + resource;

        if (!hasExtension(resource)) {
            result += ".png";
        }

        return result;
    }
    private static boolean hasExtension(String filename) {
        return filename.lastIndexOf('.') > 0;
    }
}
