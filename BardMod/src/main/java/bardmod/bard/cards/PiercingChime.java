package bardmod.bard.cards;

import bardmod.BardMod;
import bardmod.bard.BardCardTags;
import bardmod.bard.BardColor;
import bardmod.bard.ScaleHelper;
import bardmod.bard.powers.SadnessPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;

public class PiercingChime extends CustomCard {
    public static final String ID = "PiercingChime";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    private static final int SCALING_FACTOR = 3;
    private static final int UPGRADE_MINUS_SCALING_FACTOR = -1;

    public PiercingChime() {
        super(ID, NAME, BardMod.makeCardImagePath(ID), COST, DESCRIPTION, CardType.SKILL, BardColor.BARD_ORANGE, CardRarity.UNCOMMON, CardTarget.NONE);
        tags.add(BardCardTags.NOTE_B);
        this.baseMagicNumber = SCALING_FACTOR;
        this.magicNumber = baseMagicNumber;
    }

    public void triggerOnGlowCheck() {
        ScaleHelper.GlowCheck(this);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int amount = ScaleHelper.ScaleAmount() / magicNumber;

        addToBot(new SFXAction("UNLOCK_PING"));
        if (Settings.FAST_MODE) {
            addToBot(new VFXAction(p, new ShockWaveEffect(p.hb.cX, p.hb.cY, BardColor.ORANGE_COLOR, ShockWaveEffect.ShockWaveType.NORMAL), 0.3F));
        }
        else {
            addToBot(new VFXAction(p, new ShockWaveEffect(p.hb.cX, p.hb.cY, BardColor.ORANGE_COLOR, ShockWaveEffect.ShockWaveType.NORMAL), 1.5F));
        }

        if (amount > 0) {
            for (AbstractMonster mo : AbstractDungeon.getMonsters().monsters) {
                addToBot(new ApplyPowerAction(mo, p, new SadnessPower(p, amount), amount));
            }
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_MINUS_SCALING_FACTOR);
        }
    }
}
