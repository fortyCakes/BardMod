package bardmod.bard.cards;

import bardmod.BardMod;
import bardmod.bard.BardColor;
import bardmod.bard.ScaleHelper;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class HarmonicScale extends CustomCard {
    public static final String ID = "HarmonicScale";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = -1;
    private static final int BLOCK = 3;
    private static final int SCALE_BLOCK = 4;
    private static final int UPGRADE_PLUS_BLOCK = 1;
    private static final int UPGRADE_PLUS_SCALE = 1;

    public HarmonicScale() {
        super(ID, NAME, BardMod.makeCardImagePath(ID), COST, DESCRIPTION, CardType.SKILL, BardColor.BARD_ORANGE, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = BLOCK;
        this.baseMagicNumber = SCALE_BLOCK;
    }

    public void triggerOnGlowCheck() {
        ScaleHelper.GlowCheck(this);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < energyOnUse; i++)
        {
            for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                if (ScaleHelper.WasScale(energyOnUse))
                {
                    AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, magicNumber));
                } else {
                    AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, block));
                }
            }
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            upgradeMagicNumber(UPGRADE_PLUS_SCALE);
        }
    }
}
