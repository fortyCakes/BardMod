package bardmod.bard.cards;

import bardmod.BardMod;
import bardmod.bard.BardCardTags;
import bardmod.bard.BardColor;
import bardmod.bard.ScaleHelper;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SteelScale extends CustomCard {
    public static final String ID = "SteelScale";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 0;
    private static final int BLOCK = 3;
    private static final int ADDITIONAL_BLOCK = 3;

    public SteelScale() {
        super(ID, NAME, BardMod.makeCardImagePath(ID), COST, DESCRIPTION, CardType.SKILL, BardColor.BARD_ORANGE, CardRarity.COMMON, CardTarget.SELF);

        this.baseBlock = BLOCK;
        this.baseMagicNumber = ADDITIONAL_BLOCK;
    }


    public void use(com.megacrit.cardcrawl.characters.AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.GainBlockAction(p, p, this.block));
        if (ScaleHelper.WasScale(cost))
        {
            AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.GainBlockAction(p, p, this.magicNumber));
        }
    }

    public void triggerOnGlowCheck() {
        ScaleHelper.GlowCheck(this);
    }

    public AbstractCard makeCopy() {
        return new SteelScale();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            tags.add(BardCardTags.NOTE_B);
        }
    }
}
