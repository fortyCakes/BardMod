package bardmod.bard.cards;

import bardmod.BardMod;
import bardmod.bard.BardCardTags;
import bardmod.bard.BardColor;
import bardmod.bard.powers.SadnessPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class CMinor extends CustomCard {
    public static final String ID = "CMinor";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 2;

    private static final int ENERGY_GAIN = 0;
    private static final int UPGRADE_PLUS_ENERGY_GAIN = 1;

    public CMinor() {
        super(ID, NAME, BardMod.makeCardImagePath(ID), COST, DESCRIPTION, CardType.SKILL, BardColor.BARD_ORANGE, CardRarity.UNCOMMON, CardTarget.ENEMY);

        this.baseMagicNumber = ENERGY_GAIN;
        this.magicNumber = baseMagicNumber;

        tags.add(BardCardTags.NOTE_C);
    }

    @Override
    public void upgrade() {
        if (!upgraded)
        {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_ENERGY_GAIN);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new SadnessPower(p, 1), 1));
        if (upgraded) {
            AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(ENERGY_GAIN));
        }
    }
}
