package bardmod.bard.cards;

import bardmod.BardMod;
import bardmod.bard.BardColor;
import bardmod.bard.powers.SubliminalTonesPower;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;

public class SubliminalTones extends CustomCard {
    public static final String ID = "SubliminalTones";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    private static final int PUPPET = 1;
    private static final int UPGRADE_PLUS_PUPPET = 1;

    public SubliminalTones() {
        super(ID, NAME, BardMod.makeCardImagePath(ID), COST, DESCRIPTION, CardType.POWER, BardColor.BARD_ORANGE, CardRarity.UNCOMMON, CardTarget.SELF);

        baseMagicNumber = PUPPET;
        magicNumber = baseMagicNumber;
    }

    @Override
    public void upgrade() {
        if (!upgraded)
        {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_PUPPET);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new VFXAction(p, new ShockWaveEffect(p.hb.cX, p.hb.cY, Color.YELLOW, ShockWaveEffect.ShockWaveType.NORMAL), 0.3F));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p, new SubliminalTonesPower(p, magicNumber), magicNumber));
    }
}
