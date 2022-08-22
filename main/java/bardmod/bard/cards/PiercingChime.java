package bardmod.bard.cards;

import bardmod.BardMod;
import bardmod.bard.BardCardTags;
import bardmod.bard.BardColor;
import bardmod.bard.ScaleHelper;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;

public class PiercingChime extends CustomCard {
    public static final String ID = "PiercingChime";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    private static final int STRENGTH_REDUCTION = 3;
    private static final int UPGRADE_PLUS_EFFECT = 2;
    private static final int SCALE = 2;

    public PiercingChime() {
        super(ID, NAME, BardMod.makeCardImagePath(ID), COST, DESCRIPTION, CardType.SKILL, BardColor.BARD_ORANGE, CardRarity.UNCOMMON, CardTarget.NONE);
        tags.add(BardCardTags.NOTE_B);
        this.baseMagicNumber = STRENGTH_REDUCTION;
        this.magicNumber = baseMagicNumber;
    }

    public void triggerOnGlowCheck() {
        ScaleHelper.GlowCheck(this);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int amount = ScaleHelper.WasScale(cost) ? magicNumber + SCALE : magicNumber;

        addToBot(new SFXAction("UNLOCK_PING"));
        if (Settings.FAST_MODE) {
            addToBot(new VFXAction(p, new ShockWaveEffect(p.hb.cX, p.hb.cY, BardColor.ORANGE_COLOR, ShockWaveEffect.ShockWaveType.NORMAL), 0.3F));
        }
        else {
            addToBot(new VFXAction(p, new ShockWaveEffect(p.hb.cX, p.hb.cY, BardColor.ORANGE_COLOR, ShockWaveEffect.ShockWaveType.NORMAL), 1.5F));
        }
        for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
            addToBot(new ApplyPowerAction(mo, p, new StrengthPower(mo, -this.magicNumber), -this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
            if (!mo.hasPower("Artifact")) {
                addToBot(new ApplyPowerAction(mo, p, new GainStrengthPower(mo, this.magicNumber), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
            }
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_EFFECT);
        }
    }
}
