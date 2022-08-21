package bardmod.bard.characters;

import bardmod.BardMod;
import bardmod.bard.BardColor;
import bardmod.bard.cards.*;
import bardmod.bard.relics.SevenStringLute;

import java.util.ArrayList;

import basemod.abstracts.CustomPlayer;
import basemod.animations.SpineAnimation;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.FontHelper;

public class TheBard extends CustomPlayer {
    public static final int ENERGY_PER_TURN = 3;
    public static final String MY_CHARACTER_SHOULDER_2 = BardMod.makePath("char/shoulder2"); // campfire pose
    public static final String MY_CHARACTER_SHOULDER_1 = BardMod.makePath("char/shoulder"); // another campfire pose
    public static final String MY_CHARACTER_CORPSE = BardMod.makePath("char/corpse"); // dead corpse

    public static final String[] ORB_TEXTURES = {BardMod.makePath("orb/bard_orb")};
    public static final String ORB_VFX = BardMod.makePath("orb/bard_orb_vfx.png");

    public TheBard(String name, PlayerClass setClass) {
        super(name, setClass, ORB_TEXTURES, ORB_VFX, new SpineAnimation(BardMod.makePath("char/skeleton.atlas"), BardMod.makePath("char/skeleton.json"), 1.0f));

        this.dialogX = (this.drawX + 0.0F * Settings.scale); // set location for text bubbles
        this.dialogY = (this.drawY + 220.0F * Settings.scale); // you can just copy these values

        initializeClass(
                null,
                MY_CHARACTER_SHOULDER_2,
                MY_CHARACTER_SHOULDER_1,
                MY_CHARACTER_CORPSE,
                getLoadout(),
                20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN));

        //loadAnimation(MY_CHARACTER_SKELETON_ATLAS, MY_CHARACTER_SKELETON_JSON, 1.0F); // if you're using modified versions of base game animations or made animations in spine make sure to include this bit and the following lines
        //AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
        //e.setTimeScale(0.6F);
    }

    @Override
    public String getPortraitImageName() { return BardMod.makePath("ui/bardPortrait"); }

    public ArrayList<String> getStartingDeck() { // starting deck 'nuff said
        ArrayList<String> startingDeck = new ArrayList<>();
        startingDeck.add(Strike_BARD.ID);
        startingDeck.add(Strike_BARD.ID);
        startingDeck.add(Strike_BARD.ID);
        startingDeck.add(Strike_BARD.ID);
        startingDeck.add(Defend_BARD.ID);
        startingDeck.add(Defend_BARD.ID);
        startingDeck.add(Defend_BARD.ID);
        startingDeck.add(Defend_BARD.ID);
        startingDeck.add(ASharp.ID);
        startingDeck.add(BFlat.ID);
        startingDeck.add(CMajor.ID);
        startingDeck.add(UpbeatBeatup.ID);
        return startingDeck;
    }

    public ArrayList<String> getStartingRelics() { // starting relics - also simple
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(SevenStringLute.ID);
        UnlockTracker.markRelicAsSeen(SevenStringLute.ID);
        return retVal;
    }

    public static final int STARTING_HP = 75;
    public static final int MAX_HP = 75;
    public static final int STARTING_GOLD = 99;
    public static final int HAND_SIZE = 5;

    public CharSelectInfo getLoadout() { // the rest of the character loadout so includes your character select screen info plus hp and starting gold
        return new CharSelectInfo("Bard", "The Bard is a musician, using the power of emotion and song to do battle.",
                STARTING_HP, MAX_HP, 0, STARTING_GOLD + 50, HAND_SIZE,
                this, getStartingRelics(), getStartingDeck(), false);
    }

    @Override
    public String getTitle(PlayerClass playerClass) {
        return "The Bard";
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return BardColor.BARD_ORANGE;
    }

    @Override
    public Color getCardRenderColor() {
        return BardColor.ORANGE_COLOR;
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return new ASharp();
    }

    @Override
    public Color getCardTrailColor() {
        return BardColor.ORANGE_COLOR;
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 7;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("UNLOCK_PING", MathUtils.random(-0.2F, 0.2F));
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT,
                false);
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "UNLOCK_PING";
    }

    @Override
    public String getLocalizedCharacterName() {
        return "The Bard";
    }

    @Override
    public AbstractPlayer newInstance() {
        return new TheBard(name, chosenClass);
    }

    @Override
    public String getSpireHeartText() {
        return "bard heart text here";
    }

    @Override
    public Color getSlashAttackColor() {
        return BardColor.ORANGE_COLOR;
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{
                AbstractGameAction.AttackEffect.SLASH_DIAGONAL,
                AbstractGameAction.AttackEffect.LIGHTNING,
                AbstractGameAction.AttackEffect.SLASH_HORIZONTAL
        };
    }

    @Override
    public String getVampireText() {
        return "bard vampire text here";
    }

}